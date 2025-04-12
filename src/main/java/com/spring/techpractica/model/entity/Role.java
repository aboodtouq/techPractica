package com.spring.techpractica.model.entity;

import com.spring.techpractica.model.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

}
