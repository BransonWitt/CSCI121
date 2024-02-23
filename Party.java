public class Party {
    private int numGuests = 0;

    public void setGuests(int numGuests){
        this.numGuests = numGuests;
    }
    public int getGuests(){
        return this.numGuests;
    }



    public String displayInvitation() {
        return("Please come to my party!");
    }
}