package com.example.furniture_app_2.Model;

public class Upload {
    String url;
    String name;
    String manufacturer;
    String description;
    int price;
    int ratings;
    String url_3d;
    String product_id;

    public String getUrl_3d() {
        return url_3d;
    }

    public void setUrl_3d(String url_3d) {
        this.url_3d = url_3d;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPrice() {
        return "$"+String.valueOf(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public Upload(String url, String name, String manufacturer, int price, int ratings, String description, String url_3d, String product_id) {
        this.url = url;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.ratings = ratings;
        this.description = description;
        this.url_3d = url_3d;
        this.product_id=product_id;
    }

    public Upload() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
