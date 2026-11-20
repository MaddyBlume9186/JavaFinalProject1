import java.util.ArrayList;
import java.util.Objects;

public class Subject extends Student {
    private int studID;
    private String name;
    private String subject;
    private ArrayList <Integer> testScores = new ArrayList<>();

    public Subject(String[] student){
        super(student);
        this.subject = student[3];
    }

    public String getSubject(){
        return subject;
    }

    @Override
    public String toString(){
        return studID + "," + name + "," + subject + "," + String.join(", ", testScores.toString());
    }

}
