public class Student{
    //attributes ID, name, grades, etc
    private String[] studentData;
    private int studID;
    private String name;
    private String subject;
    private int[] testScores = new int [5];
    private int total = 0;
    private double average;

    public Student(String[] studentData){
        this.studentData = studentData;
        setID(studentData[0]);
        this.name = studentData[1];
        this.subject = studentData[2];
        setTestScores(studentData[3], studentData[4], studentData[5], studentData[6], studentData[7]);
    }

    public void setID(String id){
        studID = Integer.parseInt(id);
    }
    public int getID(){
        return studID;
    }

    public String getName(){
        return name;
    }

    public String getSubject(){
        return subject;
    }

    public void setTestScores(String test1, String test2, String test3, String test4, String test5){
        testScores[0] = Integer.parseInt(test1);
        testScores[1] = Integer.parseInt(test2);
        testScores[2] = Integer.parseInt(test3);
        testScores[3] = Integer.parseInt(test4);
        testScores[4] = Integer.parseInt(test5);
        setAverage(testScores);

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

    public int[] getTestScores(){
        return testScores;
    }

    public void setAverage(int[] scores){
        for (int i = 0; i < scores.length; i++){
            total+= scores[i];
        }
        average = total / 5.0;
    }

    public double getAverage(){
        return average;
    }



}
