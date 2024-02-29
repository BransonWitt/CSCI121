import java.time.*;
public class Wedding {
    //Private variables
    private String location;
    private LocalDate date;
    private Couple couple;
    //Constructors
    public Wedding(Couple couple, String location, LocalDate Date){
        this.couple = couple;
        this.date = Date;
        this.location = location;
    }
    //get methods
    public String getLocation(){return this.location;}
    public LocalDate getDate(){return this.date;}
    public Couple getCouple(){return this.couple;}
}
