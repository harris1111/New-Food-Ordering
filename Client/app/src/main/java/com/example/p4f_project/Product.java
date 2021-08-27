package com.example.p4f_project;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String ID;
    private String Name;
    private String Des;
    private int amount=1;
    private int Price;

    protected Product(Parcel in) {
        ID = in.readString();
        Name = in.readString();
        Des = in.readString();
        amount = in.readInt();
        Price = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Name);
        dest.writeString(Des);
        dest.writeInt(amount);
        dest.writeInt(Price);
    }
}
