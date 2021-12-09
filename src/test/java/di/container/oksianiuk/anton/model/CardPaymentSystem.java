package di.container.oksianiuk.anton.model;

public class CardPaymentSystem implements PaymentSystem
{
    @Override
    public void pay() {
        System.out.println("Pay for present using Card payment");
    }
}
