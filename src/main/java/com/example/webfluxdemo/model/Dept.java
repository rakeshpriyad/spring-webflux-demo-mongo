package com.example.webfluxdemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * Created by rajeevkumarsingh on 08/09/17.
 */
@Document(collection = "department")
public class Dept {
    @Id
    private String id;

    public String getName() {
        return name;
    }

    @NotBlank
    @Size(max = 140)
    private String name;

    @NotNull
    private Date createdAt = new Date();

    public Dept() {

    }

    public Dept(String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
