package neuralize.me.dao;

import neuralize.me.model.DatasetLine;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

public class DatasetLineDao extends BasicDAO<DatasetLine, ObjectId> {
	
    public DatasetLineDao(Class<DatasetLine> entityClass, Datastore ds) {
    	super(entityClass, ds);
    }
	public DatasetLineDao(){
		super(DatasetLine.class, Connection.instance().getDb());
	}
	
}
