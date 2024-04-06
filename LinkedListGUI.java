import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class LinkedListGUI {
    //Global variables
    JFrame f;
    JPanel settings;
    LinkedListVisualized visualList;
    GridBagConstraints gbc = new GridBagConstraints();
    LinkedList<String> globalList = new LinkedList<>();

    //Constructor
    LinkedListGUI(){
        //Setting up the frame
        f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setTitle("Linked List");
        //f.setResizable(false);
        f.setSize(800,525);
        f.setLayout(new GridBagLayout());

        //Setting up constraints
        gbc.gridy = 1;
        gbc.gridx= 1;
        gbc.insets = new Insets(0,0,0,0);

        //Setting up the left side
        settings = new JPanel();
        settings.setPreferredSize(new Dimension(300,500));
        settings.setMinimumSize(new Dimension(300,500));
        settings.setLayout(new GridBagLayout());

        //Constraints for the left side
        GridBagConstraints settingGBC = new GridBagConstraints();
        settingGBC.gridy = 1;
        settingGBC.gridx = 1;
        settingGBC.insets = new Insets(0,0,0,0);

        //Title
        JLabel title = new JLabel("Add to Linked List");
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        //Extra Information below title
        JLabel desc = new JLabel("List displayed visually to right");
        desc.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        JLabel desc2 = new JLabel("Top is previous address and Bottom is next Address");
        desc2.setFont(new Font(Font.SERIF, Font.PLAIN, 12));

        //JPanel for an input box and buttons
        JPanel inputBox = new JPanel();
        inputBox.setLayout(new GridBagLayout());
        inputBox.setMinimumSize(new Dimension(300,80));
        inputBox.setPreferredSize(new Dimension(300,80));
        inputBox.setBackground(Color.white);

        //Constraints for the panel
        GridBagConstraints inputGBC = new GridBagConstraints();
        inputGBC.gridx = 1;
        inputGBC.gridy = 1;
        inputGBC.insets = new Insets(10,0,5,0);

        JTextField input = new JTextField(13); //Text input to create a new list object
        input.setMinimumSize(new Dimension(150, 20));
        input.setColumns(13);
        JButton updateList = new JButton("Add"); //Button to add the text to the list
        updateList.setPreferredSize(new Dimension(40, 25));
        JButton clear = new JButton("Clear"); //Button to clear the list

        //Action listener to add text to the display
        updateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Caps out at a length of 25
                if(globalList.listLength() < 25){
                    globalList.append(input.getText()); //Adding the text to the list
                    input.setText("");//Clearing the text enter

                    //removing the previous item
                    f.remove(visualList);
                    f.revalidate();
                    f.repaint();

                    //Creating a new right side
                    visualList = new LinkedListVisualized(500,500, globalList);

                    gbc.gridx = 2;
                    f.add(visualList, gbc); //Adding the new right side and updating the frame

                    f.revalidate();
                    f.repaint();
                }
                else{
                   input.setText("");
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Resetting the list
                globalList = new LinkedList<>();
                globalList.append("Head");

                //removing the previous right side
                f.remove(visualList);
                f.revalidate();
                f.repaint();

                //creating a new right side based off our new linked list
                visualList = new LinkedListVisualized(500,500, globalList);

                //Adding item to frame
                gbc.gridx = 2;
                f.add(visualList, gbc);

                //Updating frame
                f.revalidate();
                f.repaint();
            }
        });

        //Adding items to the JPanel for inputs and buttons
        inputBox.add(input, inputGBC);
        inputGBC.gridx = 2;
        inputBox.add(updateList, inputGBC);
        inputGBC.gridwidth = 2;
        inputGBC.gridx = 1;
        inputGBC.gridy = 2;
        inputBox.add(clear, inputGBC);

        //Adding items to the left side
        settings.setBackground(Color.white);
        settings.add(title, settingGBC);
        settingGBC.gridy = 2;
        settings.add(desc, settingGBC);
        settingGBC.gridy = 3;
        settings.add(desc2, settingGBC);
        settingGBC.gridy = 4;
        settings.add(inputBox, settingGBC);

        //Adding an original item to the linked list to show something
        globalList.append("Head");

        //Creating an initial right side based off a class that extends Jpanel
        visualList = new LinkedListVisualized(500,500, globalList);

        //Adding the left and right sides to frame
        f.add(settings,gbc);
        gbc.gridx = 2;
        f.add(visualList, gbc);

        //Showing frame
        f.setVisible(true);
    }

    public static void main(String[] args){
        LinkedListGUI gui = new LinkedListGUI(); //Initializing a LinkedListGUI item
    }
}
