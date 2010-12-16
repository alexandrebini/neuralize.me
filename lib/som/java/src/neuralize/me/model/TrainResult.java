package neuralize.me.model;

public class TrainResult {
	private Integer index;
	private Integer x;
	private Integer y;
	
	public TrainResult(){
	}
	public TrainResult(Integer index, Integer x, Integer y){
		this.index = index;
		this.x = x;
		this.y = y;
	}
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
}
