package neuralize.me.dao;

import java.util.ArrayList;
import java.util.List;

import neuralize.me.model.DatasetColumn;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class DatasetColumnDao {
	private static DBCollection coll = Connection.instance().getDB().getCollection("dataset_columns");
	
	public DatasetColumnDao(){
		super();
	}
	
	public static DatasetColumn find(ObjectId id){
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		return fromMongo( coll.findOne(query) );
	}
	
	public static List<DatasetColumn> findAllByDatasetId(ObjectId id){
		BasicDBObject query = new BasicDBObject();
		query.put("dataset_id", id);
		
		List<DatasetColumn> result = new ArrayList<DatasetColumn>();
		
		DBCursor cur = coll.find(query);
		while(cur.hasNext())
			result.add( fromMongo(cur.next()) );
		
		return result;
	}
	
	public static void insert(DatasetColumn datasetColumn){
		BasicDBObject doc = toMongo(datasetColumn);
		coll.insert(doc);
		fromMongo(doc, datasetColumn);
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
	private static DatasetColumn fromMongo(DBObject doc, DatasetColumn datasetColumn){
		datasetColumn.setId( (ObjectId)doc.get("_id") );
		datasetColumn.setDatasetId( (ObjectId)doc.get("dataset_id") );
		datasetColumn.setTitle( (String)doc.get("title") );
		datasetColumn.setDescription( (String)doc.get("description") );
		datasetColumn.setIncluded( (Boolean)doc.get("included") );
		return datasetColumn;
	}
	private static DatasetColumn fromMongo(DBObject doc){
		return fromMongo(doc, new DatasetColumn());

	}
}
