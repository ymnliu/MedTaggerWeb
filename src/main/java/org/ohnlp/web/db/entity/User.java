package org.ohnlp.web.db.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name = "user")
public class User {

    // Primary ID which increments 
    // automatically when new entry is added into the database 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name = "id")
    private int id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "date_created")
    private Date date_created;

    public User() { 
    }

    public User(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateCreated() {
        return this.date_created;
    }

    public void setDateCreated(Date date_created) {
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return String.format(
            "User [%d] [%s]",
            this.id, this.username
        );
    }
}
