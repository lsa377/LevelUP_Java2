package org.levelup.bank.lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaExamples {

    public static void main(String[] args) {
        Comparator<Phone> weightComparator = new Comparator<Phone>() {
            @Override
            public int compare(Phone o1, Phone o2) {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
        };
        int maxLength = 5;

        Predicate<Phone> maxLengthLambdaPredicate2 = (p) -> {
            return p.getBrand().length() <= maxLength;
        };

        //  Predicate<Phone> maxLengthLambdaPredicate = phone -> phone.getBrand().length() <= maxLength;

        Comparator<Phone> weightComparator2 = (p1,p2) -> Double.compare(p1.getWeight(), p2.getWeight());

        List<Phone> list = new ArrayList<>();
        list.add(new Phone("Xiaomi","Redmi1",134));
        list.add(new Phone("Xiaomi","Redmi2",164));
        list.add(new Phone("Samsung","S20",210));
        list.add(new Phone("Samsung","Note2",250));
        list.add(new Phone("iPhone","12",189));
        list.add(new Phone("Huawei","8 Max",195));

        List<Phone> filteredList = list.stream()
                .filter(phone -> phone.getBrand().length() <= maxLength)
                .collect(Collectors.toList());

        list.stream()
                .map(phone -> phone.getWeight())
                .filter(weight -> weight >= 200)
                .collect(Collectors.toList());

        OptionalDouble optional = list.stream()
                .mapToDouble(p -> p.getWeight()) //.mapToDouble(Phone::getWeight)
                .max();

        double maxWeight = optional.orElse(0);

        List<Phone> sortedList = list.stream()
                .sorted((p1,p2) -> -p1.getBrand().compareTo(p2.getBrand()))
                .collect(Collectors.toList());

        list.forEach(phone -> System.out.println(phone));
        list.forEach(System.out::println);
    }
}
