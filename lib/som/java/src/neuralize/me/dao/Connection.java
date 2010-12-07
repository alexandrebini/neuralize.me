package neuralize.me.dao;

import neuralize.me.model.Dataset;
import neuralize.me.model.DatasetColumn;
import neuralize.me.model.DatasetLine;
import neuralize.me.model.Train;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public final class Connection {
	private static final Connection INSTANCE = new Connection();
	
	private final Morphia morphia;
	private final Datastore db;
	public final String dbName = "neuralizeme-development";
	
	private Connection(){
		try{
			Mongo m = new Mongo();
			morphia = new Morphia();
			mapClasses();
			db = morphia.createDatastore(m, dbName);
		}catch(Exception e){
			throw new RuntimeException("Error initializing mongo db", e);
		}
	}
	
	private void mapClasses(){
		morphia.map(Dataset.class);
		morphia.map(DatasetLine.class);
		morphia.map(DatasetColumn.class);
		morphia.map(Train.class);
	}
	
	public static Connection instance(){
		return INSTANCE;
	}
	
	public Datastore getDb(){
		return db;
	}
}
