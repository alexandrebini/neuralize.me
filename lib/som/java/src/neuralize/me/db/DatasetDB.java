package neuralize.me.db;

import neuralize.me.model.Dataset;

import com.mongodb.DBObject;

public class DatasetDB extends Connection{
	public DatasetDB(){
		super("datasets");
		DBObject a = collection.findOne();
		System.out.println(a);
		

	}
}
