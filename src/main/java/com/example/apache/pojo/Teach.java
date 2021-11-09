package com.example.apache.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.function.Predicate;

@Data
@Accessors(chain = true)
public class Teach implements Predicate {

    @Override
    public boolean test(Object o) {
        return Long.valueOf(o.toString()) == 123;
    }

    public void te(){

    }

}
