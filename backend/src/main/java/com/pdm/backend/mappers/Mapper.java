package com.pdm.backend.mappers;

import org.springframework.stereotype.Component;


    public interface Mapper<A,B> {

        B mapto (A a);
    
        A mapfrom(B b );
    
    }

