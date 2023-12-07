import java.util.*;
import java.io.*;

class Gradebook {
  
  static Scanner s = new Scanner(System.in);
  
  static ArrayList<String> names = new ArrayList<String>();
  static ArrayList<String> categories = new ArrayList<String>();
  static ArrayList<Double> weights = new ArrayList<Double>();
  static ArrayList<String> labels = new ArrayList<String>();
  static ArrayList<ArrayList<Double>> grades = new ArrayList<ArrayList<Double>>();
  static ArrayList<Double> overallGrade;
  
  public static void main(String[] args) {
    
    initializeGradebook();
    
    addStudent();

    overallGrade = updateOverallGrade();
    
    System.out.println("Welcome to gradebook.");
    System.out.println("Here are everyone's grades:");
    
    for (int i = 0; i < names.size(); i++) {
      System.out.print(names.get(i) + ": ");
      System.out.println(grades.get(i).toString());
    }
    
    System.out.println("Everyone's overall grades.");
    for (int i = 0; i < names.size(); i++) {
      System.out.print(names.get(i) + ": ");
      System.out.println(overallGrade.get(i).toString());
    }
    
    System.out.println("labels: " + labels.toString());
    System.out.println("Grades: " + grades.get(grades.size() - 1).toString());
    
  }
  
  public static void addStudent() {
    System.out.println("What is this students name? ");
    String name = s.nextLine();
    names.add(name);
    ArrayList<Double> studentGrades = new ArrayList<Double>();
    for (int i = 0; i < labels.size(); i++) {
      System.out.printf("What is %s's grade for %s? ", name, labels.get(i));
      studentGrades.add(s.nextDouble());
    }
    s.nextLine();
    grades.add(studentGrades);
  }
  
  public static void addCategory() {
    System.out.print("Enter the name of this category: ");
    categories.add(s.nextLine());
    weights.add(0d);
      
    System.out.println("Update the weights of each category: ");
    changeWeights();
  }
  
  public static void changeWeights() {
    while (true) {
      double [] tempWeights = new double [categories.size()];
      for (int i = 0; i < categories.size(); i++) {
        System.out.print("Enter the new weight for the " + categories.get(i) + " category: ");
        tempWeights[i] = s.nextDouble();
      }
      s.nextLine();
      double sum = 0;
      for (double w: tempWeights) sum += w;
      if ((double)Math.round(sum * 1000000d) / 1000000d == 1) {
        for (int i = 0; i < tempWeights.length; i++) weights.set(i, tempWeights[i]);
        break;
      }
      System.out.println("Your weights do not add up to 1. Please re-enter valid weights.");
    }
    System.out.println("Weights have been updated");
  }
  
  public static void addAssignment() {
    System.out.print("Enter the name of this assignment: ");
    labels.add(s.nextLine());
    for (int i = 0; i < names.size(); i++) { 
      System.out.printf("What is %s's grade?", names.get(i));
      grades.get(i).add(s.nextDouble());
    }
  } 
  public static ArrayList<Double> updateOverallGrade() {
    ArrayList<Double> overall = new ArrayList<Double>();
    for (int s = 0; s < names.size(); s++) { 
      overall.add(0d); 
      double [] categoryAvg = new double[categories.size()];
      for (int c = 0; c < categories.size(); c++) { 
        int count = 0;
        int sum = 0;

        for (int a = 0; a < labels.size(); a++) {
          if (labels.get(a).contains(categories.get(c))) {
            sum += grades.get(s).get(a);
            count++;
          }
        }
        categoryAvg[c] = (double)(sum / count);
      } 
      for (int a = 0; a < categoryAvg.length; a++) {
        overall.set(s, overall.get(s) + categoryAvg[a] * weights.get(a));
      }
      
    }  
    return overall;
  }
  public static void initializeGradebook() {
    String [] initNames = {"Adam", "Beth", "Charlie", "Daniel"};
  
   	String [] initCategories = {"HW", "Quiz", "Test", "Final"};
   	double [] initWeights = {.3, .15, .25, .3};
  
   	String [] initLabels = {"HW 1", "HW 2", "HW 3", "HW 4", "Quiz 1", "Quiz 2", "Quiz 3", "Quiz 4", 
                      		 "Test 1", "Test 2", "Test 3", "Test 4", "Final"};
   	double [][] initGrades = {{90, 91, 80, 77, 78, 89, 87, 81, 92, 81, 80, 91, 81},
                               {83, 73, 79, 93, 73, 81, 76, 87, 90, 88, 76, 96, 95},
                               {77, 93, 83, 88, 83, 89, 74, 82, 77, 79, 86, 92, 76},
                               {79, 91, 93, 78, 84, 92, 82, 84, 86, 79, 87, 72, 74}};


    for (String name : initNames) names.add(name);
    
    for (String category : initCategories) categories.add(category);

    for (Double weight : initWeights) weights.add(weight);

    for (String label : initLabels) labels.add(label);
    
    for (int i = 0; i < initGrades.length; i++) {
      grades.add(new ArrayList<Double>());
      for (double grade : initGrades[i]) grades.get(i).add(grade);
    } 
  }
  
}