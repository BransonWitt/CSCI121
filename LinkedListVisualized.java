import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;
public class LinkedListVisualized extends JPanel{
    //Global, final, and private variables
    final int maxRow = 5;
    final int maxCol = 5;
    private int numRow;
    private int numCol;
    String nextA;
    String content;
    String previousA;

    //Method to calculate rows and columns, used as a backup currently because grid layout is more complicated than originally thought
    private void calculateRC(LinkedList list){
        int listLength = list.listLength();

        //Capping out at 5 columns
        if(listLength < 5){
            this.numCol = listLength;
            this.numRow = 1;
        }
        else{
            this.numCol = 5;
            if(listLength % 5 == 0){
                this.numRow = listLength / 5;
            }
            else{
                this.numRow = (listLength / 5) + 1;
            }
        }
    }

    //A more modern version to return expected rows and columns based more around how Grid layout works
    public int returnOptimumWidth(int length){
        //Checking for any perfect matches between 3-5 columns
        for(int j = 5; j > 2; j--){
            if(length % j == 0){
                return j;
            }
        }

        //Using and array to check for which number of columns 3-5 produces the least amount of blank spaces for n rows
        ArrayList<Integer> remainders = new ArrayList<>(); //using an ArrayList to store the blank spaces
        for(int i = 3; i <= 5; i++){
            remainders.add(length % i);
        }
        return remainders.indexOf(Collections.min(remainders)) + 3; //+3 to compensate for starting at 3 col
    }

    //Constructor
    LinkedListVisualized(int width, int height, LinkedList list){
        //Creating initial predictions for rows and columns
        calculateRC(list);

        //Setting size
        setPreferredSize(new Dimension(500,500));
        setMinimumSize(new Dimension(500,500));

        //Next block is creating a mostly accurate prediction for rows and columns using our two methods above
        int predictedGridColNum = returnOptimumWidth(list.listLength());

        if(predictedGridColNum == 0){
            predictedGridColNum = this.numCol;
        }

        int predictedGridRowNum = list.listLength() / predictedGridColNum;

        if(predictedGridRowNum == 0){
            predictedGridRowNum = this.numRow;
        }

        //Setting layout
        setLayout(new GridLayout(predictedGridRowNum, predictedGridColNum));//We are using a gridlayout for this

        //Predictions for the width and height of each row and column based off of a 500x500 workspace and our predictions generated above
        int widthPixels = (500 / predictedGridColNum) - 2;
        int heightPixels = (500 / predictedGridRowNum) - 2;

        //For loop to go over every item in our linked list and generate a visual representation of the node based off the width and height just predicted
        for(int i = 0; i < list.listLength(); i++){
            //Creating a string for the previous memory address
            if(list.getObjectAt(i).returnPreviousNode() != null){
                previousA = list.getObjectAt(i).returnPreviousNode().toString().replace("LinkedList$", "");
            } else{
                previousA = "null";
            }
            //Creating a string for the object stored in the value of the linked list node
            if(list.getItemAt(i) != null){
                content = list.getItemAt(i).toString();
            }else{
                content = "null";
            }
            //Creating a String variable for the next memory address
            if(list.getObjectAt(i).getNextNode() != null){
                nextA = list.getObjectAt(i).getNextNode().toString().replace("LinkedList$", "");
            } else{
                nextA = "null";
            }

            //Generating a visual representation for the node using another class that extends Jpanel and adding that class to this jpanel
            LinkNodeVisualized myNode = new LinkNodeVisualized(widthPixels, heightPixels, previousA, content, nextA);
            add(myNode);
        }


    }
}
