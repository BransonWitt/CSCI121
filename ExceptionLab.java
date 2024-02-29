import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExceptionLab extends JFrame{
    //Global Variables
    JFrame f;
    JTextField insert;
    JPanel fin = new JPanel();

    ExceptionLab(){
        //Initializing Frame
        f = new JFrame();
        f.setTitle("Exception Lab");
        f.setSize(500,500);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(new GridBagLayout());

        //Title
        JLabel askSquare = new JLabel("What number would you like the square root of:");
        askSquare.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        //Text field to insert number
        insert = new JTextField(10);

        //Calculate Button
        JButton calc = new JButton("Calculate");

        //Using Grid Bag Constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15,5,15);
        gbc.gridy = 1;
        gbc.gridx = 1;

        //Adding items to frame
        f.add(askSquare, gbc);
        gbc.gridy = 2;
        f.add(insert,gbc);
        gbc.gridy = 3;
        f.add(calc,gbc);

        //Action
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double sqrt;
                fin.removeAll(); //Clearing panel to allow for multiple goes
                try{ //Try Except handling for someone inserting letters
                    Double sqrNum = Double.parseDouble(insert.getText()); //Parsing integer from input

                    //The if else is more efficient than try/catch handling for negative numbers
                    if(sqrNum >= 0){
                        sqrt = Math.sqrt(sqrNum); //Calculating square root
                        JLabel answer = new JLabel(String.valueOf(sqrt)); //Adding square root to jLabel
                        answer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
                        fin.add(answer); //Adding JLabel to JPanel

                    }else {
                        //Creating a label for a negative number
                        JLabel negError = new JLabel("Please insert a positive number");
                        fin.add(negError);
                    }

                }catch (java.lang.Exception NumberFormatException){ //Catching items that can't be parsed to an int
                    //Label for items that can't be parsed to an int
                    JLabel NumberError = new JLabel("Please insert a valid number");
                    fin.add(NumberError);
                }
                //Adding items to frame
                gbc.gridy = 4;
                f.add(fin,gbc);

                //Animation
                f.revalidate();
                f.repaint();
                f.pack();


            }
        });

        f.pack();
        f.setVisible(true); //Showing frame
    }

    public static void main(String[] args) {
        new ExceptionLab(); //Initializing object
    }
}