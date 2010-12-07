package neuralize.me.dao;

import neuralize.me.model.Train;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

public class TrainDao extends BasicDAO<Train, ObjectId> {
	
    public TrainDao(Class<Train> entityClass, Datastore ds) {
    	super(entityClass, ds);
    }
	public TrainDao(){
		super(Train.class, Connection.instance().getDb());
	}
}
