package com.example.btl_android_apporderticket.model;

public class Cast {
    private String castId;
    private String name;
    private String birthDay;
    private String nationality;

    public Cast() {
    }

    public Cast(String castId, String name, String birthDay, String nationality) {
        this.castId = castId;
        this.name = name;
        this.birthDay = birthDay;
        this.nationality = nationality;
    }

    public String getCastId() {
        return castId;
    }

    public void setCastId(String castId) {
        this.castId = castId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "castId='" + castId + '\'' +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
