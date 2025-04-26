package com.spring.techpractica.service.techSkills.Technology;

import com.spring.techpractica.mengmentData.TechnologyManagementData;
import com.spring.techpractica.model.entity.techSkills.Technology;
import com.spring.techpractica.repository.techSkills.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService {

    private final TechnologyManagementData technologyManagementData;

    public TechnologyService(TechnologyManagementData technologyManagementData) {
        this.technologyManagementData = technologyManagementData;
    }


    public List<Technology> getAllTechnologies() {
        return technologyManagementData.getAllTechnologies();
    }


}
