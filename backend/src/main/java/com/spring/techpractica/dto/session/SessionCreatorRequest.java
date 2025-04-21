package com.spring.techpractica.dto.session;

import com.spring.techpractica.model.entity.Category;
import com.spring.techpractica.model.entity.Field;
import com.spring.techpractica.model.entity.Technology;
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


    private List<Technology> technologies;


    private List<Field> fields;

}
