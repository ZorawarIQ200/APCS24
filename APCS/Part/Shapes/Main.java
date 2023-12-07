import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int length = DataVala(s);
        Shape1[] arr = new Shape1[length];

        for (int i = 0; i < length; i++) {
            if ((int) (Math.random() * 10) % 2 == 0) {
                arr[i] = new Circle1(Math.random() * 10);
            } else {
                arr[i] = new Square1(Math.random() * 10);
            }
        }

        System.out.println("Array of shapes:");
        for (Shape1 shape : arr) {
            System.out.println(shape);
        }

        Arrays.sort(arr);

        System.out.println("\nSorted array of shapes:");
        int i = 1;
        for (Shape1 shape : arr) {
            System.out.println(i + ": "+ shape + " has area: " + shape.area());
            i++;
        }
    }

    public static int DataVala(Scanner s) {
        int n = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the number of shapes: ");
            if (s.hasNextInt()) {
                n = s.nextInt();
                if (n > 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                s.next(); // Consume invalid input
            }
        }

        return n;
    }

}

abstract class Shape1 implements Comparable<Shape1> {
    protected double len;

    public Shape1(double len) {
        this.len = len;
    }
    // if we don't need it, why we implementing>
    public int compareTo(Shape1 other) {
        return Double.compare(this.len, other.len);
    }

    public abstract double area();

    public abstract String toString();
}

class Circle1 extends Shape1 {
    public Circle1(double radius) {
        super(radius);
    }

    public double area() {
        return Math.PI * len * len;
    }

    public String toString() {
        return "Circle with radius " + len;
    }
}

class Square1 extends Shape1 {
    public Square1(double sideLength) {
        super(sideLength);
    }

    public double area() {
        return len * len;
    }

    public String toString() {
        return "Square with side length " + len;
    }
}


