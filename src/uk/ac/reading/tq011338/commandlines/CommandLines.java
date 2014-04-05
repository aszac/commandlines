package uk.ac.reading.tq011338.commandlines;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

import uk.ac.reading.tq011338.parser.CommandLinesLexer;
import uk.ac.reading.tq011338.parser.CommandLinesParser;
import uk.ac.reading.tq011338.parser.ExtendedCommandLinesBaseVisitor;
import uk.ac.reading.tq011338.serializer.CommandLinesJSONSerializer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class CommandLines extends Activity {

	private GameView mView; // displays and manages the game
	private GameThread mGameThread;

	private Button mRunButton;
	private Button mClearButton;
	private MultiAutoCompleteTextView mCommandView;

	private ImageButton mAttackButton;
	private ImageButton mHealButton;
	private ImageButton mDefendButton;
	private ImageButton mMoveButton;

	private static final int MENU_RESUME = 1;
	private static final int MENU_MISSION = 2;
	private static final int MENU_MENU = 3;
	
	private static final String FILENAME = "resume_game.json";
	private static final String TAG = "WorldObjects";
	private CommandLinesJSONSerializer mSerializer;
	
	private String mMissionDescription = "mission_desc";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_command); // inflate the layout
		
		int selected_level = getIntent().getExtras().getInt("selected_level");

		mView = (GameView) findViewById(R.id.gameArea); // get the GameView
		mGameThread = new TheGame(mView, this, selected_level);
		mView.setThread(mGameThread);
		mView.setStatusView((TextView) findViewById(R.id.text));

		mRunButton = (Button) findViewById(R.id.run_button);
		mRunButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ActionFigure figure = mGameThread.getActiveFigure();
				if (figure == null)
					return;

				// parses the commend and executes the code on the object
				CharStream cs = new ANTLRInputStream(mCommandView.getText()
						.toString());
				CommandLinesLexer lexer = new CommandLinesLexer(cs);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				CommandLinesParser parser = new CommandLinesParser(tokens);

				// check if input has parsing errors
				ParserRuleContext tree = parser.parse();
				if (parser.getNumberOfSyntaxErrors() == 0) {

					ExtendedCommandLinesBaseVisitor visitor = new ExtendedCommandLinesBaseVisitor(
							figure);
					visitor.visit(tree);
				}

				mGameThread.isButtonClicked = true;
				mCommandView.setText("");
			}
		});

		mClearButton = (Button) findViewById(R.id.clear_button);
		mClearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mCommandView.setText(""); // delete text and text in the object
				ActionFigure figure = mGameThread.getActiveFigure();
				if (figure == null)
					return;
			}
		});

		mCommandView = (MultiAutoCompleteTextView) findViewById(R.id.commandView);
		String[] commands = mView.getResources().getStringArray(
				R.array.list_of_commands);
		ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
				android.R.layout.simple_list_item_1, commands);
		mCommandView.setAdapter(adapter);
		mCommandView.setTokenizer(new NewLineTokenizer());
		mCommandView.setThreshold(1);

		mAttackButton = (ImageButton) findViewById(R.id.attackButton);
		mAttackButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDescriptionDialog(R.string.attackId, R.string.attackText,
						"");
			}
		});

		mHealButton = (ImageButton) findViewById(R.id.healButton);
		mHealButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDescriptionDialog(R.string.healId, R.string.healText, "");
			}
		});

		mDefendButton = (ImageButton) findViewById(R.id.defendButton);
		mDefendButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDescriptionDialog(R.string.defendId, R.string.defendText,
						"");
			}
		});

		mMoveButton = (ImageButton) findViewById(R.id.moveButton);
		mMoveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDescriptionDialog(R.string.moveId, R.string.moveText, "");
			}
		});

	}

	protected void onDestroy() {
		super.onDestroy();
		mView.cleanup();

		mGameThread = null;
		mView = null;
	}

	protected void onPause() {
		super.onPause();
	}

	private void showDescriptionDialog(int messageId, int message,
			String message_txt) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				mView.getContext());
		builder.setTitle(mView.getResources().getString(messageId));
		builder.setCancelable(true);

		if (message_txt.equals("")) {
			builder.setMessage(mView.getResources().getString(message));
		} else {
			builder.setMessage(message_txt);
		}

		builder.setNeutralButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		this.runOnUiThread(new Runnable() {
			public void run() {
				builder.show();
			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(0, MENU_RESUME, 0, R.string.menu_resume);
		menu.add(0, MENU_MISSION, 0, R.string.menu_mission);
		menu.add(0, MENU_MENU, 0, R.string.menu_menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_RESUME: {
			return true;
		}
		case MENU_MISSION:
			showDescriptionDialog(R.string.missionId, 0, mMissionDescription);
			return true;
		case MENU_MENU:
			saveWorldObjects();
			finish();
			return true;
		}

		return false;
	}
	
	public boolean saveWorldObjects() {
		try {
			//TODO FIXXXXXXXXXXXXXXXXXXXX
//			mSerializer.saveWorldObjects(TheGame.figureList);
			Log.d(TAG, "World objects saved to a file");
			return true;
		}
		catch (Exception e) {
			Log.e(TAG, "Error saving world objects: ", e);
			return false;
		}
	}
}
