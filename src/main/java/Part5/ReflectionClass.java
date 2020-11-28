package Part5;

import java.io.PrintStream;
import java.util.Scanner;

public class ReflectionClass {
    private String buffer;
    public ReflectionClass(String str) {
        buffer = str;
    }
    @Count(count = 2)
    private int defaultRead() {
        Scanner in = new Scanner(System.in);
        buffer = in.nextLine();
        System.out.println("READ");
        return buffer.length();
    }
    @Count(count = 1)
    private void defaultWrite() {
        System.out.println(buffer);
        System.out.println("Write");
    }
    @Count(count = 3)
    private void trim() {
        buffer = buffer.trim();
        System.out.println("TRIM");
    }
}
