package com.submit.domain;

import java.util.Date;

public class Student {
    private String studentId;

    private String openid;

    private String nickname;

    private String sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String studentName;

    private String studentProvince;

    private String studentSchool;

    private String studentMark;

    private String emsName;

    private String emsProvince;

    private String emsAddress;

    private String emsPhone;

    private String emsEmail;

    private String emsNumber;

    private Integer studentState;

    private Date studentFirstDate;

    private Date studentLastDate;

    private Integer studentLoginTime;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentProvince() {
        return studentProvince;
    }

    public void setStudentProvince(String studentProvince) {
        this.studentProvince = studentProvince == null ? null : studentProvince.trim();
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool == null ? null : studentSchool.trim();
    }

    public String getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(String studentMark) {
        this.studentMark = studentMark == null ? null : studentMark.trim();
    }

    public String getEmsName() {
        return emsName;
    }

    public void setEmsName(String emsName) {
        this.emsName = emsName == null ? null : emsName.trim();
    }

    public String getEmsProvince() {
        return emsProvince;
    }

    public void setEmsProvince(String emsProvince) {
        this.emsProvince = emsProvince == null ? null : emsProvince.trim();
    }

    public String getEmsAddress() {
        return emsAddress;
    }

    public void setEmsAddress(String emsAddress) {
        this.emsAddress = emsAddress == null ? null : emsAddress.trim();
    }

    public String getEmsPhone() {
        return emsPhone;
    }

    public void setEmsPhone(String emsPhone) {
        this.emsPhone = emsPhone == null ? null : emsPhone.trim();
    }

    public String getEmsEmail() {
        return emsEmail;
    }

    public void setEmsEmail(String emsEmail) {
        this.emsEmail = emsEmail == null ? null : emsEmail.trim();
    }

    public String getEmsNumber() {
        return emsNumber;
    }

    public void setEmsNumber(String emsNumber) {
        this.emsNumber = emsNumber == null ? null : emsNumber.trim();
    }

    public Integer getStudentState() {
        return studentState;
    }

    public void setStudentState(Integer studentState) {
        this.studentState = studentState;
    }

    public Date getStudentFirstDate() {
        return studentFirstDate;
    }

    public void setStudentFirstDate(Date studentFirstDate) {
        this.studentFirstDate = studentFirstDate;
    }

    public Date getStudentLastDate() {
        return studentLastDate;
    }

    public void setStudentLastDate(Date studentLastDate) {
        this.studentLastDate = studentLastDate;
    }

    public Integer getStudentLoginTime() {
        return studentLoginTime;
    }

    public void setStudentLoginTime(Integer studentLoginTime) {
        this.studentLoginTime = studentLoginTime;
    }

    public void removeForUser(){
        this.studentId = null;
        this.openid = null;
        this.nickname = null;
        this.sex = null;
        this.province = null;
        this.city = null;
        this.country = null;
        this.headimgurl = null;
if(this.emsNumber == null || "".equals(this.emsNumber)){
    this.emsNumber = "暂无快递单号";
}
        this.studentFirstDate = null;
        this.studentLastDate = null;
        this.studentLoginTime = null;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentProvince='" + studentProvince + '\'' +
                ", studentSchool='" + studentSchool + '\'' +
                ", studentMark='" + studentMark + '\'' +
                ", emsName='" + emsName + '\'' +
                ", emsProvince='" + emsProvince + '\'' +
                ", emsAddress='" + emsAddress + '\'' +
                ", emsPhone='" + emsPhone + '\'' +
                ", emsEmail='" + emsEmail + '\'' +
                ", emsNumber='" + emsNumber + '\'' +
                ", studentState=" + studentState +
                ", studentFirstDate=" + studentFirstDate +
                ", studentLastDate=" + studentLastDate +
                ", studentLoginTime=" + studentLoginTime +
                '}';
    }
}