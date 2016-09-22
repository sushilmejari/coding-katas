package net.serenitybdd.dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class Teller {

    private final SupermarketCatalog catalog;
    private List<OfferType> offersType = new ArrayList<>();

    public Teller(SupermarketCatalog catalog) {

        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {

        Receipt receipt = new Receipt();
        theCart.getCartMap().forEach(
                (products, integer) -> receipt.addProduct(products,
                        integer, offersType));
        return receipt;
    }

    public List<OfferType> getOffers() {
        return offersType;
    }
}
