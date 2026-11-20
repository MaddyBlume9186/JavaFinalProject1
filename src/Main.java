import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//menu for the student management system. //

public class Main{
    private static final Scanner in = new Scanner(System.in);
    private static final StudentManagementSystem sms = new StudentManagementSystem();
    private static final String FILE = "src/Student.txt";

    // interface choices for user 
    public static void main(String[] args) throws IOException, InvalidInput {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose:  ");
            switch (choice) {
                case 1: addStudent(); break;
                case 2: removeStudent(); break;
                case 3: updateName(); break;
                case 4: addGrade(); break;
                case 5: viewStudent(); break;
                case 6: viewAll(); break;
                case 7: subjectReport(); break;
                case 8: rankingReport(); break;
                case 9: System.out.println(sms.save(FILE)); break;
                case 10: System.out.println(sms.load(FILE)); break;
                case 0: running = false; break;

                default:
                    System.out.println("Invalid choice. Please try again with a number of 0-10.");
            }           
        }
        System.out.println("Goodbye!");
    }
    //interface for options on students grades and other information.
    private static void printMenu(){
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student         2. Remove student");
        System.out.println("3. Update              4. Add grade");
        System.out.println("5. View student        6. View all (averages)");
        System.out.println("7. Subject high/low    8. Students sorted by grade");
        System.out.println("9. Save to file        10. Load from File");
        System.out.println("0. Exit");
    }

    // method to receive ID and name along with gradType to add student and checks success through SMSe
    private static void addStudent() throws IOException {
        String id = readLine("ID: ");
        String name = readLine("Name: ");
        if (id.isEmpty()  ||  name.isEmpty()) {
            System.out.println("ID and name are required.");
            return;
    }
        String gradType = readLine("Graduate student? (y/n): ");
        Subject s;
        if (gradType.equalsIgnoreCase("y")) {
            s = new Subject(new String[]{id, name, "Graduate", null,"0", "0", "0", "0", "0"});
        } else {
            s =  new Subject(new String[]{id, name, "Undergraduate", null, "0", "0", "0", "0", "0"});
        }

        boolean success = sms.addStudent(s);
            if (success) {
                System.out.println("Student added.");
            } else {
                System.out.println("ID already exists.");
            }
        }
    // method to call SMS to remove student based on received ID
    private static void removeStudent(){
        String id = readLine("ID: ");
        boolean success = sms.removeStudent(id);
        if (success) {
            System.out.println("Removed.");
                } else {
            System.out.println("Student not found.");
        }
    }

    // method to call SMS to update name based on received ID
    private static void updateName(){
        Student s = sms.checkStudent(readLine("ID: "));
        if (s == null) {
            System.out.println("Student not found.");
            return;
    }
        String name = readLine("New name: ");
        if (!name.isEmpty()) {
            s.setName(name);
            System.out.println("Updated.");
        }
    }  

    // method to add grade for student based on received ID and received subject
    private static void addGrade() throws InvalidInput{
        ArrayList <Subject> s = sms.checkStudentAll(readLine("ID: "));
        if (s == null) { 
            System.out.println("Student not found. "); 
            return;
        }
        String subject = readLine("Subject: ");
        if (subject.isEmpty()) { 
            System.out.println("Subject required. "); 
            return;
        }
        try{
            int grade = readInt("Grade (0-100): ");
            for (Subject sub : s){
                if(sub.getSubject().equalsIgnoreCase(subject)){
                    sub.setTestScore(grade);
                    sms.addGrade(sub, grade);
                }
            }
            System.out.println("Grade Saved.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void viewStudent(){
        ArrayList<Subject> s = sms.checkStudentAll(readLine("ID: "));
        if (s == null) {
        System.out.println("Student not found.");
        } else {
            for(Subject student : s){
                System.out.printf("%s - %s: Average Grade %.2f (%s)\n", student.getName(), student.getSubject(), student.getAverage(), student.getLetterGrade());
            }
        }
    }
    
    private static void viewAll(){
        Subject[] allStudents = sms.getAll();
        if (allStudents == null || allStudents.length == 0) {
            System.out.println("No Students.");
            return;
        }
        for (Subject s : allStudents) {
            if (s != null) {
            System.out.printf("%s - %s: %s: Average Grade %.2f (%s)\n", s.getID(), s.getName(), s.getSubject(), s.getAverage(), s.getLetterGrade());
        }
    }
    }
    
    private static void subjectReport(){
        String subject = readLine("Subject: ");
        sms.highLowForSubject(subject);
    }
    
    private static void rankingReport(){
        List<Subject> list = sms.sortedByAverage();
        if (list.isEmpty()) {
            System.out.println("No students.");
            return;
    }
        int rank = 1;
        for (Subject s : list) {
            System.out.printf("%d. %s - %s: %.2f%% (%s)\n", rank++, s.getName(), s.getSubject(),s.getAverage(), s.getLetterGrade());
     }
    }
    
    private static String readLine(String prompt) {
        System.out.println(prompt); 
        return in.nextLine().trim();
    }
    private static int readInt(String prompt){
        try {
            return Integer.parseInt(readLine(prompt));
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static double readDouble(String prompt){
        while (true) {
            try { 
                return Double.parseDouble(readLine(prompt));
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a number."); 
            }
        }
    }
  }
