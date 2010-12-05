package neuralize.me.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;


public class Connection {
	
	public DB connection() throws UnknownHostException{
		Mongo mongo = new Mongo();
		return mongo.getDB("neuralizeme-development");
	}
	
}
