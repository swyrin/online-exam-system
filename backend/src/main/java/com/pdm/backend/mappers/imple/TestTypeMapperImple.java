package com.pdm.backend.mappers.imple;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.TestType;
import com.pdm.backend.models.dto.TestTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TestTypeMapperImple implements Mapper<TestType, TestTypeDto> {

    private ModelMapper modelMapper;


    @Override
    public TestType mapfrom(TestTypeDto testTypeDto) {
        return modelMapper.map(testTypeDto, TestType.class);
    }

    @Override
    public TestTypeDto mapto(TestType testType) {
        return modelMapper.map(testType, TestTypeDto.class);
    }
}
