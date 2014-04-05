package uk.ac.reading.tq011338.commandlines;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class LevelMenu extends Activity {

	private final int numberOfLevels = 5;
//	public static final String PREF_CURRENT_LEVEL = "current_level"; 
	public static final String PREF_ENABLED_LEVEL = "enabled_level"; 

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_menu);
		LinearLayout layout = (LinearLayout) findViewById(R.id.linear_level_layout);
		
		int width = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 250, getResources()
						.getDisplayMetrics());
//		String current_level = PreferenceManager.getDefaultSharedPreferences(this).getString(PREF_CURRENT_LEVEL, null);
		String enabled_level = PreferenceManager.getDefaultSharedPreferences(this).getString(PREF_ENABLED_LEVEL, null);
		if (enabled_level == null) {
			PreferenceManager.getDefaultSharedPreferences(this).edit().putString(PREF_ENABLED_LEVEL, "0").commit();
		}
		
		for (int i = 0; i <= numberOfLevels; i++) {
			Button button = new Button(this);
			button.setLayoutParams(new LayoutParams(width,
					LayoutParams.WRAP_CONTENT));
			button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
			button.setId(i);

			if (i == numberOfLevels) {
				button.setText("MAIN MENU");

				button.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						finish();
					}
				});
			} else {
				button.setText("LEVEL " + (i + 1));

				button.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						Intent openStart = new Intent(
								"uk.ac.reading.tq011338.commandlines.COMMANDLINES");
						openStart.putExtra("selected_level", v.getId());
						startActivity(openStart);
					}
				});
			}

			layout.addView(button);

		}
	}
}
