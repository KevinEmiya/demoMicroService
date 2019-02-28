package com.ky.common.aop;

import com.ky.common.model.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * ResultBeanExceptionHandler
 *
 * @Author: kevin yang
 * @Description:
 * @Date: Created at 19-2-28
 * @Last Modified:
 **/

@RestControllerAdvice
@Slf4j
public class ResultBeanExceptionHandler {

    /**
     * 如果微服务出现了IO异常，会跳转到该方法进行处理，对于文件未找到异常，返回404错误， 除此之外的其他IO异常均当做500错误来处理。
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler(IOException.class)
    public ResultBean<?> handleIOException(IOException exception, HttpServletResponse response) {

        log.error("IO异常", exception);
        Throwable throwable = exception;
        if (exception instanceof FileNotFoundException) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // 这两行代码，主要解决链式异常的消息提取问题，默认提取触发异常的最原始异常的消息
            while (throwable.getCause() != null) {
                throwable = throwable.getCause();
            }
        }
        return ResultBean.genResult(ResultBean.CODE_FAIL, throwable);
    }

    /**
     * 如果微服务出现数据校验出错异常，会跳转到该处理器处理，该方法将数据校验出错异常转为Http状态码400
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean<?> handleSpringValidException(MethodArgumentNotValidException exception,
                                                    HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String message = "参数" + fieldError.getObjectName() + "中的属性" + fieldError.getField() + "校验不通过，校验信息为："
                + fieldError.getDefaultMessage();
        log.error(message, exception);
        return ResultBean.genResult(ResultBean.CODE_FAIL, exception);
    }

    /**
     * 参数绑定错误时跳转到该处理器处理
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultBean<?> handleBeanPropertyBindingException(BindException exception, HttpServletResponse response) {

        StringBuilder builder = new StringBuilder();

        BindingResult result = exception.getBindingResult();
        List<FieldError> errList = result.getFieldErrors();
        for (FieldError fieldError : errList) {
            builder = builder.append("参数绑定错误，出错对象：").append(fieldError.getObjectName()).append("，出错字段：")
                    .append(fieldError.getField()).append("，错误值：").append(fieldError.getRejectedValue())
                    .append("，错误信息：").append(fieldError.getDefaultMessage()).append("。");
        }

        // 打印日志
        log.error(new String(builder), exception);
        return ResultBean.genResult(ResultBean.CODE_FAIL, exception);
    }

    /**
     * 如果数据库发生了异常（违反唯一约束，未找到父项关键字，数据库连接异常等），则由该
     * 异常处理器处理。该方法会对本次响应设置500响应状态码，并在message中返回异常的详细信息
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public ResultBean<?> handleSQLIntegrityException(SQLException exception, HttpServletResponse response) {
        log.error("SQL异常", exception);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Throwable throwable = exception;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        return ResultBean.genResult(ResultBean.CODE_FAIL, exception);
    }

    /**
     * 如果微服务出现了其他的严重异常（既不是JPBaseException，也不是数据库异常），那么
     * 异常将交给该方法处理，响应码为500，同时在message中设置异常信息
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultBean<?> handleException(Exception exception, HttpServletResponse response) {

        log.error("严重异常", exception);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        Throwable throwable = exception;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        return ResultBean.genResult(ResultBean.CODE_FAIL, exception);
    }

}
