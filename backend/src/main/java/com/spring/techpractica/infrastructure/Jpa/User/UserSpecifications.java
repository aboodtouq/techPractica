package com.spring.techpractica.infrastructure.Jpa.User;

import com.spring.techpractica.Core.User.AccountStatus;
import com.spring.techpractica.Core.User.User;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {

    public static Specification<User> buildDynamicSpecification (String userName, String role){
        return (root, query, criteriaBuilder) ->{
            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            boolean hasRole = role != null && !role.trim().isEmpty();
            boolean hasUserName = userName != null && !userName.trim().isEmpty();

            Predicate statusPredicate = criteriaBuilder.equal(
                    root.get("accountStatus"), AccountStatus.COMPLETE_PROFILE
            );
            predicates.add(statusPredicate);

            if (hasRole) {
                var joinRole = root.join("roles");
                Predicate rolePredicate = criteriaBuilder.equal(
                        criteriaBuilder.lower(joinRole.get("roleType").as(String.class)),
                        role.trim().toLowerCase()
                );
                predicates.add(rolePredicate);
            }

            if (hasUserName) {
                Path<?> namePath = root.get("name");
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(namePath.as(String.class)),
                        "%" + userName.trim().toLowerCase() + "%"
                ));
            }

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
