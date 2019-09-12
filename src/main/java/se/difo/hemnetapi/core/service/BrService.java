package se.difo.hemnetapi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.core.exception.ApiNotFoundException;
import se.difo.hemnetapi.core.mapper.BrMapper;
import se.difo.hemnetapi.core.repo.BrRepository;
import se.difo.hemnetapi.core.repo.entity.BrEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class BrService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrService.class);

    private BrRepository brRepository;

    public BrService(BrRepository brRepository) {
        this.brRepository = brRepository;
    }

    public Page<Br> getBrs(
            int pageNumber,
            int pageSize,
            Specification<BrEntity> searchSpec
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "timeAdded"));

        Page<BrEntity> page = brRepository.findAll(searchSpec, pageRequest);

        List<Br> brs = page.getContent().stream()
                .map(BrMapper::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(brs, pageRequest, page.getTotalElements());

    }

    public List<Br> getAllActive() {
        return brRepository.findAllActive().stream()
                .map(BrMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Br getBr(String id) {
        return brRepository.findOneById(UUID.fromString(id))
                .map(BrMapper::toDomain)
                .orElseThrow(() -> new ApiNotFoundException("Br ID " + id + " not found"));
    }

    public Br getBrByUrl(String url) {
        return brRepository.findOneByUrl(url)
                .map(BrMapper::toDomain)
                .orElseThrow(() -> new ApiNotFoundException("Br with URL " + url + " not found"));
    }

    boolean brExists(UUID id) {
        return brRepository.findOneById(id).isPresent();
    }

    public Br add(Br toAdd) {

        if (toAdd.getTimeAdded() == null) {
            toAdd.setTimeAddedIfNotNull(Instant.now());
        }
        if (toAdd.getTimeUpdated() == null) {
            toAdd.setTimeUpdatedIfNotNull(Instant.now());
        }

        Optional<BrEntity> preExisting = brRepository.findOneByUrl(toAdd.getUrl());
        if (preExisting.isPresent()) {
            BrEntity oneAndOnly = preExisting.get();
            LOGGER.warn(
                    "Attempt to add {} when already existing {} detected, idempotent behavior kicking in",
                    toAdd,
                    oneAndOnly
            );
            return BrMapper.toDomain(oneAndOnly);
        }

        Br added = BrMapper.toDomain(brRepository.save(BrMapper.toEntity(toAdd)));
        LOGGER.info("Added new {}", added);
        return added;
    }

    public Br update(String id, Br updates) {

        Br existing = getBr(id);

        existing.setAddressIfNotNull(updates.getAddress());
        existing.setAreaIfNotNull(updates.getArea());
        existing.setPriceIfNotNull(updates.getPrice());
        existing.setFeeIfNotNull(updates.getFee());
        existing.setSqmLivingIfNotNull(updates.getSqmLiving());
        existing.setRoomsIfNotNull(updates.getRooms());
        existing.setMunicipalityIfNotNull(updates.getMunicipality());
        existing.setCoordinateIfNotNull(updates.getCoordinate());
        existing.setVisitCountIfNotNull(updates.getVisitCount());
        existing.setBrfIdIfNotNull(updates.getBrfId());

        existing.getBroker().setPersonNameIfNotNull(updates.getBroker().getPersonName());
        existing.getBroker().setFirmNameIfNotNull(updates.getBroker().getFirmName());
        existing.getBroker().setFirmWebPageIfNotNull(updates.getBroker().getFirmWebPage());

        existing.setTimeAddedIfNotNull(updates.getTimeAdded());
        if (updates.getTimeUpdated() == null) {
            existing.setTimeUpdatedIfNotNull(Instant.now());
        } else {
            existing.setTimeUpdatedIfNotNull(updates.getTimeUpdated());
        }
        existing.setTimeRemoved(null);

        Br updated = BrMapper.toDomain(brRepository.save(BrMapper.toEntity(existing)));
        LOGGER.info("Updated {}", updated);

        return updated;
    }

    public Br markAsRemoved(String id) {

        Br existing = getBr(id);
        existing.setTimeRemoved(Instant.now());

        Br updated = BrMapper.toDomain(brRepository.save(BrMapper.toEntity(existing)));
        LOGGER.info("Removed {}", updated);

        return updated;
    }


}
