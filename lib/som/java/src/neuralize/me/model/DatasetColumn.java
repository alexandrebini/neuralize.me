package neuralize.me.model;

import org.bson.types.ObjectId;

public class DatasetColumn {
	public DatasetColumn(){}
	
	private ObjectId id;
	private String title;
	private String description;
	private boolean included;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
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
