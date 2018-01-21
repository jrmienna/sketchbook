package exercises15.exercise00;

import java.util.Scanner;

/**
 * Created by mienna on 14/01/15.
 */
public class Rects {

    private float r1x1=0, r1x2=0, r1y1=0, r1y2=0;
    private float r2x1=0, r2x2=0, r2y1=0, r2y2=0;

    public boolean intervalsOverlap(float n1, float n2, float m1, float m2){
        return !(n1 > m2 || n2 < m1);
    }

    public boolean rectanglesOverlap(){
        return (intervalsOverlap(r1x1, r1x2, r2x1, r2x2) && intervalsOverlap(r1y1, r1y2, r2y1, r2y2));
    }

    public String rectangle2String(float x1, float y1, float x2, float y2){
        return "(" + x1 + " , " + y1 + ")" + "," + "(" + x2 + " , " + y2 + ")";
    }

    private void main(){
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("Rect1: " + rectangle2String(r1x1, r1y1, r1x2, r1y2));
            System.out.println("Rect2: " + rectangle2String(r2x1, r2y1, r2x2, r2y2));
            System.out.print(" > ");

            String token = in.nextLine();

            if ("overlaps?".equals(token)) {
                System.out.println(rectanglesOverlap());
            } else if("exit".equals(token)) {
                System.out.println("quiting....");
                break;
            } else {
                int pos = token.indexOf("=");
                if (pos >= 4) {
                    float val = Float.parseFloat(token.substring(pos + 1));

                    if(token.startsWith("r1x1")) {
                        r1x1 = val;
                    } else if(token.startsWith("r1y1")) {
                        r1y1 = val;
                    } else if(token.startsWith("r1x2")) {
                        r1x2 = val;
                    } else if(token.startsWith("r1y2")) {
                        r1y2 = val;
                    } else if(token.startsWith("r2x1")) {
                        r2x1 = val;
                    } else if(token.startsWith("r2y1")) {
                        r2y1 = val;
                    } else if(token.startsWith("r1x2")) {
                        r2x2 = val;
                    } else if(token.startsWith("r2y2")) {
                        r2y2 = val;
                    }
                }
            }
        }
        in.close();
        System.out.println("bye!");
    }

    public static void main(String[] args) {
        new Rects().main();
    }

}
