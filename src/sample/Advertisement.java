package sample;

import java.sql.Date;

public class Advertisement {
    private int id;
    private String name_of_adv;
    private String name_of_perfume;
    private float price_of_adv;
    private Date date_of_adv;
    private boolean is_paid;

    public void setName_of_perfume(String name_of_perfume) {
        this.name_of_perfume = name_of_perfume;
    }

    @Override
    public String toString() {
        return id +
                " |\t" + name_of_adv+
                ",   " + name_of_perfume +
                ",   " + price_of_adv+
                "$,  " + date_of_adv+
                ",  " + (is_paid ? "Оплачено.":"Неоплачено.");
    }

    public Advertisement(int id, String name_of_adv, String name_of_perfume, float price_of_adv, Date date_of_adv, boolean is_paid) {
        this.id = id;
        this.name_of_adv = name_of_adv;
        this.name_of_perfume = name_of_perfume;
        this.price_of_adv = price_of_adv;
        this.date_of_adv = date_of_adv;
        this.is_paid = is_paid;
    }

    public Advertisement(String name_of_adv, String name_of_perfume, float price_of_adv, Date date_of_adv, boolean is_paid) {
        this.name_of_adv = name_of_adv;
        this.name_of_perfume = name_of_perfume;
        this.price_of_adv = price_of_adv;
        this.date_of_adv = date_of_adv;
        this.is_paid = is_paid;
    }

    public Advertisement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_of_adv() {
        return name_of_adv;
    }

    public String getName_of_perfume() {
        return name_of_perfume;
    }

    public float getPrice_of_adv() {
        return price_of_adv;
    }

    public Date getDate_of_adv() {
        return date_of_adv;
    }

    public boolean isIs_paid() {
        return is_paid;
    }
}

