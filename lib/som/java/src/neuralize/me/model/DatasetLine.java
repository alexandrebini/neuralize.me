package neuralize.me.model;

import java.util.List;

import org.bson.types.ObjectId;
@SuppressWarnings("rawtypes")

public class DatasetLine {
    private ObjectId id;
	private List data;
	private String pictureFilename;
	private ObjectId datasetId;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public String getPictureFilename() {
		return pictureFilename;
	}
	public void setPictureFilename(String pictureFilename) {
		this.pictureFilename = pictureFilename;
	}
	public ObjectId getDatasetId() {
		return datasetId;
	}
	public void setDatasetId(ObjectId datasetId) {
		this.datasetId = datasetId;
	}
}
