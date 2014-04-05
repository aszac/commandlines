package uk.ac.reading.tq011338.commandlines;

import org.json.JSONException;
import org.json.JSONObject;

import uk.ac.reading.tq011338.commandlines.ActionFigure.State;

public interface WorldObject {
	int x = 0;
	int y = 0;
	int hitPoints = 100;
	State state = State.MOVE;
	
	static final String JSON_TYPE = "type";
	static final String JSON_HP = "hp";
	static final String JSON_X = "x";
	static final String JSON_Y = "y";

	public int getHitPoints();
	public void reduceHitPoints(int hitPoints);
	public State getState();
	public void decideOnNextMove();
	
	public JSONObject toJSON() throws JSONException;
	public void setAP(int i);
}
