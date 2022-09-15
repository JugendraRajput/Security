package com.jdgames.security;

public class Parse {

    int imageID;
    String title;
    String subtitle;
    String value;

    public Parse(int imageID, String title, String subtitle, String value) {
        this.imageID = imageID;
        this.title = title;
        this.subtitle = subtitle;
        this.value = value;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getValue() {
        return value;
    }
}
