package com.pdm.backend.mappers;

public interface Mapper<A, B> {

    B mapto(A a);

    A mapfrom(B b);

}

