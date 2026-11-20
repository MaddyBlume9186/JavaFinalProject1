import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;

public class StudentManagementSystem {
    private ArrayList<String> studentData = new ArrayList<>();
    private Student[] studentObjects;
    private Subject[] sub;

     public void openFile() {
         //Opening file and scanning contents
         studentData.clear();
         String filename = ("src/Student.txt");
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
             String[] students = studentData.get(i).split(", ");
                 studentObjects[i] = new Student(students);
         }
     }
     
     public String save(String filename) {
         try {
             FileWriter fw = new FileWriter(filename);
             PrintWriter out = new PrintWriter(fw);
             Files.write(Paths.get(filename), studentData);
             out.close();
             return "Successfully saved to " + filename;
         }
         catch (IOException e) {
             return "Error saving file: " + e.getMessage();
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

     public boolean addStudent(Student newStud ) throws IOException{
         openFile();
         boolean added = true;
         for(int g = 0; g < studentData.size(); g++){
             if (studentObjects[g].getID() == newStud.getID()){
                 added = false;
                 break;
             }
         }
         if (added){
             try{
                 studentData.add(String.join(", ",newStud.getStudent()));
                 save("Student.txt");
                 throw new IOException("Error in adding student. Please try again.");}
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
             if (studentObjects[i] !=null && studentObjects[i].getID() == targetID) {
                 studentData.remove(i);
                 removed = true;
                 break;
             }
         }
         
         if (removed) {
             save("Student.txt");
         }
         return removed;
     }

     public void highLowForSubject(String subject) {
         openFile();
         ArrayList<Student> students = new ArrayList<>();

         for (int i = 0; i < studentObjects.length; i++) {

             if (studentObjects[i].getSubject().equalsIgnoreCase(subject)) {

                 students.add(studentObjects[i]);
             }
         }

         students.sort(Comparator.comparingDouble(Student::getAverage).reversed());

         System.out.println("Ascending:");
         int index = 1;
         for (Student student : students) {
             System.out.println(index + " " + student.getName() + " - " + student.getAverage() + "%");
             index += 1;
         }
}
    public List <Student> sortedByAverage() {
        openFile();
        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < studentObjects.length; i++) {
            students.add(studentObjects[i]);
        }

        students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
        return students;
        /*System.out.println("Ascending:");
        int index = 1;
        for (Student student : students) {
            System.out.println(index + " " + student.getName() + " - " + student.getAverage() + "%");
            index += 1;
        }*/
    }
}




         





