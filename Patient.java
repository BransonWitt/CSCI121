public class Patient {
    //Global Variables
    private int idNumber;
    private short age;
    private BloodData bloodType;
    private String name;


    public static void parseBloodType(String type, BloodData patient){
        assert(type.length() == 2 || type.length() == 3); //Asserting proper blood type

        //Try catch to assure that it is a valid group
        try{
            String group;
            char rh;

            //Getting the group and rh depending on the length of the entered string
            if(type.length() == 2){
                group = String.valueOf(type.charAt(0));
                rh = type.charAt(1);
            } else {
                group = type.substring(0,2);
                rh = type.charAt(2);
            }

            //Updating the fields of an already constructed type
            patient.setBloodGroup(BloodType.valueOf(group));
            patient.setRhFactor(rh);

        } catch(Exception err){
            System.out.println("Please enter a proper blood type");
        }
    }
    //Default Constructor
    Patient(){
        this.idNumber = 0;
        this.age = 0;
        this.bloodType = new BloodData();
        this.name = "Anonymous";
    }
    //Variable Constructor
    Patient(int id, short age, String bType, String name){
        this.bloodType = new BloodData();
        parseBloodType(bType, this.bloodType);
        this.name = name;
        this.idNumber = id;
        this.age = age;
    }

    //Set Methods
    public void changeAge(short newAge){
        this.age = newAge;
    }
    public void changeID(int id){
        this.idNumber = id;
    }
    public void changeBloodType(String type){
        parseBloodType(type, this.bloodType); //Uses the parse blood type method to change
    }
    public void setName(String name){
        this.name = name;
    }

    //Get Methods
    public BloodData getBloodType(){
        return this.bloodType;
    }
    public short getAge(){
        return this.age;
    }
    public int getIdNumber(){
        return this.idNumber;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
