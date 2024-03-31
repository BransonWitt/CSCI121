import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class GUIPatientData {
    //Public Variables
    JFrame f;
    JComboBox<Patient> dropDown  = new JComboBox<>();
    ArrayList<Patient> patientList = new ArrayList<>(); //Ok so this second list is a mistake because I was using it to debug something with the combo box, and I honestly just kept it ....
    GridBagConstraints gbc;
    JPanel header;
    JPanel body;
    JTextField nameText;
    JTextField ageText;
    JTextField idNumberText;
    JTextField bloodTypeText;
    Font normalFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    JButton addNewPatient;
    JButton updateExistingPatient;
    JLabel currentName;
    JLabel currentAge;
    JLabel currentID;
    JLabel currentBloodType;
    Patient currentPatient;

    //Constructor
    GUIPatientData(){
        //Setting up frame
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,400);
        f.setResizable(false);
        f.setLayout(new GridBagLayout());
        f.setTitle("Patient Data Manager");

        //Grid Cosnstraints
        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;

        //Generating three beginning
        Patient patient1 = new Patient(12345, (short) 50,"AB+","Geoff");
        Patient patient2 = new Patient();
        Patient patient3 = new Patient();

        //Adding items to drop down
        dropDown.addItem(patient1);
        dropDown.addItem(patient2);
        dropDown.addItem(patient3);

        //Adding items to a seperate list
        patientList.add(patient1);
        patientList.add(patient2);
        patientList.add(patient3);

        //Customizing drop down
        dropDown.setPreferredSize(new Dimension(90,20));
        dropDown.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));

        //Setting up a header panel
        header = new JPanel();
        header.setPreferredSize(new Dimension(600,40));
        header.setMinimumSize(new Dimension(600,40));
        header.setBackground(Color.WHITE);
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        JLabel patientSelectTitle = new JLabel("Please Select a Patient:  ");
        patientSelectTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        header.add(patientSelectTitle);
        header.add(dropDown);


        //Setting up a body panel
        body = new JPanel();
        body.setMinimumSize(new Dimension(600, 340));
        body.setPreferredSize(new Dimension(600,340));
        body.setLayout(new GridBagLayout());
        body.setBackground(Color.white);

        //Constraints for the body
        GridBagConstraints gbcBody = new GridBagConstraints();
        gbcBody.insets = new Insets(0,0,0,0);
        gbcBody.gridy = 1;
        gbcBody.gridx = 1;

        //Title for patient details
        JLabel title = new JLabel("Patient Details");
        title.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        title.setSize(new Dimension(600,50));

        //Panel for the right of the JPanel
        JPanel right = new JPanel();
        right.setMinimumSize(new Dimension(290,300));
        right.setPreferredSize(new Dimension(290,300));
        right.setLayout(new GridBagLayout());
        right.setBackground(Color.white);

        //Constraints for the right of the JPanel
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(7,0,3,0);
        gbcRight.gridx = 1;
        gbcRight.gridy = 1;

        //The following is setting up the entry boxes
        JLabel nameLabel = new JLabel("Patient Name: ");
        nameLabel.setFont(normalFont);

        nameText = new JTextField(12);

        JLabel ageLabel = new JLabel("Age of Patient:  ");
        ageLabel.setFont(normalFont);

        ageText = new JTextField(12);

        JLabel idLabel = new JLabel("Enter Patient ID Number:  ");
        idLabel.setFont(normalFont);

        idNumberText = new JTextField(12);

        JLabel bloodTypeLabel = new JLabel("Enter Patient Blood Type:   ");
        bloodTypeLabel.setFont(normalFont);

        bloodTypeText = new JTextField(12);

        //Buttons for the bottom of the right
        addNewPatient = new JButton("Generate New Patient");
        addNewPatient.setFont(normalFont);

        updateExistingPatient = new JButton("Update Current Patient");
        updateExistingPatient.setFont(normalFont);

        //Adding items to the right side
        right.add(nameLabel, gbcRight);
        gbcRight.gridx = 2;
        right.add(nameText, gbcRight);
        gbcRight.gridy = 2;
        gbcRight.gridx = 1;
        right.add(ageLabel, gbcRight);
        gbcRight.gridx = 2;
        right.add(ageText, gbcRight);
        gbcRight.gridy = 3;
        gbcRight.gridx = 1;
        right.add(idLabel, gbcRight);
        gbcRight.gridx = 2;
        right.add(idNumberText, gbcRight);
        gbcRight.gridy = 4;
        gbcRight.gridx = 1;
        right.add(bloodTypeLabel, gbcRight);
        gbcRight.gridx = 2;
        right.add(bloodTypeText, gbcRight);
        gbcRight.gridy = 5;
        gbcRight.gridx = 1;
        gbcRight.gridwidth = 2;
        right.add(addNewPatient, gbcRight);
        gbcRight.gridy = 6;
        right.add(updateExistingPatient, gbcRight);


        //I've been coding for 4 hours and I just realized I mixed up left and right
        //Left panel
        JPanel left = new JPanel();
        left.setMinimumSize(new Dimension(300,300));
        left.setPreferredSize(new Dimension(300,300));
        left.setBackground(Color.white);
        left.setLayout(new GridBagLayout());

        //Left panel constraints
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 1;
        gbcLeft.gridy = 1;

        //Setting up initial patient details
        currentPatient = patientList.get(dropDown.getSelectedIndex());

        currentName = new JLabel("Patient Name: " + currentPatient.getName());
        currentAge = new JLabel("Patient Age: " + currentPatient.getAge());
        currentID = new JLabel("Patient ID: " + currentPatient.getIdNumber());
        currentBloodType = new JLabel("Patient Blood Type: " + currentPatient.getBloodType());

        //Customization
        currentName.setFont(normalFont);
        currentAge.setFont(normalFont);
        currentID.setFont(normalFont);
        currentBloodType.setFont(normalFont);

        //Adding items to the right side
        left.add(currentName, gbcLeft);
        gbcLeft.gridy = 2;
        left.add(currentAge, gbcLeft);
        gbcLeft.gridy = 3;
        left.add(currentID, gbcLeft);
        gbcLeft.gridy =  4;
        left.add(currentBloodType, gbcLeft);

        //Adding title, left, and right to the body
        gbcBody.gridwidth = 2;
        body.add(title,gbcBody);
        gbcBody.gridwidth = 1;
        gbcBody.gridy = 2;
        body.add(right, gbcBody);
        gbcBody.gridx = 2;
        body.add(left, gbcBody);

        //Adding functionality to the drop down to change patient details
        dropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPatient = patientList.get(dropDown.getSelectedIndex()); //Updating the current patient we're looking at

                //Changing the current patient details based on our current patient defined above
                currentName.setText("Patient Name: " + currentPatient.getName());
                currentAge.setText("Patient Age: " + currentPatient.getAge());
                currentID.setText("Patient ID: " + currentPatient.getIdNumber());
                currentBloodType.setText("Patient Blood Type: " + currentPatient.getBloodType());

                //System.out.println(patientList.get(dropDown.getSelectedIndex()).getName());
            }
        });

        //Button to add a new patient
        addNewPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //Creating items for all entries
                    short age = Short.parseShort(ageText.getText());
                    int id = Integer.parseInt(idNumberText.getText());
                    String name = nameText.getText();
                    String bloodType = bloodTypeText.getText();

                    //Creating a new default patient based on those items
                    Patient addedPatient = new Patient();

                    //Updating the patient details, honestly could do this in the constructor but was doing it for debugging and couldn't be bothered
                    addedPatient.setName(name);
                    addedPatient.changeID(id);
                    addedPatient.changeBloodType(bloodType);
                    addedPatient.changeAge(age);

                    //Adding patient to comboBox and the arrayList
                    dropDown.addItem(addedPatient);
                    patientList.add(addedPatient);

                    //Clearing entries
                    ageText.setText("");
                    idNumberText.setText("");
                    nameText.setText("");
                    bloodTypeText.setText("");


                }catch(Exception err){ //Catching any potential errors
                    System.out.println("Please Enter Proper Entries");
                }
            }
        });
        //Button to update existing patient
        updateExistingPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Looking for all textBoxes that aren't empty and changing the current selected based on the entry and putting it into the arrayList
                if(!Objects.equals(ageText.getText(), "")){
                    short newAge = Short.parseShort(ageText.getText());
                    patientList.get(dropDown.getSelectedIndex()).changeAge(newAge);
                }
                if(!Objects.equals(idNumberText.getText(), "")){
                    int newID = Integer.parseInt(idNumberText.getText());
                    patientList.get(dropDown.getSelectedIndex()).changeID(newID);
                }
                if(!Objects.equals(nameText.getText(), "")){
                    String newName = nameText.getText();
                    patientList.get(dropDown.getSelectedIndex()).setName(newName);
                }
                if(!Objects.equals(bloodTypeText.getText(), "")){
                    String newbloodType = bloodTypeText.getText();
                    patientList.get(dropDown.getSelectedIndex()).changeBloodType(newbloodType);
                }

                //Clearing entries
                ageText.setText("");
                idNumberText.setText("");
                nameText.setText("");
                bloodTypeText.setText("");

                //Updating the current patient again
                currentPatient = patientList.get(dropDown.getSelectedIndex());

                //Updating patient details
                currentName.setText("Patient Name: " + currentPatient.getName());
                currentAge.setText("Patient Age: " + currentPatient.getAge());
                currentID.setText("Patient ID: " + currentPatient.getIdNumber());
                currentBloodType.setText("Patient Blood Type: " + currentPatient.getBloodType());

                //Reanimating jic
                f.revalidate();
                f.repaint();
            }

        });

        //Adding the header and the body
        f.add(header, gbc);
        gbc.gridy = 2;
        f.add(body, gbc);

        f.setVisible(true);
    }

    public static void main(String[] args) {
        GUIPatientData gui = new GUIPatientData(); //Initializing gui
    }
}