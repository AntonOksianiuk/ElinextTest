package di.container.oksianiuk.anton;

import di.container.oksianiuk.anton.exception.BindingNotFoundException;
import di.container.oksianiuk.anton.exception.ConstructorNotFoundException;
import di.container.oksianiuk.anton.exception.TooManyConstructorsException;
import di.container.oksianiuk.anton.injection.Injector;
import di.container.oksianiuk.anton.injection.InjectorImpl;
import di.container.oksianiuk.anton.injection.Provider;
import di.container.oksianiuk.anton.model.Distributor;
import di.container.oksianiuk.anton.model.PaymentSystem;
import di.container.oksianiuk.anton.model.impl.*;
import di.container.oksianiuk.anton.unrealized_interfaces.Parcel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.TooManyListenersException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;


public class TestInjector {

    static final Injector injector = InjectorImpl.getInstance();

    @Test
    void testExistingBinding() throws TooManyListenersException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        injector.bind(PaymentSystem.class, CashPaymentSystem.class);
        Provider<PaymentSystem> paymentSystemProvider = injector.getProvider(PaymentSystem.class);

        assertNotNull(paymentSystemProvider);
        assertNotNull(paymentSystemProvider.getInstance());
        assertSame(CashPaymentSystem.class, paymentSystemProvider.getInstance().getClass());
    }

    @Test
    void testConstructorByDefault() throws TooManyListenersException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        injector.bind(PaymentSystem.class, CashPaymentSystem.class);
        injector.bind(Distributor.class, GiftDistributorWithoutInjectAnnotation.class);

        Provider<Distributor> distributorProvider = injector.getProvider(Distributor.class);

        assertNotNull(distributorProvider);
        assertNotNull(distributorProvider.getInstance());
        assertSame(GiftDistributorWithoutInjectAnnotation.class, distributorProvider.getInstance().getClass());
    }


    @Test
    void testWrongBindingInConstructor() {

        Assertions.assertThrows(BindingNotFoundException.class, () -> {

            injector.bind(PaymentSystem.class, CashPaymentSystem.class);
            injector.bind(Distributor.class, GiftDistributorWithIncorrectBinding.class);

            Provider<Distributor> distributorProvider = injector.getProvider(Distributor.class);
        });
    }

    @Test
    void testWrongBindingInProvider() throws TooManyListenersException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        Provider<Parcel> parcelProvider = injector.getProvider(Parcel.class);
        assertSame(null, parcelProvider);
    }


    @Test
    void testTooManyConstructorsException() {
        Assertions.assertThrows(TooManyConstructorsException.class, () -> {

            injector.bind(PaymentSystem.class, CashPaymentSystem.class);
            injector.bind(Distributor.class, GiftDistributorWithManyInjectAnnotations.class);

            Provider<Distributor> daoProvider = injector.getProvider(Distributor.class);
        });
    }

    @Test
    void testConstructorNotFoundException() {
        Assertions.assertThrows(ConstructorNotFoundException.class, () -> {

            injector.bind(PaymentSystem.class, CashPaymentSystem.class);
            injector.bind(Distributor.class, GiftDistributorWithoutInjectAnnotationAndConstructor.class);

            Provider<Distributor> distributorProvider = injector.getProvider(Distributor.class);
        });
    }


}
