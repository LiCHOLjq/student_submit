package com.submit.service.impl;

import com.submit.domain.Student;
import com.submit.mapper.StudentMapper;
import com.submit.service.StudentService;
import com.submit.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getByWeChatOpenId(Student student) {
        Student selStudent = studentMapper.selectByOpenId(student.getOpenid());
        if(selStudent!=null){
            student.setStudentId(selStudent.getStudentId());
            student.setStudentLastDate(new Date());
            student.setStudentLoginTime(selStudent.getStudentLoginTime()+1);
            studentMapper.updateByPrimaryKeyOnlyWeChat(student);
        }else{
            student.setStudentId(UUIDUtil.getUUID());
            student.setStudentProvince(student.getProvince());
            student.setStudentState(1);
            student.setStudentFirstDate(new Date());
            student.setStudentLastDate(new Date());
            student.setStudentLoginTime(1);
            studentMapper.insertSelective(student);
        }
        student = studentMapper.selectByPrimaryKey(student.getStudentId());

        return student;
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public void updStudent(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
    }
}
