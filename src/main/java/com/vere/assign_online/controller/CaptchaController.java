package com.vere.assign_online.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ClassName:CaptchaController
 * Package:com.vere.demo.controller
 * Description:
 *
 * @Date:2022/4/19 22:04
 * @Author:3046990030@qq.com
 */

@Controller
public class CaptchaController {
    @Autowired
    private Producer captchaProducer;

    @GetMapping("/static/public/captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        // 将文本放入到本次会话当中
        System.out.println("验证码生成器：" + request.getSession().getId());
        request.getSession().setAttribute("captcha", capText);
        BufferedImage image = captchaProducer.createImage(capText);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        outputStream.flush();
    }
}
