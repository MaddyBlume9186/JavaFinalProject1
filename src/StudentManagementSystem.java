import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Array;
import java.util.Comparator;
import java.util.Scanner;


public class StudentManagementSystem {
    private ArrayList<String> studentData = new ArrayList<>();
    private Student[] studentObjects;
    //handling all management operations

    //Use scanner for user input to interact with system

    /*System should display menu w/ options for usees to
    select operations like removing, or viewing student
    details*/

    /*Implement functionality to save and load student
    records to and from a text file
    Ensure proper file handling with try-catch blocks to
    handle errors */

    /* Implement input validation (check for valid grades between
    0 and 100; use exception handling to manage incorrect inputs
    or file errors  */

    /* The system should generate reports such as:
    average grade for each student
    highest an lowest grades for a specific subject
    list of students sorted by grade*/
  /*  public static int StudentMenu(){
        //Menu of options to work in StudentManagement System
        int selection;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please select one of the following options: ");
        System.out.println("To see your test scores, select 1 ");
        System.out.println("To add a student record, select 2 ");
        System.out.println("To delete a student record, select 3 ");
        System.out.println("To generate a report, select 4 ");
        selection = keyboard.nextInt();
        if (selection == 4){
            System.out.println("To generate a report of average grade of student, select 5 ");
            System.out.println("To generate a report of the top 5 performers of a specific subject, select 6 ");
            System.out.println("To generate a report of the bottom 5 performers of a specific subject, select 7 ");
            System.out.println("To generate a report of students sorted by average grade, select 8 ");
            selection = keyboard.nextInt();
        }
        return selection;
    }*/

     public void openFile() {
         //Opening file and scanning contents
         studentData.clear();
         String filename = ("Student.txt");
         File studentFile = new File(filename);
         //Try catch blocks to handle file reading errors
         try{
             if (!studentFile.exists()) {
                 studentFile.createNewFile();
             }
             Scanner inputFile = new Scanner(studentFile);
             while (inputFile.hasNextLine()){
                 studentData.add(inputFile.nextLine());
             }
             inputFile.close();
         } catch (IOException e) {
             System.out.println("Error handling file. " + e.getMessage());
         }

         studentObjects = new Student[studentData.size()];
         for (int i = 0; i < studentData.size(); i++) {
             String[] students = studentData.get(i).split(",");
                 studentObjects[i] = new Student(students);
         }
     }
     
     public String save(String filename) {
         try {
             FileWriter fw = new FileWriter(filename);
             PrintWriter out = new PrintWriter(fw);
             for (int i = 0; i < studentData.size(); i++) {
                 out.println(studentData.get(i));
             }
             out.close();
             return "Successfully saved to " + filename;
         }
         catch (IOException e) {
             return "Error saving file: " e.getMessage();
          }
         }
     
     public String load(String filename){
         openFile();
         return "Successfully loaded records from " + filename;
     }
             
     public Student checkStudent(String ID){
         openFile();
         int studID = Integer.parseInt(ID);
         Student setReturn = null;
         for (int i = 0; i < studentData.size(); i++){
             if(studentObjects[i].getID()== studID){
                 setReturn = studentObjects[i];
                 break;
             }
         }
         return setReturn;
     }

     public Student[] getAll(){
         openFile();
         return studentObjects;
     }

     public boolean addStudent(Student newStud ){
         openFile();
         boolean added = true;
         for(int g = 0; g < studentData.size(); g++){
             if (studentObjects[g].getID() == newStud.getID()){
                 added = false;
                 break;
             }
         }
         if (added){
             try(PrintWriter out = new PrintWriter(new FileWriter("Student.txt", true))){
                 out.println(newStud);
             }
             catch(IOException e){
                 System.err.println("Could not add to file: "+ e.getMessage());
             }
         }

         return added;
     }
     
     public boolean removeStudent(String id) {
         openFile();
         int targetID = Integer.parseInt(id);
                 boolean removed = false;
                 
         for (int i = 0; i < studentObjects.length; i++) {
             if (studentObjects[i] !=null && Integer.parseInt(studentObjects[i].getID().trim()) == targetID) {
                 studentData.remove(i);
                 removed = true;
                 break;
             }
         }
         
         if (removed) {
             save("Student.txt");
             openFile();
         }
         return removed;
     }
}




         





