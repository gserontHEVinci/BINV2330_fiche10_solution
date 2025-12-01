package ioc;

import java.lang.reflect.*;

public class Injector {
    public static void inject(Object target) throws Exception {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object dep = field.getType().getDeclaredConstructor().newInstance();
                field.set(target, dep);
            }
        }
    }
}