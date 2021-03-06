package uk.ac.reading.tq011338.commandlines;

import java.util.ArrayList;
import java.util.List;

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

	private final int numberOfLevels = 6;
	private static final String PREF_ENABLED_LEVEL = "enabled_level";
	private List<Button> levelButtons = new ArrayList<Button>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_menu);
		LinearLayout layout = (LinearLayout) findViewById(R.id.linear_level_layout);
		int width = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 250, getResources()
						.getDisplayMetrics());
		
		for (int i = 0; i <= numberOfLevels; i++) {
			Button button = new Button(this);
			button.setLayoutParams(new LayoutParams(width,
					LayoutParams.WRAP_CONTENT));
			button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
			button.setId(i);

			int enabledLevel = Integer.parseInt(checkEnabledLevel());
			// disactivate access to more advanced levels
			if (enabledLevel + 1 >= i) {
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}

			if (i == numberOfLevels) {
				button.setText("MAIN MENU");
				button.setEnabled(true);
				button.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						finish();
					}
				});
			} else if (i == 0) {
				button.setText("RESUME");		
				if (checkIfResumeFileExists()) {
					button.setEnabled(true);
				} else {
					button.setEnabled(false);
				}
				setSelectedLevelListener(button);
			} else {
				button.setText("LEVEL " + (i));	
				setSelectedLevelListener(button);
				levelButtons.add(button);
			}
				
			layout.addView(button);
		}
	}
	
	protected void onResume() {
		super.onResume();
		int enabledLevel = Integer.parseInt(checkEnabledLevel());
		for (int i = 0; i <= enabledLevel; i++) {
			levelButtons.get(i).setEnabled(true);
		}
	}



	/**
	 * OnClick start a new game, pass the selected level
	 * 
	 * @param button
	 */
	private void setSelectedLevelListener(Button button) {
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent openStart = new Intent(
						"uk.ac.reading.tq011338.commandlines.COMMANDLINES");
				openStart.putExtra("selected_level", v.getId());
				startActivity(openStart);
			}
		});		
	}

	/**
	 * Check if resume file exists to enable the resume button
	 * 
	 * @return
	 */
	private boolean checkIfResumeFileExists() {
		String[] fileList = fileList();

		for (String fileName : fileList) {
			if (fileName.equalsIgnoreCase("resume_game.json")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find the highest enabled level
	 * 
	 * @return enabled level
	 */
	public String checkEnabledLevel() {
		String enabled_level = PreferenceManager.getDefaultSharedPreferences(
				this).getString(PREF_ENABLED_LEVEL, null);
		if (enabled_level == null) {
			PreferenceManager.getDefaultSharedPreferences(this).edit()
					.putString(PREF_ENABLED_LEVEL, String.valueOf("0"))
					.commit();
			enabled_level = "0";
		}
		return enabled_level;
	}
}
