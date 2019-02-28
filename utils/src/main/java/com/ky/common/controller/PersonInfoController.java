package com.ky.common.controller;

import com.ky.common.model.PersonInfo;
import com.ky.common.model.ResultBean;
import com.ky.common.service.PersonInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PersonInfoController
 *
 * @Author: kevin yang
 * @Description:
 * @Date: Created at 19-2-28
 * @Last Modified:
 **/

@RestController
@RequestMapping("person/")
public class PersonInfoController {

    @Autowired
    PersonInfoServiceImpl personInfoService;

    @GetMapping("/{idCardNum}")
    public ResultBean<PersonInfo> getPersonInfo(@PathVariable String idCardNum) {
        return ResultBean.genResult(personInfoService.queryPersonInfo(idCardNum));
    }

    @GetMapping("/judgement/oldGuy")
    public ResultBean<Boolean> getPersonInfo(@RequestParam int age) {
        return ResultBean.genResult(personInfoService.isOld(age));
    }

}
