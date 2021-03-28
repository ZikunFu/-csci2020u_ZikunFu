package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main extends Application {
    double apple[];
    double google[];
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("lab09");
        apple=downloadStockPrices("AAPL");
        google=downloadStockPrices("GOOG");
        drawLinePlot();
        primaryStage.setScene(new Scene(new Pane(canvas), 800, 800));
        primaryStage.show();
    }

    public double[] downloadStockPrices(String symobl) throws IOException {
        URL rowdata = new URL("https://query1.finance.yahoo.com/v7/finance/download/" +
                symobl+"?period1=1262322000&period2=1451538000&interval=1mo&" +
                "events=history&includeAdjustedClose=true");
        URLConnection data = rowdata.openConnection();
        Scanner input = new Scanner(data.getInputStream());
        double stock[]=new double[100];
        boolean first = true;
        int count=0;
        while (input.hasNext()){
            if(first==true){
                input.nextLine();
                first=false;
            }
            String line[]=input.nextLine().split(",");
            stock[count] =Double.parseDouble(line[4]);
            count++;
        }
        return stock;
    }

    Canvas canvas = new Canvas(800,800);

    public void drawLinePlot(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50,50,50,750);
        gc.strokeLine(50,750,750,750);
        gc.stroke();
        drawLine(Color.RED,apple,gc);
        drawLine(Color.AQUA,google,gc);
    }

    public void drawLine(Color color,double stock[],GraphicsContext gc){
        for(int i=0;i<69;i++){
            gc.setStroke(color);
            gc.strokeLine(50+i*10,750-stock[i],50+(i+1)*10,750-stock[i+1]);
            gc.stroke();

        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
