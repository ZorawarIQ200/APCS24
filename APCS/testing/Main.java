import java.util.ArrayList;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello Codiva");

    // Create an Admin
    Admin admin = new Admin("admin", "admin_password");

    // Create a Course
    Course course = new Course("Java Programming", "CS101");
    course.setTeacher(admin);

    // Initialize the gradebook for the course
    initGradebook(course);
  }

  static void initGradebook(Course c) {
    String[] initNames = {"Adam", "Beth", "Charlie", "Daniel"};
    String[] initCategories = {"HW", "Quiz", "Test", "Final"};
    double[] initWeights = {0.3, 0.15, 0.25, 0.3};
    String[] initLabels = {"HW 1", "HW 2", "HW 3", "HW 4", "Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", "Test 1", "Test 2", "Test 3", "Test 4", "Final"};
    double[][] initGrades = {{90, 91, 80, 77, 78, 89, 87, 81, 92, 81, 80, 91, 81},
      {83, 73, 79, 93, 73, 81, 76, 87, 90, 88, 76, 96, 95},
      {77, 93, 83, 88, 83, 89, 74, 82, 77, 79, 86, 92, 76},
      {79, 91, 93, 78, 84, 92, 82, 84, 86, 79, 87, 72, 74}};

    for (int i = 0; i < initNames.length; i++) {
      Student temp = new Student(initNames[i], "password"); // Pass a default password or set it as needed
      for (int j = 0; j < initCategories.length; j++)
        temp.addCategory(initCategories[j], initWeights[j]);
      for (int j = 0; j < initLabels.length; j++)
        temp.addAssignment(initLabels[j], initGrades[i][j]);
      c.addStudent(temp);
    }
  }
}

class Admin extends User {
  Admin(String username, String password) {
    super(username, password);
  }
}

class Course {
  private String courseName;
  private String courseID;
  private ArrayList<Student> students;
  private Admin teacher;

  Course(String courseName, String courseID) {
    this.courseName = courseName;
    this.courseID = courseID;
    this.students = new ArrayList<>();
  }

  public void setTeacher(Admin t) {
    this.teacher = t;
  }

  public Admin getTeacher() {
    return this.teacher;
  }

  public void addStudent(Student s) {
    this.students.add(s);
  }
}

class Student extends User {
  private ArrayList<String> assignmentLabels;
  private ArrayList<Double> assignmentGrades;
  private double overallGrade;

  Student(String username, String password) {
    super(username, password);
    this.assignmentLabels = new ArrayList<>();
    this.assignmentGrades = new ArrayList<>();
  }

  public void addCategory(String category, double weight) {
    // Implement this method as needed
  }

  public void addAssignment(String label, double grade) {
    // Implement this method as needed
  }
}

abstract class User {
  private String username;
  private String password;

  User(String username, String password) {
    this.username = username;
    this.password = password;
  }
}





import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello Codiva");

        // Create a Course
        Course course = new Course("Java Programming", "CS101");

        // Create a Teacher
        Teacher teacher = new Teacher("teacher", "teacher_password", course);
        course.setTeacher(teacher);

        // Create a Student
        Student student = new Student("student", "student_password", course);
        course.addStudent(student);

        // Initialize the gradebook for the course
        initGradebook(course);

        // Login system
        String inputUsername, inputPassword;
        do {
            System.out.print("Enter username: ");
            inputUsername = scanner.next();
            System.out.print("Enter password: ");
            inputPassword = scanner.next();

            User user = authenticateUser(inputUsername, inputPassword, course);

            if (user != null) {
                user.displayActions();
                String action = scanner.next();
                user.performAction(action);
            } else {
                System.out.println("Invalid username or password. Try again.");
            }
        } while (!inputUsername.equalsIgnoreCase("exit"));
    }

    // Other methods and classes...

    static User authenticateUser(String username, String password, Course course) {
        // Implement authentication logic
        // Check if the username and password are valid for a Student or Teacher
        // Return the corresponding User object or null if authentication fails
        // You may want to use instanceof to determine the type of the user
    }

    // Additional methods and classes...
}

// Other classes...

class Teacher extends User {
    private Course course;

    Teacher(String username, String password, Course course) {
        super(username, password);
        this.course = course;
    }

    @Override
    public void displayActions() {
        // Implement displayActions for Teacher
    }

    @Override
    public void performAction(String action) {
        // Implement performAction for Teacher
    }

    // Additional methods for Teacher...
}

class Student extends User {
    private Course course;

    Student(String username, String password, Course course) {
        super(username, password);
        this.course = course;
    }

    @Override
    public void displayActions() {
        // Implement displayActions for Student
    }

    @Override
    public void performAction(String action) {
        // Implement performAction for Student
    }

    // Additional methods for Student...
}
