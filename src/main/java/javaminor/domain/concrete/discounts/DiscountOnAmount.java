package javaminor.domain.concrete.discounts;

import javaminor.domain.abs.Discount;

/**
 * Created by alex on 9/9/15.
 */
public class DiscountOnAmount extends Discount {
    private int toPay;
    private int toBeFree;

    /**
     * Constructor.
     *
     * i.e.
     *
     * buy five get one toBeFree
     *
     * DiscountAmount(5,1)
     *
     * @param toPay discounted amount of products to pay for
     * @param toBeFree discounted amount of toBeFree products
     */
    public DiscountOnAmount(final int toPay, final int toBeFree){
        super(0);
        this.toPay = toPay;
        this.toBeFree = toBeFree;
    }

    @Override
    public double getDiscountOn(double price, int amount) {

        // initially all products need to be paid for
        int productsToPayFor = amount;

        // products are only free if we actually "buy" them
        // i.e. buy 5 get 1 free, we need a total bought of 6 in order to get a freebie
        int futureFree = 0;

        // counts up to toPay
        int counter = 0;

        // for each product bought
        for (int i = 0; i < amount; i++) {
            counter++;

            // if we have toBeFree products,
            if(futureFree > 0){

                // remove one from the pay-list
                productsToPayFor--;

                // remove one from the future toBeFree-list
                futureFree--;
            }

            // if our counter equals the amount of products we need to pay for
            if(counter == toPay){

                // free products gets increased by the specified amount of free products per amount
                futureFree += toBeFree;

                // reset counter
                counter = 0;
            }
        }

        // whatever is left times price
        return productsToPayFor * price;

    }

    @Override
    public String toString(){
        return "Buy " + toPay + " get " + toBeFree + " free.";
    }
}
