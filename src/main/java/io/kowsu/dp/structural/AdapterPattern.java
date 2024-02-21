package io.kowsu.dp.structural;

/*
    @created February/21/2024 - 4:33 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class AdapterPattern {

    public static void main(String[] args) {
        //Creating FlipkartOrder to place at Amazon
        FlipkartOrder flipkartOrder = new FlipkartOrder("721191", "Vivo X90 Pro", 64000, "MIG, CHITRAPURI");
        //Creating PlaceAMazonOrder to make use of while placing a Flipkart Order
        PlaceAmazonOrder placeAmazonOrder = new PlaceAmazonOrder();

        //Through inheritance
        //UsingAmazonIeAdapter to place an order at Amazon
        AmazonIeAdapter amazonIeAdapter = new AmazonIeAdapter(placeAmazonOrder);
        //client calling FlipkartOrder to place order
        amazonIeAdapter.placeFlipkartOrder(flipkartOrder);


        System.out.println("--------------------------");
        System.out.println("--------------------------");
        System.out.println("--------------------------");

        //Through composition
        //UsingAmazonCoAdapter to place an order at Amazon
        AmazonCoAdapter amazonCoAdapter = new AmazonCoAdapter(placeAmazonOrder);
        //client calling FlipkartOrder to place order
        amazonCoAdapter.placeFlipkartOrder(flipkartOrder);

    }

}

/**
 * Flipkart wants to integrate with Amazon to sell its products on Amazon
 * Adaptor  -- Amazon
 * Adaptee  -- Flipkart
 *
 * Option1 : Through inheritance
 *
 */
class AmazonIeAdapter extends PlaceFlipkartOrder {
    private PlaceAmazonOrder placeAmazonOrder;

    public AmazonIeAdapter(PlaceAmazonOrder placeAmazonOrder) {
        this.placeAmazonOrder = placeAmazonOrder;
    }

    /**
     * This method used to place a Flipkart order in the Amazon
     * @param flipkartOrder
     */
    @Override
    public void placeFlipkartOrder(FlipkartOrder flipkartOrder) {
        System.out.println("Placing order at Flipkart");
        //transformations
        AmazonOrder amazonOrder = new AmazonOrder(
                                                    flipkartOrder.orderId(),
                                                    flipkartOrder.productName(),
                                                    flipkartOrder.price(),
                                                    new Address(
                                                            flipkartOrder.deliveryAddress().substring(0, 1),
                                                            flipkartOrder.deliveryAddress().substring(2, 3),
                                                            flipkartOrder.deliveryAddress().substring(4, flipkartOrder.deliveryAddress().length())
                                                    )
        );
        this.placeAmazonOrder.placeAmazonOrder(amazonOrder);
    }

}

class AmazonCoAdapter {

    private PlaceAmazonOrder placeAmazonOrder;

    public AmazonCoAdapter(PlaceAmazonOrder placeAmazonOrder) {
        this.placeAmazonOrder = placeAmazonOrder;
    }

    public void placeFlipkartOrder(FlipkartOrder flipkartOrder) {
        System.out.println("Placing order at Flipkart");
        //transformations
        AmazonOrder amazonOrder = new AmazonOrder(
                flipkartOrder.orderId(),
                flipkartOrder.productName(),
                flipkartOrder.price(),
                new Address(
                        flipkartOrder.deliveryAddress().substring(0, 1),
                        flipkartOrder.deliveryAddress().substring(2, 3),
                        flipkartOrder.deliveryAddress().substring(4, flipkartOrder.deliveryAddress().length())
                )
        );
        this.placeAmazonOrder.placeAmazonOrder(amazonOrder);
    }
}

class PlaceFlipkartOrder {
    public void placeFlipkartOrder(FlipkartOrder flipkartOrder) {
        System.out.println("Order Received at Flipkart " + flipkartOrder);
    }
}


class PlaceAmazonOrder {
    public void placeAmazonOrder(AmazonOrder amazonOrder) {
        System.out.println("Order Received at Amazon " + amazonOrder);
    }
}

record FlipkartOrder(String orderId, String productName, double price, String deliveryAddress) {
}

record AmazonOrder(String order, String itemName, double price, Address address) {
}

record Address(String flat, String street, String city) {
}
