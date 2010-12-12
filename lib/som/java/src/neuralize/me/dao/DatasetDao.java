package neuralize.me.dao;

import java.util.Date;

import neuralize.me.model.Dataset;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class DatasetDao {
	private static DBCollection coll = Connection.instance().getDB().getCollection("datasets");
	
	public DatasetDao(){
		super();
	}

	public static Dataset first(){
		return fromMongo( coll.findOne() );
	}
	
	public static Dataset find(ObjectId id){
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		return fromMongo( coll.findOne(query) );
	}
	
	public static void insert(Dataset dataset){
		dataset.setCreatedAt( new Date() );
		dataset.setUpdatedAt( new Date() );
		
		BasicDBObject doc = toMongo(dataset);
		coll.insert(doc);
	}
	
	private static BasicDBObject toMongo( Dataset dataset ){
		BasicDBObject doc = new BasicDBObject();
		doc.put( "_id", dataset.getId() );
		doc.put( "title", dataset.getTitle() );
		doc.put( "description", dataset.getDescription() );
		doc.put( "created_at", dataset.getCreatedAt() );
		doc.put( "updated_at", dataset.getUpdatedAt() );
		return doc;
	}
	
	private static Dataset fromMongo(DBObject doc){
		Dataset dataset = new Dataset();
		dataset.setId( (ObjectId)doc.get("_id") );
		dataset.setTitle( (String)doc.get("title") );
		dataset.setDescription( (String)doc.get("description") );
		dataset.setCreatedAt( (Date)doc.get("created_at") );
		dataset.setUpdatedAt( (Date)doc.get("updated_at") );
		
		dataset.setDatasetLines( DatasetLineDao.findAllByDatasetId(dataset.getId()) );
		dataset.setDatasetColumns( DatasetColumnDao.findAllByDatasetId(dataset.getId()) );
		
		return dataset;
	}
	
}
