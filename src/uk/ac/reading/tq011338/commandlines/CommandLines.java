package uk.ac.reading.tq011338.commandlines;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import uk.ac.reading.tq011338.parser.CommandLinesLexer;
import uk.ac.reading.tq011338.parser.CommandLinesParser;
import uk.ac.reading.tq011338.parser.ExtendedCommandLinesBaseVisitor;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class CommandLines extends Activity {

	private GameView mView; // displays and manages the game
	private GameThread mGameThread;

	private Button mRunButton;
	private Button mClearButton;
	private EditText mCommandView;

	private ImageButton mAttackButton;
	private ImageButton mHealButton;	
	private ImageButton mDefendButton;
	private ImageButton mMoveButton;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_command); // inflate the layout

		mView = (GameView) findViewById(R.id.gameArea); // get the GameView
		mGameThread = new TheGame(mView, this);
		mView.setThread(mGameThread);
		mView.setStatusView((TextView) findViewById(R.id.text));

		mRunButton = (Button) findViewById(R.id.run_button);
		mRunButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ActionFigure figure = TheGame.getActiveFigure();
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

				TheGame.isButtonClicked = true;
				mCommandView.setText("");
			}
		});

		mClearButton = (Button) findViewById(R.id.clear_button);
		mClearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mCommandView.setText(""); // delete text and text in the object
				ActionFigure figure = TheGame.getActiveFigure();
				if (figure == null)
					return;
			}
		});

		mCommandView = (EditText) findViewById(R.id.commandView);

		mAttackButton = (ImageButton) findViewById(R.id.attackButton);
		mAttackButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDescriptionDialog(R.string.attackId, R.string.attackText);
			}
		});
		
		mHealButton = (ImageButton) findViewById(R.id.healButton);
		mHealButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDescriptionDialog(R.string.healId, R.string.healText);
			}
		});

		mDefendButton = (ImageButton) findViewById(R.id.defendButton);
		mDefendButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDescriptionDialog(R.string.defendId, R.string.defendText);
			}
		});
		
		mMoveButton = (ImageButton) findViewById(R.id.moveButton);
		mMoveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDescriptionDialog(R.string.moveId, R.string.moveText);
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

	private void showDescriptionDialog(int messageId, int message) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				mView.getContext());
		builder.setTitle(mView.getResources().getString(messageId));
		builder.setCancelable(true);

		builder.setMessage(mView.getResources().getString(message));
		builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
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
}
