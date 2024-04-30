import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryTree {

    //Embedded Class
    class BinaryNode{
        //Global Variables
        private BinaryNode parent;
        private BinaryNode left;
        private BinaryNode right;
        private String value;

        //Constructor
        BinaryNode(BinaryNode parent, String input){
            //Adding a parent
            this.parent = parent;

            //Setting value
            this.value = input;
        }

        //Return the stored value in the node
        public String getValue(){
            return this.value;
        }
        public void changeValue(String newValue){
            this.value = newValue;
        }

        //Get and set methods for parent node
        public void changeParent(BinaryNode newParent){
            this.parent = newParent;
        }
        public BinaryNode returnParent(){
            return this.parent;
        }

        //Set children (true = left, right = false)
        public void setChild(BinaryNode child, Boolean value){
            if(value){
                this.left = child;
            } else if(!value){
                this.right = child;
            }
        }

        //Set and Get for children
        public BinaryNode getRightNode(){
            return this.right;
        }
        public BinaryNode getLeftNode(){
            return this.left;
        }

        public void changeLeftNode(BinaryNode newNode){
            this.left = newNode;
        }
        public void changeRightNode(BinaryNode newNode){
            this.right = newNode;
        }

    }

    private BinaryNode start;
    private BinaryNode currentNode;
    private BufferedReader reader;
    private BufferedWriter writer;

    //Tree Constructor
    BinaryTree(String name, Boolean newTree) throws IOException {
        //Setting up the file for a new Tree
        if(newTree){
            try{
                this.writer = new BufferedWriter(new FileWriter(name + ".txt"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if(!newTree){
            this.reader = new BufferedReader(new FileReader(name + ".txt"));
            readFromFile();
            this.currentNode = this.start;
            this.reader.close();
        }
    }

    //Read a tree from a file
    public void readFromFile() throws IOException {
        String line;
        while((line = this.reader.readLine()) != null){
            long numRight = line.chars().filter(ch -> ch == '\\').count();
            long numLeft = line.chars().filter(ch -> ch == '/').count();


            //Catching the beginning as the start
            if(((numLeft + numRight) == 0) & !line.isEmpty()){
                this.start = new BinaryNode(null, line);
                continue;
            }

            //Resetting the beginning
            this.currentNode = this.start;

            //Looping over existing tree
            while((numRight + numLeft) > 1){
                if(line.charAt(0) == '/'){
                    this.currentNode = this.currentNode.getLeftNode();
                } else if (line.charAt(0) == '\\') {
                    this.currentNode = this.currentNode.getRightNode();
                }

                line = line.substring(1, line.length());

                numRight = line.chars().filter(ch -> ch == '\\').count();
                numLeft = line.chars().filter(ch -> ch == '/').count();
            }

            //Final movement char and creating the node
            if(line.charAt(0) == '\\'){
                line = line.substring(1, line.length());
                BinaryNode newNode = new BinaryNode(this.currentNode, line);
                this.currentNode.setChild(newNode, false);
            } else if(line.charAt(0) == '/'){
                line = line.substring(1, line.length());
                BinaryNode newNode = new BinaryNode(this.currentNode, line);
                this.currentNode.setChild(newNode, true);
            }
        }
    }

    //Adding a new node to the tree
    public void addNode(String v, Boolean relationship){
        if(this.start == null){
            this.start = new BinaryNode(null, v);
            this.currentNode = this.start;
        } else{
            this.currentNode.setChild(new BinaryNode(this.currentNode, v), relationship);
        }
    }

    //Moving the current node
    public void updateCurrentNode(Boolean direction){
        //Left or right depending on true or false value
        if(direction & this.currentNode.getLeftNode() != null){
            this.currentNode = this.currentNode.getLeftNode();
        } else if (this.currentNode.getRightNode() != null){
            this.currentNode = this.currentNode.getRightNode();
        }
    }

    //Preorder Tree Traversal
    public ArrayList<String> traverseTree(BinaryNode root, String path, ArrayList<String> seenNodes){
        if(root == null){
            return seenNodes;
        } else{
            seenNodes.add(path + root.getValue());
            path = path + "/";
            seenNodes = traverseTree(root.getLeftNode(), path, seenNodes);
            path = path.substring(0, path.length() - 1);
            path = path + "\\";
            seenNodes = traverseTree(root.getRightNode(), path, seenNodes);
            return seenNodes;
        }
    }

    //Reading in a file
    public void writeToFile() throws IOException {
        ArrayList<String> paths = new ArrayList<>();
        paths = traverseTree(this.start, "", paths);

        String stringToWrite = "";

        for (String s : paths) {
            stringToWrite = stringToWrite + s + "\n";
        }

        this.writer.write(stringToWrite);
        this.writer.close();
    }

    public void playGame(){
        Scanner myScan = new Scanner(System.in);

        if(this.currentNode == null){
            this.currentNode = this.start;
        }

        System.out.print(this.currentNode.value);
        System.out.print(" (y/n)");

        String answer = myScan.nextLine();

        if(answer.charAt(0) == 'y' || answer.charAt(0) == 'Y'){
            this.currentNode = this.currentNode.getLeftNode();
        }  if(answer.charAt(0) == 'n' || answer.charAt(0) == 'N'){
            this.currentNode = this.currentNode.getRightNode();
        }

        if (this.currentNode != null){
            playGame(); //Recursive
        } else{
            System.out.println("Game Ended");
        }
    }

    public String returnCurrent(){
        return this.currentNode.getValue();
    }
}
