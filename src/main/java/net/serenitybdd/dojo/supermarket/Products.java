package net.serenitybdd.dojo.supermarket;

/**
 * Created by pawpawar on 9/13/2016.
 */
public enum Products {

    EMPTY(0), TOOTHPASTE(10), TOOTHBRUSH(20), SHIRT(200);

    private int pricePerQuantity;

    public int getPricePerQuantity() {
        return pricePerQuantity;
    }

    Products(int pricePerQuantity) {
        this.pricePerQuantity = pricePerQuantity;
    }
}
