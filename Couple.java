public class Couple {
    //Private vraibles
    private Person bride;
    private Person groom;

    //Constructor
    public Couple(Person Person1, Person Person2){
        this.bride = Person1;
        this.groom = Person2;
    }

    //Get methods
    public Person getBride(){
        return this.bride;
    }

    public Person getGroom(){
        return this.groom;
    }
}
