package uk.ac.reading.tq011338.serializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import uk.ac.reading.tq011338.commandlines.WorldObject;
import android.content.Context;
import android.content.res.AssetManager;

public class CommandLinesJSONSerializer {

	private Context mContext;
	private String mFilename;

	public CommandLinesJSONSerializer(Context c, String f) {
		mContext = c;
		mFilename = f;
	}

	public void saveWorldObjects(List<WorldObject> objectList)
			throws JSONException, IOException {
		JSONArray array = new JSONArray();
		for (WorldObject w : objectList) {
			array.put(w.toJSON());
			Writer writer = null;
			try {
				OutputStream out = mContext.openFileOutput(mFilename,
						Context.MODE_PRIVATE);
				writer = new OutputStreamWriter(out);
				writer.write(array.toString());
			} finally {
				if (writer != null)
					writer.close();
			}
		}
	}

	public JSONArray loadWordObjects() throws IOException, JSONException {
		BufferedReader reader = null;
		JSONArray array = null;
		AssetManager assetManager = mContext.getResources().getAssets();
		InputStream in = null;
		try {
			if ("resume_game.json".equalsIgnoreCase(mFilename)) {
				in = mContext.openFileInput(mFilename);
			} else {
				in = assetManager.open(mFilename);
			}
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}

			array = (JSONArray) new JSONTokener(jsonString.toString())
					.nextValue();
		} catch (FileNotFoundException e) {

		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		return array;
	}

}
