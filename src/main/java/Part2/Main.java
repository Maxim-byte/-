package Part2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private Main() {
        // Should not be instantiated
    }

    static private String getDigit(Integer digit, boolean isOneDigit, boolean oneSecond) {
        String[] digits = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять"};
        String result = "";
        if (digit == 0) {
            if (!isOneDigit) {
                return result;
            }
        }
        if (oneSecond) {
            if (Math.abs(digit) == 1) {
                return result + "одна";
            }
            return result + "две";
        }
        result += digits[Math.abs(digit)];
        return result;
    }

    static private String getDozens(Integer num, boolean oneSecond) {
        if(num < 10) {
            return getDigit(Math.abs(num), false, false);
        }
        String result = "";
        if (num == 0) {
            return result;
        }
        String[] ten = {"десять", "одинадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
                "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
        String[] dozen = {"двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят",
                "девяносто"};
        if ((Math.abs(num) >= 10 && Math.abs(num) < 20)) {
            return (result = ten[Math.abs(num) % 10]);
        }
        return (result += dozen[(Math.abs(num) / 10) - 2] + ' ' + getDigit(Math.abs(num % 10), false, oneSecond));
    }

    static private String getHundreds(Integer num, boolean OneSecond) {
        String result = "";
        if (num == 0) {
            return result;
        }
        String[] hundreds = {"сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        result += hundreds[Math.abs(num) / 100 - 1] + ' ' + getDozens(Math.abs(num) % 100, OneSecond);
        return result;
    }

    static private String getThousand(Integer num, boolean oneSecond) {
        String result = "";
        if (Math.abs(num) / 1000 == 1) {
            return result += "одна тысяча " + getHundreds(Math.abs(num) % 1000, oneSecond);
        } else if (Math.abs(num) / 1000 == 2) {
            return result += "две тысячи " + getHundreds(Math.abs(num) % 1000, oneSecond);
        } else if (Math.abs(num) / 1000 == 3) {
            return result += "три тысячи " + getHundreds(Math.abs(num) % 1000, oneSecond);
        } else if (Math.abs(num) / 1000 == 4) {
            return result += "четыре тысячи " + getHundreds(Math.abs(num) % 1000, oneSecond);
        } else if (Math.abs(num) / 10000 == 0) {
            return result += getDigit(Math.abs(num) / 1000, false, oneSecond) + " тысячь " + getHundreds(Math.abs(num) % 1000, oneSecond);
        }
        return result += getDigit(Math.abs(num) / 10000, false, oneSecond) + " тысячь " + getHundreds(Math.abs(num) % 10000, oneSecond);
    }

    static private String getDozenThousand(Integer num) {
        String result = "";
        if (Math.abs(num) / 1000 % 10 == 0) {
            return result += getDozens(num / 1000, false) + " тысяч " + getHundreds(Math.abs(num) % 1000, false);
        } else if (Math.abs(num) / 1000 % 10 == 1) {
            return result += getDozens(num / 1000, true) + " тысячa " + getHundreds(Math.abs(num) % 1000, false);
        } else if (Math.abs(num) / 1000 % 10 == 2) {
            return result += getDozens(num / 1000, true) + " тысячи " + getHundreds(Math.abs(num) % 1000, false);
        } else if (Math.abs(num) / 1000 % 10 == 3) {
            return result += getDozens(num / 1000, false) + " тысячи " + getHundreds(Math.abs(num) % 1000, false);
        } else if (Math.abs(num) / 1000 % 10 == 4) {
            return result += getDozens(num / 1000, false) + " тысячи " + getHundreds(Math.abs(num) % 1000, false);
        }
        return result += getDozens(Math.abs(num) / 1000, false) + " тысяч " + getHundreds(Math.abs(num) % 1000, false);
    }

    static private void task1(BufferedReader in, PrintWriter out) throws IOException {
        out.write("Task1\n");
        out.flush();
        int number = Integer.parseInt(in.readLine());
        String result = "";
        if (Math.abs(number) <= 10) {
            result += getDigit(number, true, false);
        } else if (Math.abs(number) >= 11 && Math.abs(number) < 100) {
            result = getDozens(number, false);
        } else if (Math.abs(number) >= 100 && Math.abs(number) < 1000) {
            result = getHundreds(number, false);
        } else if (Math.abs(number) >= 1000 && Math.abs(number) < 10000) {
            result = getThousand(number, false);
        } else if (Math.abs(number) >= 10000 && Math.abs(number) < 100000) {
            result = getDozenThousand(number);
        }
        StringBuilder builder = new StringBuilder();
        if(number < 0) {
            builder.append("минус ");
        }
        out.write(builder.append(result).toString() + '\n');
        //по аналогии до бесконечночти, но смысла нет, лишь писать много...Надеюсь до 100к хватит...
    }

    static private void task2(FileReader fin, PrintWriter out) throws
            IOException, IndexOutOfBoundsException, NullPointerException,
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
        for (int i = 0; i < array.size(); ++i) {
            if (array.get(i).length() == 3) {
                char ch = (char) (Math.random() * 65536);
                while(ch == array.get(i).charAt(0) && ch == array.get(i).charAt(2))
                {
                    ch = (char) (Math.random() * 65536);
                }
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
            task1(in, out);
            task2(fin, out);
            task4(in, out);
            task5(in, out);
            fin.close();
        } catch (IOException | IndexOutOfBoundsException | NullPointerException | ClassCastException | IllegalArgumentException |
                UnsupportedOperationException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException exception) {

                exception.printStackTrace();
            }
        }
        Triangle.Point a = new Triangle.Point(1, 0);
        Triangle.Point b = new Triangle.Point(100, 23);
        Triangle.Point c = new Triangle.Point(1100, 100);
        try {
            Triangle tr = new Triangle(a, b, c);
            System.out.println(tr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
