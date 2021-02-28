package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("lab05");


        DataSource source = new DataSource();
        ObservableList Data = source.getAllMarks();

        TableView tableView = new TableView();
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

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));;
        vbox.getChildren().addAll(tableView);
        Scene scene = new Scene(vbox, 500, 475); // w x h

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
