package com.submit.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.submit.domain.Student;

import java.util.Date;

public class TokenUtil {

    private static final String password = "LiCHOLjq40546697";




    public static String getToken(Student student, int time) throws Exception { //time秒
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + time * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(student.getStudentId()).withIssuedAt(start).withExpiresAt(end).withSubject("Web")
                .sign(Algorithm.HMAC256(student.getOpenid()));
        return AESUtil.encrypt(password,token);
    }





    public static String getUserId(String AESToken) throws Exception {
        String token = AESUtil.decrypt(password,AESToken);
        try {
            return JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            return null;
        }
    }



    public static boolean verifyPassword(String AESToken, String adminPassword) throws Exception {
        String token = AESUtil.decrypt(password,AESToken);
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(adminPassword)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Student student = new Student();
        student.setStudentId("f9b334767055400791bcfca55fa38d8f");
        student.setOpenid("oxk87v-EEhNjbWdzMb7yQeC1RIq4");
        System.out.print(getToken(student,2592000));
    }

}
