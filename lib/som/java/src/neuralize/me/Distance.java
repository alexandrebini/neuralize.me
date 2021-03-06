package neuralize.me;

import java.util.Arrays;
import java.util.List;

public class Distance {
	public static double euclideanDistance(List<Double> x, List<Double> y){
		double dist = 0;
		for(int i=0; i<x.size(); i++)
			dist += Math.pow(x.get(i) - y.get(i), 2); 
		return Math.sqrt(dist);
	}
	
	public static int indexDistance(List<Integer> closer, List<Integer> point){
		return Math.max( Math.abs(closer.get(0) - point.get(0)), Math.abs(closer.get(1)-point.get(1)) );
	}
	
	public static List<Integer> closer(List<Double> x, List<List<List<Double>>> array){
		List<Integer> closer = Arrays.asList(0, 0);
		for(int i=0; i< array.size(); i++){
			for(int j=0; j<array.get(i).size(); j++){
				List<Integer> point = Arrays.asList(i, j);
				
				if( array.get(i).get(j) == x ){
					closer = point;
					break;
				} else if( euclideanDistance(x, array.get(i).get(j)) < euclideanDistance(x, array.get(closer.get(0)).get(closer.get(1))) ) {
					closer = point;
				}
			}
		}
		
		return closer;
	}
	
	public static boolean inRange(List<Integer> point, List<Integer> closer, Integer range){
		return indexDistance(closer, point) <= range;
	}
}
