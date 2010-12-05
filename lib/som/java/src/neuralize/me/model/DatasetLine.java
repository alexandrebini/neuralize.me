package neuralize.me.model;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class DatasetLine {
	public DatasetLine(){}
	public DatasetLine(DBObject document){
		id = (ObjectId) document.get("_id");
		data = (BasicDBList) document.get("data");
		pictureFilename = (String) document.get("picture_filename");
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public ArrayList<?> getData() {
		return data;
	}
	public void setData(BasicDBList data) {
		this.data = data;
	}
	public String getPictureFilename() {
		return pictureFilename;
	}
	public void setPictureFilename(String pictureFilename) {
		this.pictureFilename = pictureFilename;
	}
	private ObjectId id;
	private BasicDBList data;
	private String pictureFilename;
}
