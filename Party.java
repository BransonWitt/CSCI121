public class Party {
    //Private variable for the number of Guests
    private int numGuests = 0;

    //Set method for the number of guests
    public void setGuests(int numGuests){
        this.numGuests = numGuests;
    }

    //Get method for the number of guests
    public int getGuests(){
        return this.numGuests;
    }

    //Updating printing to a return statement to implement GUI
    public String displayInvitation() {
        return("Please come to my party!");
    }
}
