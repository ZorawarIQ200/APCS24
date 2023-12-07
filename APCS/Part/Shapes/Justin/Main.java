import java.util.*;

class Main {
  public static ArrayList<Shape> shapes = new ArrayList<Shape>();
  public static Scanner s = new Scanner(System.in);
  public static void main(String[] args) {
    
    
    System.out.print("Please input how many shape would you like to randomize (minimum 2 max 26): ");
    int length = dataValid(s.nextLine());
    
    while (true) {
    shapes = new ArrayList<Shape>();
    randArray(length);
    int [] area = new int [length];
      
    System.out.println("Unsorted ArrayList of shapes: ");
    printArrayList(shapes);
    
    inheritRL(shapes);
    sortArray(shapes);
    System.out.println("After sorting (comparing lengths/radii): ");
    printArrayList(shapes);
    
    inheritArea(shapes);
    sortArray(shapes);
    System.out.println("After sorting (comparing areas): ");
    printArrayList(shapes);
    
    rearrangeCirc(shapes);
    System.out.println();
    System.out.println("After sorting (squares before circles): ");
    printArrayList(shapes);
    
    rearrangeSqua(shapes);
    System.out.println();
    System.out.println("After sorting (circles before squares): ");
    printArrayList(shapes);
      
    System.out.println("(Press enter to randomize shapes again)");
      s.nextLine();
    }
  }
  
  private static void rearrangeCirc (ArrayList<Shape> arr) {
    int j = 0;
    for (int i = 0; i < arr.size(); i++)
      if (arr.get(j) instanceof Circle) arr.add(arr.remove(j));
      else j++;
  }
  
  private static void rearrangeSqua (ArrayList<Shape> arr) {
    int j = 0;
    for (int i = 0; i < arr.size(); i++)
      if (arr.get(j) instanceof Square) arr.add(arr.remove(j));
      else j++;
  }
  
  private static void inheritRL (ArrayList<Shape> arr) {
    for (Shape shape : arr) shape.inheritRL();
  }
  
  private static void inheritArea (ArrayList<Shape> arr) {
    for (Shape shape : arr) shape.inheritArea();
  }
  
  private static void sortArray (ArrayList<Shape> arr) {
    for (int i = 0; i < arr.size(); i++)
      for (int j = i; j < arr.size(); j++) 
        if (arr.get(i).compareTo(arr.get(j)) == 0) {
          Shape temp = arr.get(i);
          arr.set(i, arr.get(j));
          arr.set(j, temp);
        }
  }
  private static void printArrayList (ArrayList<Shape> arr) {
    for (Shape shape : arr) System.out.printf("%s\n", shape.toString());
    System.out.println();
  }
  
  private static void randArray (int size) {
    Random r = new Random();
    for (int i = 0; i < size; i++)
    if (r.nextInt(10) % 2 == 0) shapes.add(new Circle("Circle "+Character.toString('A'+i), r.nextInt(10)+1));
	else shapes.add(new Square("Square "+Character.toString('A'+i), r.nextInt(20)+1));
  }
  
  private static int dataValid (String inputStr) {
    while (true) 
      try {
        int inputInt = Integer.parseInt(inputStr);
        if (inputInt > 1 && inputInt <= 26)return inputInt;
        throw new Exception();
      }
    catch (Exception e) {
      System.out.print("Input error, try again: ");
      inputStr = s.nextLine();
    }
  }
}


abstract class Shape implements Comparable<Shape> {
  public String name;
  public double l, n, area;//n is compareVariable; l is radius / length
  Shape (String name, double l, double a) {super(); this.name = name; this.l = l; this.area = a;}
  
  public int compareTo(Shape s) {
    if (Math.abs(this.n - s.n) < .00001) return 0;
    return this.n - s.n > 0 ? 0 : 1;
  }
  public void inheritRL () {this.n = this.l;}
  public void inheritArea () {this.n = this.area;}
  public int getArea () {return (int)area;}
  public String toString () {return String.format("%s (%.2f, %.2f)", this.name, this.l, this.area);}
}

class Circle extends Shape {
  Circle (String name, double radius) {super(name, radius, radius*radius*Math.PI);}
}

class Square extends Shape {
  Square (String name, double length) {super(name, length, length*length);}
}