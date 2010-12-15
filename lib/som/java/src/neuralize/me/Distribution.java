package neuralize.me;

import java.util.ArrayList;
import java.util.List;

public class Distribution {
	
	public static List<Double> distribute(double startValue, double endValue, int size){
		List<Double> array = new ArrayList<Double>();
		double difference = endValue - startValue;
		double n = Math.max(size-1, 1);
		
		for(int i=0; i<size; i++)
			array.add(  startValue + i * difference / n );

		return array;
	}
	
	public static List<Integer> distributeInteger(double startValue, double endValue, int size){
		List<Double> doubleArray = distribute(startValue, endValue, size);
		List<Integer> array = new ArrayList<Integer>();

		for(Double value:doubleArray)
			array.add( value.intValue() );

		return array;
	}
	
}
