package di.container.oksianiuk.anton.model.impl;

import di.container.oksianiuk.anton.annotation.Inject;
import di.container.oksianiuk.anton.model.*;

public class GiftDistributorWithIncorrectBinding implements Distributor {
    private PaymentSystem paymentSystem;

    private Gift gift;

    private User user;


    @Inject
    public GiftDistributorWithIncorrectBinding(PaymentSystem paymentSystem, Gift gift, User user, WrongUser user1) {
        this.paymentSystem = paymentSystem;
        this.gift = gift;
        this.user = user;
    }


    public GiftDistributorWithIncorrectBinding() {
    }

    @Override
    public void distributeGift() {
        paymentSystem.pay();
        System.out.println(String.format("Person %s got his gift %s", user.getUsername(), gift.getName()));
    }
}
