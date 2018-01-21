package exercises15.exercise00;

/**
 * Created by mienna on 14/01/15.
 */
public class RectsCore {

    float r1x1=0, r1x2=0, r1y1=0, r1y2=0;
    float r2x1=0, r2x2=0, r2y1=0, r2y2=0;

    public boolean intervalsOverlap(float n1, float n2, float m1, float m2){
        return !(n1 > m2 || n2 < m1);
    }

    public boolean rectanglesOverlap(){
        return (intervalsOverlap(r1x1, r1x2, r2x1, r2x2) && intervalsOverlap(r1y1, r1y2, r2y1, r2y2));
    }

    public String rectangle2String(float x1, float y1, float x2, float y2){
        return "(" + x1 + " , " + y1 + ")" + "," + "(" + x2 + " , " + y2 + ")";
    }

}
