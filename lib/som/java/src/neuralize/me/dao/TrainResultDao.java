package neuralize.me.dao;

import neuralize.me.model.TrainResult;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

public class TrainResultDao {
	public static BasicDBObject toMongo(TrainResult result){
		BasicDBObject doc = new BasicDBObject();
		doc.put("index", result.getIndex());
		doc.put("x", result.getX());
		doc.put("y", result.getY());
		return doc;
	}
	
	public static TrainResult fromMongo(BSONObject doc){
		TrainResult result = new TrainResult();
		result.setIndex( (Integer)doc.get("index") );
		result.setX( (Integer)doc.get("x") );
		result.setY( (Integer)doc.get("y") );
		return result;
	}
}
