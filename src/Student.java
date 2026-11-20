import java.util.ArrayList;
public class Student {
    private String[] studentData;
    private int studID;
    private String name;
    private String gradLevel;
    private ArrayList <Integer> testScores = new ArrayList<>();
    private double total = 0.0;
    private double average;


    public Student(String[] studentData) {
        this.studentData = studentData;
        setID(studentData[0]);
        this.name = studentData[1];
        this.gradLevel = studentData[2];
        for (int i = 4; i < studentData.length; i ++){
            int score = Integer.parseInt(studentData[i].trim());
            testScores.add(score);
        }
       // System.out.println(testScores.size());
        setAverage(testScores);

    }

    public Student (int id, String name){
        studID = id;
        this.name = name;
    }


    public String[] getStudent(){
        return studentData;
    }

    public String getGradLevel(){
    return gradLevel;}


    public void setID(String id){
        studID = Integer.parseInt(id);
    }
    public int getID(){
        return studID;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

     public String getLetterGrade(){
        // return letter grade back to main but also logic here or retrun a default grade/// i need to fix this. #########
        double avg = getAverage();
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 60) return "D";
        return "F";
    }

    public ArrayList<Integer> getTestScores(){
        return testScores;
    }

    public void setAverage(ArrayList <Integer> testScores){
        //System.out.println(testScores.size());
        this.testScores = testScores;
        for (int i = 0; i < testScores.size(); i++){
            total+= testScores.get(i);
        }
        average = total / testScores.size();
    }

    public double getAverage(){
        return average;
    }

    public void setTestScore(int score) throws InvalidInput {
        try{
            if (score < 0 || score > 100){
            throw new InvalidInput("Grade must be between 0 and 100");
        }}
        catch (InvalidInput e){
            System.out.println("Error: " + e.getMessage());
        }
        testScores.add(score);
        setAverage(testScores);

}}
