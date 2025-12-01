package ioc;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Injector {
    static Map<Class, Class> interf2Concrete = new HashMap<>();
    static {
        try (FileInputStream in = new FileInputStream("di.properties")) {
            Properties props = new Properties();
            props.load(in);
            try {
                for (String itf : props.stringPropertyNames()){
                    String className = props.getProperty(itf);
                    Class<?> cls = Class.forName(props.getProperty(itf));
                    interf2Concrete.put(Class.forName(itf), Class.forName(props.getProperty(itf)));
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void inject(Object target) throws Exception {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Class<?> typ = field.getType();
                Object dep = interf2Concrete.get(field.getType()).getDeclaredConstructor().newInstance();
                field.set(target, dep);
            }
        }
    }
}