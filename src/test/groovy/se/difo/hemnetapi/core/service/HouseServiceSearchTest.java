package se.difo.hemnetapi.core.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import se.difo.hemnetapi.core.domain.House;
import se.difo.hemnetapi.core.mapper.HouseMapper;
import se.difo.hemnetapi.core.repo.HouseRepository;
import se.difo.hemnetapi.core.repo.entity.HouseEntity;
import se.difo.hemnetapi.core.repo.specification.HouseSpecification;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class HouseServiceSearchTest {

    @Autowired
    private HouseRepository repository;

    private HouseService houseService;

    private HouseEntity house1;
    private HouseEntity house2;

    @Before
    public void init() {
        if (houseService == null) {
            houseService = new HouseService(repository);
        }

        house1 = new HouseEntity();
        house1.setUrl("Hej kom och hjälp mig");
        house1.setTimeAdded("2019-01-01 12:00:00");
        house1.setAddress("Paradisäppelvägen 52");
        house1.setSqmLand(new BigDecimal(314));
        repository.save(house1);

        house2 = new HouseEntity();
        house2.setUrl("Hej kom och hjälp mig 2");
        house2.setTimeAdded("2019-01-01 12:00:00");
        house2.setAddress("Apelbergsgatan 54");
        house2.setSqmLand(new BigDecimal(630));
        repository.save(house2);
    }


    @Test
    public void searchByAddressShouldFunction() {
        Specification<HouseEntity> spec = HouseSpecification.builder()
                .with("address", ":", "apelberg")
                .build();

        List<House> results = houseService.getHouses(0, 10, spec).getContent();

        assertThat(results).isNotEmpty();
        assertThat(results).usingRecursiveFieldByFieldElementComparator().containsOnly(HouseMapper.toDomain(house2));
    }

    @Test
    public void searchByMultiplePropsShouldFunction() {
        Specification<HouseEntity> spec = HouseSpecification.builder()
                .with("address", ":", "apelberg")
                .with("sqmLand", ">", 400)
                .build();

        List<House> results = houseService.getHouses(0, 10, spec).getContent();

        assertThat(results).isNotEmpty();
        assertThat(results).usingRecursiveFieldByFieldElementComparator().containsOnly(HouseMapper.toDomain(house2));
    }

}