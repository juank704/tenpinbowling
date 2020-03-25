package com.jobsity.tenpinbowling.entity;

import java.util.List;


/**
 * Model for frame, refers to a single turn by the player
 * @author Juan_
 *
 */
public class FrameTO {
	
	
	public static final int MAX_NUMBER_OF_FRAMES = 10;
	public static final int FIRST_FRAME = 1;
	public final static int LAST_FRAME = 10;
	
	
	public FrameTO(int number) {
	    this.number = number;
	}
	
	// Score for current frame
	private List<ScoreTO> score;
	 
	// Score for frame
	private int frameScore;
	 
	// Score the result frame depending on frame type
	private int result;
	
	// Value of score on given frame
	private int point;
	
	//Number of frame
	private int number;
	
	
	public List<ScoreTO> getScore() {
		return score;
	}

	public void setScore(List<ScoreTO> score) {
		this.score = score;
	}

	public int getFrameScore() {
		return frameScore;
	}

	public void setFrameScore(int frameScore) {
		this.frameScore = frameScore;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	 

}
