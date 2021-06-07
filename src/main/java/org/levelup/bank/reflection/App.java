package org.levelup.bank.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class App {

    @SneakyThrows
    public static void main(String[] args) {
        Phone phone = new Phone(8,"Sony Xperia");

        Class<?> phoneClass =  phone.getClass();

        Class<?> classOfPhone = Phone.class;

        System.out.println("Class name: "+ classOfPhone.getName());

        Field[] publicFields = classOfPhone.getFields();

        System.out.println("List of public fields");
        for(Field pf: publicFields){
            System.out.println(pf.getType().getName()+" "+pf.getName());
        }
        System.out.println(" ");
        System.out.println("List of all fields");
        Field[] fields = classOfPhone.getDeclaredFields();
        for(Field pf: fields){
            System.out.println(pf.getType().getName()+" "+pf.getName());
        }

        Field ramFiled = classOfPhone.getDeclaredField("ram");
        ramFiled.setAccessible(true);
        ramFiled.set(phone,16);
        int ram = (int)ramFiled.get(phone);
        System.out.println("Ram: "+ram);

        Method method = classOfPhone.getDeclaredMethod("printPhone");
        method.setAccessible(true);
        method.invoke(phone);
        Constructor<?> allArgsConstructor = classOfPhone
                .getDeclaredConstructor(String.class,String.class,int.class);
        allArgsConstructor.setAccessible(true);
        Phone samsung = (Phone) allArgsConstructor.newInstance("Samsung","Galaxy 21",16);
        method.invoke(samsung);

    }
}