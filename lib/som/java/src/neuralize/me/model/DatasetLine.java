package neuralize.me.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Property;
@SuppressWarnings("rawtypes")

@Entity(value = "dataset_lines", noClassnameStored=true)
public class DatasetLine {
    @Id private ObjectId id;
	private List data;
	@Property("picture_filename") private String pictureFilename;
	
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
}
