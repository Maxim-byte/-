package Part4;

import java.util.*;
import java.util.stream.Collectors;

public class Part4 {

    private static final String englishSymbols = "abcdefghijklmnopqrstuvwxyz";

    private static void shift(List<Integer> list, int step) {
        if (step > 0) { //right shift
            for (int i = 0; i < step; ++i) {
                int temp = list.get(list.size() - 1);
                for (int j = list.size() - 1; j > 0; --j) {
                    list.set(j, list.get(j - 1));
                }
                list.set(0, temp);
            }
        } else { //left shift
            for (int i = 0; i < Math.abs(step); ++i) {
                int temp = list.get(0);
                for (int j = 1; j < list.size(); ++j) {
                    list.set(j - 1, list.get(j));
                }
                list.set(list.size() - 1, temp);
            }
        }
    }


    private static void stackPrinter(Scanner in) {
        Stack<Integer> stack = new Stack<>();
        int num = 0, digit = 0;
        while (in.hasNext()) {
            num = in.nextInt();
            while (num != 0) {
                digit = num % 10;
                num = num / 10;
                stack.push(digit);
            }
            stack.forEach(System.out::print);
            stack.clear();
            System.out.print('\n');
        }
    }

    private static <K, V> Map<V, K> swapKeyValueMap(Map<K, V> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            Random random = new Random();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < (int) (Math.random() * 15 + 1); ++i) {
                list.add((int) (Math.random() * 100));
            }
            System.out.println(list);
            shift(list, in.nextInt());
            System.out.println(list);
            stackPrinter(in);
            Map<String, Integer> map = new TreeMap<>();
            for (int i = 0; i < (int) (Math.random() * 15 + 1); ++i) {
                String randomString = random.ints(15, 0, englishSymbols.length())
                        .mapToObj(englishSymbols::charAt)
                        .map(Object::toString)
                        .collect(Collectors.joining());
                map.put(randomString, (int) (Math.random() * 100));
            }
            System.out.println(map);
            var newMap = swapKeyValueMap(map);
            System.out.println(newMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
