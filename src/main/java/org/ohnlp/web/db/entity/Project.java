package org.ohnlp.web.db.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "project")
public class Project {

    // Primary ID which increments 
    // automatically when new entry is added into the database 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name = "id")
    private int id;
    
    @Column(name = "title")
    private String title;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, 
        optional=false)
    @JoinColumn(name = "user_id")
    private User user;

    public Project() { 
    }

    public Project(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format(
            "Project [%d] [%s]",
            this.id, this.title
        );
    }

}