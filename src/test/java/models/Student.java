package models;

import java.sql.Date;

public class Student {

    public Student(String name, String surname, String faculty, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private String name;
    private String surname;
    private String faculty;
    private Date birthday;
}
