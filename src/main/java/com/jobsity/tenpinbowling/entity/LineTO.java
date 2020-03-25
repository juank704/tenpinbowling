package com.jobsity.tenpinbowling.entity;

import java.util.List;


/**
 * Line Detail contains Player's name and Score
 * @author Juan_
 *
 */
public class LineTO {

	private List<FrameTO> frames;
	private BowlerTO bowler;
	
	public LineTO(String bowlerName) {
		    this.bowler = new BowlerTO(bowlerName);
	}

	public List<FrameTO> getFrames() {
		return frames;
	}

	public void setFrames(List<FrameTO> frames) {
		this.frames = frames;
	}

	public BowlerTO getBowler() {
		return bowler;
	}

	public void setBowler(BowlerTO bowler) {
		this.bowler = bowler;
	}

	
}
