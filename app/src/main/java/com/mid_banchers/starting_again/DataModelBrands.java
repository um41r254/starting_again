package com.mid_banchers.starting_again;

import com.google.firebase.Timestamp;

public class DataModelBrands {

    private String  image;
    private String brandName;
    private Double  id;
    private Timestamp addedOn;


    public DataModelBrands() {

    }

    public DataModelBrands(String image, String brandName, Double id, Timestamp addedOn) {
        this.image = image;
        this.brandName = brandName;
        this.id = id;
        this.addedOn = addedOn;
    }

    public String getImage() {
        return image;
    }

    public String getBrandName() {
        return brandName;
    }

    public Double getId() {
        return id;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }
}
