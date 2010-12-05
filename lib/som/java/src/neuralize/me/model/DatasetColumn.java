package neuralize.me.model;

import org.bson.types.ObjectId;

import com.mongodb.DBObject;

public class DatasetColumn {
	public DatasetColumn(){}
	public DatasetColumn(DBObject document){
		id = (ObjectId) document.get("_id");
		title = (String) document.get("title");
		description = (String) document.get("description");
		included = (Boolean) document.get("included");
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public ObjectId getDataset_id() {
		return dataset_id;
	}
	public void setDataset_id(ObjectId dataset_id) {
		this.dataset_id = dataset_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean isIncluded() {
		return included;
	}
	public void setIncluded(boolean included) {
		this.included = included;
	}

	private ObjectId id;
	private ObjectId dataset_id;
	private String title;
	private String description;
	private boolean included;
}
