package com.pdm.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
