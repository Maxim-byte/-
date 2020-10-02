package Part2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private Main() {
        // Should not be instantiated
    }

    static private void task2(FileReader fin, PrintWriter out) throws IOException, IndexOutOfBoundsException, NullPointerException,
            UnsupportedOperationException, ClassCastException, IllegalArgumentException {
        out.write("Task2\n");
        out.flush();
        BufferedReader reader = new BufferedReader(fin);
        reader.readLine();
        String line = reader.readLine();
        Map<String, Integer> map = new TreeMap<>();
        Integer count;
        while (!line.equals("]")) {
            line = line.trim();
            line = line.substring(line.indexOf(':') + 2);
            if (line.charAt(line.length() - 1) == ',') {
                line = line.substring(0, line.length() - 1);
            }
            count = map.get(line);
            if (count != null) {
                map.put(line, count + 1);
            } else {
                map.put(line, 1);
            }
            line = reader.readLine();
        }
        List<Map.Entry<String, Integer>> list = map.entrySet().stream()
                .sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue()))
                .collect(Collectors.toList());
        int size = list.size();
        if (size > 3) {
            out.write(list.get(0).toString() + '\n' + list.get(1).toString() + '\n' + list.get(2).toString());
        } else if (size == 2) {
            out.write(list.get(0).toString() + '\n' + list.get(1).toString());
        } else if (size == 1) {
            out.write(list.get(0).toString());
        }
        out.write('\n');
    }

    static private void task4(BufferedReader in, PrintWriter out) throws IOException {
        out.write("Task4\n");
        out.flush();
        String str = in.readLine();
        str = str.trim();
        boolean wasSpace = false;
        StringBuilder builder = new StringBuilder();
        for (Character ch : str.toCharArray()) {
            if (ch == ' ') {
                wasSpace = true;
            }
            if (ch != ' ') {
                if (wasSpace) {
                    builder.append(' ');
                }
                builder.append(ch);
                wasSpace = false;
            }
        }
        str = builder.toString();
        out.write(str + '\n');
    }

    static private void task5(BufferedReader in, PrintWriter out) throws IOException {
        out.write("Task5\n");
        out.flush();
        String line = in.readLine();
        int stringLength = line.length();
        int currentIndex = line.length() - 3;
        List<String> array = new ArrayList<>((line.length() / 3) + 1);
        while (stringLength - Math.abs(currentIndex) + 1 >= 0) {
            if (stringLength - Math.abs(currentIndex) >= 3) {
                array.add(line.substring(currentIndex, stringLength));
            } else if (stringLength - Math.abs(currentIndex) < 3) {
                array.add(line.substring(0, stringLength));
            }
            stringLength -= 3;
            currentIndex -= 3;
        }
        StringBuilder builder = new StringBuilder("");
        for(int i = 0; i < array.size(); ++i) {
            if(array.get(i).length() == 3) {
                char ch = (char)(Math.random() * 65536);
                builder.append(array.get(i).charAt(0)).append(ch).append(array.get(i).charAt(2));
                array.set(i, builder.toString());
                builder.setLength(0);
            }
        }
        out.write("Unsorted: " + array.toString() + '\n');
        array = array.stream().sorted().collect(Collectors.toList());
        out.write("Sorted: " + array.toString() + '\n');
    }

    public static void main(String[] args) {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (final PrintWriter out = new PrintWriter(System.out)) {
            final FileReader fin = new FileReader(new File("/Users/gvgromov/lab2.oop.java/src/main/resources/test"));
            task2(fin, out);
            task4(in, out);
            task5(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException | IndexOutOfBoundsException | NullPointerException | ClassCastException | IllegalArgumentException |
                    UnsupportedOperationException exception) {

                exception.printStackTrace();
            }
        }
    }
}
