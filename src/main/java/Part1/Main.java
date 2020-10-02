package Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void print(final PrintWriter out, int size, int size1, double[][] matrix) {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size1; ++j) {
                out.write(String.valueOf(matrix[i][j]) + ' ');
            }
            out.write("\n");
        }
    }

    private static ArrayList<Integer> readArray(final BufferedReader in) throws IOException, StringIndexOutOfBoundsException {
        String str = in.readLine();
        var array = new ArrayList<Integer>();
        var digit = new StringBuilder();
        try {
            for (Character i : str.toCharArray()) {
                if (i != ' ') {
                    digit.append(i);
                } else {
                    array.add(Integer.parseInt(digit.toString()));
                    digit.delete(0, digit.length());
                }
            }
            array.add(Integer.parseInt(digit.toString()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return array;
    }

    private static void solve1(final BufferedReader in, final PrintWriter out) throws IOException, NullPointerException, IndexOutOfBoundsException {
        out.write("Solve 1\n");
        out.flush();
        ArrayList<Integer> array = readArray(in);
        if (array == null) {
            throw new NullPointerException();
        }

        out.write("Array: " + array.toString() + '\n');

        int step = array.size() / 2;
        while (step >= 1) {
            for (int j = step; j < array.size(); ++j) {
                int k = j - step;
                while (k >= 0 && array.get(k) > array.get(k + step)) {
                    int temp = array.get(k);
                    array.set(k, array.get(k + step));
                    array.set(k + step, temp);
                    k -= step;
                }
            }
            step /= 2;
        }

        out.write("Sorted: " + array.toString() + '\n');
    }

    private static void solve2(final PrintWriter out) throws IOException {

        Integer[] intArray = new Integer[(int) (Math.random() * 100) + 1];
        for (int i = 0; i < intArray.length; ++i) {
            intArray[i] = (int) (Math.random() * 100);
        }
        int[] arrayOfIndex = new int[intArray.length];
        for (int i = 0; i < arrayOfIndex.length; i++) {
            arrayOfIndex[i] = (int) (Math.random() * arrayOfIndex.length);
            boolean isUnique = false;
            while (!isUnique) {
                if (arrayOfIndex[i] == i) {
                    arrayOfIndex[i] = (int) (Math.random() * arrayOfIndex.length);
                    continue;
                }
                int j;
                for (j = 0; j < i; j++) {
                    if (arrayOfIndex[i] == arrayOfIndex[j]) {
                        arrayOfIndex[i] = (int) (Math.random() * arrayOfIndex.length);
                        break;
                    }
                }
                if (j == i) {
                    isUnique = true;
                }
            }
        }
        int[] temp = new int[intArray.length];
        System.arraycopy(intArray, 0, temp, 0, intArray.length);
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = temp[arrayOfIndex[i]];
        }
        out.write(Arrays.toString(intArray) + '\n');
    }

    private static void solve3(final BufferedReader in, final PrintWriter out) throws IOException, NullPointerException {
        out.write("Solve 3\n");
        out.flush();
        ArrayList<Integer> array = readArray(in);
        if (array == null) {
            throw new NullPointerException();
        }
        int count = 0;
        Set<Integer> set = new HashSet<>(array);
        out.write("Unique count:" + set.size() + '\n');
    }

    private static void solve4(final BufferedReader in, final PrintWriter out) throws IOException {
        out.write("Solve 4\n");
        out.flush();
        boolean flag = false;
        try {
            int size = Integer.parseInt(in.readLine());
            int size1 = Integer.parseInt(in.readLine());
            double[][] matrix = new double[size][size1];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < i + 1; j++) {
                    if (j >= size1) {
                        continue;
                    }
                    if(flag) {
                        continue;
                    }
                    if (i >= size1) {
                        matrix[i][j] = i + 1;
                        flag = true;
                        continue;
                    }
                    matrix[i][j] = 1;
                }
                flag = false;
            }
            print(out, size, size1, matrix);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void solve5(final PrintWriter out) throws IOException {
        out.write("Solve 5\n");
        out.flush();
        int size = (int) (Math.random() * 10 + 1);
        int size1 = (int) (Math.random() * 10 + 1);

        double max = Double.MIN_VALUE;
        int place = 0;

        out.write("Size = " + size + "\nSize1 = " + size1 + "\n");
        double[][] matrix = new double[size][size1];
        double minFromMax;

        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < size1; ++j) {
                matrix[i][j] = (Math.random() * 10);
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }


        minFromMax = max;


        for (int i = 1; i < size; ++i) {
            for (int j = 0; j < size1; ++j) {
                matrix[i][j] = (Math.random() * 10);
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            if (minFromMax > max) {
                place = i;
                minFromMax = max;
            }
            max = Double.MIN_VALUE;
        }

        out.write("Matrix\n");

        print(out, size, size1, matrix);


        double[][] newMatrix = new double[size - 1][size1];
        boolean flag = false;

        for (int i = 0; i < size; ++i) {
            if (place == i) {
                flag = true;
                continue;
            }
            for (int j = 0; j < size1; ++j) {
                if (flag) {
                    newMatrix[i - 1][j] = matrix[i][j];
                } else {
                    newMatrix[i][j] = matrix[i][j];
                }
            }
        }


        out.write("\nNewMatrix\n");

        print(out, size - 1, size1, newMatrix);
    }

    public static void main(final String[] arg) {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (final PrintWriter out = new PrintWriter(System.out)) {
            solve1(in, out);
            solve2(out);
            solve3(in, out);
            solve4(in, out);
            solve5(out);
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
