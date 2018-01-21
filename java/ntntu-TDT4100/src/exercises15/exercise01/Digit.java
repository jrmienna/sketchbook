package exercises15.exercise01;

/**
 * Created by mienna on 25/01/15.
 */
public class Digit {

    public int numberValue;
    public int tallSystem;


    public Digit(int tallSystem, int numberValue){
        this.tallSystem = tallSystem;
        this.numberValue = numberValue;
    }
    public int getValue(){
        return numberValue;
    }
    public boolean increment(){
        numberValue += 1;
        if (numberValue == tallSystem){
            numberValue = 0;
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public String toString() {
        String numLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return numLetters.substring(numberValue,numberValue+1);
    }

    public static void main(String[] args) {
        Digit d = new Digit(10, 0);
        System.out.println(d.getValue());
        System.out.println(d.increment());
        System.out.println(d.getValue());
        System.out.println(d.toString());
    }
}
