package com.spring.techpractica.dto.session;

import com.spring.techpractica.dto.techSkills.CategoryTransfer;
import com.spring.techpractica.dto.techSkills.TechnologyTransfer;
import com.spring.techpractica.model.entity.Category;
import com.spring.techpractica.model.entity.Technology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SessionResponse {

    private String sessionName;

    private String sessionDescription;

    private List<String> technologies;

    private String category;
}
