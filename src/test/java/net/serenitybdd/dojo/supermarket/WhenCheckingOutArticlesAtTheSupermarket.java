package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WhenCheckingOutArticlesAtTheSupermarket {

    private SupermarketCatalog catalog;
    private Teller teller;
    private ShoppingCart theCart;

    @Before
    public void initializeCart(){
        catalog = new DummyCatalog();
        teller = new Teller(catalog);
        theCart = new ShoppingCart(Products.EMPTY,0);
    }

    @Test
    public void an_empty_shopping_cart_should_cost_nothing() {
        // WHEN

        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice(), equalTo(0.00));

    }

    //TODO:The teller should be able to handle a shopping cart with no special deals
    @Test
    public void teller_should_be_able_to_checkout_a_tootthpaste(){
        //ShoppingCart theCartWithToothPaste = ShoppingCart.add(Products.TOOTHPASTE).withQuantity(1);

        ShoppingCart theCart = new ShoppingCart();
        theCart.addProductsToCart(Products.TOOTHPASTE, 1);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        // THEN
        assertThat(theCart.getSumOfPrice(), equalTo(10.00));
    }

    @Test
    public void teller_should_be_able_to_checkout_multiple_products(){
        ShoppingCart theCart = new ShoppingCart();
        theCart.addProductsToCart(Products.TOOTHPASTE, 1);
        theCart.addProductsToCart(Products.TOOTHBRUSH, 1);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        // THEN
        assertThat(receipt.getTotalPrice(), equalTo(30.00));
    }


    @Test
    public void teller_should_be_able_to_handle_a_shopping_cart_with_no_special_deals(){
        ShoppingCart theCart = new ShoppingCart();
        theCart.addProductsToCart(Products.TOOTHPASTE, 1);
        theCart.addProductsToCart(Products.TOOTHBRUSH, 1);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        assertThat(receipt.getTotalPrice(), equalTo(30.00));
    }

}
