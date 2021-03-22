package sample;

import javafx.collections.ObservableList;
import java.io.FileWriter;
import java.io.IOException;
/*
 *Exporter is used to save data from tableview to a csv file
 *,it generates a formatted csv file named result.csv
 * */
public class Exporter {
    ObservableList<StudentRecord> list;
    String filename;
    Exporter(ObservableList<StudentRecord> list, String filename){ this.list=list;this.filename=filename; }

    public void writeCSV() throws IOException {
        FileWriter csvWriter = new FileWriter(filename);
        csvWriter.append("SID");
        csvWriter.append(",");
        csvWriter.append("Assignments");
        csvWriter.append(",");
        csvWriter.append("Midterm");
        csvWriter.append(",");
        csvWriter.append("Final Exam");
        csvWriter.append(",");
        csvWriter.append("Final Mark");
        csvWriter.append(",");
        csvWriter.append("Letter Grade");
        csvWriter.append("\n");

        for(int i=0;i< list.size();i++){
            csvWriter.append(list.get(i).getStudentID());
            csvWriter.append(",");
            csvWriter.append( list.get(i).getAssignments()+"");
            csvWriter.append(",");
            csvWriter.append(list.get(i).getMidterm()+"");
            csvWriter.append(",");
            csvWriter.append(list.get(i).getFinalExam()+"");
            csvWriter.append(",");
            csvWriter.append(list.get(i).getFinalMark()+"");
            csvWriter.append(",");
            csvWriter.append(list.get(i).getLetterGrade());
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
