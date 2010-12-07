package neuralize.me.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.NotSaved;

@Entity(value="datasets", noClassnameStored=true)
public class Dataset {
	@Id private ObjectId id;
	private String title;
	private String description;
	@NotSaved private List<DatasetLine> datasetLines;
	@NotSaved private List<DatasetColumn> datasetColumns;
	
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
