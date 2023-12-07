
class Inheritance {
  public static void main(String[] args) {
    // Create instances of the classes and test their methods

    // Test Parallelogram
    Parallelogram parallelogram = new Parallelogram(60, 120, 6, 4);
    System.out.println("Parallelogram Area: " + parallelogram.getArea());
    parallelogram.printAngles();

    // Test Rectangle
    Rectangle rectangle = new Rectangle(8, 6);
    System.out.println("Rectangle Area: " + rectangle.getArea());
    rectangle.printAngles();

    // Test Square
    Square square = new Square(5);
    System.out.println("Square Area: " + square.getArea());
    square.printAngles();

    // Test Rhombus
    Rhombus rhombus = new Rhombus(5, 60);
    System.out.println("Rhombus Area: " + rhombus.getArea());
    rhombus.printAngles();

    // Test Kite
    Kite kite = new Kite(4, 6, 4, 6);
    System.out.println("Kite Sides: " + kite.side1 + ", " + kite.side2 + ", " + kite.side3 + ", " + kite.side4);
  }
}

class Quadrilateral {
  // any quadrilateral, you don't need to worry about this class
  static int nSides = 4;

  Quadrilateral() {}
}

class Parallelogram extends Quadrilateral {
  // quadrilateral with opposite sides and angles being the same
  double angle1;
  double angle2;
  double base;
  double height;
  double area;

  Parallelogram(double angle1, double angle2, double base, double height) {
    super(); // Calls the constructor of the parent class
    this.angle1 = angle1;
    this.angle2 = angle2;
    this.base = base;
    this.height = height;
    this.area = base * height;
  }

  double getArea() {
    return this.area;
  }

  void printAngles() {
    System.out.printf("Angle 1: %f%nAngle 2: %f%n", this.angle1, this.angle2);
  }
}

class Rectangle extends Parallelogram {
  Rectangle(double base, double height) {
    super(90, 90, base, height); // Calls the constructor of the parent class with right angles
  }
}

class Square extends Rectangle {
  int length;

  Square(int length) {
    super(length, length); // Call the constructor of the parent class with equal sides
    this.length = length;
  }
}

class Rhombus extends Parallelogram {
  Rhombus(double side, double angle) {
    super(angle, 180 - angle, side, side);
  }
}

class Kite extends Quadrilateral {
  double side1;
  double side2;
  double side3;
  double side4;

  Kite(double side1, double side2, double side3, double side4) {
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
    this.side4 = side4;
  }
}
