package com.submit.controller;

import com.submit.config.URLConfig;
import com.submit.config.WeChatConfig;
import com.submit.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/wxAuth")
public class WxLoginController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/login")
    public void wxLogin(HttpServletResponse response) throws IOException {
        //请求获取code的回调地址
        //用线上环境的域名或者用内网穿透，不能用ip
        String callBack = URLConfig.baseURL + "submit/wxAuth/callBack";

        //请求地址 //APPID
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + WeChatConfig.appid +
                "&redirect_uri=" + URLEncoder.encode(callBack) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        //重定向
        response.sendRedirect(url);
    }

    //	回调方法
    @RequestMapping("/callBack")
    public void wxCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        response.sendRedirect(URLConfig.baseURL + "submitUser/login/"+code);





    }
}
