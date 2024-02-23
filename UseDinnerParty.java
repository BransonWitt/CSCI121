import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UseDinnerParty extends JFrame implements ActionListener{
    JFrame f = new JFrame(); //Declaring main frame
    DinnerParty myParty = new DinnerParty(); //Creating a class of dinner party

    //Frame is split into two partitions
    JPanel left = new JPanel();
    JPanel right = new JPanel();

    JLabel guests;//Declaring a global variable to update

    //Setting variables for fonts to reference
    Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    Font font1 = new Font(Font.SERIF, Font.PLAIN, 14);

    //Declaring global constraints for each side
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbc2 = new GridBagConstraints();

    //Declaring global variables
    String[] DinnerTypes = {"Party","Dinner Party"};
    JComboBox dinnerTypesCombo = new JComboBox(DinnerTypes);
    JTextField numGuests = new JTextField(13);
    JLabel dinnerChoice;

    //Constructor
    UseDinnerParty(){
        //Setting frame size and operations
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setLayout(new GridLayout());
        f.setTitle("Wedding Invitation");
        f.setSize(800,500);
        f.setResizable(false);

        GridLayout gl = new GridLayout(1,2); //Using grid layout to split frame down the middle evenly

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Creating the left side
        left.setPreferredSize(new Dimension(500,500));
        left.setLayout(new GridBagLayout());

        //Updating constraints
        gbc.gridx = 1;
        gbc.gridy = 1;

        //Creating and styling title
        JLabel title = new JLabel("Create your Dinner Party");
        title.setFont(titleFont);
        title.setBorder(new EmptyBorder(25,0,0,0));

        //Updating the title
        left.add(title,gbc);
        gbc.gridy = 2;

        //Creating a panel to ask the type of party
        JPanel DinnerType = new JPanel();
        DinnerType.setSize(new Dimension(500,200));
        DinnerType.setBorder(new EmptyBorder(15,20,0,20));

        //Question for the type of party
        JLabel DinnerTypeQ = new JLabel("What type of party would you like?");
        DinnerTypeQ.setFont(font1);

        dinnerTypesCombo.setPreferredSize(new Dimension(130,20));

        //Adding the question to the panel
        DinnerType.add(DinnerTypeQ);
        DinnerType.add(dinnerTypesCombo);

        dinnerTypesCombo.addActionListener(this); //Implementing action listener depending on the type of dinner

        //Adding panel to parent panel
        left.add(DinnerType,gbc);
        gbc.gridy = 4;

        //New subpanel to ask guest number
        JPanel customizeGuests = new JPanel();
        customizeGuests.setSize(500,200);

        //Question to ask the number of guests
        JLabel numGuestsQ = new JLabel("Enter your number of guests: ");
        numGuestsQ.setFont(font1);

        numGuests.addActionListener(this); //Implementing action listener to our global variable numGuests

        //Adding items to subpanel
        customizeGuests.add(numGuestsQ);
        customizeGuests.add(numGuests);

        //adding subpanel to parent panel
        left.add(customizeGuests,gbc);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Right side main panel
        right.setSize(new Dimension(300,500));
        right.setLayout(new GridBagLayout());


        //Labeling a title, customizing, and adding
        JLabel output = new JLabel("Displayed Card");
        output.setFont(titleFont);
        output.setBorder(new EmptyBorder(25,0,0,0));
        gbc2.gridy = 1;
        gbc2.gridx = 1;
        right.add(output, gbc2);

        JPanel displayedGuests = new JPanel(); //Creating another subpanel

        //Implementing functionality from the Party class into the card
        JLabel exclamation = new JLabel(myParty.displayInvitation()); //Calling party class
        gbc2.gridy = 3;
        right.add(exclamation, gbc2);

        //Adding both panels to frame
        f.add(left,gl);
        f.add(right,gl);
        f.setVisible(true); //setting frame visible
    }

    public static void main(String[] args){
        new UseDinnerParty(); //Recursive call
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Updating depending on if dinner party is selected
        if(dinnerTypesCombo.getSelectedItem()=="Dinner Party"){
            //New subpanel
            JPanel customizeFood = new JPanel();
            customizeFood.setSize(new Dimension(500,200));

            //Question for food options
            JLabel chooseFood = new JLabel("Please choose dinner for the night: ");
            chooseFood.setFont(font1);

            //Combo box for food options
            String[] foodOptions = {"Nothing","1 - Chicken", "2 - Vegetarian"};
            JComboBox dinnerOptions = new JComboBox(foodOptions);
            dinnerOptions.setPreferredSize(new Dimension(120,20));

            //Nested actionlistener :p updating right side to comply with the food selected
            dinnerOptions.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Figuring out which food is selected in combo box and updating DinnerParty class
                    if(dinnerOptions.getSelectedItem() == "1 - Chicken"){
                        myParty.setChoice(1);
                    }
                    else if(dinnerOptions.getSelectedItem() == "2 - Vegetarian"){
                        myParty.setChoice(2);
                    }
                    //Updating the right side with the selected food displayed
                    if (myParty.getChoice() != 0) {
                        JLabel dinnerChoice = new JLabel("Your food choice is option " + myParty.getChoice());
                        gbc2.gridy = 4;
                        right.add(dinnerChoice, gbc2);

                    }
                }
            }
            );

            //Adding the chosen options to the subpanel
            customizeFood.add(chooseFood);
            customizeFood.add(dinnerOptions);

            gbc.gridy = 3;
            left.add(customizeFood,gbc); //Adding subpanel to left panel


        }
        //Try and catch to avoid throwing a conversion exception with "" to int
        try{
            myParty.setGuests(Integer.parseInt(numGuests.getText())); //Trying to convert inputted number into an int which is passed to DinnerParty class
        }catch (Exception NumberFormatException){
            myParty.setGuests(0); //Setting zero as default in the DinnerParty class
        }
        JLabel guests = new JLabel("Number of guests coming: " + myParty.getGuests()); //Adding a label to display the number of guests

        gbc2.gridy = 2;
        right.add(guests, gbc2); //Adding the label for the number of guests to the right side

        //Refreshing technique
        f.invalidate();
        f.repaint();
        f.revalidate();
    }
}
