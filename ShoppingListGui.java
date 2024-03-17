import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingListGui extends JFrame{
    //Instantiating global variables
    JFrame f = new JFrame();
    JPanel listP = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));
    JLabel totalCost = new JLabel("$0.00");
    ShoppingList globalList = new ShoppingList();

    //Method to remove null values from the global list
    public void removeAllNull(){
        //For loop to loop over the list
        for(int i =0; i <= globalList.returnLength(); i++){
            //Garbage Collection for null values
            if(globalList.getItem(i) == null){
                globalList.removeItem(globalList.getItem(i));
            }
        }
    }

    //Method to add a row to the inside panel
    public void addRow(ItemOrder item){
        JPanel container = new JPanel(); //Container to hold all the information we're adding

        //Layouts
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 1;

        //Storing the font in a variable to reference
        Font myFont = new Font(Font.SANS_SERIF, Font.PLAIN, 13);

        //Creating the labels for the name of each item entered in the text entry, also setting font and size
        JLabel description = new JLabel(item.getName());
        description.setFont(myFont);
        description.setPreferredSize(new Dimension(190,15));
        JLabel price = new JLabel("$"+item.getPrice());
        price.setPreferredSize(new Dimension(60,15));
        price.setFont(myFont);
        JLabel quantity = new JLabel(String.valueOf(item.getQuantity()));
        quantity.setPreferredSize(new Dimension(20,15));
        quantity.setFont(myFont);

        //Creating the delete button
        JButton deleteButton= new JButton("x");
        deleteButton.setPreferredSize(new Dimension(20,20));
        deleteButton.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
        //Adding functionality
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Removing from canvas and removing from the global list
                listP.remove(container);
                globalList.removeItem(item);

                //Updating
                f.revalidate();
                f.repaint();
            }
        });

        //Adding our labels to the container
        container.add(description, gbc2);
        gbc2.gridx = 2;
        container.add(price, gbc2);
        gbc2.gridx = 3;
        container.add(quantity, gbc2);
        gbc2.gridx = 4;
        container.add(deleteButton, gbc2);

        //Finally adding the container into the panel
        listP.add(container);
    }
    //Constructor
    ShoppingListGui(){
        //Setting up frame
        f.setSize(350, 500);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(new GridBagLayout());

        //Constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10,3, 10);
        gbc.gridy = 1;
        gbc.gridx = 1;

        //Title
        JLabel title = new JLabel("Create your Shopping List");
        title.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        f.add(title, gbc);

        //Actual Shopping List
        //idk which one of these following size methods have priority, but both make it work :p
        listP.setMinimumSize(new Dimension(310, 300));
        listP.setPreferredSize(new Dimension(310,300));
        //Constraints for the panel
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 1;

        //My attempt at creating a scroll bar
        JScrollPane scroll = new JScrollPane(listP);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Header
        JLabel header = new JLabel("Description                                     Price          Qty"); //Jerry Rigged
        header.setBorder(new EmptyBorder(5,5,5,5));
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        gbc.gridy = 2;
        f.add(header,gbc); //Adding to frame

        //Adding the container for all the entered items to the frame
        gbc.gridy = 3;
        f.add(listP, gbc);
        f.add(scroll);

        //Item add container and setting layout
        JPanel itemAdd = new JPanel();
        itemAdd.setMinimumSize(new Dimension(310, 30));
        itemAdd.setLayout(new FlowLayout());

        //Creating entry boxes
        JTextField itemEntry = new JTextField(15);
        JLabel dollarSign = new JLabel("$");
        JTextField priceEntry = new JTextField(5);
        JTextField quantityEntry = new JTextField(3);

        //Adding items to the container
        itemAdd.add(itemEntry);
        itemAdd.add(dollarSign);
        itemAdd.add(priceEntry);
        itemAdd.add(quantityEntry);

        //Adding the container to the grid
        gbc.gridy = 4;
        f.add(itemAdd, gbc);

        //Creating a panel for the bottom
        JPanel bottom = new JPanel();

        //Setting constraints
        bottom.setLayout(new GridBagLayout());
        GridBagConstraints bgbc = new GridBagConstraints();
        bgbc.insets = new Insets(0, 5,0, 5);
        bgbc.gridy = 1;
        bgbc.gridx = 1;

        //Creating an add to list button
        JButton addItem = new JButton("Add to list");
        //Functionality
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creating an ItemOrder object using the entry
                ItemOrder newItem = new ItemOrder(itemEntry.getText(), Double.parseDouble(priceEntry.getText()), Integer.parseInt(quantityEntry.getText()));

                //Adding our new ItemOrder to the list of items
                globalList.addItem(newItem, globalList.returnLength() + 1);


                //Removing null values
                removeAllNull();

                if(globalList.returnLength() <= 15){ //Capping the canvas at 15 because I can't figure out the scroll bar for the life of me
                    //Calling method to add to the row
                    addRow(newItem);
                }

                //Resetting the entry boxes
                itemEntry.setText("");
                priceEntry.setText("");
                quantityEntry.setText("");

                //Updating the panel
                listP.revalidate();
                listP.repaint();

            }
        });

        //Adding the button to the container
        bottom.add(addItem, bgbc);

        //New button to calculate total
        JButton calcTotal = new JButton("Calculate Total:");
        //Method to calculate total
        calcTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double calcPrice = 0.0; //Variable to keep track

                //For loop to loop over the list
                for(int i =0; i <= globalList.returnLength(); i++){
                    if (globalList.getItem(i) != null){
                        ItemOrder obj = globalList.getItem(i);
                        calcPrice += obj.getPrice() * obj.getQuantity();
                    }

                }
                calcPrice = Math.round(calcPrice * 100.0)/100.0; //Rounding the value

                //Changing the text on the frame and updating the frame
                totalCost.setText("$" + calcPrice);
                f.repaint();
                f.revalidate();

            }
        });
        //Adding the button to the frame
        bgbc.gridx = 2;
        bottom.add(calcTotal, bgbc);

        //Adding the JLabel that holds the value
        bgbc.gridx = 3;
        bottom.add(totalCost, bgbc);

        //Adding the bottom container to the frame
        gbc.gridy = 5;
        f.add(bottom, gbc);

        //Showing the frame
        f.setVisible(true);
    }

    public static void main(String[] args) {
        ShoppingListGui g = new ShoppingListGui(); //Creating the object
    }
}