package neuralize.me;

import java.util.ArrayList;
import java.util.List;

public class Distribution {
	
	public static List<Double> distribute(double startValue, double endValue, int size, boolean wantInts){
		List<Double> array = new ArrayList<Double>();
		double difference = endValue - startValue;
		double n = Math.max(size-1, 1);
		
		for(int i=0; i<size; i++){
			Double value = startValue + i * difference / n;
			if(wantInts){
				array.add( (double)value.intValue() );
			}else{
				array.add( value );
			}
		}

		return array;
	}
}
