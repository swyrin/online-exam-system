package com.pdm.backend.models.dto;

import com.pdm.backend.models.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {

    private Long RoleID;
    private String Name;
    private Person person;
}
