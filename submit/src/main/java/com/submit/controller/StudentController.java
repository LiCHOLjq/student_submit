package com.submit.controller;

import com.alibaba.fastjson.JSONObject;
import com.submit.annotation.UserLoginToken;
import com.submit.domain.Student;
import com.submit.exception.BaseException;
import com.submit.service.StudentService;
import com.submit.util.TokenUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;


    @RequestMapping(value = "/getStudent", method = RequestMethod.POST)
    @UserLoginToken
    public JSONObject getStudent(HttpServletRequest httpServletRequest, @RequestBody String params) {
        JSONObject result = new JSONObject();
        try {
            String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
            String studentId = TokenUtil.getUserId(token);
            Student student = studentService.getStudentById(studentId);
            if(student == null){
                throw new Exception("学生不存在");
            }
            student.removeForUser();
            result.put("data", student);
            result.put("code", 200);
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

    @RequestMapping(value = "/submitBind", method = RequestMethod.POST)
    @UserLoginToken
    public JSONObject submitBind(HttpServletRequest httpServletRequest, @RequestBody String params) {
        JSONObject result = new JSONObject();
        try {
            String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
            JSONObject paramsJson = JSONObject.parseObject(JSONObject.parseObject(params).getString("params"));
            Student student = JSONObject.parseObject(paramsJson.getString("student"), Student.class);
            String studentId = TokenUtil.getUserId(token);
            Student studentSel = studentService.getStudentById(studentId);
            if(studentSel == null){
                throw new Exception("学生不存在");
            }
            Student studentUpd = new Student();
            studentUpd.setStudentId(studentId);
            studentUpd.setStudentName(student.getStudentName());
            studentUpd.setStudentProvince(student.getStudentProvince());
            studentUpd.setStudentSchool(student.getStudentSchool());
            studentUpd.setStudentMark(student.getStudentMark());
            studentUpd.setEmsName(student.getStudentName());
            studentUpd.setEmsProvince(student.getStudentProvince());
            studentUpd.setStudentState(2);
            studentService.updStudent(studentUpd);
            result.put("data", "绑定成功");
            result.put("code", 200);
        } catch (BaseException e){
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


    @RequestMapping(value = "/submitApply", method = RequestMethod.POST)
    @UserLoginToken
    public JSONObject submitApply(HttpServletRequest httpServletRequest, @RequestBody String params) {
        JSONObject result = new JSONObject();
        try {
            String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
            JSONObject paramsJson = JSONObject.parseObject(JSONObject.parseObject(params).getString("params"));
            Student student = JSONObject.parseObject(paramsJson.getString("student"), Student.class);
            String studentId = TokenUtil.getUserId(token);
            Student studentSel = studentService.getStudentById(studentId);
            if(studentSel == null){
                throw new Exception("学生不存在");
            }
            Student studentUpd = new Student();
            studentUpd.setStudentId(studentId);
            studentUpd.setEmsName(student.getEmsName());
            studentUpd.setEmsProvince(student.getEmsProvince());
            studentUpd.setEmsPhone(student.getEmsPhone());
            studentUpd.setEmsEmail(student.getEmsEmail());
            studentUpd.setEmsAddress(student.getEmsAddress());
            studentUpd.setStudentState(3);
            studentService.updStudent(studentUpd);
            result.put("data", "申领成功");
            result.put("code", 200);
        } catch (BaseException e){
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
