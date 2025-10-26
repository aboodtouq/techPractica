package com.spring.techpractica.infrastructure.Jpa.Session;

import com.spring.techpractica.core.Requirement.Entity.Requirement;
import com.spring.techpractica.core.Session.Entity.Session;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SessionSpecifications {


    public static Specification<Session> buildDynamicSpecification(String sessionName, String fieldName) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            boolean hasSessionName = sessionName != null && !sessionName.trim().isEmpty();
            boolean hasFieldName = fieldName != null && !fieldName.trim().isEmpty();

            if (hasSessionName) {
                    Path<?> namePath = root.get("name");
                        predicates.add(criteriaBuilder.like(
                                criteriaBuilder.lower(namePath.as(String.class)),
                                "%" + sessionName.trim().toLowerCase() + "%"
                        ));
            }

            if (hasFieldName) {
                String value = fieldName.trim().toLowerCase();

                    Join<Session, Requirement> reqJoin = root.join("requirements", JoinType.INNER);

                    Join<Requirement, ?> fieldJoin = reqJoin.join("field", JoinType.INNER);

                    Path<?> fieldNamePath = fieldJoin.get("name");

                        predicates.add(criteriaBuilder.like(
                                criteriaBuilder.lower(fieldNamePath.as(String.class)),
                                "%" + value + "%"
                        ));


            }

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
