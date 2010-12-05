package neuralize.me;

public class Distribution {
	public static double[] distribute(double startValue, double endValue, int size, boolean wantInts){
		double[] array = new double[size];
		double difference = endValue - startValue;
		double n = Math.max(size-1, 1);
		
		for(int i=0; i<size; i++){
			double value = startValue + i * difference / n;
			if(wantInts){
				array[i] = Math.round(value);
			}else{
				array[i] = value;
			}
		}
		
		return array;
	}
}
