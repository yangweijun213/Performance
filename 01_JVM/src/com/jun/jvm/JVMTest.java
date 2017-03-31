package com.jun.jvm;

/**
 * Created by jeff yang on 3/29/2017.
 * use below example to understand JVM by Visual VM
 */
public class JVMTest {
    public static void main(String arg[]) throws InterruptedException {

        int x = 10;
        while (x < 10000) {
            System.out.print("value of x : " + x);
            x++;
            Thread.sleep(10);
            System.out.print("value of x : " + x);
        }

        Person p = new Person();
            p.walk();

    }
}


class Person {
    void walk() {
        int x = 10;
        while (x < 10000) {
            System.out.print("Person value of x : " + x);
            x++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("Person value of x : " + x);
        }
    }

}