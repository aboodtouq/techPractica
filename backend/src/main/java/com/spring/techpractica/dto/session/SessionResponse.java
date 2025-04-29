package com.spring.techpractica.dto.session;

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

    private boolean isPrivate;

    private List<String> fields;

}
