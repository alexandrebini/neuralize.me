package neuralize.me;

import neuralize.me.dao.DatasetLineDao;
import neuralize.me.model.DatasetLine;
import neuralize.me.model.Train;

public class TrainSOM extends Train {
	
	public static void main(String[] args) {
		
		//DatasetLine line = new DatasetLine();
		//line.setPictureFilename("test22.jpg");
		DatasetLineDao.test();
		
		//DatasetLineDao lineDao = new DatasetLineDao(); 
		
		//new DatasetLineDao();
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
