package com.example.onlin_shop;

public class Item {
    private String name_item;
    private String nguoi_ban;
    private double gia;
    private String thong_tin;

    public Item(String name_item, String nguoi_ban, double gia, String thong_tin) {
        this.name_item = name_item;
        this.nguoi_ban = nguoi_ban;
        this.gia = gia;
        this.thong_tin = thong_tin;
    }

    public String getName_item() {
        return name_item;
    }

    public void setName_item(String name_item) {
        this.name_item = name_item;
    }

    public String getNguoi_ban() {
        return nguoi_ban;
    }

    public void setNguoi_ban(String nguoi_ban) {
        this.nguoi_ban = nguoi_ban;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getThong_tin() {
        return thong_tin;
    }

    public void setThong_tin(String thong_tin) {
        this.thong_tin = thong_tin;
    }
}
