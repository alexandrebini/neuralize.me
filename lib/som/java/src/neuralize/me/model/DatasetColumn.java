package neuralize.me.model;

import org.bson.types.ObjectId;

public class DatasetColumn {
	public DatasetColumn(){}
	
	private ObjectId id;
	private ObjectId datasetId;
	private String title;
	private String description;
	private boolean included;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public ObjectId getDatasetId() {
		return datasetId;
	}
	public void setDatasetId(ObjectId datasetId) {
		this.datasetId = datasetId;
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
}
