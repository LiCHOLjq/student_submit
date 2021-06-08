package com.submit.interceptor;

import com.alibaba.fastjson.JSONObject;

import com.submit.annotation.UserLoginToken;
import com.submit.domain.Student;
import com.submit.exception.BaseException;
import com.submit.service.StudentService;
import com.submit.util.TokenUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private StudentService studentService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        try {
            String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
            if (!(object instanceof HandlerMethod)) {
                return true;
            }
            HandlerMethod handlerMethod = (HandlerMethod) object;
            Method method = handlerMethod.getMethod();

            if (method.isAnnotationPresent(UserLoginToken.class)) {
                UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
                if (userLoginToken.required()) {
                    if (token == null||"".equals(token)) {
                        throw new BaseException("无token",401);
                    }
                    String studentId = TokenUtil.getUserId(token);
                    if(studentId == null||"".equals(studentId)) {
                        throw new BaseException("token解析失败",401);
                    }
                    Student student = studentService.getStudentById(studentId);
                    if(student == null) {
                        throw new BaseException("用户不存在",401);
                    }
                    if(!TokenUtil.verifyPassword(token,student.getOpenid())){
                        throw new BaseException("token已过期",401);
                    }
                }
            }
            return true;

        }catch (Exception e){
            e.printStackTrace();
            httpServletResponse.setHeader("content-type", "application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");      //获取PrintWriter输出流
            JSONObject result = new JSONObject();
            PrintWriter out = httpServletResponse.getWriter();
            result.put("data", e.getMessage());
            result.put("code", 401);
            out.write(result.toJSONString());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
