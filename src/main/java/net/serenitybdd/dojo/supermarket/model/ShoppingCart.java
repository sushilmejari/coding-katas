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

    public ShoppingCart() {

    }

    public ShoppingCart(Products product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Double getSumOfPrice() {
        return cartMap.values().stream().mapToDouble(item-> item.doubleValue()).sum();
    }





    public int getQuantity() {
        return quantity;
    }

    public static ShoppingCartBuilder createNewCart()
    {
        return new ShoppingCartBuilder();
    }


    public static ShoppingCartBuilder add(Products product) {
        return new ShoppingCartBuilder();
    }

    public void addProductsToCart(Products product, int quantity) {
        cartMap.put(product, product.getPricePerQuantity()*quantity);
    }

    public static class ShoppingCartBuilder {
//        private  Map<Products, Integer> cart = new LinkedHashMap<>();
//        private Products currentProduct=;
//
//        public ShoppingCartBuilder() {
//
//        }
//
//        public ShoppingCartBuilder add(Products product) {
//
//            cart.put(product,0) ;
//            currentProduct=product;
//
//        return this;
//        }
//
//        public  ShoppingCart quantity(Integer qty) {
//
//            cart.put(product,qty);
//            return this;
//        }



    /*
        public ShoppingCartBuilder(Products product) {
            this.product = product;
        }

        public ShoppingCart withQuantity(int quantity) {

            return new ShoppingCart(product,quantity);
        }*/
    }

    public Products getProduct() {
        return product;
    }
}
