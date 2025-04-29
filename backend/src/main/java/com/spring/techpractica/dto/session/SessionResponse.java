package com.spring.techpractica.dto.session;

import com.spring.techpractica.model.entity.techSkills.Category;
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

    private long id;

    private String sessionName;

    private String sessionDescription;

    private List<String> technologies;

    private String category;

<<<<<<< HEAD
    private boolean isPrivate;

    private List<String> fields;
=======
>>>>>>> 72bc7089974fdc73faaed020aea303aae21549a1

}
