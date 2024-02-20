package pl.javastart;

import java.math.BigInteger;

import static pl.javastart.MathProcessor.MATH;

class MathProcessorTest {
    public static void main(String[] args) {
        System.out.println(MATH."1000000000000 + 2");   //1000000000002

        int a = 5;
        int b = 10;
        BigInteger result = MATH."\{a} / \{b}";
        System.out.println(result);    //0

        System.out.println(MATH."50 *    2");   //100
    }
}
