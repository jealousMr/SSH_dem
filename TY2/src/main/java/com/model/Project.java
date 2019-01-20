package com.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Project {
    private int id;
    private String proName;
    private String proContent;
    private String publisher;
    private String reward;
    private Integer type;
    private String sendTime;
    private String deadline;
    private byte[] img;
    private String applicant;
    private String executor;
    private Integer isTeam;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "proName", nullable = false, length = 30)
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Basic
    @Column(name = "proContent", nullable = false, length = 225)
    public String getProContent() {
        return proContent;
    }

    public void setProContent(String proContent) {
        this.proContent = proContent;
    }

    @Basic
    @Column(name = "publisher", nullable = false, length = 30)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "reward", nullable = false, length = 30)
    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
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
    @Column(name = "sendTime", nullable = false, length = 30)
    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "deadline", nullable = false, length = 30)
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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
    @Column(name = "applicant", nullable = true, length = 30)
    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    @Basic
    @Column(name = "executor", nullable = true, length = 30)
    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    @Basic
    @Column(name = "isTeam", nullable = true)
    public Integer getIsTeam() {
        return isTeam;
    }

    public void setIsTeam(Integer isTeam) {
        this.isTeam = isTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (proName != null ? !proName.equals(project.proName) : project.proName != null) return false;
        if (proContent != null ? !proContent.equals(project.proContent) : project.proContent != null) return false;
        if (publisher != null ? !publisher.equals(project.publisher) : project.publisher != null) return false;
        if (reward != null ? !reward.equals(project.reward) : project.reward != null) return false;
        if (type != null ? !type.equals(project.type) : project.type != null) return false;
        if (sendTime != null ? !sendTime.equals(project.sendTime) : project.sendTime != null) return false;
        if (deadline != null ? !deadline.equals(project.deadline) : project.deadline != null) return false;
        if (!Arrays.equals(img, project.img)) return false;
        if (applicant != null ? !applicant.equals(project.applicant) : project.applicant != null) return false;
        if (executor != null ? !executor.equals(project.executor) : project.executor != null) return false;
        if (isTeam != null ? !isTeam.equals(project.isTeam) : project.isTeam != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (proName != null ? proName.hashCode() : 0);
        result = 31 * result + (proContent != null ? proContent.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (reward != null ? reward.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(img);
        result = 31 * result + (applicant != null ? applicant.hashCode() : 0);
        result = 31 * result + (executor != null ? executor.hashCode() : 0);
        result = 31 * result + (isTeam != null ? isTeam.hashCode() : 0);
        return result;
    }
}
