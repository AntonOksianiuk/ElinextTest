package di.container.oksianiuk.anton.testDistributor;

import di.container.oksianiuk.anton.model.Distributor;
import di.container.oksianiuk.anton.model.Gift;
import di.container.oksianiuk.anton.model.PaymentSystem;
import di.container.oksianiuk.anton.model.User;

public class GiftDistributorWithoutInjectAnnotationAndConstructor implements Distributor {
    private PaymentSystem paymentSystem;

    private Gift gift;

    private User user;


    public GiftDistributorWithoutInjectAnnotationAndConstructor(PaymentSystem paymentSystem, Gift gift, User user) {
        this.paymentSystem = paymentSystem;
        this.gift = gift;
        this.user = user;
    }

    @Override
    public void distributeGift(){
        paymentSystem.pay();
        System.out.println(String.format("Person %s got his gift %s", user.getUsername(), gift.getName()));
    }
}
