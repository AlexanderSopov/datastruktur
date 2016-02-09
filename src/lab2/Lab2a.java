package lab2;
public class Lab2a {
  public static double[] simplifyShape(double[] poly, int k){
	
	
	/*
    System.out.println("K = " + k + "\nPolyLength = " + poly.length + "\n" +
     "poly[first] = (" + poly[0] + ", " + poly[1] + ")\n" +
      	"poly[last] = (" + poly[poly.length-2] + ", " + poly[poly.length-1] + ")");
    */
    
    
    // escape recursion
    if (poly.length == k*2)
      return poly;
    
    int index = 0;
    double smallest = 100;

    for (int i = 2; i<poly.length-2; i+=2) {
      double dist = calculateValue(poly, i);
      if (dist < smallest) {
        smallest = dist;
        index = i;
      }
    }
    
    System.out.println("Index = " + index + "\nPoint = (" + poly[index]+", "+poly[index+1]+")\n"+
    "Distance = " + smallest + "\n\n");

    return simplifyShape(reAllocate(poly, index), k);
  }






  private static double[] reAllocate(double[] poly, int index) {
  	//System.out.println("index = " + index);
    
    
    double[] newPoly = new double[poly.length-2];
    for (int i = 0, j=0; i<poly.length; i++)
      if ( i == index )
        i++;
      else {
        newPoly[j] = poly[i];
        j++;
      }
    
    return newPoly;
  }

  private static double calculateValue(double[] poly, int i) {
    double l1 = calculateDistance (poly[i], poly[i+1], poly[i-2], poly[i-1]);
    double l2 = calculateDistance (poly[i], poly[i+1],poly[i+2], poly[i+3]);
    double l3 = calculateDistance (poly[i-2], poly[i-1],poly[i+2], poly[i+3] );

    //System.out.println("Distance = " + (l1+l2-l3) + "\n");
        return l1+l2-l3;
  }

  private static double calculateDistance(double x1, double y1, double x2, double y2) {
    double x = x2 - x1;
    double y = y2 - y1;
    return Math.sqrt(x*x + y*y);
  }

}
