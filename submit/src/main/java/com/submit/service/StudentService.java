package com.submit.service;

import com.submit.domain.Student;

public interface StudentService {
    Student getByWeChatOpenId(Student student);
    Student getStudentById(String studentId);

    void updStudent(Student student);
}
