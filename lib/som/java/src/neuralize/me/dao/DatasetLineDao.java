package neuralize.me.dao;

import java.util.ArrayList;
import java.util.List;

import neuralize.me.model.DatasetLine;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@SuppressWarnings("rawtypes")

public class DatasetLineDao {
	private static DBCollection coll = Connection.instance().getDB().getCollection("dataset_lines");
	
	public DatasetLineDao(){
		super();
	}
	
	public static DatasetLine find(ObjectId id){
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		return fromMongo( coll.findOne(query) );
	}
	
	public static List<DatasetLine> findAllByDatasetId(ObjectId id){
		BasicDBObject query = new BasicDBObject();
		query.put("dataset_id", id);
		
		List<DatasetLine> result = new ArrayList<DatasetLine>();
		
		DBCursor cur = coll.find(query);
		while(cur.hasNext()){
			result.add( fromMongo(cur.next()) );
		}
		
		return result;
	}
	
	public static void insert(DatasetLine datasetLine){
		BasicDBObject doc = toMongo(datasetLine);
		coll.insert(doc);
	}
	
	private static BasicDBObject toMongo(DatasetLine datasetLine){
		BasicDBObject doc = new BasicDBObject();
		doc.put("_id", datasetLine.getId());
		doc.put("data", datasetLine.getData());
		doc.put("dataset_id", datasetLine.getDatasetId());
		doc.put("picture_filename", datasetLine.getPictureFilename());
		return doc;
	}
	
	private static DatasetLine fromMongo(DBObject doc){
		if(doc==null) return null;
		DatasetLine datasetLine = new DatasetLine();
		datasetLine.setId( (ObjectId)doc.get("_id") );
		datasetLine.setData( (List)doc.get("data") );
		datasetLine.setDatasetId( (ObjectId)doc.get("dataset_id") );
		datasetLine.setPictureFilename( (String)doc.get("picture_filename") );
		return datasetLine;
	}
}
