package di.container.oksianiuk.anton.testDistributor;

import di.container.oksianiuk.anton.annotation.Inject;
import di.container.oksianiuk.anton.model.Distributor;
import di.container.oksianiuk.anton.model.Gift;
import di.container.oksianiuk.anton.model.PaymentSystem;
import di.container.oksianiuk.anton.model.User;

public class GiftDistributorWithManyInjectAnnotations implements Distributor {
    private PaymentSystem paymentSystem;

    private Gift gift;

    private User user;


    @Inject
    public GiftDistributorWithManyInjectAnnotations(PaymentSystem paymentSystem, Gift gift, User user) {
        this.paymentSystem = paymentSystem;
        this.gift = gift;
        this.user = user;
    }

    @Inject
    public GiftDistributorWithManyInjectAnnotations(PaymentSystem paymentSystem, Gift gift) {
        this.paymentSystem = paymentSystem;
        this.gift = gift;
    }

    public GiftDistributorWithManyInjectAnnotations() {
    }

    @Override
    public void distributeGift(){
        paymentSystem.pay();
        System.out.println(String.format("Person %s got his gift %s", user.getUsername(), gift.getName()));
    }
}
