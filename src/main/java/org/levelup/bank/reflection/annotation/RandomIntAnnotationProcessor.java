package org.levelup.bank.reflection.annotation;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Random;


public class RandomIntAnnotationProcessor {

    @SneakyThrows
    public Object createObjectByClassName(String className){
        Class<?> objectClass = Class.forName(className);
        Object instance = objectClass.getDeclaredConstructor().newInstance();
        Field[] fields = objectClass.getDeclaredFields();
        for(Field field : fields){
                RandomInt annotation = field.getAnnotation(RandomInt.class);
                if(annotation != null){
                    int min = annotation.min();
                    int max = annotation.max();

                    Random r = new Random();
                    int randomInt = r.nextInt(max - min) + min;
                    field.setAccessible(true);
                    field.set(instance,randomInt);

                }
            }
        return instance;
    }
}
