package se.difo.hemnetapi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import se.difo.hemnetapi.core.domain.Brf;
import se.difo.hemnetapi.core.exception.ApiNotFoundException;
import se.difo.hemnetapi.core.mapper.BrfMapper;
import se.difo.hemnetapi.core.repo.BrfRepository;
import se.difo.hemnetapi.core.repo.entity.BrfEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class BrfService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrfService.class);

    private BrfRepository brfRepository;

    public BrfService(BrfRepository brfRepository) {
        this.brfRepository = brfRepository;
    }

    public Page<Brf> getBrfs(
            int pageNumber,
            int pageSize,
            Specification<BrfEntity> searchSpec
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "name"));

        Page<BrfEntity> page = brfRepository.findAll(searchSpec, pageRequest);

        List<Brf> brs = page.getContent().stream()
                .map(BrfMapper::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(brs, pageRequest, page.getTotalElements());

    }

    public List<String> getBrfNames() {
        return brfRepository.findAll().stream().map(brf -> brf.getName().toLowerCase()).collect(Collectors.toList());
    }

    public Brf getBrf(String id) {
        return brfRepository.findOneById(UUID.fromString(id))
                .map(BrfMapper::toDomain)
                .orElseThrow(() -> new ApiNotFoundException("Brf ID " + id + " not found"));
    }

    public Brf getBrfByName(String name) {
        return brfRepository.findOneByName(name.toLowerCase())
                .map(BrfMapper::toDomain)
                .orElseThrow(() -> new ApiNotFoundException("Brf with name " + name + " not found"));

    }

    boolean brfExists(String name) {
        return brfRepository.findOneByName(name).isPresent();
    }

    public Brf add(Brf toAdd) {

        if (brfExists(toAdd.getName())) {
            Brf oneAndOnly = getBrfByName(toAdd.getName());
            LOGGER.warn(
                    "Attempt to add {} when already existing {} detected, idempotent behavior kicking in",
                    toAdd,
                    oneAndOnly
            );
            return oneAndOnly;
        }

        Brf added = BrfMapper.toDomain(brfRepository.save(BrfMapper.toEntity(toAdd)));
        LOGGER.info("Added new {}", added);
        return added;
    }

    public Brf update(String id, Brf updates) {

        Brf existing = getBrf(id);

        existing.setStatusIfNotNull(updates.getStatus());
        existing.setRegisteredYearIfNotNull(updates.getRegisteredYear());
        existing.setMemberCountIfNotNull(updates.getMemberCount());

        Brf updated = BrfMapper.toDomain(brfRepository.save(BrfMapper.toEntity(existing)));
        LOGGER.info("Updated {}", updated);

        return updated;
    }


}
