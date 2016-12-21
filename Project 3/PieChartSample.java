import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
 
/**
 * Code from: https://docs.oracle.com/javafx/2/charts/pie-chart.htm
 * 3/26/15
 * Project 3
 * ONLY ECLIPSE VERSION LUNA RUNS THIS
 *
 */
public class PieChartSample extends Application 
{
    private static ArrayList<Integer> listOfAges; 
    private static ArrayList<Double> percentageAges;
 
    public static void findAgesWithTypeStrings(String finalQweryLine)
    {
        if(finalQweryLine == null)
        {
            System.out.println("NO AGES TO GRAPH");
        }
        else
        {
            System.out.println("UNABLE TO GRAPH AGES FROM LAST QWERY LINE");
        }
    }
    
    public static void findAgesWithTypeArrayListPerson(ArrayList<Person> finalQweryLine)
    {
        finalQweryLine.trimToSize();  //saves memory
        //for(Person x: finalQweryLine)
        int k;
        for(int i = 0; i < finalQweryLine.size(); i++)
        {
        	k = finalQweryLine.get(i).calculateCurrentAge();   
            listOfAges.add(k);   //TODO: gives a nullpointerexception???
        }
    }
    
    public static ArrayList<Double> percentageOfAges(ArrayList<Integer> listOfAges)
    {
        int[] ageCount = new int[150];
        for(int age: listOfAges)
        {
            ageCount[age]++;
        }
        for(int i = 0; i < ageCount.length; i++)
        {
            percentageAges.add(i, (double) (ageCount[i]/listOfAges.size()));
        }
        return percentageAges;
        
    }
    
    public static void graphPieChart(Stage stage) 
    {
        Scene scene = new Scene(new Group());
        stage.setTitle("PIE CHART OF DIFFERENT AGES");
        stage.setWidth(500);
        stage.setHeight(500);
        
        ArrayList<Double> percentageAges = PieChartSample.percentageOfAges(listOfAges);
        ObservableList<PieChart.Data> pieChartData; 
        for(int i = 0; i < percentageAges.size(); i++)
        {
            Double ageOfSlice = percentageAges.get(i);
            String nameOfSlice = ("Age " + ageOfSlice);
            FXCollections.observableArrayList(new PieChart.Data(nameOfSlice, percentageAges.get(i)));
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("PIE CHART OF DIFFERENT AGES");
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) 
    {
        launch(args);
    }
}