package neuralize.me;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import neuralize.me.model.Train;
@SuppressWarnings("rawtypes")

public class TrainSOM {
	private Train train;
	private List<List<Number>> inputs;
	private List<List<List<Number>>> weights;
	private List<Number> ranges;
	private List<Number> alphas;
	
	public TrainSOM(){
		start();
	}
	public void start(){
		train = Train.generate();
		treatInputs();
		createWeights();
		
		ranges = Distribution.distribute(train.getStartRange(), train.getEndRange(), train.getTrainingTimes(), true);
		alphas = Distribution.distribute(train.getStartAlpha(), train.getEndAlpha(), train.getTrainingTimes(), false);
		
		train.setStartAt( new Date() );
	}
	
	private void treatInputs(){
		inputs = new ArrayList<List<Number>>();

		for( Object list:train.getInputs() ){
			List<Number> input = new ArrayList<Number>();
			inputs.add(input);
			
			for( Object item:(List)list )
				try{
					input.add( (Number)item );
				}catch(Exception e){
					int value = 0;
					for( char c:((String)item).toCharArray() )
						value += (int)c;
					input.add(value);
				}
		}
	}
	
	private void createWeights(){
		int zSize = inputs.get(0).size();
		
		weights = new ArrayList<List<List<Number>>>();
		for( int i=0; i<train.getWeightColumns(); i++ ){
			List<List<Number>> line = new ArrayList<List<Number>>();
			weights.add(line);
			
			for(int j=0; j<train.getWeightLines(); j++){
				if( train.isRandomizeWeights() ){
					line.add( randomWeightRange(zSize) );
				}else{
					line.add( randomWeight(zSize, train.getStartRandomWeights(), train.getEndRandomWeights())  );
				}
			}
		}
	}
	
	private List<Number> randomWeight(int size, Double min, Double max){
		List<Number> weight = new ArrayList<Number>(size);  
		for( int i=0; i<size; i++)
			weight.add(  radomRange(min, max) );
		return weight;
	}
	
	private List<Number> randomWeightRange(int size){
	    ArrayList< ArrayList<Double> > columnList = new ArrayList<ArrayList<Double>>(size); 
	    
		for(int i=0; i<size; i++)
			columnList.add( new ArrayList<Double>() );

		for( List<Number> input:inputs ){
			for(int i=0; i<size; i++)
				columnList.get(i).add( input.get(i).doubleValue() );
		}
			
		List<Number> weight = new ArrayList<Number>(size);  
		for( int i=0; i<size; i++)
			weight.add( radomRange(Collections.min(columnList.get(i)), Collections.max(columnList.get(i))) );
		
		return weight;		
	}
	
	private Number radomRange(Double min, Double max){
		if(min == max){
			return Math.random()*(max);
		}else{
			return Math.random()*(max-min) + min;
		}
	}
	
}