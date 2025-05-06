package com.spring.techpractica.dto.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionRequestCreation {

    private Long sessionId;

    private String category;

    private String brief;

    private Long reqId;
    
}
