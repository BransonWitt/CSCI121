import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UseDinnerParty extends JFrame implements ActionListener{
    JFrame f = new JFrame();
    DinnerParty myParty = new DinnerParty();
    JPanel left = new JPanel();
    JPanel right = new JPanel();
    JLabel guests;
    Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    Font font1 = new Font(Font.SERIF, Font.PLAIN, 14);
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbc2 = new GridBagConstraints();
    String[] DinnerTypes = {"Party","Dinner Party"};
    JComboBox dinnerTypesCombo = new JComboBox(DinnerTypes);
    JTextField numGuests = new JTextField(13);
    JLabel dinnerChoice;
    UseDinnerParty(){
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setLayout(new GridLayout());
        f.setTitle("Wedding Invitation");
        f.setSize(800,500);
        f.setResizable(false);

        GridLayout gl = new GridLayout(1,2);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        left.setPreferredSize(new Dimension(500,500));
        left.setLayout(new GridBagLayout());


        gbc.gridx = 1;
        gbc.gridy = 1;

        JLabel title = new JLabel("Create your Dinner Party");
        title.setFont(titleFont);
        title.setBorder(new EmptyBorder(25,0,0,0));

        left.add(title,gbc);
        gbc.gridy = 2;

        JPanel DinnerType = new JPanel();
        DinnerType.setSize(new Dimension(500,200));
        DinnerType.setBorder(new EmptyBorder(15,20,0,20));

        JLabel DinnerTypeQ = new JLabel("What type of dinner would you like?");
        DinnerTypeQ.setFont(font1);


        dinnerTypesCombo.setPreferredSize(new Dimension(130,20));

        DinnerType.add(DinnerTypeQ);
        DinnerType.add(dinnerTypesCombo);

        dinnerTypesCombo.addActionListener(this);


        left.add(DinnerType,gbc);
        gbc.gridy = 4;


        JPanel customizeGuests = new JPanel();
        customizeGuests.setSize(500,200);

        JLabel numGuestsQ = new JLabel("Enter your number of guests: ");
        numGuestsQ.setFont(font1);


        numGuests.addActionListener(this);

        customizeGuests.add(numGuestsQ);
        customizeGuests.add(numGuests);


        left.add(customizeGuests,gbc);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        right.setSize(new Dimension(300,500));
        right.setLayout(new GridBagLayout());



        JLabel output = new JLabel("Displayed Card");
        output.setFont(titleFont);
        output.setBorder(new EmptyBorder(25,0,0,0));
        gbc2.gridy = 1;
        gbc2.gridx = 1;
        right.add(output, gbc2);

        JPanel displayedGuests = new JPanel();



        JLabel exclamation = new JLabel(myParty.displayInvitation());
        gbc2.gridy = 3;
        right.add(exclamation, gbc2);


        f.add(left,gl);
        f.add(right,gl);
        f.setVisible(true);
    }

    public static void main(String[] args){
        new UseDinnerParty();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(dinnerTypesCombo.getSelectedItem()=="Dinner Party"){
            JPanel customizeFood = new JPanel();
            customizeFood.setSize(new Dimension(500,200));

            JLabel chooseFood = new JLabel("Please choose dinner for the night: ");
            chooseFood.setFont(font1);

            String[] foodOptions = {"Nothing","1 - Chicken", "2 - Vegetarian"};
            JComboBox dinnerOptions = new JComboBox(foodOptions);
            dinnerOptions.setPreferredSize(new Dimension(120,20));

            dinnerOptions.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(dinnerOptions.getSelectedItem() == "1 - Chicken"){
                        myParty.setChoice(1);
                    }
                    else if(dinnerOptions.getSelectedItem() == "2 - Vegetarian"){
                        myParty.setChoice(2);
                    }
                    if (myParty.getChoice() != 0) {
                        JLabel dinnerChoice = new JLabel("Your food choice is option " + myParty.getChoice());
                        gbc2.gridy = 4;
                        right.add(dinnerChoice, gbc2);

                    }
                }
            }
            );

            customizeFood.add(chooseFood);
            customizeFood.add(dinnerOptions);

            gbc.gridy = 3;
            left.add(customizeFood,gbc);


        }

        try{
            myParty.setGuests(Integer.parseInt(numGuests.getText()));
        }catch (Exception NumberFormatException){
            myParty.setGuests(0);
        }
        JLabel guests = new JLabel("Number of guests coming: " + myParty.getGuests());

        gbc2.gridy = 2;
        right.add(guests, gbc2);

        f.invalidate();
        f.repaint();
        f.revalidate();
    }
}
