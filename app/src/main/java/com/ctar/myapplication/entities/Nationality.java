package com.ctar.myapplication.entities;

import java.io.Serializable;

public class Nationality  implements Serializable {

    private Long id;

    private String nationality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
