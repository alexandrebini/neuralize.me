package neuralize.me.dao;

import neuralize.me.model.DatasetColumn;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

public class DatasetColumnDao extends BasicDAO<DatasetColumn, ObjectId> {
	
	public DatasetColumnDao(Class<DatasetColumn> entityClass, Datastore ds){
		super(entityClass, ds);
	}
	public DatasetColumnDao(){
		super(DatasetColumn.class, Connection.instance().getDb());
	}

}
