package se.difo.hemnetapi.core.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.core.domain.House;
import se.difo.hemnetapi.core.mapper.BrMapper;
import se.difo.hemnetapi.core.mapper.HouseMapper;
import se.difo.hemnetapi.core.repo.BrRepository;
import se.difo.hemnetapi.core.repo.entity.BrEntity;
import se.difo.hemnetapi.core.repo.entity.HouseEntity;
import se.difo.hemnetapi.core.repo.specification.BrSpecification;
import se.difo.hemnetapi.core.repo.specification.HouseSpecification;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class BrServiceSearchTest {

    @Autowired
    private BrRepository repository;

    private BrService brService;

    private BrEntity br1;
    private BrEntity br2;

    @Before
    public void init() {
        if (brService == null) {
            brService = new BrService(repository);
        }

        br1 = new BrEntity();
        br1.setUrl("Hej kom och hjälp mig");
        br1.setTimeAdded("2019-01-01 12:00:00");
        br1.setAddress("Paradisäppelvägen 52");
        br1.setSqm(new BigDecimal(31));
        repository.save(br1);

        br2 = new BrEntity();
        br2.setUrl("Hej kom och hjälp mig 2");
        br2.setTimeAdded("2019-01-01 12:00:00");
        br2.setAddress("Apelbergsgatan 54");
        br2.setSqm(new BigDecimal(63));
        repository.save(br2);
    }


    @Test
    public void searchByAddressShouldFunction() {
        Specification<BrEntity> spec = BrSpecification.builder()
                .with("address", ":", "apelberg")
                .build();

        List<Br> results = brService.getBrs(0, 10, spec).getContent();

        assertThat(results).isNotEmpty();
        assertThat(results).usingRecursiveFieldByFieldElementComparator().containsOnly(BrMapper.toDomain(br2));
    }

    @Test
    public void searchByMultiplePropsShouldFunction() {
        Specification<BrEntity> spec = BrSpecification.builder()
                .with("address", ":", "apelberg")
                .with("sqm", ">", 40)
                .build();

        List<Br> results = brService.getBrs(0, 10, spec).getContent();

        assertThat(results).isNotEmpty();
        assertThat(results).usingRecursiveFieldByFieldElementComparator().containsOnly(BrMapper.toDomain(br2));
    }

}