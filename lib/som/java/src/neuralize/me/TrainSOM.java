package neuralize.me;

import java.util.Arrays;
import java.util.List;

import neuralize.me.dao.DatasetDao;
import neuralize.me.dao.DatasetLineDao;
import neuralize.me.model.Dataset;
import neuralize.me.model.DatasetLine;
import neuralize.me.model.Train;

public class TrainSOM extends Train {
	
	public static void main(String[] args) {
		new DatasetLineDao();
		//new DatasetDao();
		/*
		Dataset dataset = new Dataset();
		dataset.setTitle("aaaaaaaa");
		dataset.setDescription("Description");
		
		DatasetLine line = new DatasetLine();
		line.setPictureFilename("test.jpg");
		DatasetLineDao lineDao = new DatasetLineDao(); 
		lineDao.save(line);
		
		List<DatasetLine> list = Arrays.asList(line);
		
		dataset.setDatasetLines(list);
		
		DatasetDao dao = new DatasetDao();
		dao.save(dataset);
		*/
	}
	
}
