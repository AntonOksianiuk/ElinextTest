package di.container.oksianiuk.anton.model;

import di.container.oksianiuk.anton.annotation.Inject;

public class GiftDistributor implements Distributor
 {

    private PaymentSystem paymentSystem;

    private Gift gift;

    private User user;

    @Inject
    public GiftDistributor(PaymentSystem paymentSystem, Gift gift, User user) {
        this.paymentSystem = paymentSystem;
        this.gift = gift;
        this.user = user;
    }

    public GiftDistributor() {
    }

    @Override
    public void distributeGift(){
        paymentSystem.pay();
        System.out.println(String.format("Person %s got his gift %s", user.getUsername(), gift.getName()));
    }

}
