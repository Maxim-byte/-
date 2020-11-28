package Part5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ReflectionClass reflectionClass = new ReflectionClass("Hello _Man _Bro _WHAT");
        Method[] methods = reflectionClass.getClass().getDeclaredMethods();
        for(Method method : methods) {
            method.setAccessible(true);
            if(method.isAnnotationPresent(Count.class)) {
                int count = method.getAnnotation(Count.class).count();
                for(int i = 0; i < count; ++i) {
                    method.invoke(reflectionClass);
                }
            }
        }
    }
}
