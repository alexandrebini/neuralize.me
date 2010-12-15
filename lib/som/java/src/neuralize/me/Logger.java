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
	
	public void out(List<List<List<Double>>> weights, List<Double>input, List<Integer>closer, int time, int trainingTimes, Double range, Double alpha){
		stream += "<div class='step'>";
		step(time, trainingTimes, input, range, alpha);
		weights(weights, input, closer, range);
		stream += "</div>";
		
		write();
	}
	
	private void step(int time, int trainingTimes, List<Double>input, Double range, Double alpha){
		stream += " Step: " + time + "/" + trainingTimes +
					" Input: " + input.toString() + 
					" Neighborhood: " + range.toString() + 
					" Alpha: " + alpha.toString();
	}
	
	private void weights(List<List<List<Double>>> weights, List<Double>input, List<Integer>closer, Double range){
		stream += "<table><tbody>";
		for(int i=0; i<weights.size(); i++ ){
			List<List<Double>> line = weights.get(i);
			
			stream += "<tr>";
			
			for(int j=0; j<line.size(); j++){
				List<Double> column = line.get(j);
				List<Integer> point = Arrays.asList(i,j);

				Integer distance = Distance.indexDistance(closer, point);
				
				if(distance == 0){
					stream += "<td class='closer'>";
				}else if( distance <= range){
					stream += "<td class='range_"+distance.toString()+"'>";
				}else if(distance == range+1){
					stream += "<td class='first_out'>";
				}else{
					stream += "<td>";
				}
				
				for(int k=0; k<column.size(); k++)
					stream += "<span>" + formater.format(column.get(k)) + "</span>";
				
				stream += "</td>";
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
