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
            while(Math.abs(returnValue) - x < epsilon);
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
            while(Math.abs(returnValue) - x < epsilon);
        }
        return Math.abs(returnValue);
    }

    public static double mySqrtRecurse(double x, double epsilon){

        return recursiveSqrt(0, x, x, epsilon);
    }

    private static double recursiveSqrt(double ymin, double ymax, double x, double epsilon) {

        if (Math.pow(ymin, 2) < x || Math.pow(ymax, 2) > x)
            recursiveSqrt(ymin, ymax/2, x, epsilon);


        if(Math.pow(ymin, 2) < x || Math.pow(ymax, 2) < x)
            recursiveSqrt(ymax, x, x, epsilon);


        double difference =getDifference(ymin, ymax, x);
        if(difference < epsilon);
            return difference;

    }

    private static double getDifference(double ymin, double ymax, double x) {
        double mean = (ymax+ymin)/2;
        double meanSquared = Math.pow(mean, 2);
        double difference = meanSquared - x;
        return difference;
    }

    public static void main(String[] args){
        System.out.println(mySqrtLoop(5, 0.000001));
    }

}
