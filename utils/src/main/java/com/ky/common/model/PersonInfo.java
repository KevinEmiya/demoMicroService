package com.ky.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * PersonInfo
 *
 * @Author: kevin yang
 * @Description:
 * @Date: Created at 19-2-28
 * @Last Modified:
 **/

@ApiModel("人员信息")
@Data
public class PersonInfo {

    @ApiModelProperty("姓名")
    String name;

    @ApiModelProperty("身份证号")
    String idCardNum;

    @ApiModelProperty("年龄")
    int age;

    @ApiModelProperty("性别")
    String gender;
}
