public class LinkedList <T>{
    private Node<T> head;

    //Nested Node Class
    static class Node<T>{
        //Doubly Linked List
        private Node<T> previous;
        private Node<T> next;

        private T obj;

        //Two separate Constructors, one for the first node, and one for every thing else
        Node(T obj) {
            this.obj = obj;
        }

        //Changing the object value
        public void changeObject(T newObject){
            this.obj = newObject;
        }
        //Returning the Object
        public T getObj(){
            return this.obj;
        }
        //Setting the next node
        public void setNext(Node<T> nextNode){
            this.next = nextNode;
        }
        //Returning the next node
        public Node<T> getNextNode(){
            return this.next;
        }
        //Changing the previous node
        public void changePreviousNode(Node<T> newPrevious){
            this.previous = newPrevious;
        }
        //Returning the previous node for backwards tracking
        public Node<T> returnPreviousNode(){
            return this.previous;
        }
    }
    //Method that returns the last node
    public Node<T> returnLastNode(){
        Node<T> lastNode = null; //last node seen

        if(this.head != null){
            lastNode = this.head;
            //while true loop to loop through all nodes and break when a node is not assigned a next
            while (true){
                if(lastNode.getNextNode() == null){
                    break;
                }
                else{
                    lastNode = lastNode.getNextNode();
                }
            }
        }

        return lastNode;
    }

    //Appending obj
    public void append(T object){
        Node<T> newNode = new Node<>(object); //Initializing a new node

        //If the head is null, assign this node to be the head
        if(this.head == null){
            this.head = newNode;
        }
        else{
            //attaching the node to the link
            newNode.changePreviousNode(returnLastNode());
            returnLastNode().setNext(newNode);
        }
    }
    //Returns the length of the list
    public int listLength(){
        int length = 0;

        if(this.head == null){
            return length; //Length of 0 if the head is empty
        }
        else{
            Node<T> lastSeenNode = this.head; //starting at the head

            //while true loop
            while(true){
                //catching when the link ends
                if(lastSeenNode.getNextNode() == null){
                    length += 1;
                    return length;
                }
                //progressing the link by 1
                else{
                    length += 1;
                    lastSeenNode = lastSeenNode.getNextNode();
                }
            }
        }
    }
    //returning the object at a given int position
    public Node<T> getObjectAt(int position){
        assert position < listLength(); //asserting a valid position

        //same construction as the method above
        Node<T> lastSeenNode = this.head;
        int currentPosition = 0;
        while(currentPosition != position){
            lastSeenNode = lastSeenNode.getNextNode();
            currentPosition += 1;
        }

        //returning the object at that position when the position is reached
        return lastSeenNode;
    }

    //returning an item at a specified int position
    public T getItemAt(int position){
        return getObjectAt(position).getObj();
    }

    //removes the item at a specified position
    public void removeItemAt(int position){
        Node<T> lastSeen = getObjectAt(position);

        //Removing the lastSeen item out of the link
        lastSeen.returnPreviousNode().setNext(lastSeen.getNextNode());
        lastSeen.getNextNode().changePreviousNode(lastSeen.returnPreviousNode());
    }
    //removes the item after position p (I can't input a node because of the nested class structure I used for the nodes)
    public void removeAfter(int position){
        //Asserting that there is an item after
        if(listLength() - 1 > position){
            removeItemAt(position + 1);
        }
    }
    //returns an exact copy of the LinkedList
    public LinkedList<T> copy(){
        LinkedList<T> copiedList = new LinkedList<>(); //new LinkedList for a new memory address

        //Checking if the list we're copying isn't empty
        if(listLength() == 0){
            return copiedList;
        }
        else {
            for (int i = 0; i < listLength(); i++) {
                copiedList.append(getItemAt(i)); //returning the item at the for loop position
            }
            return copiedList;
        }
    }

    //removing duplicates of an item, this is more efficient than what the prompt is because it only requires one input
    public void removeDuplicate(T item){
        //Boolean value to determine if we've seen an item already once
        Boolean alreadySeen = false;

        //Looping over the entire list
        for (int i = 0; i < listLength(); i++) {
            T itemAtPosition = getItemAt(i); //variable for the value of the Node
            if(item == itemAtPosition){
                if(alreadySeen == false){
                    alreadySeen = true; //If we haven't already seen the item, now we have
                }
                //if we've already seen the item at least once, we can remove this item
                else if(alreadySeen == true){
                    removeItemAt(i);
                }
            }
        }
    }

    //This method returns what type of objects we are working with
    private Class<?> getClazz(){
        return this.head.getObj().getClass();
    }

    //returns the max item seen
    public <t extends Comparable<t>> t max(){
        //Honestly this is some black magic stuff

        //Zero length handler
        if(listLength() == 0){
            return null; //design choice to work with null instead of 0
        }

        //Try catch wrapper
        try {
            t max = (t) this.head.getObj(); //this type of type casting is bad, but I don't know a way around

            //looping over every item and comparing the item at that place to our max known value
            for (int i = 0; i < listLength(); i++) {
                t newItem = (t) getItemAt(i);
                if(newItem.compareTo(max) > 0){
                    max = newItem;
                }
            }
            return max; //This won't work well for Strings, it only returns the last item
                        //However, this works amazing for number comparable data types

        } catch (Exception err){
            System.out.println("Error incurred: Please use a list with comparable data types");
        }
        return null;
    }

    //Changing the value at some point in our list
    public void changeValueAt(int position, T newValue){
        getObjectAt(position).changeObject(newValue);
    }
}
