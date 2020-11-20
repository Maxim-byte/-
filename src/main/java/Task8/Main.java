package Task8;


import com.google.common.base.Functions;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static OptionalDouble returnAverage(List<Integer> list) {
        return list.stream()
                .mapToInt(e -> e).average();
    }

    private static List<String> toUpperCase(List<String> list) {
         return list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        //list.forEach(String::toUpperCase);
    }

    private static List<Double> uniqSquare(List<Double> list) {
        return list.stream()
                .distinct()
                .map(x -> x * x)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int size = (int)(Math.random() * 100 + 1);
        List<Integer> list = new ArrayList<>(size);
        for(int i = 0; i < size; ++i) {
            list.add((int)(Math.random() * 100));
        }
        System.out.println(returnAverage(list).getAsDouble());
        List<String> list1 = new ArrayList<>(size);
        final String englishSymbols = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        for(int i = 0; i < size; ++i) {
            String randomString = randomString = random.ints(15, 0, englishSymbols.length())
                    .mapToObj(englishSymbols::charAt)
                    .map(Object::toString)
                    .collect(Collectors.joining());
            list1.add(randomString);
        }
        list1.forEach(System.out::println);
        System.out.println('\n');
        toUpperCase(list1).forEach(System.out::println);
        List<Double> list2 = new ArrayList<>(size);
        for(int i = 0; i < size; ++i) {
            list2.add(Math.random() * 100);
        }
        list2.forEach(System.out::println);
        System.out.println('\n');
        uniqSquare(list2).forEach(System.out::println);
    }
}
