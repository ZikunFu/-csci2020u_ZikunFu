package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;


import java.io.*;
import java.util.*;

public class Controller {
    Map<String,Integer> warning= new HashMap<String,Integer>();
    {
        try {
            warning = countCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final int[] numberOfStudentsPerDegree = getVal(warning);
    private final String[] warningTypes = getKey(warning);
    private final static Color[] pieColours = {
            Color.ORANGE, Color.CORAL, Color.AQUA, Color.GOLD
    };

    @FXML
    private Canvas canvas;


    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawPieChart(gc);
    }
    private Map<String,Integer> countCSV() throws IOException {
        File file = new File("weatherwarnings-2015.csv");
        Map<String, Integer> warning = new HashMap<String, Integer>();
        String nextLine;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((nextLine = br.readLine())!=null){
            String[] col=nextLine.split(",");
            String str = col[5];
            if(warning.containsKey(str)){
                int previous = warning.get(str);
                warning.put(str, previous + 1);
            }else{
                warning.put(str, 1);
            }
        }
        return warning;
    }

    private String[] getKey(Map<String,Integer> warning){
        String[] str = new String[warning.keySet().size()];
        Iterator<Map.Entry<String, Integer>> itr = warning.entrySet().iterator();
        int count=0;
        while(itr.hasNext())
        {
            Map.Entry<String, Integer> entry = itr.next();
            str[count]=entry.getKey();
            count++;
        }
        return str;
    }

    private int[] getVal(Map<String,Integer> warning){
        int[] val = new int[warning.keySet().size()];
        Iterator<Map.Entry<String, Integer>> itr = warning.entrySet().iterator();
        int count=0;
        while(itr.hasNext())
        {
            Map.Entry<String, Integer> entry = itr.next();
            val[count]=entry.getValue();
            count++;
        }
        return val;
    }

    private void drawPieChart(GraphicsContext gc) {
        int numOfStudents = 0;
        for (int studentsForDegree: numberOfStudentsPerDegree)
            numOfStudents += studentsForDegree;

        double startAngle = 0.0;
        for (int i = 0; i < numberOfStudentsPerDegree.length; i++) {
            double slicePercentage = (double) numberOfStudentsPerDegree[i] / (double) numOfStudents;
            double sweepAngle = slicePercentage * 360.0;

            gc.setFill(pieColours[i]);
            gc.fillArc(350, 150, 300, 300, startAngle, sweepAngle, ArcType.ROUND);

            startAngle += sweepAngle;
        }

        for(int i=0; i< warningTypes.length;i++){
            gc.setFill(pieColours[i]);
            gc.fillRect(30,10+i*30,40,20);
            gc.strokeRect(30,10+i*30,40,20);
            gc.setFill(Color.BLACK);
            gc.fillText(warningTypes[i],80,25+i*30);
        }

    }
}
