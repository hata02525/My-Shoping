package com.shop.model;
public class Model {

    private int product_id;
    private String product_all_name;
    private String product_all_price;
    private String product_all_discount;
    private String product_all_Image_url;


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj==null)
            return false;
        if(getClass() !=obj.getClass())
            return false;
        Model model = (Model) obj;

        if(product_id!=model.product_id)
            return false;
        return true;

    }



    public int getProduct_all_id() {
        return product_id;
    }

    public String getProduct_all_name() {
        return product_all_name;
    }

    public String getProduct_all_price() {
        return product_all_price;
    }

    public String getProduct_all_discount() {
        return product_all_discount;
    }

    public String getProduct_all_Image_url() {
        return product_all_Image_url;
    }
}