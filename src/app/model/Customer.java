package app.model;

import java.util.Date;

public class Customer {

    private int id;
    private String name;
    private String surname;
    private Date date;

    public Customer() {
    }

    public Customer(int id, String name, String surname, Date date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer [id=" + this.id + ", name=" + this.name + ", surname=" + this.surname + ", date=" + this.date + "]";
    }
}
