package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.Products;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart {
    private Products product;
    private int quantity;
    protected Map<Products, Integer> cartMap = new LinkedHashMap<>();

    public Map<Products, Integer> getCartMap() {
        return cartMap;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addProductsToCart(Products product, int quantity) {
        cartMap.put(product, quantity + cartMap.getOrDefault(product, 0));
    }


    private boolean checkOffer(Products product) {
        return Offer.offersOnProduct.containsKey(product);
    }


    public Products getProduct() {
        return product;
    }

    public Integer getQuantityFor(Products product) {
        return cartMap.get(product);
    }


}
