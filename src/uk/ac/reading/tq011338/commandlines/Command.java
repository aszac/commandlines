package uk.ac.reading.tq011338.commandlines;

import java.util.UUID;

public class Command {
	private UUID mID;
	private String commandName;
	
	public Command() {
		
	}

	public UUID getID() {
		return mID;
	}

	public void setID(UUID iD) {
		mID = iD;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
}
