package neuralize.me.dao;

import com.mongodb.DB;
import com.mongodb.Mongo;

public final class Connection {
	private static final Connection INSTANCE = new Connection();
	
	private final Mongo mongo;
	private final DB db;
	public final String dbName = "neuralizeme-development";
	
	private Connection(){
		try{
			mongo = new Mongo();
			db = mongo.getDB(dbName);
		}catch(Exception e){
			throw new RuntimeException("Error initializing mongo db", e);
		}
	}
	public static Connection instance(){
		return INSTANCE;
	}
	
	public DB getDB(){
		return db;
	}
	
}
