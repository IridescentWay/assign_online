package com.vere.assign_online.common.exception;

import javax.security.sasl.AuthenticationException;

/**
 * ClassName:VerificationCodeException
 * Package:com.vere.demo.common.exception
 * Description:
 *
 * @Date:2022/4/19 22:51
 * @Author:3046990030@qq.com
 */

public class VerificationCodeException extends AuthenticationException {
    public VerificationCodeException() {
        super("图形验证码错误");
    }
}
