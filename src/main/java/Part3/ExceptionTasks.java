package Part3;

import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTasks {
    public static void main(String[] args) throws URISyntaxException {
        task1();
        task2();
        task3();
        task4();
        try {
            task5();
        } catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            task6();
        } catch (Exception3 e3) {
            e3.printStackTrace();
        } catch (Exception2 e2) {
            e2.printStackTrace();
            ;
        } catch (Exception1 e1) {
            e1.printStackTrace();
        }
        try {
            task7();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
        task8();
    }

    private static void task1() {
        try {
            int a = 42 / 0;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    private static void task2() {
        String s = null;
        try {
            String m = s.toLowerCase();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private static void task3() {
        int[] m = new int[3];
        try {
            m[6] = 5;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private static void task4() {
        try {
            int num = Integer.parseInt("XYZ");
            System.out.println(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void task5() throws NullPointerException, ArithmeticException, FileNotFoundException, URISyntaxException {
        int i = (int) (Math.random() * 4);
        if (i == 0) {
            throw new NullPointerException();
        } else if (i == 1) {
            throw new ArithmeticException();
        } else if (i == 2) {
            throw new FileNotFoundException();
        } else if (i == 3) {
            throw new URISyntaxException("", "");
        }
    }

    private static void task6() throws Exception1 {
        int i = (int) (Math.random() * 3);
        if (i == 0) {
            throw new Exception1();
        } else if (i == 1) {
            throw new Exception2();
        } else if (i == 2) {
            throw new Exception3();
        }

    }

    private static void task7() throws FileSystemException {
        StatelessBean obj = new StatelessBean();
        try {
            obj.methodThrowExceptions();
        } catch (FileSystemException e) {
            obj.log(e);
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            obj.log(e);
        }
    }

    private static void task8() {
        int num = 0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        try {
            while (true) {
                arrayList.add(in.nextInt());
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println(arrayList);
        }
    }

    static class Exception1 extends Exception {

    }

    static class Exception2 extends Exception1 {

    }

    static class Exception3 extends Exception2 {

    }

    public static class StatelessBean {
        public void log(Exception exception) {
            System.out.println(exception.getMessage() + ", " + exception.getClass().getSimpleName());
        }

        public void methodThrowExceptions() throws CharConversionException, FileSystemException, IOException {
            int i = (int) (Math.random() * 3);
            if (i == 0) {
                throw new CharConversionException();
            } else if (i == 1) {
                throw new FileSystemException("");
            } else if (i == 2) {
                throw new IOException();
            }
        }
    }
}