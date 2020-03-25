package com.jobsity.tenpinbowling.service;

import java.util.List;

import com.jobsity.tenpinbowling.entity.FrameTO;

/**
 * Service for the Frame 
 * @author Juan_
 *
 */
public interface IFrameService {

	 /**
	    * Create a frame with n number of scores
	    * @param number
	    * @param scores
	    */
	    public FrameTO createFrame(int number, List<String> scores);

	    /**
	     * Creates a list of frames according with list of scores.
	    */
	    public List<FrameTO> createFrames(List<String> scores);

	    /**
	    * Checks if is the last frame in the game.
	    * @param currentFrame, frame index to check
	    * @return validation status.
	    */
	    public boolean isLastFrame(int currentFrame);

	    /**
	    * Checks if score is a strike throw.
	    * @param score to validate
	    * @return validation status
	    */
	    public boolean isStrike(String score);

	    /**
	    * Checks if is a frame is a strike
	    * @param frame to check
	    * @return status validation.
	    */
	    public boolean isStrike(FrameTO frame);

	    /**
	    * Checks if is a frame is a spark
	    * @param frame to check
	    * @return status validation.
	    */
	    public boolean isSparkFrame(FrameTO frame);

	    /**
	    * Checks if is a open frame
	    * @param frame to check
	    * @return status validation.
	    */
	    public boolean isOpenFrame(FrameTO frame);
	
	
}
