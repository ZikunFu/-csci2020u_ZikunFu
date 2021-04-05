package sample;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {
    TextArea textArea;

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("lab10 - Server");
        textArea = new TextArea();
        Button exit = new Button("Exit");

        exit.setOnAction(actionEvent -> {
            Platform.exit();
        });

        GridPane gridPane = new GridPane();
        gridPane.add(textArea, 0, 0, 1, 1);
        gridPane.add(exit, 0, 1, 1, 1);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        primaryStage.setScene(new Scene(gridPane, 300, 275));
        chatServer chat = new chatServer();

        primaryStage.show();
        chat.start();
    }

    public class chatServer extends Thread{
        protected int SERVER_PORT = 6666;
        protected int MAX_THREAD = 20;

        public void start(){
            try {
                ServerSocket serverSocket;
                serverSocket = new ServerSocket(SERVER_PORT);
                ClientConnectionHandler[] threads =
                        new ClientConnectionHandler[MAX_THREAD];

                new Thread(()->{
                    try {
                        int count = 1;
                        while (true){
                            var clientSocket = serverSocket.accept();
                            System.out.println("Connection " + count + " established");
                            threads[count]= new ClientConnectionHandler(clientSocket);
                            threads[count].start();
                            count++;
                        }
                    }catch(Exception e){ System.err.println(e); }
                }).start();
            }catch (Exception e){ System.err.println(e); }
        }
    }

    class ClientConnectionHandler extends Thread{

        protected Socket clientSocket;//Socket
        protected BufferedReader in;  //networkInput
        protected PrintWriter out;    //networkOutput

        public ClientConnectionHandler(Socket socket) throws IOException {
            super();
            clientSocket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        public void run() {
            String input="";
            try {
                 input = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textArea.appendText(input.split(" ",2)[0] + ": "
                    + input.split(" ",2)[1]+"\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}