package it.unimi.di.prog2.cali;

public class Example {
  public static int twice(int x) {
    return 2 * x;
  }
  public static double twice(double x) {
    return 2 * x;
  }

  public static void main(String[] args) {
    System.out.println(twice(3));
    System.out.println(twice(1.5));
  }
}