import java.util.Scanner;
public class Main {
    public static Double[] askInput(){
        Scanner input = new Scanner(System.in);
        Double[] numberArray = new Double[20];

        for(int i=0;i<20;i++){
            System.out.println("Enter a Number");
            String userInput = input.nextLine();
            Double inputDouble = Double.parseDouble(userInput);
            if(inputDouble==99999){
                break;
            }
            else{
                numberArray[i] = inputDouble;
            }
        }
        return numberArray;
    }

    public static void main(String[] args) {
        Double[] returnArray = askInput();
        System.out.println("Here are your numbers:");
        if(returnArray[0]==null){
            System.out.println("Do didn't put any Numbers :(");
        }
        else{
            for(int i=0;i<returnArray.length;i++){
                if(returnArray[i]!=null){
                    System.out.println(returnArray[i]);
                }

            }
        }
    }
}