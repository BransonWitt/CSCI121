import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.util.Calendar;
import java.text.ParseException;

import org.jdatepicker.impl.*;


public class TestWedding extends JFrame{
    //Declaring global Variables
    JFrame f; //Frame
    //Groom Variables
    JPanel Person1;
    JTextField p1First = new JTextField("Groom's First Name", 12);
    JTextField p1Last = new JTextField("Groom's Last Name", 12);
    //Bride Variables
    JPanel Person2;
    JTextField p2First = new JTextField("Bride's First Name", 12);
    JTextField p2Last = new JTextField("Bride's Last Name", 12);
    //Locations
    JPanel Place;
    JTextField location;
    Font normal = new Font(Font.SERIF, Font.PLAIN, 14); //Most used font
    JDatePickerImpl DatePicked;

    TestWedding(){
        //Setting up frame
        JFrame f = new JFrame();
        f.setResizable(false); //Not resizable
        f.setLayout(new GridBagLayout()); //Using Grid Bag Layout
        f.setSize(500, 500);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setTitle("Wedding Planner");

        JLabel title = new JLabel("Create a new wedding:");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        //Person1 JPanel
        Person1 = new JPanel();
        Person1.setSize(new Dimension(500,125));
        Person1.setLayout(new GridBagLayout());

        //Person 1 Contraints
        GridBagConstraints p1gbc = new GridBagConstraints();
        p1gbc.insets = new Insets(5, 5,0,5);
        p1gbc.gridy = 1;
        p1gbc.gridx = 1;

        //Title for asking
        JLabel p1a = new JLabel("Groom's Name:");
        p1a.setFont(normal);

        //Setting font for entries
        p1First.setFont(new Font(Font.SERIF, Font.PLAIN,12));
        p1Last.setFont(new Font(Font.SERIF, Font.PLAIN,12));

        //Adding items to panel
        Person1.add(p1a, p1gbc);
        p1gbc.gridy = 2;
        Person1.add(p1First, p1gbc);
        p1gbc.gridx = 2;
        Person1.add(p1Last, p1gbc);

        //Person2 JPanel Copy of Person1
        Person2 = new JPanel();
        Person2.setSize(new Dimension(500,125));
        Person2.setLayout(new GridBagLayout());

        GridBagConstraints p2gbc = new GridBagConstraints();
        p2gbc.insets = new Insets(5, 5,0,5);
        p2gbc.gridy = 1;
        p2gbc.gridx = 1;

        JLabel p2a = new JLabel("Bride's Name:");
        p2a.setFont(normal);

        p2First.setFont(new Font(Font.SERIF, Font.PLAIN,12));
        p2Last.setFont(new Font(Font.SERIF, Font.PLAIN,12));

        Person2.add(p2a, p2gbc);
        p2gbc.gridy = 2;
        Person2.add(p2First, p2gbc);
        p2gbc.gridx = 2;
        Person2.add(p2Last, p2gbc);

        //Creating the date model - used tutorial from: https://www.youtube.com/watch?v=LpCxpnhVxbw
        UtilDateModel model = new UtilDateModel();
        //Setting the properties used
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        //Using an anonymous class
        DatePicked = new JDatePickerImpl(datePanel, new AbstractFormatter(){
                private String datePattern = "MM/dd/yyyy"; //Month day year format
                private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern); //Using date formatter

                //Overriding methods
                @Override
                public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

                @Override
                public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return ""; //If doesn't have an object, return empty string
            }
        });

        //Place
        Place = new JPanel();
        Place.setSize(new Dimension(500, 100));
        Place.setLayout(new GridBagLayout());

        JLabel askLocation = new JLabel("Enter wedding venue and select date below:"); //Title to ask for items
        askLocation.setFont(normal);

        location = new JTextField(15); //Text field to ask for the location

        GridBagConstraints gbcl = new GridBagConstraints();
        gbcl.insets = new Insets(0, 5,0,5);
        gbcl.gridy = 1;
        gbcl.gridx = 1;

        Place.add(askLocation, gbcl);
        gbcl.gridx = 2;
        Place.add(location, gbcl);


        //Creating Constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15,5,15);
        gbc.gridx = 1;
        gbc.gridy = 1;

        //Adding items to frame
        f.add(title, gbc);
        gbc.gridy = 2;
        f.add(Person1, gbc);
        gbc.gridy = 3;
        f.add(Person2, gbc);
        gbc.gridy = 4;
        f.add(Place, gbc);
        gbc.gridy = 5;
        f.add(DatePicked,gbc);

        JButton button = new JButton("Finish"); //Button for when people are finished
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Getting the date
                LocalDate myDate = LocalDate.of((int)model.getYear(), (int)model.getMonth(), (int)model.getDay());

                //Creating person objects
                Person groom = new Person(p1First.getText(), p1Last.getText());
                Person bride = new Person(p2First.getText(), p2Last.getText());

                //Creating couple from person objects
                Couple weddingCouple = new Couple(bride, groom);

                //Creating wedding object
                Wedding newWedding = new Wedding(weddingCouple, location.getText(), myDate);

                //New JPanel to add with the final results to the frame
                JPanel setPlan = new JPanel();
                setPlan.setPreferredSize(new Dimension(500, 100));
                setPlan.setLayout(new GridLayout(3, 1));

                //Adding three Jlabels for the plan (time/location) and the bride and groom
                JLabel plan = new JLabel("Wedding Scheduled for " + myDate + " at " + newWedding.getLocation());
                JLabel Groom = new JLabel("Groom: "+ p1First.getText() + " " +  p1Last.getText());
                JLabel Bride = new JLabel("Bride: "+ p2First.getText() + " " +  p2Last.getText());

                //Adding items to frame
                setPlan.add(plan);
                setPlan.add(Groom);
                setPlan.add(Bride);
                gbc.gridy = 7;
                f.add(setPlan, gbc);

                //Refreshing page
                f.revalidate();
                f.repaint();
                f.pack();
            }
        });

        //Adding button to frame
        gbc.gridy = 6;
        f.add(button, gbc);

        //Showing Frame
        f.setVisible(true);
    }

    public static void main(String[] args){
        new TestWedding(); //Creating instance of class
    }

}
