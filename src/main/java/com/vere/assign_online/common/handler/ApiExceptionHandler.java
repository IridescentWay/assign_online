package com.vere.assign_online.common.handler;

import com.vere.assign_online.common.Result;
import com.vere.assign_online.common.ResultCode;
import com.vere.assign_online.common.exception.BadStudentInfoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ClassName:ApiExceptionHandler
 * Package:com.vere.assign_online.common.handler
 * Description:
 *
 * @Date:2022/4/30 9:52
 * @Author:3046990030@qq.com
 */

@ControllerAdvice
public class ApiExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(BadStudentInfoException.class)
    @ResponseBody
    public Result handleBadCredentialsException(BadStudentInfoException e) {
        LOGGER.warn("接口错误：{}", e.getMessage());
        return Result.fail(ResultCode.BAD_STUDENT_INFO);
    }
}
