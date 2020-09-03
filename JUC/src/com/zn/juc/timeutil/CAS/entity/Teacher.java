package com.zn.juc.timeutil.CAS.entity;

public class Teacher {
    private String name;
    private Integer age;
    private Boolean isShangke;

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getShangke() {
        return isShangke;
    }

    public void setShangke(Boolean shangke) {
        isShangke = shangke;
    }

    public Teacher(String name, Integer age, Boolean isShangke) {
        this.name = name;
        this.age = age;
        this.isShangke = isShangke;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isShangke=" + isShangke +
                '}';
    }
}
