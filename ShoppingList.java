import java.util.ArrayList;

public class ShoppingList {
    //declaring attributes
    private ArrayList<ItemOrder> shoppingList;
    //Constructor
    ShoppingList(){
        this.shoppingList = new ArrayList<ItemOrder>();
    }
    //Set method
    public void addItem(ItemOrder item, int location){
        this.shoppingList.add(item);
    }
    //Get Method
    public ItemOrder getItem(int location){
        try{
            return this.shoppingList.get(location);
        }catch (Exception err){
            return null;
        }
    }
    //returning the length of the list
    public int returnLength(){
        return this.shoppingList.size();
    }
    //removing an item from the list
    public void removeItem(ItemOrder item){
        this.shoppingList.remove(item);
    }
}
