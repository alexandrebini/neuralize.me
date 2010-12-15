package neuralize.me;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;

public class Logger {
	private String stream = new String();
	private ObjectId trainId;
    private String fileName;
    private DecimalFormat formater = new DecimalFormat("#.##");
    
	public Logger(ObjectId trainId) {
		this.trainId = trainId;
		fileName = "log/" + trainId.toString() + ".html";
	}
	
	public void step(int time, int trainingTimes, Integer range, Double alpha){
		stream += "<div class='step'>";
		stream += "<div class='header'>" +
				" Step: " + time + "/" + trainingTimes +
				" Neighborhood: " + range.toString() + 
				" Alpha: " + alpha.toString()+
				"</div>";
	}
	
	public void input(List<Double>input){
		stream += "<div class='input'> <span class='title'>Input: </span>" + input.toString() + "</div>";
	}
	
	public void finalizeStep(){
		stream += "</div>";
		write();
	}
	
	public void weights(List<List<List<Double>>> weights, List<Double>input, List<Integer>closer, Integer range){
		stream += "<table class='weights'><tbody>";
		for(int i=0; i<weights.size(); i++ ){
			List<List<Double>> line = weights.get(i);
			
			stream += "<tr>";
			
			for(int j=0; j<line.size(); j++){
				List<Double> column = line.get(j);
				List<Integer> point = Arrays.asList(i,j);

				Integer distance = Distance.indexDistance(closer, point);
				String tdClass = new String();
				
				if(distance == 0){
					tdClass = "closer";
				}else if( distance <= range){
					tdClass = "range_"+distance.toString();
				}else if(distance == range+1){
					tdClass = "first_out";
				}
				
				stream += "<td><table class='"+tdClass+"'><tbody><tr>";
				for(int k=0; k<column.size(); k++)
					stream += "<td>" + formater.format(column.get(k)) + "</td>";
				stream += "</tr></tbody></table></td>";
			}
			
			stream += "</tr>";
		}
		stream += "</tbody></table>";
	}
	
	private void write(){
		try{
			BufferedWriter out = new BufferedWriter( new FileWriter(fileName) );
			out.write(parseStream());
			out.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}

	private String parseStream(){
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"+
		"<html xmlns='http://www.w3.org/1999/xhtml'>" +
			"<head>" +
				"<title> Training " + trainId.toString() + "</title>"+
				"<link type='text/css' rel='stylesheet' media='screen' href='../../../../public/stylesheets/log.css'/>"+ //just for testing
			"</head>" +
			"<body>" +
				stream +
			"</body>" +
		"</html>";
	}
}
