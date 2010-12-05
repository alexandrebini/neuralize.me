package neuralize.me;

public class Distance {
	public static double euclideanDistance(double x[], double y[]){
		double dist = 0;
		for(int i=0; i<x.length; i++){
			dist += Math.pow(x[i] - y[i], 2); 
		}
		return Math.sqrt(dist);
	}
	
	public static int indexDistance(int[] closer, int [] point){
		return Math.max( Math.abs(closer[0] - point[0]), Math.abs(closer[1]-point[1]) );
	}
	
	public static int[] closer(double[] x, double[][][] array){
		int[] closer = {0,0};
		
		for(int i=0; i< array.length; i++){
			for(int j=0; j<array[i].length; j++){
				int[] pointer = {i,j};
				
				if( array[i][j] == x ){
					closer = pointer;
					break;
				} else if( euclideanDistance(x, array[i][j]) < euclideanDistance(x, array[closer[0]][closer[1]]) ) {
					closer = pointer;
				}
			}
		}
		
		return closer;
	}
	
	public static boolean inRange(int[] point, int[] closer, int range){
		return indexDistance(closer, point) <= range;
	}
}
