package uk.ac.reading.tq011338.commandlines;

public class Obstacle implements WorldObject{

	private int x; // x coordinate
	private int y; // y coordinate
	
	public Obstacle (int x, int y) {
		this.x = x;
		this.y = y;		
	}
	
	public boolean isSelected() {
		return false;
	}

	public void setSelected(boolean selected) {
	}

	public void setCommand(String command) {
		
	}

	public String getCommand() {
		return null;
	}

	@Override
	public int getHitPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reduceHitPoints(int hitPoints) {
		// TODO Auto-generated method stub
		
	}

}
