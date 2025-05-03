package com.spring.techpractica.service.session.linker;

import com.spring.techpractica.maper.TechnologyMapper;
import com.spring.techpractica.model.entity.Session;
import com.spring.techpractica.model.entity.techSkills.Technology;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SessionTechnologyLinker {


   private final TechnologyMapper technologyMapper;

    public void linkTechnologiesToSession(Session session, List<String> techNames) {
        List<Technology> technologies = technologyMapper.technologiesStringToTechnologyList(techNames);

        session.setSessionTechnologies(technologies);
    }
}
