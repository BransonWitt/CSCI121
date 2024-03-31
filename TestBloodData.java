import java.util.Scanner;
public class TestBloodData {


    public static String askForBloodType(){
        Scanner bloodInput = new Scanner(System.in);
        System.out.println("Enter blood type:");

        String enteredBloodType = bloodInput.nextLine();
        return enteredBloodType;
    }

    public static void parseBloodType(String type, BloodData patient){
        assert(type.length() == 2 || type.length() == 3);

        try{
            String group;
            char rh;

            if(type.length() == 2){
                group = String.valueOf(type.charAt(0));
                rh = type.charAt(1);
            } else {
                group = type.substring(0,2);
                rh = type.charAt(2);
            }

            patient.setBloodGroup(BloodType.valueOf(group));
            patient.setRhFactor(rh);

        } catch(Exception err){
            System.out.println("Please enter a proper blood type");
            askForBloodType();
        }
    }

    public static void main(String[] args){
        //Initializing both patient
        BloodData patient1 = new BloodData();
        BloodData patient2 = new BloodData();

        //Asking for the blood type for patient2 initially
        String enteredBloodType = askForBloodType();
        parseBloodType(enteredBloodType, patient2);


        //Printing the current patient info
        System.out.println("");
        System.out.println("Current Patient Records:");
        System.out.println("###########################################################");
        System.out.println("Patient1: Blood Type  " + patient1.getBloodGroup() + patient1.getRhFactor());
        System.out.println("Patient2: Blood Type  " + patient2.getBloodGroup() + patient2.getRhFactor());
        System.out.println("");


        //Changing Patient 1
        Scanner p1Entry = new Scanner(System.in); //Scanner

        //Asking the question
        System.out.println("Would you like to change patient1? (y/n)");
        String response = p1Entry.nextLine();

        //Handling response
        if(response.equals("y")){
            String patient1BloodType = askForBloodType();
            parseBloodType(patient1BloodType, patient1);
        } else{
            System.out.println(response);
        }


        System.out.println("");
        System.out.println("Current Patient Records:");
        System.out.println("###########################################################");
        System.out.println("Patient1: Blood Type  " + patient1.getBloodGroup() + patient1.getRhFactor());
        System.out.println("Patient2: Blood Type  " + patient2.getBloodGroup() + patient2.getRhFactor());
        System.out.println("Thanks for your entry");
    }

}
