package sample;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("lab10 - client");
        Label lb1 = new Label("Username: ");
        Label lb2 = new Label("Message: ");
        TextField user = new TextField();
        TextField mesg = new TextField();
        Button send = new Button("Send");
        send.setOnAction(actionEvent -> {
            PrintWriter out=null;
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 6666);
                out = new PrintWriter( socket.getOutputStream(),true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.println(user.getText()+" "+mesg.getText());
        });

        Button exit = new Button("Exit");
        exit.setOnAction(actionEvent -> {
            Platform.exit();
        });

        GridPane gridPane = new GridPane();

        gridPane.add(lb1, 0, 0, 1, 1);
        gridPane.add(user, 1, 0, 1, 1);
        gridPane.add(lb2, 0, 1, 1, 1);
        gridPane.add(mesg, 1, 1, 1, 1);
        gridPane.add(send, 0, 2, 1, 1);
        gridPane.add(exit, 0, 3, 1, 1);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        primaryStage.setScene(new Scene(gridPane, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
