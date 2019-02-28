package com.ky.common.service;

import com.ky.common.model.PersonInfo;
import com.ky.common.model.ResultBean;
import org.springframework.stereotype.Service;

/**
 * PersonInfoServiceImpl
 *
 * @Author: kevin yang
 * @Description:
 * @Date: Created at 19-2-28
 * @Last Modified:
 **/

@Service
public class PersonInfoServiceImpl {

    public PersonInfo queryPersonInfo(String idCardNum) {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAge(23);
        personInfo.setGender("MALE");
        personInfo.setName("王大力");
        personInfo.setIdCardNum(idCardNum);
        return personInfo;
    }

    public boolean isOld(int age) {
        return age > 16;
    }

}
