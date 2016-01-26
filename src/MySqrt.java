/**
 * Created by oskar on 23/01/2016.
 */

public class MySqrt {

    public static double mySqrtLoop(double x, double epsilon){
        double upper, lower;
        double returnValue = Double.NaN;

        if(x >= 0 && x <= 1){
            upper = 1;
            lower = x;
            do {
                if(Math.pow(((upper+lower)/2), 2) - x > 0){
                    upper = (upper+lower)/2;
                }
                else if(Math.pow(((upper+lower)/2), 2) - x < 0){
                    lower = (upper+lower)/2;
                }
                returnValue = (upper+lower)/2;
            }
            while(Math.abs(Math.pow(returnValue,2) - x) > epsilon);
        }
        else if(x > 1){
            upper = x;
            lower = 1;
            do {
                if(Math.pow(((upper+lower)/2), 2) - x > 0){
                    upper = (upper+lower)/2;
                }
                else if(Math.pow(((upper+lower)/2), 2) - x < 0){
                    lower = (upper+lower)/2;
                }
                returnValue = (upper+lower)/2;
            }
            while(Math.abs(Math.pow(returnValue,2) - x) > epsilon);
        }
        return Math.abs(returnValue);
    }

    public static double mySqrtRecurse(double x, double epsilon){
        double upper, lower;
        double returnValue = Double.NaN;

        return returnValue;
    }

    public static void main(String[] args){
        System.out.println(mySqrtLoop(25, 0.000001));
    }

}
