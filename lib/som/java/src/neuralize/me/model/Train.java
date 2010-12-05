package neuralize.me.model;

import java.util.ArrayList;
import java.util.Date;

public class Train {
	protected ArrayList inputs;
	protected int trainingTimes, currentTrainingTime;
	protected int weightLines, weightColumns;
	protected int startRange, endRange;
	protected double startAlpha, endAlpha;
	protected boolean randomizeWeights;
	protected double startRandomWeights, endRandomWeights;
	protected TrainResult[][] results;
	protected Date startAt, endAt;
	
	public static Train generate(){
		Train train = new Train();
		
		train.inputs = new ArrayList();
		train.inputs.add( 1 );
		train.weightLines = train.weightColumns = 10;
		train.trainingTimes = 5;
		train.startRange = 5;
		train.endRange = 1;
		train.startAlpha = 0.1;
		train.endAlpha = 0.01;
		
		return train;
	}
	
}
