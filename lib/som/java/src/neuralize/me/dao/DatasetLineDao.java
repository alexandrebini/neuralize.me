package neuralize.me.dao;

import java.util.List;

import neuralize.me.model.DatasetLine;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@SuppressWarnings("rawtypes")
public class DatasetLineDao {
	private static DBCollection coll = Connection.instance().getDB().getCollection("dataset_lines");
	
    public DatasetLineDao(DatasetLine entityClass) {
    	super();
    }
	public DatasetLineDao(){
		super();
	}
	
	public static void insert(DatasetLine datasetLine){
		BasicDBObject doc = datasetLineToBasicDBObject(datasetLine);
		coll.insert(doc);
	}
	
	public static void test(){
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
		DatasetLine line = dbObjectToDatasetLine(myDoc);
		System.out.println( line );
		for(Object o:line.getData()  ){
			System.out.println(o);
		}
	}
	
	
	private static BasicDBObject datasetLineToBasicDBObject(DatasetLine datasetLine){
		BasicDBObject doc = new BasicDBObject();
		doc.put("_id", datasetLine.getId());
		doc.put("data", datasetLine.getData());
		doc.put("dataset_id", datasetLine.getDatasetId());
		doc.put("picture_filename", datasetLine.getPictureFilename());
		return doc;
	}
	
	private static DatasetLine dbObjectToDatasetLine(DBObject doc){
		DatasetLine datasetLine = new DatasetLine();
		datasetLine.setId( (ObjectId)doc.get("_id") );
		datasetLine.setData( (List)doc.get("data") );
		datasetLine.setDatasetId( (ObjectId)doc.get("dataset_id") );
		datasetLine.setPictureFilename( (String)doc.get("picture_filename") );
		doc.put("data", datasetLine.getData());
		doc.put("picture_filename", datasetLine.getPictureFilename());
		return datasetLine;
	}
}
