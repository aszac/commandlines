package uk.ac.reading.tq011338.commandlines;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import uk.ac.reading.tq011338.parser.CommandLinesLexer;
import uk.ac.reading.tq011338.parser.CommandLinesParser;
import uk.ac.reading.tq011338.parser.ExtractInterfaceListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

public class CommandLines extends Activity {

	private GameView mView; // displays and manages the game
	private GameThread mGameThread;

	private Button mRunButton;
	private Button mClearButton;
	private EditText mCommandView;

	private GestureDetector gestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_command); // inflate the layout

		mView = (GameView) findViewById(R.id.gameArea); // get the GameView
		mGameThread = new TheGame(mView, this);
		mView.setThread(mGameThread);

		mRunButton = (Button) findViewById(R.id.run_button);
		mRunButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ActionFigure figure = mGameThread.getSelectedFigure();
				if (figure == null)
					return;

				// parses the commend and executes the code on the object
				CharStream cs = new ANTLRInputStream("figure."
						+ mCommandView.getText().toString());
				CommandLinesLexer lexer = new CommandLinesLexer(cs);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				CommandLinesParser parser = new CommandLinesParser(tokens);
				
				// check if input has parsing errors
					ParserRuleContext tree = parser.move();
					if (parser.getNumberOfSyntaxErrors() == 0) {

					ParseTreeWalker walker = new ParseTreeWalker();
					ExtractInterfaceListener extractor = new ExtractInterfaceListener(
							parser, figure);
					walker.walk(extractor, tree);
				}

				figure.setCommand("");
				mCommandView.setText("");
			}
		});

		mClearButton = (Button) findViewById(R.id.clear_button);
		mClearButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mCommandView.setText(""); // delete text and text in the object
				ActionFigure figure = mGameThread.getSelectedFigure();
				if (figure == null)
					return;
				figure.setCommand("");
			}
		});

		mCommandView = (EditText) findViewById(R.id.commandView);

		gestureDetector = new GestureDetector(gestureListener);

	}

	SimpleOnGestureListener gestureListener = new SimpleOnGestureListener() {

	};

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			mGameThread.selectFigure(event);
		}
		return gestureDetector.onTouchEvent(event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mView.cleanup();

		mGameThread = null;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
