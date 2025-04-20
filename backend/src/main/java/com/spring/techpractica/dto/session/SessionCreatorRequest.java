package com.spring.techpractica.dto.session;

import com.spring.techpractica.dto.RequirementUsers;
import com.spring.techpractica.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionCreatorRequest {

    private String nameSession;

    private String descriptionSession;

    private boolean isPrivateSession;

    private Category category;

    private List<RequirementUsers> requirements;

}
