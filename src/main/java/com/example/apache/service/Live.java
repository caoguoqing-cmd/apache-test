package com.example.apache.service;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@FunctionalInterface
public interface Live<A,B> extends Serializable {

    A youLive(B b,Object o);
}
