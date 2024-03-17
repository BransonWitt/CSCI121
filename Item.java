public class Item {
    //Declaring Attributes
    private String name;
    private Double price;
    //Constructor
    Item(String name, Double price){
        this.name = name;
        this.price = price;
    }
    //Methods to change in case of mistake
    public void changeName(String name){
        this.name = name;
    }
    public void changePrice(Double price){
        this.price = price;
    }
    //Get methods
    public String getName(){
        return this.name;
    }
    public Double getPrice(){
        return this.price;
    }
}
