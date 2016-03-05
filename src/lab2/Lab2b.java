package lab2;

import java.util.PriorityQueue;

public class Lab2b {


	private static class Vector implements Comparable{
		public double x, y, d;
		public DLList.Node node;

		public Vector(double x, double y){
			this(x,y,null,100);

		}
		public Vector(double x, double y, DLList.Node n, double d){
			this.x = x;
			this.y = y;
			node = n;
			this.d = d;
		}


		public void setNode(DLList.Node n){
			node = n;

		}

		public double getDist(Vector v){
			double xD = x - v.x;
			double yD = y - v.y;
			return Math.sqrt(xD*xD + yD*yD);
		}
		
		public double getX(){
			return x;
		}
		public double getY(){
			return y;
		}

		public void calculateDelta() {
			if (node.prev != null && node.next != null){
				Vector v1 = (Vector) node.prev.elt;
				Vector v2 = (Vector) node.next.elt;
				double l1 = getDist(v1);
				double l2 = getDist(v2);
				double l3 = v1.getDist(v2);
				d= l1 + l2 - l3;
			}else
				d = 100;
		}

		@Override
		public int compareTo(Object o){
			int v = (int) ( (d - ((Vector)o).d) *10000);
			calculateDelta();
			return v;
		}
	
	}	


  	private static DLList listOfPoints = new DLList<Vector>();
	private static PriorityQueue<Vector> q = new PriorityQueue<Vector>();
	
	
  	public static double[] simplifyShape(double[] poly, int k) {

		int pointsToRemove = (poly.length / 2) - k;
		// Populate list of Vectors (points in graph)
		listOfPoints.addFirst(new Vector(poly[0], poly[1]));
		DLList.Node node = listOfPoints.first;
		((Vector) node.elt).setNode(node);

		for (int i = 2; i<poly.length;i+=2)
			populateList(poly[i], poly[i + 1]);


		((Vector)listOfPoints.last.elt).calculateDelta();
		q.add(((Vector) listOfPoints.last.elt));


		DLList.Node testn = listOfPoints.first;
		while (testn != null) {
			System.out.println(((Vector) testn.elt).d);
			testn = testn.next;
		}



		for (int i = 0; i<pointsToRemove; i++)
			delete(((Vector)(q.remove())).node);





		double[] array = new double[poly.length-(pointsToRemove*2)];
		DLList.Node curNode = listOfPoints.first;
		for (int i = 0; i<array.length; i++){
			array[i++] = ((Vector)curNode.elt).x;
			array[i] = ((Vector)curNode.elt).y;
			curNode = curNode.next;
		}


		return array;
	}

	private static void populateList(double x, double y) {

		listOfPoints.addLast(new Vector(x, y));
		DLList.Node n = listOfPoints.last;
		((Vector) n.elt).setNode(n);
		((Vector)n.prev.elt).calculateDelta();
		q.add(((Vector) n.prev.elt));
	}


	private static void delete(DLList.Node n){
		Vector v = ((Vector)n.elt);
		System.out.println("Deleting point: (" + v.x + ", " + v.y + "), d = " + (int)(v.d*100));

		DLList.Node prev = n.prev;
		DLList.Node next = n.next;

		listOfPoints.remove(n);

		q.remove((Vector) prev.elt);
		((Vector)prev.elt).calculateDelta();
		q.add((Vector) prev.elt);

		q.remove((Vector) next.elt);
		((Vector)next.elt).calculateDelta();
		q.add((Vector)next.elt);
	}
	
}
