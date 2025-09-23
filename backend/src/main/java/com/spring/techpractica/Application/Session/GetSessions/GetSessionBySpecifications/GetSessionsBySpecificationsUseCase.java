package com.spring.techpractica.Application.Session.GetSessions.GetSessionBySpecifications;

import com.spring.techpractica.Core.Session.Entity.Session;
import com.spring.techpractica.Core.Session.SessionRepository;
import com.spring.techpractica.infrastructure.Jpa.Session.SessionSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class GetSessionsBySpecificationsUseCase {

    private final SessionRepository sessionRepository;

    public List<Session> execute(GetSessionsBySpecificationsCommand command) {

        Specification<Session> specification = SessionSpecifications.buildDynamicSpecification(command.sessionName(), command.fieldName());

        Pageable pageable = buildPageable(command.page(), command.size(), command.sort());

        return sessionRepository.findAllWithSpecification(specification, pageable);
    }

    private Pageable buildPageable(int page, int size, String sort) {
        if (sort == null || sort.trim().isEmpty()) {
            return PageRequest.of(page, size);
        }

        Sort sortOrder = determineSortOrder(sort.toLowerCase());
        return PageRequest.of(page, size, sortOrder);
    }


    private Sort determineSortOrder(String sortType) {
        return switch (sortType) {
            case "asc", "ascending" -> Sort.by(Sort.Direction.ASC, "name");
            case "desc", "descending" -> Sort.by(Sort.Direction.DESC, "name");
            case "newestfirst" -> Sort.by(Sort.Direction.DESC, "atCreated");
            case "oldestfirst" -> Sort.by(Sort.Direction.ASC, "atCreated");
            default -> Sort.by(Sort.Direction.ASC, "name");
        };
    }
}


