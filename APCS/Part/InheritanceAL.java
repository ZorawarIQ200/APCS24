import java.util.Scanner;

class InheritanceAL {
    static double angleSum = 0;
    static double angleCount = 0;
    static Scanner s = new Scanner(System.in);
    static Int sideLen = new Array[3];
    public static void main(String[] args) {
        

        System.out.println("Enter the angles to determine the type of quadrilateral.");
        double angle1 = DataVala(s, "Angle 1: ");
        double angle2 = DataVala(s, "Angle 2: ");
        double angle3 = DataVala(s, "Angle 3: ");
        double angle4 = (360-(angle1+angle2+angle3));
        System.out.println("Angle 4: " + angle4);

        // Determine the type of quadrilateral
        String quadrilateralType = determineQuadrilateralType(angle1, angle2, angle3, angle4);
        if (quadrilateralType == "Square") {
            
        }
        System.out.println("You have created a " + quadrilateralType);

        s.close();
    }

    public static double DataValaSide(Scanner s, String prompt) {
        double angle = 0;
        boolean validInput = false;
        angleCount++;
        while (!validInput) {
            System.out.print(prompt);
            if (s.hasNextDouble()) {
                angle = s.nextDouble();
                    if (angle > 0 && angle < 360) {
                        if (((angleSum+angle) <= 360 && angleCount == 4) || (angleSum+angle) < 360 && angleCount <= 4) {
                            angleSum += angle;
                            validInput = true;
                        } else System.out.println("Please input a number "+ (360-angleSum) +", or smaller.");

                    } else {
                        System.out.println("Please enter a valid angle in range (0, 360).");
                    }
            } else {
                System.out.println("Invalid input. Please enter a valid angle.");
                s.next(); // Consume invalid input
            }
        }
        
        return angle;
    }

    public static double DataVala(Scanner s, String prompt) {
        double angle = 0;
        boolean validInput = false;
        angleCount++;
        while (!validInput) {
            System.out.print(prompt);
            if (s.hasNextDouble()) {
                angle = s.nextDouble();
                    if (angle > 0 && angle < 360) {
                        if (((angleSum+angle) <= 360 && angleCount == 4) || (angleSum+angle) < 360 && angleCount <= 4) {
                            angleSum += angle;
                            validInput = true;
                        } else System.out.println("Please input a number "+ (360-angleSum) +", or smaller.");

                    } else {
                        System.out.println("Please enter a valid angle in range (0, 360).");
                    }
            } else {
                System.out.println("Invalid input. Please enter a valid angle.");
                s.next(); // Consume invalid input
            }
        }
        
        return angle;
    }

    public static String determineQuadrilateralType(double angle1, double angle2, double angle3, double angle4) {
        if (angle1 == angle2 && angle2 == angle3 && angle3 == angle4) {
            return "Square";
        } else if ((angle1 == angle2 && angle3 == angle4) || (angle1 == angle3 && angle2 == angle4)) {
            return "Rectangle";
        } else if (angle1 == angle3 && angle2 == angle4) {
            return "Parallelogram";
        } else if (angle1 == angle2 && angle2 + angle3 + angle4 == 180) {
            return "Rhombus";
        } else if ((angle1 == angle2 && angle3 == angle4) || (angle1 == angle3 && angle2 == angle4) || angle1 == angle4 && angle2 == angle3) {
            return "Kite";
        } else {
            return "Quadrilateral";
        }
    }
}

class Quadrilateral {
    static int nSides = 4;

    Quadrilateral() {}
}

class Parallelogram extends Quadrilateral {
    double angle1;
    double angle2;
    double base;
    double height;
    double area;

    Parallelogram(double angle1, double angle2, double base, double height) {
        super();
        this.angle1 = angle1;
        this.angle2 = angle2;
        this.base = base;
        this.height = height;
        this.area = base * height;
    }

    double getArea() {
        return this.area;
    }
}

class Rectangle extends Parallelogram {
    Rectangle(double base, double height) {
        super(90, 90, base, height);
    }
}

class Square extends Rectangle {
    int length;

    Square(int length) {
        super(length, length);
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
