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
    public void initializeCart() {
        catalog = new DummyCatalog();
        teller = new Teller(catalog);
        theCart = new ShoppingCart();
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
    public void teller_should_be_able_to_checkout_multiple_products() {
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
        ShoppingCart theCartWithToothbrush = new ShoppingCart();
        theCartWithToothbrush.addProductsToCart(Products.TOOTHBRUSH, 6);
        theCartWithToothbrush.addProductsToCart(Products.TOOTHPASTE, 5);
        Offer.addOfferOnProduct(Products.TOOTHBRUSH).applyOffer(OfferType.BUY_TWO_GET_ONE);
        Offer.addOfferOnProduct(Products.TOOTHPASTE).applyOffer(OfferType.BUY_FOUR_GET_ONE);
        //When
        Receipt receipt = teller.checksOutArticlesFrom(theCartWithToothbrush);
        //Then
        assertThat(teller.getOffers(), hasItems(OfferType.BUY_FOUR_GET_ONE, OfferType.BUY_TWO_GET_ONE));
        assertThat(receipt.getQuantityOf(Products.TOOTHPASTE), equalTo(6));
        assertThat(receipt.getQuantityOf(Products.TOOTHBRUSH), equalTo(9));

    }

    @Test
    public void should_be_able_to_check_out_Shirt_with_buy_four_get_one_free() throws Exception {
        //Given
        ShoppingCart theCartWithToothbrush = new ShoppingCart();
        theCartWithToothbrush.addProductsToCart(Products.SHIRT, 7);
        theCartWithToothbrush.addProductsToCart(Products.TOOTHBRUSH, 3);

        Offer.addOfferOnProduct(Products.SHIRT).applyOffer(OfferType.BUY_FOUR_GET_ONE);
        //When
        Receipt receipt = teller.checksOutArticlesFrom(theCartWithToothbrush);
        //Then
        assertThat(teller.getOffers(), hasItems(OfferType.BUY_FOUR_GET_ONE));
        assertThat(receipt.getQuantityOf(Products.SHIRT), equalTo(8));

    }


/*    @Test
    public void should_be_able_to_check_out_Product_with_Ten_Percent_Discount() throws Exception {
        //Given
        ShoppingCart theCartWithToothbrush = new ShoppingCart();
        theCartWithToothbrush.addProductsToCart(Products.SHIRT, 7);
        theCartWithToothbrush.addProductsToCart(Products.TOOTHBRUSH, 3);

        Offer.addOfferOnProduct(Products.SHIRT).applyOffer(OfferType.BUY_FOUR_GET_ONE);
        //When
        Receipt receipt = teller.checksOutArticlesFrom(theCartWithToothbrush);
        //Then
        assertThat(teller.getOffers(), hasItems(OfferType.BUY_FOUR_GET_ONE));
        assertThat(receipt.getQuantityOf(Products.SHIRT), equalTo(8));

    }*/






}
