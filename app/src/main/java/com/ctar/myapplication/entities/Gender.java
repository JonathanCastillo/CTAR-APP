package com.ctar.myapplication.entities;

import java.io.Serializable;

public class Gender  implements Serializable {

    private Long id;

    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
