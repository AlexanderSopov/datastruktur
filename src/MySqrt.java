/**
 * Created by Oskar Willman and Alexander Sopov on 23/01/2016.
 */

public class MySqrt {
    //Method which uses a loop to calculate an approximate square root of x.
    public static double mySqrtLoop(double x, double epsilon){
        double upper, lower;
        double returnValue = Double.NaN;
        if(x == 0 || x == 1){
            returnValue = x;
        }
        //If x is non-negative and less than one, the original interval is set to [x,1].
        else if(x > 0 && x < 1){
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

        // check for negative numbers
        if (x<0)
			return Double.NaN;
        //Check for 0 and 1, which have quick and easy answers
		if (x == 0 || x == 1)
			return x;
        // check for x between 0 and 1
        if (0 < x && x < 1)
            return recursiveSqrt(x, 1, x, epsilon);

        // no special case? alright, recourse
        return recursiveSqrt(1, x, x, epsilon);
    }




    private static double recursiveSqrt(double ymin, double ymax, double x, double epsilon) {
		/*
		System.out.println("Ymin = " + ymin + "\n" +
							"Ymax = " + ymax);
        */
        
       	double mean = (ymax + ymin) / 2;
        double meanSquared = Math.pow(mean, 2);
		
		if( getDelta(meanSquared, x) < epsilon) //Is delta smaller than epsilon? If so we're done
            return mean;
        
        if (meanSquared > x)
            return recursiveSqrt(ymin, mean, x, epsilon);
		
		return recursiveSqrt(mean, ymax, x, epsilon);
    }




    private static double getDelta(double mean, double x) {
        double difference = Math.abs(mean - x);
        return difference;
    }



    public static void main(String[] args){
        double[] numbers = {25, 7, 0.5, 0, -4};
        String[] results = {"5", "2.64575", "0.70710", "0", "NaN"};

        test(numbers, results);

    }

    private static void test(double[] x, String[] returnValue){
        //Test sqrLoop

        long currentTime = System.nanoTime();
        for (int i=0; i< x.length; i++)
            System.out.println(mySqrtLoop(x[i], 0.000001)+ ". Should return approximately " +
                    returnValue[i]);

        long delta = System.nanoTime() - currentTime;
        System.out.println("\nTime it took: " + delta + " nanosec.\n");
        System.out.println("\nRecursive Strategy:\n");

        currentTime = System.nanoTime();
        //Test sqrRecurse
        for (int i=0; i< x.length; i++)
            System.out.println(mySqrtRecurse(x[i], 0.000001)+ ". Should return approximately " +
                    returnValue[i]);

        delta = System.nanoTime() - currentTime;
        System.out.println("\nTime it took: " + delta + " nanosec\n");
    }

}
