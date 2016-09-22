package net.serenitybdd.dojo.supermarket;

import net.serenitybdd.dojo.supermarket.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WhenCheckingOutArticlesAtTheSupermarket {
    SupermarketCatalog catalog;
    Teller teller;
    ShoppingCart theCart;

    @Before
    public void initializeCart(){
        catalog = new DummyCatalog();
        teller = new Teller(catalog);
        theCart = new ShoppingCart();
//        ShoppingCart shoppingCart = new ShoppingCart();
    }

    @Test
    public void an_empty_shopping_cart_should_cost_nothing() {
        // WHEN

        Receipt receipt = teller.checksOutArticlesFrom(theCart);

        // THEN
        assertThat(receipt.getTotalPrice(), equalTo(0.00));

    }

    @Test
    public void teller_should_be_able_to_add_the_product_into_shopping_cart() {
        ShoppingCart theCartWithToothPaste = new ShoppingCart();
        theCartWithToothPaste.addProductsToCart(Products.TOOTHBRUSH, 1);
        // THEN
        assertThat(theCartWithToothPaste.getCartMap().size(), is(equalTo(1)));

    }

    //TODO:The teller should be able to handle a shopping cart with no special deals
    @Test
    public void teller_should_be_able_to_checkout_a_toothpaste() {
        ShoppingCart theCartWithToothPaste = new ShoppingCart();
        theCartWithToothPaste.addProductsToCart(Products.TOOTHBRUSH, 1);
        Receipt receipt = teller.checksOutArticlesFrom(theCartWithToothPaste);
        // THEN
        assertThat(receipt.getTotalPrice(), equalTo(20.00));
    }

    @Test
    public void teller_should_be_able_to_handle_duplicate_products() {
        //GIVEN

        ShoppingCart theShoppingCart = new ShoppingCart();

        //WHEN

        theShoppingCart.addProductsToCart(Products.TOOTHBRUSH, 1);
        theShoppingCart.addProductsToCart(Products.TOOTHBRUSH, 1);

        //THEN

        assertThat(theShoppingCart.getCartMap().size(), equalTo(1));
        assertThat(theShoppingCart.getQuantityFor(Products.TOOTHBRUSH), equalTo(2));
    }

    // The client should get a receipt with the list of purchases and the total price.

    @Test
    public void client_should_get_a_receipt_with_products_and_price() throws Exception {

        ShoppingCart theShoppingCart = new ShoppingCart();

        theShoppingCart.addProductsToCart(Products.TOOTHBRUSH, 1);
        theShoppingCart.addProductsToCart(Products.TOOTHPASTE, 1);
        theShoppingCart.addProductsToCart(Products.TOOTHBRUSH, 2);

        Receipt receipt = teller.checksOutArticlesFrom(theShoppingCart);

        assertThat(receipt.getTotalPrice(), equalTo(70.00));
        assertThat(receipt.getQuantityOf(Products.TOOTHBRUSH), equalTo(3));


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
    public void should_be_able_to_check_out_toothbrush_with_buy_two_get_one_free() throws Exception {
        //Given

        ShoppingCart theCartWithToothPaste = new ShoppingCart();
        theCartWithToothPaste.addProductsToCart(Products.TOOTHBRUSH, 7);

        //When
        Receipt receipt = teller.checksOutArticlesFrom(theCartWithToothPaste);

        //Then
        assertThat(teller.getOffers(), hasItem(OfferType.BUY_TWO_GET_ONE));
        assertThat(receipt.getQuantityOf(Products.TOOTHBRUSH), equalTo(10));

    }


}
