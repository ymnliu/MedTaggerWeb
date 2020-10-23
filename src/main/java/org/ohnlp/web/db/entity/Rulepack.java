package org.ohnlp.web.db.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "ruleset")
public class Rulepack {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "data", columnDefinition = "text")
    private String data;
}
