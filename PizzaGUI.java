import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PizzaGUI extends JFrame implements ActionListener{
    //Creating String Arrays for the items
    String[] Sizes =  {"Small", "Medium", "Large", "Super"};
    String[] toppings = {"","Pepperoni", "Onion", "Black Olives", "Sliced Tomato", "Pineapple"};
    JCheckBox ExtraCheese;

    //Public Variable for monitoring price
    public Double price = 5.0;

    //Initializing three ComboBoxes for ingredients and one for sizes
    JComboBox Top1;
    JComboBox Top2;
    JComboBox Top3;
    JComboBox sizeChoiceBox;


    JLabel TotalPrice; //This label is for displaying text related to price
    JLabel DisplayPrice; //This label is for actually displaying price

    JFrame f; //Our main frame
    LilPizzaGraphic PizzaGraphic = new LilPizzaGraphic(); //This is initializing a new Pizza object which extends canvas(see LilPizzaGraphic.java for more)

    PizzaGUI() {
        //Inset padding
        Insets i = new Insets(5, 0, 3, 0);

        //Initializing JFrame
        f = new JFrame();
        f.setSize(700, 700); //Setting size
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Stop code on close
        f.setLayout((new GridBagLayout())); //Using a grid Bag layout
        f.setResizable(false); //Not an app designed to be resizeable, makes things easier on me
        f.setTitle("Pizza Ordering App"); //Window title

        GridBagConstraints gbc = new GridBagConstraints(); //Creating a variable to store our constraints

        //Setting padding and insets
        gbc.insets = i;
        gbc.ipady = 15;
        gbc.gridx = 0;
        gbc.gridy = 3;


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Setting the size of the Pizza graphic
        PizzaGraphic.setSize(400, 400);
        f.add(PizzaGraphic, gbc); //Adding the PizzaGraphic to the canvas with our constraints


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////


        JPanel Sizing = new JPanel(); //JPanel to serve as a container for the size options
        Sizing.setLayout(new GridLayout(2, 1)); //Container will have a grid layout
        Sizing.setSize(500, 200); //Set the size of the container
        Sizing.setBounds(700, 0, 500, 100);

        //Question to ask what size pizza the person wants
        JLabel SizeChoice = new JLabel("What size Pizza would you like?", SwingConstants.CENTER);
        SizeChoice.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        //Creaiting the combobox with the list
        sizeChoiceBox = new JComboBox(Sizes);
        sizeChoiceBox.addActionListener(this); // Assigning action function

        //Adding items to the container
        Sizing.add(SizeChoice);
        Sizing.add(sizeChoiceBox);

        //Changing the constraints
        gbc.gridy = 0;

        f.add(Sizing, gbc); //Adding the container to the frame

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Creating a new container and sizing it
        JPanel Ingredients = new JPanel();
        Ingredients.setSize(500, 400);
        Ingredients.setBounds(700, 100, 500, 100);

        //Label to prompt the choice of ingredients
        JLabel iLabel = new JLabel("Choose 3 Ingredients:");
        iLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

        //Adding the combo boxes
        Top1 = new JComboBox(toppings);
        Top2 = new JComboBox(toppings);
        Top3 = new JComboBox(toppings);

        //Adding functions to the combo box
        Top1.addActionListener(this);
        Top2.addActionListener(this);
        Top3.addActionListener(this);

        ExtraCheese = new JCheckBox("Add Extra Cheese"); //Extra cheese option (No functionality)

        //Adding items to the container
        Ingredients.add(iLabel);
        Ingredients.add(Top1);
        Ingredients.add(Top2);
        Ingredients.add(Top3);
        Ingredients.add(ExtraCheese);

        //Updating the constraints
        gbc.gridy = 1;

        //Adding the container to the frame
        f.add(Ingredients, gbc);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Creating a new container and sizing it
        JPanel TotalPricePanel = new JPanel();
        TotalPricePanel.setSize(500, 100);

        //Adding our labels for displaying an everchanging price
        TotalPrice = new JLabel("Total Price: $");
        DisplayPrice = new JLabel(" " + price);
        TotalPrice.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        DisplayPrice.setFont(new Font(Font.DIALOG, Font.BOLD, 15));

        //Adding items to the container
        TotalPricePanel.add(TotalPrice);
        TotalPricePanel.add(DisplayPrice);

        //Updating the constraints
        gbc.gridy = 2;

        //Adding the container to the frame
        f.add(TotalPricePanel, gbc);

        //Setting the frame/window to be visible
        f.setVisible(true);

    }

    //Function to calculate the price owed
    public Double calcP (){
        //Two variables, one for price calc and the other for topping calc
        Double SizePriceCalc = 0.0;
        Double ToppingCalc = 0.0;

        //If statements to add the prize sizing
        if(sizeChoiceBox.getSelectedItem()=="Large"){
            SizePriceCalc = 15.0;
        }
        else if (sizeChoiceBox.getSelectedItem()=="Medium") {
            SizePriceCalc = 10.0;
        }
        else if (sizeChoiceBox.getSelectedItem()=="Small") {
            SizePriceCalc = 5.0;
        }
        else if (sizeChoiceBox.getSelectedItem()=="Super") {
            SizePriceCalc = 20.0;
        }

        //If there is something in the combo box, add 50 cents to the topping price
        if (Top1.getSelectedItem() != ""){
            ToppingCalc += 0.5;
        }
        if (Top2.getSelectedItem() != ""){
            ToppingCalc += 0.5;
        }
        if (Top3.getSelectedItem() != ""){
            ToppingCalc += 0.5;
        }
        //Calculating discount for 3 toppings
        if(ToppingCalc == 1.5){
            ToppingCalc = 1.25;
        }
        return (ToppingCalc+SizePriceCalc);//Returning the price of the size
    }
    public void resetPizza(){
        //Setting all variables to be false
        PizzaGraphic.setPepp(false);
        PizzaGraphic.setTom(false);
        PizzaGraphic.setOn(false);
        PizzaGraphic.setOl(false);

        //Using the set methods to updating the LilPizzaGraphics to be true based on what is chosen in the combo boxes
        if(Top1.getSelectedItem() == "Pepperoni" || Top2.getSelectedItem() == "Pepperoni"|| Top3.getSelectedItem() == "Pepperoni"){
            PizzaGraphic.setPepp(true);
        }
        if(Top1.getSelectedItem() == "Sliced Tomato" || Top2.getSelectedItem() == "Sliced Tomato"|| Top3.getSelectedItem() == "Sliced Tomato"){
            PizzaGraphic.setTom(true);
        }
        if(Top1.getSelectedItem() == "Onion" || Top2.getSelectedItem() == "Onion"|| Top3.getSelectedItem() == "Onion"){
            PizzaGraphic.setOn(true);
        }
        if(Top1.getSelectedItem() == "Black Olives" || Top2.getSelectedItem() == "Black Olives"|| Top3.getSelectedItem() == "Black Olives"){
            PizzaGraphic.setOl(true);
        }
    }
    //Functionality
    @Override
    public void actionPerformed(ActionEvent e) {
        price = calcP(); //Calculating price and assigning it to a variable

        //Charge People double for Pineapple :)
        if(Top1.getSelectedItem() == "Pineapple" || Top2.getSelectedItem() == "Pineapple" || Top3.getSelectedItem() == "Pineapple"){
            price = price * 2;
        }

        DisplayPrice.setText("" + price); //Displaying the price by updating the display price

        resetPizza(); //Updating the LilPizzaGraphic variables

        PizzaGraphic.setSize(400, 400); //Idek why, but the canvas always expands when setText is invoked so this fixes the issue ¯\_(ツ)_/¯
        PizzaGraphic.repaint(); //Repainting the Pizza Graphic

    }


    public static void main(String[] args){
        new PizzaGUI(); //Creating an instance of our class
    }
}