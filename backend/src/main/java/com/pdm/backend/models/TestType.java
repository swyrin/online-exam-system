package com.pdm.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "test_types")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestType {
    @Id
    @Column(name = "type_id")
    public Long TypeID;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;
}
