package com.jobsity.tenpinbowling.serviceImpl;

import static org.junit.Assert.assertNotNull;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.entity.LineTO;

/**
 * Test for GameBoard Implementation Service
 * @author Juan_
 *
 */
public class GameBoardServiceImplTest extends BowlingImplTest {
	
	@Test
    public void loadTest() {
    	
		//get lines from file
		List<String> linesFile = this.fileService.load("score.txt");
		
		Map<String,List<String>> scores = this.scoreService.parse(linesFile);
		
		List<LineTO> linesTO = this.lineService.parse(scores);
		
		GameBoardTO gameBoard = new GameBoardTO(linesTO);
    	
		assertNotNull(gameBoard);
    	
       
    }
	
}
