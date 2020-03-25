package com.jobsity.tenpinbowling.service;

import java.util.List;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.GameBoardTO;


/**
 * Service to calculate point of game
 * @author Juan_
 *
 */
public interface IPointService {

	/**
	 * @param gameboard information of all bowlers and score
	 * @return Game board with score
	 */
	public GameBoardTO calculate(GameBoardTO gameboard);
	
	/**
	 * Calculate all point for all frames
	 * @param frames
	 * @return
	 */
	public List<FrameTO> calculatePoint(List<FrameTO> frames);
	
}
