package com.ky.demo.msvc.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Adder {

    @ApiModelProperty("加数")
    int adder;

    @ApiModelProperty("被加数")
    int addend;
}
