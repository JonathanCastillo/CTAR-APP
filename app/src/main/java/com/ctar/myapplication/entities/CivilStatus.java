package com.ctar.myapplication.entities;

import java.io.Serializable;

public class CivilStatus  implements Serializable {

    private Long id;

    private String civilStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }
}
