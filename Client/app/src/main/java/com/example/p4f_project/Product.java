package com.example.p4f_project;

public class Product {
    private String ID;
    private String Name;
    private String Des;
    private int amount=1;
    private int Price;
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void increaseAmount(int n){
        this.amount+=n;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDes() {
        return Des;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDes(String des) {
        Des = des;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Product(String ID, String name, String des, int price) {
        this.ID = ID;
        Name = name;
        Des = des;
        Price = price;
    }

}
