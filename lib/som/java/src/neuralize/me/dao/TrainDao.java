package neuralize.me.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import neuralize.me.model.Train;
import neuralize.me.model.TrainResult;

public class TrainDao {
	private static DBCollection coll = Connection.instance().getDB().getCollection("trains");
	
	public static Train first(){
		return fromMongo( coll.findOne() );
	}
	
	public static Train find(ObjectId id){
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		return fromMongo( coll.findOne(query) );
	}
	
	public static void insert(Train train){
		BasicDBObject doc = toMongo(train);
		coll.insert(doc);
		train = fromMongo(doc);
	}
	
	private static BasicDBObject toMongo(Train train){
		BasicDBObject doc = new BasicDBObject();
		//doc.put( "_id", train.getId() );
		doc.put("training_times", train.getTrainingTimes());
		doc.put("current_training_time", train.getCurrentTrainingTime());
		doc.put("weight_lines", train.getWeightLines());
		doc.put("weight_columns", train.getWeightColumns());
		doc.put("start_range", train.getStartRange());
		doc.put("end_range", train.getEndRange());
		doc.put("start_alpha", train.getStartAlpha());
		doc.put("end_alpha", train.getEndAlpha());
		doc.put("start_random_weights", train.getStartRandomWeights());
		doc.put("end_random_weights", train.getEndRandomWeights());
		doc.put("start_at", train.getStartAt());
		doc.put("end_at", train.getEndAt());
		doc.put("results", resultsToMongo(train));
		
		return doc;
	}
	
	private static Train fromMongo(DBObject doc){
		Train train = new Train();
		train.setId( (ObjectId)doc.get("_id") );
		train.setTrainingTimes( (Integer)doc.get("training_times") );
		train.setCurrentTrainingTime( (Integer)doc.get("current_training_time") );
		train.setWeightLines( (Integer)doc.get("weight_lines") );
		train.setWeightColumns( (Integer)doc.get("weight_columns") );
		train.setStartRange( (Integer)doc.get("start_range") );
		train.setEndRange( (Integer)doc.get("end_range") );
		train.setStartAlpha( (Double)doc.get("start_alpha") );
		train.setEndAlpha( (Double)doc.get("end_alpha") );
		train.setStartRandomWeights( (Double)doc.get("start_random_weights") );
		train.setEndRandomWeights( (Double)doc.get("end_random_weights") );
		train.setStartAt( (Date)doc.get("start_at") );
		train.setEndAt( (Date)doc.get("end_at") );
		train.setResults( resultsFromMongo( (BasicDBList)doc.get("results") ) );
		return train;
	}
	
	private static BasicDBList resultsToMongo(Train train){
		BasicDBList docResults = new BasicDBList();
		
		for(List<TrainResult>results:train.getResults()){
			BasicDBList docResult = new BasicDBList();
			
			for(TrainResult result:results)
				docResult.add( TrainResultDao.toMongo(result) );
			docResults.add(docResult);
		}
		
		return docResults;
	}
	
	private static List<List<TrainResult>> resultsFromMongo(BasicDBList doc){
		List<List<TrainResult>> results = new ArrayList<List<TrainResult>>();
		
		return results;
	}
	
}
