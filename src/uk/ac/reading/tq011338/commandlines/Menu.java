package uk.ac.reading.tq011338.commandlines;

import java.util.ArrayList;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.example.games.basegameutils.BaseGameActivity;
import uk.ac.reading.tq011338.commandlines.MatchInitiatedCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends BaseGameActivity implements View.OnClickListener {

	Button startButton;
	Button helpButton;
	Button exitButton;
	Button multiplayerButton;
	Button signOutButton;
	
    final static int RC_SELECT_PLAYERS = 10000;

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
		
		findViewById(R.id.sign_in_button).setOnClickListener(this);
		
		signOutButton = (Button) findViewById(R.id.sign_out_button);
		signOutButton.setOnClickListener(this);       

	    multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
	    multiplayerButton.setEnabled(false);
	    multiplayerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = 
				        Games.TurnBasedMultiplayer.getSelectOpponentsIntent(getApiClient(), 1, 1, true);
				    startActivityForResult(intent, RC_SELECT_PLAYERS);
			}
		});
	}
	
    public void onActivityResult(int request, int response, Intent data) {
        super.onActivityResult(request, response, data);

        if (request == RC_SELECT_PLAYERS) {
            if (response != Activity.RESULT_OK) {
                // user canceled
                return;
            }

            // get the invitee list
            final ArrayList<String> invitees =
                    data.getStringArrayListExtra(Games.EXTRA_PLAYER_IDS);

            // get auto-match criteria
            Bundle autoMatchCriteria = null;
            int minAutoMatchPlayers = data.getIntExtra(
                    Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, 0);
            int maxAutoMatchPlayers
                    = data.getIntExtra(
                    Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, 0);
            if (minAutoMatchPlayers > 0) {
                autoMatchCriteria
                        = RoomConfig.createAutoMatchCriteria(
                        minAutoMatchPlayers, maxAutoMatchPlayers, 0);
            } else {
                autoMatchCriteria = null;
            }

            TurnBasedMatchConfig tbmc = TurnBasedMatchConfig.builder()
                    .addInvitedPlayers(invitees)
                    .setAutoMatchCriteria(autoMatchCriteria).build();

            // kick the match off
            Games.TurnBasedMultiplayer
                .createMatch(getApiClient(), tbmc)
                .setResultCallback(new MatchInitiatedCallback());
        }
    }

	public void onSignInFailed() {
	    // Sign in has failed. So show the user the sign-in button.
	    findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
	    findViewById(R.id.sign_out_button).setVisibility(View.GONE);		
	}

	@Override
	public void onSignInSucceeded() {
	    // show sign-out button, hide the sign-in button
	    findViewById(R.id.sign_in_button).setVisibility(View.GONE);
	    findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
	    
	    // (your code here: update UI, enable functionality that depends on sign in, etc)
	    multiplayerButton.setEnabled(true);

	}

	public void onClick(View view) {
	    if (view.getId() == R.id.sign_in_button) {
	        // start the asynchronous sign in flow
	        beginUserInitiatedSignIn();
	    }
	    else if (view.getId() == R.id.sign_out_button) {
	        // sign out.
	        signOut();

	        // show sign-in button, hide the sign-out button
	        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
	        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
		    multiplayerButton.setEnabled(false);
	    }
		
	}

}
