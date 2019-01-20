package com.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class User {
    private int id;
    private String userNumber;
    private String password;
    private String nickName;
    private String phoneNumber;
    private String email;
    private String realName;
    private Integer sex;
    private byte[] img;
    private Integer type;
    private String teamName;
    private String proName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userNumber", nullable = false, length = 30)
    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "nickName", nullable = false, length = 30)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "phoneNumber", nullable = true, length = 30)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "realName", nullable = false, length = 30)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "img", nullable = true)
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "teamName", nullable = true, length = 30)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "proName", nullable = true, length = 30)
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (userNumber != null ? !userNumber.equals(user.userNumber) : user.userNumber != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (nickName != null ? !nickName.equals(user.nickName) : user.nickName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (realName != null ? !realName.equals(user.realName) : user.realName != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (!Arrays.equals(img, user.img)) return false;
        if (type != null ? !type.equals(user.type) : user.type != null) return false;
        if (teamName != null ? !teamName.equals(user.teamName) : user.teamName != null) return false;
        if (proName != null ? !proName.equals(user.proName) : user.proName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userNumber != null ? userNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(img);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (proName != null ? proName.hashCode() : 0);
        return result;
    }
}
