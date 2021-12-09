package di.container.oksianiuk.anton;

import di.container.oksianiuk.anton.injection.Injector;
import di.container.oksianiuk.anton.injection.InjectorImpl;
import di.container.oksianiuk.anton.injection.Provider;
import di.container.oksianiuk.anton.model.Distributor;
import di.container.oksianiuk.anton.model.PaymentSystem;
import di.container.oksianiuk.anton.model.impl.CashPaymentSystem;
import di.container.oksianiuk.anton.model.impl.GiftDistributor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.TooManyListenersException;

import static org.junit.jupiter.api.Assertions.*;

public class TestSingletoneAndPrototype {

    static final Injector injector = InjectorImpl.getInstance();

    @Test
    void testSingletoneAndPrototype() throws TooManyListenersException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        injector.bind(PaymentSystem.class, CashPaymentSystem.class);
        injector.bind(Distributor.class, GiftDistributor.class);


        Provider<Distributor> distributorProvider = injector.getProvider(Distributor.class);
        Provider<Distributor> distributorProvider2 = injector.getProvider(Distributor.class);

        assertNotEquals(distributorProvider.getInstance(), distributorProvider2.getInstance());

        injector.bindSingleton(Distributor.class, GiftDistributor.class);

        Provider<Distributor> distributorProviderSingleton = injector.getProvider(Distributor.class);
        Provider<Distributor> distributorProvider2Singleton = injector.getProvider(Distributor.class);

        assertEquals(distributorProviderSingleton.getInstance(), distributorProvider2Singleton.getInstance());

        assertNotNull(distributorProvider);
        assertNotNull(distributorProvider.getInstance());
        assertNotNull(distributorProviderSingleton);
        assertNotNull(distributorProviderSingleton.getInstance());
    }
}
