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
        int offerdQuantity = calculateDeals(product, quantity, offersType);
        productsMap.put(product, offerdQuantity);


    }

    private int calculateDeals(Products product, int quantity, List<OfferType> offersType) {
        if (new Offer().getOfferMap().containsKey(OfferType.BUY_TWO_GET_ONE)) {
            offersType.add(OfferType.BUY_TWO_GET_ONE);
            quantity = quantity / 2 + quantity;
            return quantity;
        }
        return quantity;
    }

    public int getQuantityOf(Products products) {

        return productsMap.getOrDefault(products, 0);

    }

}
