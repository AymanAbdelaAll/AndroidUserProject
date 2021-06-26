package com.example.userproject.UserPresenter;

import com.example.userproject.POJO.Address;
import com.example.userproject.POJO.Company;

import java.io.Serializable;

public class UserViewModel implements Serializable {
    private int id=0;
    private String name="Name";
    private String username="UserName";
    private String website="Website";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
