package com.example.onlin_shop;

import android.net.Uri;

public class Item {
    String name_item;
    String nguoi_ban;
    String gia;
    String thong_tin;
    Uri image;

    public Item(){

    }

    public Item(String name_item, String nguoi_ban, String gia, String thong_tin) {
        this.name_item = name_item;
        this.nguoi_ban = nguoi_ban;
        this.gia = gia;
        this.thong_tin = thong_tin;
    }
    public Item(String name_item, String nguoi_ban, String gia, String thong_tin, Uri image) {
        this.name_item = name_item;
        this.nguoi_ban = nguoi_ban;
        this.gia = gia;
        this.thong_tin = thong_tin;
        this.image = image;
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

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getThong_tin() {
        return thong_tin;
    }

    public void setThong_tin(String thong_tin) {
        this.thong_tin = thong_tin;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}

