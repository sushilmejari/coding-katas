package net.serenitybdd.dojo.supermarket.model;

import java.util.List;

/**
 * Created by smejari on 9/27/2016.
 */
public class BuyOneGetOneOffer implements OfferStrategy {
    @Override
    public int handleOffer(int quantity, List<OfferType> offers) {

        quantity = quantity + quantity;
        offers.add(OfferType.BUY_TWO_GET_ONE);
        return quantity;

    }
}
