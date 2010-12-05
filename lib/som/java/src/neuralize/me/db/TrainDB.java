package neuralize.me.db;

import com.mongodb.DBObject;

public class TrainDB extends Connection{
	
	public TrainDB(){
		super("trains");
		DBObject a = collection.findOne();
	}
	
}
