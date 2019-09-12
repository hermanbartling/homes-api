package se.difo.hemnetapi.core.repo.specification;

import org.springframework.data.jpa.domain.Specification;
import se.difo.hemnetapi.core.repo.entity.HouseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class HouseSpecification extends BaseSpecification implements Specification<HouseEntity> {

    private HouseSpecification(SearchCriteria criteria) {
        super(criteria);
    }

    @Override
    public Predicate toPredicate(
            Root<HouseEntity> root,
            CriteriaQuery<?> criteriaQuery,
            CriteriaBuilder builder
    ) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (criteria.getKey().equalsIgnoreCase("excludeRemoved")) {
                if (criteria.getValue().toString().equalsIgnoreCase("true")) {
                    return builder.isNull(root.get("timeRemoved"));
                }
            } else if (root.get(criteria.getKey()).getJavaType() == String.class) {
                String searched = String.valueOf(criteria.getValue()).toLowerCase();
                return builder.like(
                        builder.lower(root.get(criteria.getKey())), "%" + searched + "%"
                );
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseSpecification.Builder<Builder> {

        public Builder getInstance() {
            return this;
        }

        public Specification<HouseEntity> build() {
            if (criteriums.isEmpty()) {
                return null;
            }

            List<Specification> specs = criteriums.stream()
                    .map(HouseSpecification::new)
                    .collect(Collectors.toList());

            Specification result = specs.get(0);

            for (int i = 1; i < criteriums.size(); i++) {
                result = Specification.where(result).and(specs.get(i));
            }
            return result;
        }
    }

}
