package di.container.oksianiuk.anton.model.impl;

import di.container.oksianiuk.anton.model.PaymentSystem;

public class CardPaymentSystem implements PaymentSystem {
    @Override
    public void pay() {
        System.out.println("Pay for present using Card payment");
    }
}
