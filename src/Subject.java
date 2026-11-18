public class Subject {
    private String name;
    private double grade;

    public Subject(String name, double grade){
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override // intentionally replacing default toString method here. 
    public String toString() {
        return name + " : " + grade;
    }
        

    //attributes: name, grade, etc.
}
