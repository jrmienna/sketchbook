package exercises15.exercise00;

import java.util.Scanner;

/**
 * Created by mienna on 14/01/15.
 */
public class RectsUI {

    RectsCore core;

    private void init(){
        core = new RectsCore();
    }

    private void main(){
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("Rect1: " + core.rectangle2String(core.r1x1, core.r1y1, core.r1x2, core.r1y2));
            System.out.println("Rect2: " + core.rectangle2String(core.r2x1, core.r2y1, core.r2x2, core.r2y2));
            System.out.print(" > ");

            String token = in.nextLine();

            if ("overlaps?".equals(token)) {
                System.out.println(core.rectanglesOverlap());
            } else if("exit".equals(token)) {
                System.out.println("quiting....");
                break;
            } else {
                int pos = token.indexOf("=");
                if (pos >= 4) {
                    float val = Float.parseFloat(token.substring(pos + 1));

                    if(token.startsWith("r1x1")) {
                        core.r1x1 = val;
                    } else if(token.startsWith("r1y1")) {
                        core.r1y1 = val;
                    } else if(token.startsWith("r1x2")) {
                        core.r1x2 = val;
                    } else if(token.startsWith("r1y2")) {
                        core.r1y2 = val;
                    } else if(token.startsWith("r2x1")) {
                        core.r2x1 = val;
                    } else if(token.startsWith("r2y1")) {
                        core.r2y1 = val;
                    } else if(token.startsWith("r1x2")) {
                        core.r2x2 = val;
                    } else if(token.startsWith("r2y2")) {
                        core.r2y2 = val;
                    }
                }
            }
        }
        in.close();
        System.out.println("bye!");
    }

    public static void main(String[] args) {
        RectsUI ui = new RectsUI();

        ui.init();
        ui.main();
    }
}
