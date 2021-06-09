package org.levelup.bank.reflection.annotation;

import lombok.SneakyThrows;
import org.levelup.bank.exceptions.IncorrectFieldAnnotationTypeException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class RandomStringAnnotationProcessor {
    @SneakyThrows
    public ArrayList<Object> returnObjectsWithRandomString(String[] objectNames){
        ArrayList<Object> returnObjects = new ArrayList<>();
        for (String objectClassName : objectNames){
            Class<?> objectClass = Class.forName(objectClassName);
            Object instance = objectClass.getDeclaredConstructor().newInstance();
            Field[] fields = objectClass.getDeclaredFields();
            for(Field field: fields){
                RandomString annotation = field.getAnnotation(RandomString.class);
                if(annotation != null){
                    if(field.getType().getName().equals("java.lang.String")) {
                        String randomString = generateRandomString(annotation.length(), annotation.isUpperCase());
                        field.setAccessible(true);
                        field.set(instance, randomString);
                    } else {
                        throw new IncorrectFieldAnnotationTypeException("Error setting random string to '"+field.getName()+"' parameter of the class '"+objectClassName+"' - field type expected to be 'String' but it is '"+field.getType().getName()+"'");
                    }
                }
            }
            returnObjects.add(instance);
        }
        return returnObjects;
    }

    private String generateRandomString(int length,boolean isUpperCase){
        Random rnd = new Random();
        char [] chars = new char[length];
        for(int i = 0; i < length; i++){
            if(isUpperCase) {
                chars[i] = (char) (65 + rnd.nextInt(26));
            }
            else{
                chars[i] = (char) (97 + rnd.nextInt(26));
            }
        }
        return String.copyValueOf(chars);
    }
}
