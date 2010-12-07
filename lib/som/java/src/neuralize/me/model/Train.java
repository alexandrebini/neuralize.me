package neuralize.me.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
@SuppressWarnings("rawtypes")

@Entity(value="trains", noClassnameStored=true)
public class Train {
	@Id private ObjectId id;
	protected List inputs; 
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
		
		//train.inputs = List( "1", 2, 3, 4);
		train.weightLines = train.weightColumns = 10;
		train.trainingTimes = 5;
		train.startRange = 5;
		train.endRange = 1;
		train.startAlpha = 0.1;
		train.endAlpha = 0.01;
		
		return train;
	}
	
}
