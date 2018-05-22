package sample;

import java.sql.Date;

public class Advertisement {
    private int id;
    private String name_of_adv;
    private String name_of_perfume;
    private float price_of_adv;
    private Date date_of_adv;
    private boolean is_paid;

    public Advertisement(int id, String name_of_add, String name_of_perfume, float price_of_add, Date date_of_add, boolean is_paid) {
        this.id = id;
        this.name_of_adv = name_of_add;
        this.name_of_perfume = name_of_perfume;
        this.price_of_adv = price_of_add;
        this.date_of_adv = date_of_add;
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

    public void setName_of_adv(String name_of_add) {
        this.name_of_adv = name_of_add;
    }

    public String getName_of_perfume() {
        return name_of_perfume;
    }

    public void setName_of_perfume(String name_of_perfume) {
        this.name_of_perfume = name_of_perfume;
    }

    public float getPrice_of_adv() {
        return price_of_adv;
    }

    public void setPrice_of_adv(float price_of_adv) {
        this.price_of_adv = price_of_adv;
    }

    public Date getDate_of_add() {
        return date_of_adv;
    }

    public void setDate_of_adv(Date date_of_adv) {
        this.date_of_adv = date_of_adv;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }
}

