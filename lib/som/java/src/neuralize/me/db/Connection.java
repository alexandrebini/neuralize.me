package neuralize.me.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class Connection {
	
	public DBCollection collection;
	
	public Connection(){}
	public Connection(String collection){
		try {
			this.collection = connection().getCollection(collection);
		}catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		catch (MongoException ex) {
			ex.printStackTrace();
		}
	}
	
	private DB connection() throws UnknownHostException{
		Mongo mongo = new Mongo();
		return mongo.getDB("neuralizeme-development");
	}
	
	
	
}
