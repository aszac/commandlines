package uk.ac.reading.tq011338.commandlines;

public interface WorldObject {
	int x = 0;
	int y = 0;
	boolean selected = false;
	int hitPoints = 100;
	
	public boolean isSelected();
	public void setSelected(boolean selected);
	public void setCommand(String command);
	public String getCommand();
	public int getHitPoints();
	public void reduceHitPoints(int hitPoints);
}
