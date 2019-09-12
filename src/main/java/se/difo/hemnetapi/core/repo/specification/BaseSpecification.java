package se.difo.hemnetapi.core.repo.specification;

import java.util.ArrayList;
import java.util.List;

public class BaseSpecification {

    protected final SearchCriteria criteria;

    protected BaseSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

//    public Predicate toPredicate(
//            Root<T> root,
//            CriteriaQuery<?> criteriaQuery,
//            CriteriaBuilder builder
//    ) {
//        if (criteria.getOperation().equalsIgnoreCase(">")) {
//            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
//        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
//            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
//        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
//            if (criteria.getKey().equalsIgnoreCase("excludeRemoved")) {
//                if (criteria.getValue().toString().equalsIgnoreCase("true")) {
//                    return builder.isNull(root.get("timeRemoved"));
//                }
//            } else if (root.get(criteria.getKey()).getJavaType() == String.class) {
//                String searched = String.valueOf(criteria.getValue()).toLowerCase();
//                return builder.like(
//                        builder.lower(root.get(criteria.getKey())), "%" + searched + "%"
//                );
//            } else {
//                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
//            }
//        }
//        return null;
//    }

    public abstract static class Builder<T extends Builder<T>> {

        final List<SearchCriteria> criteriums;

        Builder() {
            criteriums = new ArrayList<>();
        }

        public abstract T getInstance();


        public T with(String key, String operation, Object value) {
            criteriums.add(new SearchCriteria(key, operation, value));
            return getInstance();
        }


    }


}
