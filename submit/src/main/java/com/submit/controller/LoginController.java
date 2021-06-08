package com.submit.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.submit.config.WeChatConfig;
import com.submit.domain.Student;
import com.submit.exception.BaseException;
import com.submit.service.StudentService;
import com.submit.util.HttpClientUtil;
import com.submit.util.TokenUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/student")
public class LoginController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/login")
    public JSONObject wxCallBack(HttpServletRequest request, HttpServletResponse response,@RequestBody String params) throws IOException {
        JSONObject result = new JSONObject();
        try {
            JSONObject paramsJson = JSONObject.parseObject(JSONObject.parseObject(params).getString("params"));
            String code = paramsJson.getString("code");
            //获取access_token
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=" + WeChatConfig.appid +
                    "&secret=" + WeChatConfig.secret +
                    "&code=" + code +
                    "&grant_type=authorization_code";

            String results = HttpClientUtil.doGet(url);

            System.out.println("请求获取access_token:" + result);
            //返回结果的json对象
            JSONObject resultObject = JSON.parseObject(results);


            //请求获取userInfo
            String infoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=" + resultObject.getString("access_token") +
                    "&openid=" + resultObject.getString("openid") +
                    "&lang=zh_CN";

            String resultInfo = HttpClientUtil.doGet(infoUrl);

            //此时已获取到userInfo，再根据业务进行处理
            System.out.println("请求获取userInfo:" + resultInfo);

                Student student = JSONObject.parseObject(resultInfo, Student.class);
            System.out.println(student);
                if(student.getOpenid()==null){
                    throw new BaseException("登陆失败",401);
                }else{
                    student = studentService.getByWeChatOpenId(student);

                    String token = TokenUtil.getToken(student,1800);
                    //result.put("student", student);
                    result.put("token", token);
                    result.put("code", 200);
                    result.put("data", "登录成功");
                    return result;
                }
            }catch (BaseException e){
                e.printStackTrace();
                result.put("data", e.getMessage());
                result.put("code", e.getCode());
                return result;
            }catch (Exception e){
                e.printStackTrace();
                result.put("data", "服务器发生错误");
                result.put("code", 301);
            }




        return result;



    }
}
