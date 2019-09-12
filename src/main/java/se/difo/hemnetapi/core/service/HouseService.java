package se.difo.hemnetapi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import se.difo.hemnetapi.core.domain.House;
import se.difo.hemnetapi.core.exception.ApiNotFoundException;
import se.difo.hemnetapi.core.mapper.HouseMapper;
import se.difo.hemnetapi.core.repo.HouseRepository;
import se.difo.hemnetapi.core.repo.entity.HouseEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class HouseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HouseService.class);

    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public Page<House> getHouses(
            int pageNumber,
            int pageSize,
            Specification<HouseEntity> searchSpec
    ) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "timeAdded"));

        Page<HouseEntity> page = houseRepository.findAll(searchSpec, pageRequest);

        List<House> houses = page.getContent().stream()
                .map(HouseMapper::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(houses, pageRequest, page.getTotalElements());
    }

    boolean houseExists(UUID id) {
        return houseRepository.findOneById(id).isPresent();
    }


    public House getHouse(String id) {
        return houseRepository.findOneById(UUID.fromString(id))
                .map(HouseMapper::toDomain)
                .orElseThrow(() -> new ApiNotFoundException("House ID " + id + " not found"));
    }

    public House getHouseByUrl(String url) {
        return houseRepository.findOneByUrl(url)
                .map(HouseMapper::toDomain)
                .orElseThrow(() -> new ApiNotFoundException("House with URL " + url + " not found"));
    }

    public House add(House toAdd) {

        if (toAdd.getTimeAdded() == null) {
            toAdd.setTimeAddedIfNotNull(Instant.now());
        }
        if (toAdd.getTimeUpdated() == null) {
            toAdd.setTimeUpdatedIfNotNull(Instant.now());
        }

        Optional<HouseEntity> preExisting = houseRepository.findOneByUrl(toAdd.getUrl());
        if (preExisting.isPresent()) {
            HouseEntity oneAndOnly = preExisting.get();
            LOGGER.warn(
                    "Attempt to add {} when already existing {} detected, idempotent behavior kicking in",
                    toAdd,
                    oneAndOnly
            );
            return HouseMapper.toDomain(oneAndOnly);
        }


        House added = HouseMapper.toDomain(houseRepository.save(HouseMapper.toEntity(toAdd)));
        LOGGER.info("Added new house {}", added);
        return added;
    }

    public House update(String id, House updates) {

        House existing = getHouse(id);

        existing.setAddressIfNotNull(updates.getAddress());
        existing.setAreaIfNotNull(updates.getArea());
        existing.setPriceIfNotNull(updates.getPrice());
        existing.setTypeIfNotNull(updates.getType());
        existing.setRoomsIfNotNull(updates.getRooms());
        existing.setCostYearIfNotNull(updates.getCostYear());
        existing.setBuiltYearIfNotNull(updates.getBuiltYear());
        existing.setSqmLivingIfNotNull(updates.getSqmLiving());
        existing.setSqmLivingAdditionalIfNotNull(updates.getSqmLivingAdditional());
        existing.setSqmLandIfNotNull(updates.getSqmLand());
        existing.setVisitCountIfNotNull(updates.getVisitCount());

        existing.setMunicipalityIfNotNull(updates.getMunicipality());
        existing.setCoordinateIfNotNull(updates.getCoordinate());

        existing.setTimeAddedIfNotNull(updates.getTimeAdded());
        existing.setTimeRemoved(null);
        if (updates.getTimeUpdated() == null) {
            existing.setTimeUpdatedIfNotNull(Instant.now());
        } else {
            existing.setTimeUpdatedIfNotNull(updates.getTimeUpdated());
        }

        existing.setBrokerIfNotNull(updates.getBroker());

        House updated = HouseMapper.toDomain(houseRepository.save(HouseMapper.toEntity(existing)));
        LOGGER.info("Updated {}", existing);
        return existing;
    }

    public House markAsRemoved(String id) {

        House existing = getHouse(id);
        existing.setTimeRemoved(Instant.now());

        House updated = HouseMapper.toDomain(houseRepository.save(HouseMapper.toEntity(existing)));
        LOGGER.info("Removed {}", updated);

        return updated;
    }

}
