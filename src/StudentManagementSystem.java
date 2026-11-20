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
    private Subject[] studentObjects;
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
        //iterate through each line and create individual arrays of Strings
         studentObjects = new Subject[studentData.size()];
         for (int i = 0; i < studentData.size(); i++) {
             String[] students = studentData.get(i).split(", ");
                 studentObjects[i] = new Subject(students);
         }
     }

     // method to overwrite and save file based on current studentData array
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

     //method to check if student exists in current file based on ID
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
    // method to return all records of a certain student based on ID
    public ArrayList <Subject> checkStudentAll(String ID){
        openFile();
        int studID = Integer.parseInt(ID);
        ArrayList<Subject> students = new ArrayList<>();
        for (int i = 0; i < studentData.size(); i++){
            if(studentObjects[i].getID()== studID){
                students.add(studentObjects[i]);
            }
        }
        return students;
    }
    // method to return all students from file
     public Subject[] getAll(){
         openFile();
         return studentObjects;
     }

     public void addGrade(Subject sub, int grade){
             for(int i = 0; i < studentObjects.length; i ++ ){
                 if(sub.getID() == studentObjects[i].getID() && sub.getSubject().equalsIgnoreCase(studentObjects[i].getSubject())){
                     System.out.println(studentData.get(i));
                     studentData.set(i, studentData.get(i) + ", " + grade);
                     System.out.println(studentData.get(i));
                 }
             }
             save("src/Student.txt");
     }
     //method to add student to studentData array and then save to original file
     public boolean addStudent(Student newStud) throws IOException{
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
                 save("src/Student.txt");
                 throw new IOException("Error in adding student. Please try again.");}
             catch(IOException e){
                 System.err.println("Could not add to file: "+ e.getMessage());
             }
         }

         return added;
     }

     // method to remove all records of student based on ID
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
             save("src/Student.txt");
         }
         return removed;
     }

     // method to print out students ranked by average grade based on subject
     public void highLowForSubject(String subject) {
         openFile();
         ArrayList<Student> students = new ArrayList<>();
         for (int i = 0; i < studentObjects.length; i++) {

             if (studentObjects[i].getSubject().equalsIgnoreCase(subject)) {

                 students.add(studentObjects[i]);
             }
         }
         students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
         System.out.println("Highest to Lowest By Average Grade:");
         int index = 1;
         for (Student student : students) {
             System.out.println(index + " " + student.getName() + " - " + student.getAverage() + "% - (" + student.getLetterGrade() + ")");
             index += 1;
         }
}   // method to sort all students and rank them by average grade
    public List <Subject> sortedByAverage() {
        openFile();
        ArrayList<Subject> students = new ArrayList<>();

        for (int i = 0; i < studentObjects.length; i++) {
            students.add(studentObjects[i]);
        }

        students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
        return students;

    }
}




         





