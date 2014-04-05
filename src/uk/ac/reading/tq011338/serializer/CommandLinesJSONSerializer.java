package uk.ac.reading.tq011338.serializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import uk.ac.reading.tq011338.commandlines.WorldObject;

import android.content.Context;

public class CommandLinesJSONSerializer {

	private Context mContext;
	private String mFilename;
	
	public CommandLinesJSONSerializer(Context c, String f) {
		mContext = c;
		mFilename = f;
	}
	
	public void saveWorldObjects(ArrayList<WorldObject> worldObject) throws JSONException, IOException {
		JSONArray array = new JSONArray();
		for (WorldObject w : worldObject) {
			array.put(w.toJSON());
			Writer writer = null;
			try {
				OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
				writer = new OutputStreamWriter(out);
				writer.write(array.toString());
			}
			finally {
				if (writer != null)
					writer.close();
			}
		}
	}
	
	public ArrayList<WorldObject> loadWordObjects() throws IOException, JSONException {
		ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>();
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for (int i = 0; i < array.length(); i++) {
				
				//TODO FIXXXXXXXXXXXXXXXXXXX
				
				WorldObject object = (WorldObject) array.getJSONObject(i);
				worldObjects.add(object);
			}				
		}
		catch (FileNotFoundException e) {
			
		}
		finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		return worldObjects;
	}
	
	
}
