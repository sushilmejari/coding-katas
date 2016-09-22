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


    public final Map<OfferType, List<Products>> offerMap = new HashMap<>();

    {
        List<Products> buyTwoGetOneProducts = new ArrayList<>();
        buyTwoGetOneProducts.add(Products.TOOTHPASTE);
        offerMap.put(OfferType.BUY_TWO_GET_ONE, buyTwoGetOneProducts);

    }

    public Map<OfferType, List<Products>> getOfferMap() {
        return offerMap;
    }
}
