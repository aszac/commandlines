package uk.ac.reading.tq011338.commandlines;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity  {

	Button startButton;
	Button helpButton;
	Button exitButton;
	Button multiplayerButton;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent openStart = new Intent(
						"uk.ac.reading.tq011338.commandlines.LEVELMENU");
				startActivity(openStart);
			}
		});

		helpButton = (Button) findViewById(R.id.helpButton);
		helpButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
//				Intent helpStart = new Intent("uk.ac.reading.tq011338.commandlines.CommandLines");
//				startActivity(helpStart);
			}
		});

		exitButton = (Button) findViewById(R.id.exitButton);
		exitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
	    multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
	    multiplayerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent openStart = new Intent(
						"uk.ac.reading.tq011338.commandlines.COMMANDLINESMULTIPLAYER");
				startActivity(openStart);
			}
		});
	}


}
