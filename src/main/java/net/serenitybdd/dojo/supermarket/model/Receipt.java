package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.Products;

public class Receipt {

    private double totalPrice;

    public Receipt() {

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {

        this.totalPrice=totalPrice;
    }


}
