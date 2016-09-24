package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.Products;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receipt {

    private double totalPrice;
    Map<Products, Integer> productsMap = new HashMap<Products, Integer>();

    public Receipt() {

    }

    public Receipt(Products product, int quantity) {
        addProduct(product, quantity, Collections.emptyList());
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addProduct(Products product, int quantity, List<OfferType> offersType) {
        totalPrice += quantity * product.getPricePerQuantity();
        productsMap.putAll(Offer.checkOfferAndApply(product, quantity, offersType));
    }
    public int getQuantityOf(Products products) {

        return productsMap.getOrDefault(products, 0);

    }

}
