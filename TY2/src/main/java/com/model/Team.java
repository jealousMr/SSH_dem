package com.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {
    private int id;
    private String teamName;
    private String proName;
    private String captain;
    private String member1;
    private String member2;
    private String member3;
    private String member4;
    private String member5;
    private String message;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "teamName", nullable = false, length = 30)
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

    @Basic
    @Column(name = "captain", nullable = false, length = 30)
    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    @Basic
    @Column(name = "member1", nullable = true, length = 30)
    public String getMember1() {
        return member1;
    }

    public void setMember1(String member1) {
        this.member1 = member1;
    }

    @Basic
    @Column(name = "member2", nullable = true, length = 30)
    public String getMember2() {
        return member2;
    }

    public void setMember2(String member2) {
        this.member2 = member2;
    }

    @Basic
    @Column(name = "member3", nullable = true, length = 30)
    public String getMember3() {
        return member3;
    }

    public void setMember3(String member3) {
        this.member3 = member3;
    }

    @Basic
    @Column(name = "member4", nullable = true, length = 30)
    public String getMember4() {
        return member4;
    }

    public void setMember4(String member4) {
        this.member4 = member4;
    }

    @Basic
    @Column(name = "member5", nullable = true, length = 30)
    public String getMember5() {
        return member5;
    }

    public void setMember5(String member5) {
        this.member5 = member5;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 225)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (teamName != null ? !teamName.equals(team.teamName) : team.teamName != null) return false;
        if (proName != null ? !proName.equals(team.proName) : team.proName != null) return false;
        if (captain != null ? !captain.equals(team.captain) : team.captain != null) return false;
        if (member1 != null ? !member1.equals(team.member1) : team.member1 != null) return false;
        if (member2 != null ? !member2.equals(team.member2) : team.member2 != null) return false;
        if (member3 != null ? !member3.equals(team.member3) : team.member3 != null) return false;
        if (member4 != null ? !member4.equals(team.member4) : team.member4 != null) return false;
        if (member5 != null ? !member5.equals(team.member5) : team.member5 != null) return false;
        if (message != null ? !message.equals(team.message) : team.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (proName != null ? proName.hashCode() : 0);
        result = 31 * result + (captain != null ? captain.hashCode() : 0);
        result = 31 * result + (member1 != null ? member1.hashCode() : 0);
        result = 31 * result + (member2 != null ? member2.hashCode() : 0);
        result = 31 * result + (member3 != null ? member3.hashCode() : 0);
        result = 31 * result + (member4 != null ? member4.hashCode() : 0);
        result = 31 * result + (member5 != null ? member5.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
