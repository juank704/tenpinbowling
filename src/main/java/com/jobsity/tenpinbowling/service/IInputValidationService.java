package com.jobsity.tenpinbowling.service;


import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.entity.ScoreTO;


/**
 * Input Validation Interface
 * @author Juan_
 *
 */
public interface IInputValidationService {
	
	/**
	 * Validate if Game Board is valid
	 * @param gameboard
	 * @return 
	 */
	public boolean isValidGameBoard(GameBoardTO gameboard);
	
	/**
	 * Validate if frame is valid
	 * @param frame
	 * @return
	 */
	public boolean isValidFrame(FrameTO frame);
	
	/**
	 * Validate if Score is valid 
	 * @param score
	 * @return
	 */
	public boolean isValidScore(ScoreTO score);
	

}
