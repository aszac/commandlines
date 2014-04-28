package uk.ac.reading.tq011338.commandlines;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

public class MatchInitiatedCallback implements ResultCallback<TurnBasedMultiplayer.InitiateMatchResult>{

	public void onResult(TurnBasedMultiplayer.InitiateMatchResult result) {
        // Check if the status code is not success;
        if (result.getStatus().getStatusCode() != GamesStatusCodes.STATUS_OK) {
//            showError(statusCode);
            return;
        }

        TurnBasedMatch match = result.getMatch();

        // If this player is not the first player in this match, continue.
        if (match.getData() != null) {
  //          showTurnUI(match);
            return;
        }

        // Otherwise, this is the first player. Initialize the game state.
        initGame(match);

        // Let the player take the first turn
 //       showTurnUI(match);
		
	}
	
	private void initGame (TurnBasedMatch match) {
	// TODO	

	}


}
