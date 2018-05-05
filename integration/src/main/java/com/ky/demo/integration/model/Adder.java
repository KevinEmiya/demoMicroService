package com.ky.demo.integration.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Adder implements Serializable {

    public Adder(int adder, int addend) {
        this.adder = adder;
        this.addend = addend;
    }

    int adder;

    int addend;
}
