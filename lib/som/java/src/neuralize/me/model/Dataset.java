package neuralize.me.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class Dataset {
	private ObjectId id;
	private String title;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	private List<DatasetLine> datasetLines;
	private List<DatasetColumn> datasetColumns;
	
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<DatasetLine> getDatasetLines() {
		return datasetLines;
	}
	public void setDatasetLines(List<DatasetLine> datasetLines) {
		this.datasetLines = datasetLines;
	}
	public List<DatasetColumn> getDatasetColumns() {
		return datasetColumns;
	}
	public void setDatasetColumns(List<DatasetColumn> datasetColumns) {
		this.datasetColumns = datasetColumns;
	}
}
