package Part5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ReflectionClass reflectionClass = new ReflectionClass("Hello");
        Method[] methods = reflectionClass.getClass().getDeclaredMethods();
        for(Method method : methods) {
            method.setAccessible(true);
            method.invoke(reflectionClass);
            if(method.isAnnotationPresent(Count.class)) {
                int count = method.getAnnotation(Count.class).count();
                System.out.println(method.toString());
                System.out.println(count);
                for(int i = 0; i < count; ++i) {
                    method.invoke(reflectionClass);
                }
            }
        }
    }
}
