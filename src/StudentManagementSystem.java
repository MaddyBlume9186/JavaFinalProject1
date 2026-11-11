import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class StudentManagementSystem {

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
    public static int StudentMenu(){
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
    }

     public static void main(String[] args)  {
        //Opening file and scanning contents
        String filename = ("C:\\Users\\madel\\OneDrive\\Desktop\\JavaFinalProject\\src\\Student.txt");
        File studentFile = new File(filename);
        ArrayList<String> studentData = new ArrayList<>();
        int menuSelection;
        //Try catch blocks to handle errors
        try (Scanner inputFile = new Scanner(studentFile)) {
            while (inputFile.hasNextLine()) {
                studentData.add(inputFile.nextLine());
            }
        }

        catch (FileNotFoundException e){
            System.out.println("File not found.");
        }

        /* Creating matrix to separate data points for each line
        and then sending each array to Student Class */
        String[][] students = new String[studentData.size()][];

        Student[] studentObjects = new Student[studentData.size()];

        for(int i = 0; i < studentData.size(); i++) {
            students[i] = studentData.get(i).split(", ");
            studentObjects[i] = new Student(students[i]);
        }


        //Calling Menu
        menuSelection = StudentMenu();

        /*if (menuSelection == 1){

        }
        */






}}

