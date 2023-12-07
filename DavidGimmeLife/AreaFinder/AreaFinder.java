class AreaFinder {
    public static void main(String[] args) {
      System.out.printf("The area of image 1 is %.3f%n", imageOne());
      System.out.printf("The area of image 2 is %.3f%n", imageTwo());
      System.out.printf("The area of image 3 is %.3f%n", imageThree());
    }
  
    public static double imageOne() {
      // add the 2 cylinder thingies on top w/ 4 half circles & 2 Rectangles
      Circle topLeft = new Circle(1, false, true);
      Circle topRight = new Circle(1, false, true);
      Circle bottomLeft = new Circle(1, false, true);
      Circle bottomRight = new Circle(1, false, true);
  
      // add the big middle rect & subtract 2 triangles
      Rectangle topRectangle = new Rectangle(2, 10, false);
      Rectangle bottomRectangle = new Rectangle(2, 10, false);
      Rectangle middleRectangle = new Rectangle(14, 3, false);
      Triangle leftTri = new Triangle(3, 3, true);
      Triangle rightTri = new Triangle(3, 3, true);
  
      // add them up
      return (topLeft.getArea() + topRight.getArea() + bottomLeft.getArea() + bottomRight.getArea() + topRectangle.getArea() + bottomRectangle.getArea() + middleRectangle.getArea() + leftTri.getArea() + rightTri.getArea());
    }
  
    public static double imageTwo() {
      // Letter A
      Triangle triangle1 = new Triangle(1, 1, true);
      Triangle triangle2 = new Triangle(1, 1, true);
      Rectangle rect1 = new Rectangle(1, 1, true);
      Rectangle rect2 = new Rectangle(6, 3, false);
  
      Triangle triangle3 = new Triangle(1, 1, true); // I so smart=
      Rectangle rect3 = new Rectangle(1, 1, true);
      Rectangle rect4 = new Rectangle(1, 3, true);
  
      // letter P
      // add da 3x7 rect, subtract the middle triangle & the
      Rectangle rect5 = new Rectangle(7, 3, false);
      Triangle triangle4 = new Triangle(1, 1, true);
      Triangle triangle5 = new Triangle(1, 1, true);
      Rectangle rect6 = new Rectangle(2, 3, true);
      Triangle triangle6 = new Triangle(1, 1, true);
      Triangle triangle7 = new Triangle(2, 1, true);
  
      // Letter C
      Rectangle rect7 = new Rectangle(1, 3, false);
      Triangle triangle8 = new Triangle(1, 1, true);
      Triangle triangle9 = new Triangle(1, 1, true);
  
      Rectangle rect8 = new Rectangle(1, 3, false);
      Triangle triangle10 = new Triangle(1, 1, true);
      Triangle triangle11 = new Triangle(1, 1, true);
  
      Rectangle rect9 = new Rectangle(5, 3, false);
  
      Triangle triangle12 = new Triangle(1, 1, true);
      Triangle triangle13 = new Triangle(1, 1, true);
      Rectangle rect10 = new Rectangle(2, 3, false);
  
      // Letter S
      Triangle triangle14 = new Triangle(1, 3, true);
      Triangle triangle15 = new Triangle(1, 3, true);
      Rectangle rect11 = new Rectangle(2, 3, false);
      Rectangle rect12 = new Rectangle(2, 3, false);
  
      Triangle triangle16 = new Triangle(1, 1.5, true);
      Triangle triangle17 = new Triangle(1, 1.5, true);
  
      return triangle1.getArea() + triangle2.getArea() + triangle3.getArea() + triangle4.getArea() + triangle5.getArea() + triangle6.getArea() + triangle7.getArea() + triangle8.getArea() + triangle9.getArea() + triangle10.getArea() + triangle11.getArea() + triangle12.getArea() + triangle13.getArea() + triangle14.getArea() + triangle15.getArea() + triangle16.getArea() + triangle17.getArea() + rect1.getArea() + rect2.getArea() + rect3.getArea() + rect4.getArea() + rect5.getArea() + rect6.getArea() + rect7.getArea() + rect8.getArea() + rect9.getArea() + rect10.getArea() + rect11.getArea() + rect12.getArea();
    }
  
    public static double imageThree() {
      //roof
      Triangle triangle1 = new Triangle(4, 10, false);
      Circle circle1 = new Circle(0.5, false, false);
      Rectangle rect1 = new Rectangle(1, 1, true);
  
      Triangle triangle2 = new Triangle(1, 1, false);
      Rectangle rect2 = new Rectangle(1, 1.5, false);
      //under the roof
      Rectangle rect3 = new Rectangle(8, 7, false);
      Rectangle rect4 = new Rectangle(2, 3, true);
      Rectangle rect5 = new Rectangle(2, 2, true);
      Rectangle rect6 = new Rectangle(2, 2, true);
      Circle circle5 = new Circle(1.0/6.0, false, false);
  
      // Smoke? idk
      Circle circle2 = new Circle(1, false, false);
      Circle circle3 = new Circle(0.5, false, false);
      Circle circle4 = new Circle(0.75, false, false);
  
      return triangle1.getArea() + circle1.getArea() + circle2.getArea() + circle3.getArea() + circle4.getArea() + circle5.getArea() + rect1.getArea() + rect2.getArea() + rect3.getArea() + rect4.getArea() + rect5.getArea() + rect6.getArea() + triangle2.getArea();
    }
  }
  
  class Circle {
    private double area;
    private double radius;
    private boolean isNegativeSpace;
    private boolean halfCircle;
  
    public Circle(double radius, boolean isNegative, boolean halfCircle) {
      this.radius = radius;
      this.halfCircle = halfCircle;
  
      this.area = this.halfCircle ? Math.PI * this.radius * this.radius / 2 : Math.PI * this.radius * this.radius;
      this.isNegativeSpace = isNegative;
    }
  
    public double getArea() {
      return isNegativeSpace ? -this.area : this.area;
    }
  }
  
  class Trapezoid {
    private double area;
    private double base1;
    private double base2;
    private double height;
    private boolean isNegativeSpace;
  
    public Trapezoid(double base1, double base2, double height, boolean isNegative) {
      this.base1 = base1;
      this.base2 = base2;
      this.height = height;
      this.area = this.height * (this.base1 + this.base2) / 2;
      this.isNegativeSpace = isNegative;
    }
  
    public double getArea() {
      return isNegativeSpace ? -this.area : this.area;
    }
  }
  
  class Rectangle {
    private double area;
    private double len1;
    private double len2;
    private boolean isNegativeSpace;
  
    public Rectangle(double len1, double len2, boolean isNegative) {
      this.len1 = len1;
      this.len2 = len2;
      this.area = this.len1 * this.len2;
      this.isNegativeSpace = isNegative;
    }
  
    public double getArea() {
      return isNegativeSpace ? -this.area : this.area;
    }
  }
  
  class Triangle {
    private double area;
    private double alt;
    private double base;
    private boolean isNegativeSpace;
  
    public Triangle(double alt, double base, boolean isNegative) {
      this.alt = alt;
      this.base = base;
      this.area = 0.5 * this.alt * this.base;
      this.isNegativeSpace = isNegative;
    }
  
    public double getArea() {
      return isNegativeSpace ? -this.area : this.area;
    }
  }
  