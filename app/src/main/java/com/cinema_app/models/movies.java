package com.cinema_app.models;

import android.widget.TextView;

public class movies {

    public String name;
    public String img;

    public movies(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

}
