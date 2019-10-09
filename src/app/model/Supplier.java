package app.model;

import java.util.Date;

public class Supplier {

    private int supplierId;
    private String supplierName;
    private String supplierSurname;
    private Date date;

    public Supplier() {
    }

    public Supplier(int supplierId, String supplierName, String supplierSurname, Date date) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierSurname = supplierSurname;
        this.date = date;
    }

    public int getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return this.supplierName;
    }

    public void setName(String name) {
        this.supplierName = name;
    }

    public String getSurname() {
        return this.supplierSurname;
    }

    public void setSurname(String surname) {
        this.supplierSurname = surname;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Supplier [supplierId=" + this.supplierId + ", supplierName=" + this.supplierName + ", supplierSurname="
                + this.supplierSurname + ", date=" + this.date + "]";
    }
}
