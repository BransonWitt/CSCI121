import java.awt.*;

public class LilPizzaGraphic extends Canvas{
    //Creating the price variables for displaying ingredients
    private Boolean addPepp = false;
    private Boolean addTom = false;
    private Boolean addOl = false;
    private Boolean addOn = false;

    //Set methods for updating private variables
    public void setPepp(Boolean tf){
        this.addPepp = tf;
    }
    public void setTom(Boolean tf){
        this.addTom = tf;
    }
    public void setOl(Boolean tf){
        this.addOl = tf;
    }
    public void setOn(Boolean tf){
        this.addOn = tf;
    }
    //Get methods for private variables
    public Boolean getPepp(){
        return addPepp;
    }
    public Boolean getTom(){
        return addTom;
    }
    public Boolean getOl(){
        return addOl;
    }
    public Boolean getOn(){
        return addOn;
    }

    //Overriding the paint method for Canvas
    public void paint(Graphics g){
        //Adding three circles to represent the bread, sauce, and cheese
        g.setColor(new Color(227, 148, 52));
        g.fillOval(20, 20, 360, 360);

        g.setColor(new Color(130, 18, 18));
        g.fillOval(35,35,330,330);

        g.setColor(new Color(243, 211, 148));
        g.fillOval(40,40,320,320);


        //Calling functions if the private variables are true
        if(getTom()){
            addT(g);
        }
        if(getPepp()){
            addP(g);
        }
        if(getOl()){
            addOlive(g);
        }
        if(getOn()){
            addOnion(g);
        }

    }

    /*
    All of the following functions follow the same patterns
     */
    public void addP(Graphics g){
        g.setColor(new Color(157, 22, 22)); //Color for the topping

        //Arrays for x and y values
        int[] peppPlaceX = {90, 80, 310, 150, 200, 240, 180, 110, 50, 280, 220, 170, 180, 120, 270};
        int[] peppPlaceY = {110, 250, 200, 120, 320, 90, 45, 300, 160, 270, 200, 250, 175, 190, 140};

        //For loop to go over arrays
        for(int i = 0; i < peppPlaceX.length; i++){
            //Getting x and y variables
            int x = peppPlaceX[i];
            int y = peppPlaceY[i];
            g.fillOval(x,y,30,30); //Creating the topping on the pizza
        }
    }

    public void addT(Graphics g){
        g.setColor(new Color(231, 25, 25));

        int[] peppPlaceX = {200, 130, 70, 140, 240, 280, 230, 135};
        int[] peppPlaceY = {200, 260, 160, 70, 100, 220, 290, 150};

        for(int i = 0; i < peppPlaceX.length; i++){
            int x = peppPlaceX[i];
            int y = peppPlaceY[i];
            g.fillOval(x,y,50,50);
        }
    }

    public void addOlive(Graphics g){
        //To expand the line width you need to create a 2D graphics instance
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(new Color(0, 0, 0));
        g2.setStroke(new BasicStroke(8)); //Creating a new stroke

        int[] peppPlaceX = {200, 150, 100, 230, 310, 160, 60, 70, 85, 280, 310, 175, 180, 195, 190, 290, 285, 260, 205, 165, 245, 260, 300};
        int[] peppPlaceY = {200, 210, 160, 60, 290, 70, 240, 190, 250, 75, 190, 240, 270, 180, 310,  260, 150, 210, 135, 125, 280, 210, 230};

        for(int i = 0; i < peppPlaceX.length; i++){
            int x = peppPlaceX[i];
            int y = peppPlaceY[i];
            g2.drawOval(x,y,15,15);
        }
    }

    public void addOnion(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(new Color(77, 15, 89));
        g2.setStroke(new BasicStroke(6));

        int[] peppPlaceX = {210, 120, 90, 290, 180, 70, 160, 230, 220, 290};
        int[] peppPlaceY = {210, 150, 220, 110, 70, 115, 285, 130, 265, 205};

        for(int i = 0; i < peppPlaceX.length; i++){
            int x = peppPlaceX[i];
            int y = peppPlaceY[i];
            g2.drawOval(x,y,40,40);
        }
    }

}
