package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smejari on 9/22/2016.
 */
public class Offer {

    public static Map<Products, List<OfferType>> offersOnProduct = new HashMap<>();

    public static OffresBulder addOfferOnProduct(Products product) {
        return new OffresBulder(product);
    }

    public static Map<Products, Integer> checkOfferAndApply(Products product, int quantity, List<OfferType> offers) {
        Map<Products, Integer> updatedItem = new HashMap<>();
        if (offersOnProduct.size() != 0) {
            if (offersOnProduct.get(product).contains(OfferType.BUY_TWO_GET_ONE)) {
                offers.add(OfferType.BUY_TWO_GET_ONE);
                quantity = quantity + (quantity / 2);
            }
            if (offersOnProduct.get(product).contains(OfferType.BUY_ONE_GET_ONE)) {
                offers.add(OfferType.BUY_ONE_GET_ONE);
                quantity = quantity + quantity;
            }
        }
        updatedItem.put(product, Integer.valueOf(quantity));
        return updatedItem;
    }

    public static class OffresBulder {
        Products product;

        public OffresBulder(Products product) {
            this.product = product;
            offersOnProduct.put(product, new ArrayList<>());
        }

        public void applyOffer(OfferType offerType) {
            offersOnProduct.get(product).add(offerType);
        }

    }

}
