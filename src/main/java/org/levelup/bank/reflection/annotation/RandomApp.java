package org.levelup.bank.reflection.annotation;

import java.util.ArrayList;

public class RandomApp {
    public static void main(String[] args) {
        RandomIntAnnotationProcessor processor = new RandomIntAnnotationProcessor();
        User user = (User)processor.createObjectByClassName(User.class.getName());
        System.out.println(user);

        String [] classes = new String[3];
        classes[0] = User.class.getName();
        classes[1] = Scanner.class.getName();
        classes[2] = Server.class.getName();
        RandomStringAnnotationProcessor processor2 = new RandomStringAnnotationProcessor();
        ArrayList<Object> objectsWithRandomParameters = processor2.returnObjectsWithRandomString(classes);
        for(Object obj : objectsWithRandomParameters){
            System.out.println(obj.toString());
        }
    }
}
