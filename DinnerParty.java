public class DinnerParty extends Party{
    //Declaring private int
    private int dinnerChoice = 0;

    //Set method
    public void setChoice(int choice){
        this.dinnerChoice = choice;
    }

    //Get method
    public int getChoice() {
        return this.dinnerChoice;
    }
}
