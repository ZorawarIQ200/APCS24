import java.util.*;

public class GB {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello Codiva");
    
        // Create a Course
        Course course = new Course("Java Programming", "CS50");
    
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
    
            // Pass the 'teacher' parameter to the authenticateUser method
            User user = authenticateUser(inputUsername, inputPassword, course, teacher);
    
            if (user != null) {
                user.displayActions();
                String action = scanner.next();
                user.performAction(action);
            } else {
                System.out.println("Invalid username or password. Try again.");
            }
        } while (!inputUsername.equalsIgnoreCase("exit"));
    }
    

    private static User authenticateUser(String username, String password, Course course, Teacher teacher) {
        if (username.equals(teacher.username) && password.equals(teacher.password)) {
            return course.getTeacher();
        } else if (username.equals("student") && password.equals("student_password")) {
            return course.getStudents().get(0);
        } else {
            return null;
        }
    }
    

    private static void initGradebook(Course c) {
        c.addAssignmentCategory("HW", 0.3);
        c.addAssignmentCategory("Quiz", 0.15);
        c.addAssignmentCategory("Test", 0.25);
        c.addAssignmentCategory("Final", 0.3);

        String[] assignmentLabels = {"HW 1", "HW 2", "Quiz 1", "Test 1", "Final Exam"};
        double[] assignmentGrades = {90, 91, 85, 75, 88};

        c.addAssignments(assignmentLabels, assignmentGrades);
    }
}


abstract class User {
    public String username;
    public String password;
    public Course course;

    User(String username, String password, Course course) {
        this.username = username;
        this.password = password;
        this.course = course;
    }

    public abstract void displayActions();

    public abstract void performAction(String action);
}

class Student extends User {
    ArrayList<String> assignmentLabels;
    ArrayList<Double> assignmentGrades;
    double overallGrade;
    public Scanner scanner = new Scanner(System.in);

    Student(String username, String password, Course course) {
        super(username, password, course);
        this.assignmentLabels = new ArrayList<>();
        this.assignmentGrades = new ArrayList<>();
        this.overallGrade = 0.0;
    }

    @Override
    public void displayActions() {
        System.out.println("1. View all grades");
        System.out.println("2. View grades by category");
        System.out.println("3. View overall grade");
        System.out.println("4. Exit");
    }

    @Override
    public void performAction(String action) {
        
        do {
            switch (action.toLowerCase()) {
                case "1":
                    viewAllGrades();
                    break;
                case "2":
                    viewGradesByCategory();
                    break;
                case "3":
                    viewOverallGrade();
                    break;
                default:
                    System.out.println("Invalid action. Try again.");
            }
        } while (!inputUsername.equalsIgnoreCase("exit"));

        
    }

    private void viewAllGrades() {
        System.out.println("All Grades:");
        for (int i = 0; i < assignmentLabels.size(); i++) {
            System.out.println(assignmentLabels.get(i) + ": " + assignmentGrades.get(i));
        }
    }

    private void viewGradesByCategory() {

        System.out.println("Available Categories:");
        for (String category : course.getAssignmentCategories()) {
            System.out.println(category);
        }

        System.out.print("Enter the category to view grades: ");
        
        String selectedCategory = scanner.next();

        System.out.println("Grades for Category '" + selectedCategory + "':");

        for (int i = 0; i < assignmentLabels.size(); i++) {
            if (getCategoryForAssignment(assignmentLabels.get(i)).equals(selectedCategory)) {
                System.out.println(assignmentLabels.get(i) + ": " + assignmentGrades.get(i));
            }
        }
    }

    String getCategoryForAssignment(String assignmentLabel) {
        // Assuming the assignment label follows the format "{Category} {Number}"
        String[] parts = assignmentLabel.split(" ");
        if (parts.length > 1) {
            return parts[0];
        } else {
            // Default category if no category is specified in the label
            return "Uncategorized";
        }
    }

    private void viewOverallGrade() {
        System.out.println("Overall Grade: " + overallGrade);
    }
}

class Teacher extends User {
    public Scanner scanner = new Scanner(System.in);
    Teacher(String username, String password, Course course) {
        super(username, password, course);
    }

