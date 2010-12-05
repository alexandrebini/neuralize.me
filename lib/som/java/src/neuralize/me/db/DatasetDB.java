package neuralize.me.db;

import com.mongodb.DBObject;

public class DatasetDB extends Connection{
	public DatasetDB(){
		super("datasets");
		DBObject a = collection.findOne();
		System.out.println(a);
		System.out.println(a.get("lines"));
	}
}
