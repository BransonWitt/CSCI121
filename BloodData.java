enum BloodType{
    A,
    AB,
    B,
    O;
}
public class BloodData {
    //Private Variables
    private char rhFactor;
    private BloodType bloodGroup;

    //Constructor
    BloodData(){
        this.rhFactor = '+';
        this.bloodGroup = BloodType.O;
    }

    //Overloaded Constructor
    BloodData(BloodType group, char rh){
        //Asserting a correct value inputted
        assert (rh == '+' || rh == '-');

        this.rhFactor = rh;
        this.bloodGroup = group;
    }

    //Set Method
    public void setRhFactor(char rh){
        //Asserting a correct value inputted
        assert (rh == '+' || rh == '-');

        this.rhFactor = rh;
    }

    public void setBloodGroup(BloodType group){
        this.bloodGroup = group;
    }

    //Get Methods
    public BloodType getBloodGroup(){
        return this.bloodGroup;
    }
    public char getRhFactor(){
        return this.rhFactor;
    }

    @Override
    public String toString() {
        return this.bloodGroup + String.valueOf(this.rhFactor);
    }
}