    @Override
    public void displayActions() {
        System.out.println("1. View all students' grades");
        System.out.println("2. View all students' grades by category");
        System.out.println("3. View all students' overall grades");
        System.out.println("4. View a student's grades");
        System.out.println("5. View a student's grades by category");
        System.out.println("6. Add assignment category");
        System.out.println("7. Change category weights");
        System.out.println("8. Add a student to the course");
        System.out.println("9. Drop a student from the course");
        System.out.println("10. Add a new assignment into the course");
        System.out.println("11. Add a new assignment into a new student");
        System.out.println("12. Exit");
    }

    @Override
    public void performAction(String action) {
        switch (action.toLowerCase()) {
            case "1":
                viewAllStudentsGrades();
                break;
            case "2":
                viewAllStudentsGradesByCategory();
                break;
            case "3":
                viewAllStudentsOverallGrades();
                break;
            case "4":
                viewStudentGrades();
                break;
            case "5":
                viewStudentGradesByCategory();
                break;
            case "6":
                addAssignmentCategory();
                break;
            case "7":
                changeCategoryWeights();
                break;
            case "8":
                addStudentToCourse();
                break;
            case "9":
                dropStudentFromCourse();
                break;
            case "10":
                addNewAssignmentToCourse();
                break;
            case "11":
                addNewAssignmentToStudent();
                break;
            case "12":
                System.exit(0); // Exiting the program
                break;
            default:
                System.out.println("Invalid action. Try again.");
        }
    }

    private void viewAllStudentsGrades() {
        System.out.println("Viewing all students' grades");
        for (Student student : course.getStudents()) {
            System.out.println("Student: " + student.username);
            for (int i = 0; i < student.assignmentLabels.size(); i++) {
                System.out.println(student.assignmentLabels.get(i) + ": " + student.assignmentGrades.get(i));
            }
            System.out.println();
        }
    }

    private void viewAllStudentsGradesByCategory() {
        System.out.println("Viewing all students' grades by category");
        for (String category : course.getAssignmentCategories()) {
            System.out.println("Category: " + category);
            for (Student student : course.getStudents()) {
                System.out.println("Student: " + student.username);
                for (int i = 0; i < student.assignmentLabels.size(); i++) {
                    if (student.getCategoryForAssignment(student.assignmentLabels.get(i)).equals(category)) {
                        System.out.println(student.assignmentLabels.get(i) + ": " + student.assignmentGrades.get(i));
                    }
                }
                System.out.println();
            }
        }
    }

    private void viewAllStudentsOverallGrades() {
        System.out.println("Viewing all students' overall grades");
        for (Student student : course.getStudents()) {
            System.out.println("Student: " + student.username);
            System.out.println("Overall Grade: " + student.overallGrade);
            System.out.println();
        }
    }

