package neuralize.me.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
@SuppressWarnings({"rawtypes", "unchecked"})

public class Train {
	private ObjectId id;
	private List<List> inputs; 
	private int trainingTimes, currentTrainingTime;
	private int weightLines, weightColumns;
	private int startRange, endRange;
	private double startAlpha, endAlpha;
	private boolean randomizeWeights;
	private double startRandomWeights, endRandomWeights;
	private TrainResult[][] results;
	private Date startAt, endAt;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public List<List> getInputs() {
		return inputs;
	}
	public void setInputs(List<List> inputs) {
		this.inputs = inputs;
	}
	public int getTrainingTimes() {
		return trainingTimes;
	}
	public void setTrainingTimes(int trainingTimes) {
		this.trainingTimes = trainingTimes;
	}
	public int getCurrentTrainingTime() {
		return currentTrainingTime;
	}
	public void setCurrentTrainingTime(int currentTrainingTime) {
		this.currentTrainingTime = currentTrainingTime;
	}
	public int getWeightLines() {
		return weightLines;
	}
	public void setWeightLines(int weightLines) {
		this.weightLines = weightLines;
	}
	public int getWeightColumns() {
		return weightColumns;
	}
	public void setWeightColumns(int weightColumns) {
		this.weightColumns = weightColumns;
	}
	public int getStartRange() {
		return startRange;
	}
	public void setStartRange(int startRange) {
		this.startRange = startRange;
	}
	public int getEndRange() {
		return endRange;
	}
	public void setEndRange(int endRange) {
		this.endRange = endRange;
	}
	public double getStartAlpha() {
		return startAlpha;
	}
	public void setStartAlpha(double startAlpha) {
		this.startAlpha = startAlpha;
	}
	public double getEndAlpha() {
		return endAlpha;
	}
	public void setEndAlpha(double endAlpha) {
		this.endAlpha = endAlpha;
	}
	public boolean isRandomizeWeights() {
		return randomizeWeights;
	}
	public void setRandomizeWeights(boolean randomizeWeights) {
		this.randomizeWeights = randomizeWeights;
	}
	public double getStartRandomWeights() {
		return startRandomWeights;
	}
	public void setStartRandomWeights(double startRandomWeights) {
		this.startRandomWeights = startRandomWeights;
	}
	public double getEndRandomWeights() {
		return endRandomWeights;
	}
	public void setEndRandomWeights(double endRandomWeights) {
		this.endRandomWeights = endRandomWeights;
	}
	public TrainResult[][] getResults() {
		return results;
	}
	public void setResults(TrainResult[][] results) {
		this.results = results;
	}
	public Date getStartAt() {
		return startAt;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
	public Date getEndAt() {
		return endAt;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
	
	public static Train generate(){
		Train train = new Train();
		
		train.id = new ObjectId("4d00a7913d65fd0ecd000001");
		train.inputs = new ArrayList<List>();
		train.inputs.add( Arrays.asList(1, 2, 3, "teste") );
		train.inputs.add( Arrays.asList(30, 50, 60, "Aasw") );
		
		train.weightLines = train.weightColumns = 10;
		train.trainingTimes = 5;
		train.startRange = 5;
		train.endRange = 1;
		train.startAlpha = 0.1;
		train.endAlpha = 0.01;
		
		train.randomizeWeights = true;
		train.startRandomWeights = 1;
		train.endRandomWeights = 10;
		
		return train;
	}
}
