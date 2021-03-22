package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentRecord{

    private SimpleStringProperty studentID,letterGrade;

    private SimpleDoubleProperty midterm,assignments,finalExam,finalMark;

    public String getStudentID() {
        return studentID.get();
    }

    public String getLetterGrade() {
        return letterGrade.get();
    }

    public double getMidterm() {
        return midterm.get();
    }

    public double getAssignments() {
        return assignments.get();
    }

    public double getFinalExam() {
        return finalExam.get();
    }

    public double getFinalMark() {
        return finalMark.get();
    }

    public void setStudentID(String studentID) {
        this.studentID.set(studentID);
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade.set(letterGrade);
    }

    public void setMidterm(double midterm) {
        this.midterm.set(midterm);
    }

    public void setAssignments(double assignments) {
        this.assignments.set(assignments);
    }

    public void setFinalExam(double finalExam) {
        this.finalExam.set(finalExam);
    }

    public void setFinalMark(double finalMark) {
        this.finalMark.set(finalMark);
    }

    StudentRecord(String id, double ass, double mid , double end){
        studentID = new SimpleStringProperty(id);
        midterm   = new SimpleDoubleProperty(mid);
        assignments=new SimpleDoubleProperty(ass);
        finalExam =new SimpleDoubleProperty(end);
        double total = 0.2*ass+0.3*mid+0.5*end;
        finalMark =new SimpleDoubleProperty(total);

        if(total>=80){
            letterGrade=new SimpleStringProperty("A");
        }
        else if(total>=70){
            letterGrade=new SimpleStringProperty("B");
        }
        else if(total>=60){
            letterGrade=new SimpleStringProperty("C");
        }
        else if(total>=50){
            letterGrade=new SimpleStringProperty("D");
        }
        else{
            letterGrade=new SimpleStringProperty("F");
        }

    }
    StudentRecord(){
        studentID = new SimpleStringProperty();
        midterm   = new SimpleDoubleProperty();
        assignments=new SimpleDoubleProperty();
        finalExam =new SimpleDoubleProperty();
        finalMark =new SimpleDoubleProperty();
        letterGrade=new SimpleStringProperty();
    }
}
