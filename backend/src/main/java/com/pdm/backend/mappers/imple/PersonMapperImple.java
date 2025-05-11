package com.pdm.backend.mappers.imple;


import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Person;
import com.pdm.backend.models.dto.PersonDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PersonMapperImple implements Mapper<Person, PersonDto> {

    private final ModelMapper modelMapper;

    public PersonMapperImple(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonDto mapto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Person mapfrom(PersonDto persondto) {
        return modelMapper.map(persondto, Person.class);
    }


}


