package org.ohnlp.web.db.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "rulepack")
public class Rulepack {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "data", columnDefinition = "text")
    private String data;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, 
        optional=false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, 
        optional=false)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "date_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date_created;
    
    @Column(name = "date_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date_updated;

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

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }  

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }  

    public Date getDateCreated() {
        return this.date_created;
    }

    public void setDateCreated(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDateUpdated() {
        return this.date_updated;
    }

    public void setDateUpdated(Date date_updated) {
        this.date_updated = date_updated;
    }

    @Override
    public String toString() {
        return String.format(
            "Rulepack [%d] by [%s]",
            this.id, this.user.getUsername()
        );
    }
}
