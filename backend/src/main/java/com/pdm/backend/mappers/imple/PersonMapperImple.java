package com.pdm.backend.mappers.imple;


import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Person;
import com.pdm.backend.models.dto.personDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PersonMapperImple implements Mapper<Person, personDto> {

    private final ModelMapper modelMapper;

    public PersonMapperImple(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public personDto mapto(Person person) {
        return modelMapper.map(person, personDto.class);
    }

    @Override
    public Person mapfrom(personDto persondto) {
        return modelMapper.map(persondto, Person.class);
    }


}


