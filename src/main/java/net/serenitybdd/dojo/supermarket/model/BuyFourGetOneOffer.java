package net.serenitybdd.dojo.supermarket.model;

import java.util.List;

/**
 * Created by smejari on 9/27/2016.
 */
public class BuyFourGetOneOffer implements OfferStrategy {
    @Override
    public int handleOffer(int quantity, List<OfferType> offers) {

        quantity = quantity + (quantity / 4);
        offers.add(OfferType.BUY_FOUR_GET_ONE);
        return quantity;
    }
}
