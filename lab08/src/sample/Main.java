package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private String currentFilename="default.csv";
    private File currentFile= new File("default.csv");
    private TableView tableView;
    BorderPane border1 = new BorderPane();
    @Override
    public void start(Stage primaryStage) throws Exception{
        FileChooser fileChooser = new FileChooser();
        primaryStage.setTitle("lab08");
        DataSource source = new DataSource();
        HBox hbox = new HBox(20);
        ObservableList Data = source.getAllMarks();

        Button clear = new Button("New");
        Button open = new Button("Open");
        Button save = new Button("Save");
        Button saveAs = new Button("Save As");
        Button exit = new Button("Exit");
        hbox.getChildren().addAll(clear,open,save,saveAs,exit);

        tableView = new TableView();
        tableView.setItems(Data);

        TableColumn col1 = new TableColumn("SID");
        col1.setCellValueFactory(new PropertyValueFactory("studentID"));
        TableColumn col2 = new TableColumn("Assignments");
        col2.setCellValueFactory(new PropertyValueFactory("assignments"));
        TableColumn col3 = new TableColumn("Midterm");
        col3.setCellValueFactory(new PropertyValueFactory("midterm"));
        TableColumn col4 = new TableColumn("Final Exam");
        col4.setCellValueFactory(new PropertyValueFactory("finalExam"));
        TableColumn col5 = new TableColumn("Final Mark");
        col5.setCellValueFactory(new PropertyValueFactory("finalMark"));
        TableColumn col6 = new TableColumn("Letter Grade");
        col6.setCellValueFactory(new PropertyValueFactory("letterGrade"));

        tableView.getColumns().setAll(col1, col2,col3,col4,col5,col6);
        tableView.setPrefWidth(700);
        tableView.setPrefHeight(600);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        open.setOnAction(e->{
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            currentFilename=selectedFile.getName();
        });
        save.setOnAction(e->{
            try {
                save();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        saveAs.setOnAction(e->{
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            currentFilename=selectedFile.getName();
            try {
                save();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        clear.setOnAction(e->{
            tableView.getItems().clear();
            try {
                load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        exit.setOnAction(e -> Platform.exit());

        border1.setCenter(tableView);
        border1.setTop(hbox);
        primaryStage.setScene(new Scene(border1, 500, 400));
        primaryStage.show();
    }
    private void save() throws IOException {
        Exporter exp=new Exporter(tableView.getItems(),currentFilename);
        exp.writeCSV();
    }
    private void load() throws IOException {
        ObservableList<StudentRecord> list = FXCollections.observableArrayList();
        BufferedReader csvReader = new BufferedReader(new FileReader(currentFile));
        String nextLine;
        boolean first=true;
        while ((nextLine = csvReader.readLine()) != null) {
            if(first==true){
                nextLine = csvReader.readLine();
                first=false;
            }
            String[] data = nextLine.split(",");

            StudentRecord temp = new StudentRecord();

            temp.setStudentID(data[0]);
            temp.setAssignments(Double.parseDouble(data[1]));
            temp.setMidterm(Double.parseDouble(data[2]));
            temp.setFinalExam(Double.parseDouble(data[3]));
            temp.setFinalMark(Double.parseDouble(data[4]));
            temp.setLetterGrade(data[5]);

            list.add(temp);

        }
        csvReader.close();
        tableView.setItems(list);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
