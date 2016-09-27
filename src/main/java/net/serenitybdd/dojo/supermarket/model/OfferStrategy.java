package net.serenitybdd.dojo.supermarket.model;

import java.util.List;

/**
 * Created by smejari on 9/27/2016.
 */
interface OfferStrategy {
    public int handleOffer(int quantity, List<OfferType> offers);
}
