package neuralize.me.dao;

import neuralize.me.model.DatasetColumn;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;


public class DatasetColumnDao {
	private static DBCollection coll = Connection.instance().getDB().getCollection("dataset_columns");
	
	public DatasetColumnDao(){
		super();
	}
	
	public static void insert(DatasetColumn datasetColumn){
		BasicDBObject doc = toMongo(datasetColumn);
		coll.insert(doc);
	}
	
	public static void test(){
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
		DatasetColumn column = fromMongo(myDoc);
		System.out.println( column );
	}
	
	
	private static BasicDBObject toMongo(DatasetColumn datasetColumn){
		BasicDBObject doc = new BasicDBObject();
		doc.put("_id", datasetColumn.getId());
		doc.put("dataset_id", datasetColumn.getDatasetId());
		doc.put("title", datasetColumn.getTitle());
		doc.put("description", datasetColumn.getDescription());
		doc.put("included", datasetColumn.isIncluded());
		return doc;
	}
	
	private static DatasetColumn fromMongo(DBObject doc){
		DatasetColumn datasetColumn = new DatasetColumn();
		datasetColumn.setId( (ObjectId)doc.get("_id") );
		datasetColumn.setDatasetId( (ObjectId)doc.get("dataset_id") );
		datasetColumn.setTitle( (String)doc.get("title") );
		datasetColumn.setDescription( (String)doc.get("description") );
		datasetColumn.setIncluded( (Boolean)doc.get("included") );
		return datasetColumn;
	}
}
