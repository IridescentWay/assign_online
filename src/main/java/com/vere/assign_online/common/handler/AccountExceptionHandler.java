package com.vere.assign_online.common.handler;

import com.vere.assign_online.common.Result;
import com.vere.assign_online.common.ResultCode;
import com.vere.assign_online.common.exception.BadIdentityException;
import com.vere.assign_online.common.exception.BadPwdOrUsernameException;
import com.vere.assign_online.common.exception.VerificationCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ClassName:AccountExceptionHandler
 * Package:com.vere.demo.common
 * Description:
 *
 * @Date:2022/4/18 12:14
 * @Author:3046990030@qq.com
 */

@ControllerAdvice
public class AccountExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleNotNullException(MethodArgumentNotValidException e) {
        LOGGER.warn("空值异常：{}", e.getMessage());
        return Result.fail(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public Result handleBadCredentialsException(BadCredentialsException e) {
        LOGGER.warn("登录异常：{}", e.getMessage());
        return Result.fail(ResultCode.PWD_INCORRECT);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(BadPwdOrUsernameException.class)
    @ResponseBody
    public Result handleBadPwdOrUsernameException(BadPwdOrUsernameException e) {
        LOGGER.warn("登录异常：{}", e.getMessage());
        return Result.fail(ResultCode.PWD_OR_USERNAME_INCORRECT);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(VerificationCodeException.class)
    @ResponseBody
    public Result handleVerificationCodeException(VerificationCodeException e) {
        LOGGER.warn("登录异常：{}", e.getMessage());
        return Result.fail(ResultCode.VERIFICATION_CODE_INCORRECT);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(BadIdentityException.class)
    @ResponseBody
    public Result handleBadIdentityException(BadIdentityException e) {
        LOGGER.warn("登录异常：{}", e.getMessage());
        return Result.fail(ResultCode.IDENTITY_ERR);
    }
}
