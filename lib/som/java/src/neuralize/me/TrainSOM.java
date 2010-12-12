package neuralize.me;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import neuralize.me.model.Train;
@SuppressWarnings("rawtypes")

public class TrainSOM {
	private Train train;
	private List<List<Double>> inputs;
	private List<List<List<Double>>> weights;
	private List<Double> ranges;
	private List<Double> alphas;
	
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
		//train.setResults(results);
		startTrain();
	}
	
	private void treatInputs(){
		inputs = new ArrayList<List<Double>>();

		for( Object list:train.getInputs() ){
			List<Double> input = new ArrayList<Double>();
			inputs.add(input);
			
			for( Object item:(List)list )
				try{
					input.add( Double.parseDouble(item.toString()) );
				}catch(Exception e){
					Integer value = 0;
					for( char c:((String)item).toCharArray() )
						value += (int)c;
					input.add(value.doubleValue());
				}
		}
	}
	
	private void createWeights(){
		int zSize = inputs.get(0).size();
		
		weights = new ArrayList<List<List<Double>>>();
		for( int i=0; i<train.getWeightColumns(); i++ ){
			List<List<Double>> line = new ArrayList<List<Double>>();
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
	
	private List<Double> randomWeight(int size, Double min, Double max){
		List<Double> weight = new ArrayList<Double>(size);  
		for( int i=0; i<size; i++)
			weight.add(  radomRange(min, max) );
		return weight;
	}
	
	private List<Double> randomWeightRange(int size){
	    ArrayList< ArrayList<Double> > columnList = new ArrayList<ArrayList<Double>>(size); 
	    
		for(int i=0; i<size; i++)
			columnList.add( new ArrayList<Double>() );

		for( List<Double> input:inputs ){
			for(int i=0; i<size; i++)
				columnList.get(i).add( input.get(i).doubleValue() );
		}
			
		List<Double> weight = new ArrayList<Double>(size);  
		for( int i=0; i<size; i++)
			weight.add( radomRange(Collections.min(columnList.get(i)), Collections.max(columnList.get(i))) );
		
		return weight;		
	}
	
	private Double radomRange(Double min, Double max){
		if(min == max){
			return Math.random()*(max);
		}else{
			return Math.random()*(max-min) + min;
		}
	}
	
	private void startTrain(){
		for(int time=0; time<train.getTrainingTimes(); time++){
			Number alpha = alphas.get(time);
			Number range = ranges.get(time);
			
			for( List<Double> input:inputs ){
				List<Integer> closer = Distance.closer(input, weights);
				learn(input, closer);
			}
		}
	}
	
	private void learn(List<Double> input, List<Integer> closer){
		
	}
	
}