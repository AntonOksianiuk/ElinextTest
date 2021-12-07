package di.container.oksianiuk.anton.injection;

import java.lang.reflect.InvocationTargetException;
import java.util.TooManyListenersException;

public interface Injector {

    <T> Provider<T> getProvider(Class<T> type) throws TooManyListenersException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException; //получение инстанса класса со всеми иньекциями по классу интерфейса
    <T> void bind(Class<T> intf, Class<? extends T> impl); //регистрация байндинга по классу интерфейса и его реализации
    <T> void bindSingleton(Class<T> intf, Class<? extends T> impl);

}
