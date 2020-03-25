package com.jobsity.tenpinbowling.service;

import com.jobsity.tenpinbowling.entity.GameBoardTO;

/**
 * Service to print game on console
 * @author Juan_
 *
 */
public interface IOutputService {

	 public void output(GameBoardTO gameboard);
	
}
