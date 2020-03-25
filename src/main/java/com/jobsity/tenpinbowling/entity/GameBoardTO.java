package com.jobsity.tenpinbowling.entity;

import java.util.List;


/**
 * Entity class for GameBoard
 * @author Juan_
 *
 */
public class GameBoardTO {
	
	public GameBoardTO(List<LineTO> lines) {
		this.lines = lines;
	 }
	
	private List<LineTO> lines;
	private boolean valid;

	public List<LineTO> getLines() {
		return lines;
	}
	
	public void setLines(List<LineTO> lines) {
		this.lines = lines;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
	
}
