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

    public static Map<OfferType, OfferStrategy> offerStrategyMap = new HashMap<>();

    static {
        offerStrategyMap.put(OfferType.BUY_TWO_GET_ONE, new BuyTwoGetOneOffer());
        offerStrategyMap.put(OfferType.BUY_FOUR_GET_ONE, new BuyFourGetOneOffer());
        offerStrategyMap.put(OfferType.BUY_ONE_GET_ONE, new BuyOneGetOneOffer());
    }

    public static OffresBulder addOfferOnProduct(Products product) {
        return new OffresBulder(product);
    }


    public static int byeOneGetOneOffer(int quantity, List<OfferType> offers) {
        quantity = quantity + quantity;
        offers.add(OfferType.BUY_ONE_GET_ONE);
        return quantity;
    }

    public static int byeFourGetOneOffer(int quantity, List<OfferType> offers) {
        quantity = quantity + (quantity / 4);
        offers.add(OfferType.BUY_FOUR_GET_ONE);
        return quantity;
    }


    public static Map<Products, Integer> checkOfferAcheckOfferWithQuantityAndApply(Products product, int quantity, List<OfferType> offers) {
        Map<Products, Integer> updatedItem = new HashMap<>();
        if (offersOnProduct.containsKey(product)) {
            for (OfferType offerType : offersOnProduct.get(product)) {
                OfferStrategy offerStrategy = offerStrategyMap.get(offerType);
                quantity = offerStrategy.handleOffer(quantity, offers);

            }
        }
        updatedItem.put(product, Integer.valueOf(quantity));

        return updatedItem;
    }


    public static long checkOfferWithDiscountPriceAndApply(Products product, int quantity, List<OfferType> offers) {
        Map<Products, Integer> updatedItem = new HashMap<>();
        int discontedPrice = 0;
        if (offersOnProduct.containsKey(product)) {

            if (offersOnProduct.get(product).contains(OfferType.TEN_PERCENT_OFFER)) {
                offers.add(OfferType.TEN_PERCENT_OFFER);
                discontedPrice = (int) (discontedPrice + (product.getPricePerQuantity() * 0.10) * quantity);
            }
        }

        return discontedPrice;
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
