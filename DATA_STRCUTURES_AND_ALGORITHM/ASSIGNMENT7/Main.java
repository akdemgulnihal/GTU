import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Main {

    //These variables for time of the input operations and also storing the size when we need to find the average
    static long timeTotalAdd=0;
    static long sizeAdd=0;
    static long timeTotalRemove=0;
    static long sizeRemove=0;
    static long timeTotalSearch=0;
    static long sizeSearch=0;
    static long timeTotalUpdate=0;
    static long sizeUpdate=0;

    static List<Long> timeListAdd = new LinkedList<>();
    static List<Long> timeListRemove = new LinkedList<>();
    static List<Long> timeListSearch = new LinkedList<>();

    /**
     * Main function
     * Taking the command line argumnets(which is the name of the file like input.txt)
     * @param args
     * 
     */
    public static void main(String[] args) {
        if (args.length != 1) { //If the args.lenght is not 1
            System.out.println("Usage: java Main <input_file>");    //Then printing this message to terminal to regulate the command
            return;
        }

        String inputFile = args[0]; //File name is stored in this varibale
        StockDataManager manager = new StockDataManager();  //Creating the manager object

        //Raeading is occured
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager); //Invoking the processCommand
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        manager.printOrders();  //Printing the preorder,postorder and inorder

        // Perform a simple performance analysis
        performPerformanceAnalysis(manager, 1000);
    }

    /**
     * In this function, taking line is splittin according to space delimeter,
     * And checking the first token whether ADD,REMOVE,SEARCH or UPDATE
     * Then relevant part will be executed
     * @param line
     * @param manager Some function is call via StockDataManagement object
     */
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        try {
            switch (command) {

                //The operators in the input file also take time and we need to find this time 
                //when we want to perform the time  
                //So for each operator time consumption is calculated and stored in static variable at the top of the file
                //The flow of the finding the time consumption explain in the ADD command, The other command time sonsumption is calculated like that way
                case "ADD": //Add command
                    long startTimeAdd, endTimeAdd,timeConsAdd;
                    startTimeAdd = System.nanoTime(); //time is started
                    if (tokens.length != 5) {
                        throw new IllegalArgumentException("Invalid ADD command line");
                    }
                    //call theaddOrUpdateStock from StockDataManagement class 
                    manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                    endTimeAdd =  System.nanoTime();    //Time is stopped
                    timeConsAdd = endTimeAdd - startTimeAdd;    //The time consumption is calculated
                    timeTotalAdd += timeConsAdd; //Add time consumption to the timeTaotalAdd
                    sizeAdd++;  //Innncrease the size of add
                    break;
                case "REMOVE"://Remove command
                    long startTimeRemove, endTimeRemove,timeConsRemove;
                    startTimeRemove = System.nanoTime();
                    if (tokens.length != 2) {
                        throw new IllegalArgumentException("Invalid REMOVE command line.");
                    }
                    manager.removeStock(tokens[1]);
                    endTimeRemove =  System.nanoTime();
                    timeConsRemove = endTimeRemove - startTimeRemove;
                    timeTotalRemove += timeConsRemove;
                    sizeRemove++; 
                    break;
                case "SEARCH":  //Seacrh command
                    long startTimeSearch, endTimeSearch,timeConsSearch;
                    startTimeSearch = System.nanoTime();
                    if (tokens.length != 2) {
                        throw new IllegalArgumentException("Invalid SEARCH command line");
                    }
                    Stock stock = manager.searchStock(tokens[1]);
                    if (stock != null) {
                        System.out.println("Stock found: " + "[symbol=" + stock.getSymbol() + ", price=" + stock.getPrice() + ", volume=" + stock.getVolume() + ", marketCap=" + stock.getMarketCap() + "]");
                    } else {
                        System.out.println("Stock not found: " + tokens[1]);
                    }
                    endTimeSearch = System.nanoTime();
                    timeConsSearch = endTimeSearch - startTimeSearch;
                    timeTotalSearch += timeConsSearch;
                    sizeSearch++;
                    break;
                case "UPDATE":  //Update comand
                    long startTimeUpdate, endTimeUpdate,timeConsUpdate; 
                    startTimeUpdate = System.nanoTime();
                    if (tokens.length != 6) {
                        throw new IllegalArgumentException("Invalid UPDATE command line.");
                    }
                    manager.updateStock(tokens[1],tokens[2], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                    endTimeUpdate = System.nanoTime();
                    timeConsUpdate = endTimeUpdate - startTimeUpdate;
                    timeTotalUpdate += timeConsUpdate;
                    sizeUpdate++;

                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function show the performance of the ADD,REMOVE etc commands
     * @param manager    manager object to cll relevant functions
     * @param size  node size
     */
    private static void performPerformanceAnalysis(StockDataManager manager, int size) {
        long startTime, endTime,startTime2, endTime2,startTime3, endTime3;

        // Measure time for ADD operation but for all nodes
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            long startTimeInner,endTimeInner; //In inner the loop, finding the each command time consumption and add to the list
            startTimeInner =System.nanoTime();  //starting the time 
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));    //New node is added or updated(randomly generated)
            endTimeInner =System.nanoTime();    //Ending the time
            timeListAdd.add(endTimeInner-startTimeInner);
        }
        endTime = System.nanoTime();                

        // timeTotalAdd adding to (endTime - startTime) . timeTotalAdd is the total time consumption of the input.txt
        // sizeAdd added to  (size + sizeAdd)-->  sizeAdd is the number of ADD command in the input.txt
        //So, in the below this stuff inserted to calculation. The other command is like that so the genral idea is explained here.
        System.out.println("Average ADD time: " + ((endTime - startTime) + timeTotalAdd)  / (size + sizeAdd) + " ns");

        // Measure time for SEARCH operation
        startTime2 = System.nanoTime();
        for (int i = 0; i < size; i++) {
            long startTimeInner,endTimeInner; //In inner the loop, finding the each command time consumption and add to the list
            startTimeInner =System.nanoTime();  //starting the time 
            manager.searchStock("SYM" + i);
            endTimeInner =System.nanoTime();    //Ending the time
            timeListSearch.add(endTimeInner-startTimeInner);
        }
        endTime2 = System.nanoTime();
        System.out.println("Average SEARCH time: " + ((endTime2 - startTime2) + timeTotalSearch) / (size + sizeSearch) + " ns");

        // Measure time for REMOVE operation
        startTime3 = System.nanoTime();
        for (int i = 0; i < size; i++) {
            long startTimeInner,endTimeInner; //In inner the loop, finding the each command time consumption and add to the list
            startTimeInner =System.nanoTime();  //starting the time 
            manager.removeStock("SYM" + i);
             endTimeInner =System.nanoTime();    //Ending the time
             timeListRemove.add(endTimeInner-startTimeInner);
        }
        endTime3 = System.nanoTime();
        System.out.println("Average REMOVE time: " + ((endTime3 - startTime3) + timeTotalRemove) / (size+sizeRemove) + " ns");

       
    
       SwingUtilities.invokeLater(() -> {
          String plotType = "scatter"; // Change to "scatter" for scatter plot

        GUIVisualization frame = new GUIVisualization(plotType,timeListAdd); // Create a new instance of GUIVisualization
        frame.setVisible(true); // Make the frame visible

        //GUIVisualization frame1 = new GUIVisualization(plotType,timeListRemove); // Create a new instance of GUIVisualization
        //frame1.setVisible(true); // Make the frame visible

        //GUIVisualization frame2 = new GUIVisualization(plotType,timeListSearch); // Create a new instance of GUIVisualization
        //frame2.setVisible(true); // Make the frame visible
      });
    }


     
       
}
