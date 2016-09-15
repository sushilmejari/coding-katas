package net.serenitybdd.dojo.supermarket.model;

import net.serenitybdd.dojo.supermarket.Products;

import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;

    public Teller(SupermarketCatalog catalog) {

        this.catalog = catalog;
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt=new Receipt();
        receipt.setTotalPrice(theCart.getCartMap().values().stream().mapToDouble(item-> item.doubleValue()).sum());
        return receipt;
    }
}
