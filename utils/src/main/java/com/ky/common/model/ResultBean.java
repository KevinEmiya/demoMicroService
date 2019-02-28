package com.ky.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ResultBean
 *
 * @Author: kevin yang
 * @Description:
 * @Date: Created at 19-2-28
 * @Last Modified:
 **/

@Data
@ApiModel("通用返回模型")
public class ResultBean<T> {

    @ApiModelProperty("请求是否成功")
    boolean success;

    @ApiModelProperty("请求状态码")
    int code;

    @ApiModelProperty("请求状态消息")
    String msg;

    @ApiModelProperty("返回数据")
    T data;

    private static final int CODE_SUCC = 0;
    public static final int CODE_FAIL = 10001;

    public static <T> ResultBean genResult(T data) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(CODE_SUCC);
        resultBean.setSuccess(true);
        resultBean.setData(data);
        return resultBean;
    }

    public static ResultBean genResult(int errCode, String errMsg) {
        ResultBean resultBean = new ResultBean();
        resultBean.setSuccess(false);
        resultBean.setCode(errCode);
        resultBean.setMsg(errMsg);
        return resultBean;
    }

    public static ResultBean genResult(int errCode, Throwable e) {
        return genResult(errCode, e.getMessage());
    }

}
