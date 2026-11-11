import java.util.List;
import java.util.Scanner;

//menu for the stduent managment system. //

public class Main{
    private static final Scanner in = new Scanner(System.in);
    private static final StudentManagementSystem sms = new StudentManagementSystem();
    private static final String FILE = "student.txt";
    
    public static void main(String[] args){
        boolean running = true;
        while (running) {
            printMenu();
            switch (readInt("Choose: ")){
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
            }           
        }
        System.out.println("Goodbye!");
    }
    //interface for options on students grades and other information.
    private static void printMenu(){
        System.out.println("\n=== Student Management Sustem ===");
        System.out.println("1. Add Student         2. Remove student");
        System.out.println("3. Update              4. Add/Update grade");
        System.out.println("5. View student        6. View all (averages)");
        System.out.println("7. Subject high/low    8. Students sorted by grade");
        System.out.println("9. Save to file        10. Load from File");
        System.out.println("0. Exit");
    }
    
    private static void addStudent(){
        String id = readLine("ID: ");
        String name = readLine("Name: ");
        if (id.isEmpty()  ||  name.isEmpty()) { System.out.println("ID and name are required. "); return;}
        boolean grad = readLine("Graduate student? (y/n): ").equalsIgnoreCase("y");
        Student s = grad ? new GraduateStudent(new String[]{id, name}): new Student(new String[]{id, name});
        System.out.println(sms.addStudent(s) ? "Student added." : "ID already exists.");
    }
    private static void removeStudent(){
        System.out.println(sms.removeStudent(readLine("ID:")) ? "Removed." :  "Student not found.");
    }
    private static void updateName(){
        Student s = sms.getStudent(readLine("ID: "));
        if (s == null) { System.out.println("Student not found. "); return; }
        String name = readLine("New name: ");
        if (!name.isEmpty()) { s.setName(name); System.out.println("Updated.");
    }
    }  
    private static void addGrade() {
        Student s = sms.getStudent(readLine("ID: "));
        if (s == null) { System.out.println("Student not found. "); return;}
        String subject = readLine("Subject: ");
        if (subject.isEmpty()) { System.out.println("Subject required. "); return;}
        try{
            s.setGrade(subject, readDouble("Grade (0-100): "));
            System.out.println("Grade Saved.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void viewStudent(){
        Student s = sms.getStudent(readLine("ID: "));
        System.out.println(s == null ? "Student not found." : s);
    }
    
    private static void viewAll(){
        if (sms.getAll().isEmpty()) { System.out.println("No Students. "); return;}
        for (Student s : sms.getAll())
            System.out.printf("%s - %s: avg %.2f (%s)%n", s.getID(), s.getName(), s.getAverage(), s.getLetterGrade());
    }
    private static void subjectReport(){
        System.out.println(sms.highLowForSubject(readLine("Subject: ")));
    }
    
    private static void rankingReport(){
        List<Student> list = sms.sortedByAverage();
        int rank = 1;
        for (Student s : list)
            System.out.printf("%d. %s - %.2f (%s) %n", rank++, s.getName(), s.getAverage(), s.getLetterGrade());
    }
        if(list.isEmpty()) { System.out.println("No students. ");
    }
    //     input helpers and excpetion handling in case of bad input data
    
    private static String readLine(String prompt) {System.out.println(prompt); return in.nextLine().trim();}
    private static int readInt(String prompt){
        try { return Integer.parseInt(readLine(prompt));}
        catch (NumberFormatException e) {return -1;}
    }
    
    private static double readDouble(String prompt){
        while (true) {
            try { return Double.parseDouble(readLine(prompt));}
            catch (NumberFormatException e) {System.out.println("Please eneter a number. "); }
        }
    }
  }
