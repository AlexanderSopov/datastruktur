import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by oskar on 23/01/2016.
 */

public class MySqrt {
    //Method which uses a loop to calculate an approximate square root of x.
    public static double mySqrtLoop(double x, double epsilon){
        double upper, lower;
        double returnValue = Double.NaN;
        //If x is non-negative and less than one, the original interval is set to [x,1].
        if(x >= 0 && x <= 1){
            upper = 1;
            lower = x;
            //Loops until the return value is correct with a fault tolerance of epsilon.
            do {
                //If the square of the approximated value is higher than x, the upper value is reduced
                if(Math.pow(((upper+lower)/2), 2) - x > 0){
                    upper = (upper+lower)/2;
                }
                //Else the lower value is increased
                else if(Math.pow(((upper+lower)/2), 2) - x < 0){
                    lower = (upper+lower)/2;
                }
                returnValue = (upper+lower)/2;
            }
            while(Math.abs(Math.pow(returnValue,2) - x) > epsilon);
        }
        //If x is higher than 1, the original interval is set to [1,x].
        else if(x > 1){
            upper = x;
            lower = 1;
            //Loops until the return value is correct with a fault tolerance of epsilon.
            do {
                //If the square of the approximated value is higher than x, the upper value is reduced
                if(Math.pow(((upper+lower)/2), 2) - x > 0){
                    upper = (upper+lower)/2;
                }
                //Else the lower value is increased
                else if(Math.pow(((upper+lower)/2), 2) - x < 0){
                    lower = (upper+lower)/2;
                }
                returnValue = (upper+lower)/2;
            }
            while(Math.abs(Math.pow(returnValue,2) - x) > epsilon);
        }
        return returnValue;
    }

    public static double mySqrtRecurse(double x, double epsilon){
        double upper, lower;
        double returnValue = Double.NaN;

        return returnValue;
    }

    public static void main(String[] args){
        System.out.println(mySqrtLoop(25, 0.000001)); //Should return approximately 5
        System.out.println(mySqrtLoop(7, 0.000001));  //Should return approximately 2.64575
        System.out.println(mySqrtLoop(0.5, 0.000001));//Should return approximately 0.70710
        System.out.println(mySqrtLoop(0, 0.000001));  //Should return approximately 0
        System.out.println(mySqrtLoop(-4, 0.000001)); //Should return NaN

    }

}
