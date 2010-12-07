package neuralize.me.dao;

import neuralize.me.model.Dataset;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;


public class DatasetDao extends BasicDAO<Dataset, ObjectId> {
	
    public DatasetDao(Class<Dataset> entityClass, Datastore ds) {
    	super(entityClass, ds);
    }
	public DatasetDao(){
		super(Dataset.class, Connection.instance().getDb());
	}
}