    private void viewStudentGrades() {
        System.out.println("Viewing a specific student's grades");
        System.out.print("Enter the student's username: ");
        String studentUsername = scanner.next();

        Student selectedStudent = findStudentByUsername(studentUsername);

        if (selectedStudent != null) {
            System.out.println("Student: " + selectedStudent.username);
            for (int i = 0; i < selectedStudent.assignmentLabels.size(); i++) {
                System.out.println(selectedStudent.assignmentLabels.get(i) + ": " + selectedStudent.assignmentGrades.get(i));
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private void viewStudentGradesByCategory() {
        System.out.println("Viewing a specific student's grades by category");
        System.out.print("Enter the student's username: ");
        String studentUsername = scanner.next();

        Student selectedStudent = findStudentByUsername(studentUsername);

        if (selectedStudent != null) {
            System.out.println("Student: " + selectedStudent.username);
            System.out.println("Available Categories:");
            for (String category : course.getAssignmentCategories()) {
                System.out.println(category);
            }

            System.out.print("Enter the category to view grades: ");
            String selectedCategory = scanner.next();

            System.out.println("Grades for Category '" + selectedCategory + "':");

            for (int i = 0; i < selectedStudent.assignmentLabels.size(); i++) {
                if (selectedStudent.getCategoryForAssignment(selectedStudent.assignmentLabels.get(i)).equals(selectedCategory)) {
                    System.out.println(selectedStudent.assignmentLabels.get(i) + ": " + selectedStudent.assignmentGrades.get(i));
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private void addAssignmentCategory() {
        System.out.println("Adding a new assignment category");
        System.out.print("Enter the new category: ");
        String newCategory = scanner.next();

        System.out.print("Enter the weight for the category: ");
        double weight = scanner.nextDouble();

        course.addAssignmentCategory(newCategory, weight);
        System.out.println("Assignment category added successfully.");
    }

    private void changeCategoryWeights() {
        System.out.println("Changing category weights");
        for (String category : course.getAssignmentCategories()) {
            System.out.print("Enter the new weight for category '" + category + "': ");
            // Scanner scanner;
            double newWeight = scanner.nextDouble();

            // Update the category weight in the course
            int index = course.getAssignmentCategories().indexOf(category);
            course.categoryWeights.set(index, newWeight);
        }
        System.out.println("Category weights updated successfully.");
    }

    private void addStudentToCourse() {
        System.out.println("Adding a new student to the course");
        System.out.print("Enter the new student's username: ");
        String newStudentUsername = scanner.next();

        // Check if the username is already taken
        boolean usernameTaken = isUsernameTaken(newStudentUsername);

        if (usernameTaken) {
            System.out.println("Username already taken. Please choose a different username.");
        } else {
            // Create a new student and add them to the course
            Student newStudent = new Student(newStudentUsername, "default_password", course);
            course.addStudent(newStudent);
            System.out.println("Student added to the course successfully.");
        }
    }

    private void dropStudentFromCourse() {
        System.out.println("Dropping a student from the course");
        System.out.print("Enter the student's username to drop: ");
        String usernameToDrop = scanner.next();

        // Find and remove the student from the course
        Student studentToRemove = findStudentByUsername(usernameToDrop);

        if (studentToRemove != null) {
            course.getStudents().remove(studentToRemove);
            System.out.println("Student dropped from the course successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void addNewAssignmentToCourse() {

        System.out.println("Adding a new assignment to the course");
        System.out.print("Enter the assignment label: ");
        String label = scanner.next();
        System.out.print("Enter the assignment grade: ");
        double grade = scanner.nextDouble();

        course.addAssignment(label, grade);
        System.out.println("Assignment added to the course successfully.");
    }

    private void addNewAssignmentToStudent() {
        System.out.println("Adding a new assignment to a new student");
        System.out.print("Enter the student's username: ");
        String studentUsername = scanner.next();

        Student selectedStudent = findStudentByUsername(studentUsername);

        if (selectedStudent != null) {
            System.out.print("Enter the assignment label: ");
            String label = scanner.next();
            System.out.print("Enter the assignment grade: ");
            double grade = scanner.nextDouble();

            selectedStudent.assignmentLabels.add(label);
            selectedStudent.assignmentGrades.add(grade);

            System.out.println("Assignment added to the student successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudentByUsername(String username) {
        for (Student student : course.getStudents()) {
            if (student.username.equals(username)) {
                return student;
            }
        }
        return null;
    }

    private boolean isUsernameTaken(String username) {
        for (User user : course.getUsers()) {
            if (user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }
}

class Course {
    private String courseName;
    private String courseID;
    private ArrayList<Student> students;
    private Teacher teacher;
    private ArrayList<String> assignmentLabels;
    private ArrayList<Double> assignmentGrades;
    private ArrayList<String> assignmentCategories;
    public ArrayList<Double> categoryWeights;

    Course(String courseName, String courseID) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.students = new ArrayList<>();
        this.assignmentLabels = new ArrayList<>();
        this.assignmentGrades = new ArrayList<>();
        this.assignmentCategories = new ArrayList<>();
        this.categoryWeights = new ArrayList<>();
    }

    public void setTeacher(Teacher t) {
        this.teacher = t;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void addStudent(Student s) {
        this.students.add(s);
    }

    public void addAssignmentCategory(String category, double weight) {
        this.assignmentCategories.add(category);
        this.categoryWeights.add(weight);
    }

    public void addAssignment(String label, double grade) {
    this.assignmentLabels.add(label);
    this.assignmentGrades.add(grade);

    // Update each student's assignmentLists
    for (Student student : students) {
            student.assignmentLabels.add(label);
            student.assignmentGrades.add(grade);
        }
    }

    public void addAssignments(String[] labels, double[] grades) {
        if (labels.length != grades.length) {
            throw new IllegalArgumentException("Number of labels and grades must be the same");
        }

        for (int i = 0; i < labels.length; i++) {
            addAssignment(labels[i], grades[i]);
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<String> getAssignmentCategories() {
        return assignmentCategories;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(teacher);
        users.addAll(students);
        return users;
    }
}

