public class ItemOrder extends Item{
    private Integer quantity;
    //Super constructor
    ItemOrder(String name, Double unitPrice, Integer quantity) {
        super(name, unitPrice);
        this.quantity = quantity;
    }

    //set method
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    //getMethod
    public int getQuantity(){
        return this.quantity;
    }
}
