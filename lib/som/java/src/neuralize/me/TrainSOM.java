package neuralize.me;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import neuralize.me.dao.TrainDao;
import neuralize.me.model.Train;
import neuralize.me.model.TrainResult;
@SuppressWarnings("rawtypes")

public class TrainSOM {
	private Train train;
	private List<List<Double>> inputs;
	private List<List<List<Double>>> weights;
	private List<Integer> ranges;
	private List<Double> alphas;
	private Logger logger;
	
	public TrainSOM(){
		start();
	}
	public void start(){
		train = Train.generate();
		
		logger = new Logger(train.getId());
		treatInputs();
		createWeights();
		
		ranges = Distribution.distributeInteger(train.getStartRange(), train.getEndRange(), train.getTrainingTimes());
		alphas = Distribution.distribute(train.getStartAlpha(), train.getEndAlpha(), train.getTrainingTimes());
		
		train.setStartAt( new Date() );
		//train.setResults(results);
		train();
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
	
	private void train(){
		for(int time=0; time<train.getTrainingTimes(); time++){
			Double alpha = alphas.get(time);
			Integer range = ranges.get(time);
			
			logger.step(time+1, train.getTrainingTimes(), range, alpha);
			
			for( List<Double> input:inputs ){
				List<Integer> closer = Distance.closer(input, weights);
				logger.input(input);
				logger.weights(weights, input, closer, range);
				learn(input, closer, alpha, range);
			}
			
			logger.finalizeStep();
			
			updatePositions(time);
		}
	}
	
	private void learn(List<Double> input, List<Integer> closer, Double alpha, Integer range){
		for(int i=0; i<weights.size(); i++ ){
			List<List<Double>> line = weights.get(i);
			
			for(int j=0; j<line.size(); j++){
				List<Double> column = line.get(j);
				List<Integer> point = Arrays.asList(i,j);
				
				if(Distance.inRange(point, closer, range)){
					for(int k=0; k<column.size(); k++){
						column.set(k, column.get(k) + relativeAlpha(alpha, closer, point) * (input.get(k)-column.get(k)) );
					}
				}else if(Distance.indexDistance(closer, point) == range+1){
					for(int k=0; k<column.size(); k++){
						column.set(k, column.get(k) - relativeAlpha(alpha, closer, point) * (input.get(k)-column.get(k)) );
					}
				}
			}
		}
	}
	
	private void updatePositions(int time){
		List<TrainResult> result = new ArrayList<TrainResult>();
		for(int i=0; i<inputs.size(); i++){
			List<Integer> closer = Distance.closer(inputs.get(i), weights);
			result.add( new TrainResult(i, closer.get(0), closer.get(1) ) );
		}
		train.addResult(result);
		train.setCurrentTrainingTime(time);
		TrainDao.insert(train);
		System.out.println(train);
	}
	
	private double relativeAlpha(Double alpha, List<Integer>closer, List<Integer>point){
		return alpha/(Distance.indexDistance(closer, point)+1);
	}
	
}