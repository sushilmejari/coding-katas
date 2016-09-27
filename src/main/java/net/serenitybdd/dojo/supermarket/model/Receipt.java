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
        int base_qty = quantity;
        productsMap.putAll(Offer.checkOfferAcheckOfferWithQuantityAndApply(product, quantity, offersType));
        //int discounted_amount=Offer.checkOfferWithDiscountPriceAndApply(product, quantity, offersType));

        totalPrice += base_qty * product.getPricePerQuantity();
    }
    public int getQuantityOf(Products products) {

        return productsMap.getOrDefault(products, 0);

    }

}
