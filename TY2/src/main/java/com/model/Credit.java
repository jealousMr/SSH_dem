package com.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credit {
    private int id;
    private String proName;
    private String publisher;
    private String userNumber;
    private Integer count;
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
    @Column(name = "proName", nullable = false, length = 30)
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
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
    @Column(name = "userNumber", nullable = false, length = 30)
    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

        Credit credit = (Credit) o;

        if (id != credit.id) return false;
        if (proName != null ? !proName.equals(credit.proName) : credit.proName != null) return false;
        if (publisher != null ? !publisher.equals(credit.publisher) : credit.publisher != null) return false;
        if (userNumber != null ? !userNumber.equals(credit.userNumber) : credit.userNumber != null) return false;
        if (count != null ? !count.equals(credit.count) : credit.count != null) return false;
        if (message != null ? !message.equals(credit.message) : credit.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (proName != null ? proName.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (userNumber != null ? userNumber.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
