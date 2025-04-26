package com.pdm.backend.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @Column(name = "role_id")
    private Long RoleID;

    @Column(name = "name")
    private String Name;

    @ManyToOne
    private Person person;
}
