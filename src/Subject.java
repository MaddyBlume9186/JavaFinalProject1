import java.util.ArrayList;
import java.util.Objects;

public class Subject extends Student {
    private String name;
    private double average;



    public Subject(String[] student, String subject, double average){
        super(student);
        if (student[3].equals(subject)){
            this.name = student[1];
            this.average = average;
        }
        else {
            this.name = null;
            this.average = 0;
        }

    }

    public String getStudentName(){
        return name;
    }

    public double getStudentAverage(){
        return average;
    }
        

    //attributes: name, grade, etc.
}
