package uk.ac.reading.tq011338.commandlines;

import org.json.JSONException;
import org.json.JSONObject;

import uk.ac.reading.tq011338.commandlines.ActionFigure.State;

public class Obstacle implements WorldObject{
	
	protected int x; // x coordinate
	protected int y; // y coordinate

	public Obstacle (int x, int y) {
		this.x = x;
		this.y = y;
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

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void decideOnNextMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAP(int i) {
		// TODO Auto-generated method stub
		
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_TYPE, this.getClass().getName());
		json.put(JSON_HP, hitPoints);
		json.put(JSON_X, x);
		json.put(JSON_Y, y);
		
		return json;
	}

}
