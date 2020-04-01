package com.jobsity.tenpinbowling.service;

import com.jobsity.tenpinbowling.entity.GameBoardTO;


/**
 * Create the BoardGame loading the file
 * @author Juan_
 *
 */
public interface IGameBoardService {

	/**
	 * load txt file
	 * @param fileName
	 * @return GameBoardTO structure for game Line -> Player -> Frame -> Score -> Point
	 */
	 public GameBoardTO load(String fileName);
	  
	 /**
	  * Validate if the file has valid inputs
	  * @param dashboard
	  * @return boolean
	  */
	 public boolean isValidGameBoard(GameBoardTO gameboard);
	 
	
}
