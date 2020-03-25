package com.jobsity.tenpinbowling.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.entity.LineTO;
import com.jobsity.tenpinbowling.service.IFileService;
import com.jobsity.tenpinbowling.service.IGameBoardService;
import com.jobsity.tenpinbowling.service.ILineService;
import com.jobsity.tenpinbowling.service.IScoreService;


/**
 * Transform giving file to GameBoardTO structure (Lines -> (Bowler, Frames(Score, Point)))
 * Implementation for IGameBoardService 
 * @author Juan_
 *
 */
@Service
public class GameBoardServiceImpl implements IGameBoardService {

	  protected Logger logger = LoggerFactory.getLogger(GameBoardServiceImpl.class);

	  @Autowired
	  private IFileService fileService;
	  
	  @Autowired
	  private IScoreService scoreService;
	  
	  @Autowired
	  private ILineService lineService;
	  
	  
	  /**
	   * return a structure as GameBoardTO (Lines -> (Bowler, Frames(Score, Point)))
	   */
	  @Override
	  public GameBoardTO load(String fileName) {
	    
		  try {
			
			  	//check if filename is null or empty
			  	String file = Optional.ofNullable(fileName).orElse("");
				if (file.trim().isEmpty()) {
				    return null;
				}
				
				//get lines from file
				List<String> linesFile = this.fileService.load(file);

				//check if lines are not empty
			    if(linesFile.stream().anyMatch(s -> (s == null || s.equals("")))){
			    	return null;
			    }
			
			    //get score from lines
			    Map<String,List<String>> scores = this.scoreService.parse(linesFile);
			    
			    //check if scores are empty
			    if (scores ==  null || (scores != null && scores.isEmpty()) ) {
				    return null;
				}
		
			    //Transform score to Line Structure (bowler, frame) 
			    List<LineTO> linesTO = this.lineService.parse(scores);
			    
			    //Check is line is empty
			    if (linesTO == null || (linesTO != null && linesTO.isEmpty())) {
				   logger.error(String.format("The lines are null or empty after trying to parse the throws"));
				   return null;
				 }
			    
			    //Create new gameBoard
			    GameBoardTO gameBoard = new GameBoardTO(linesTO);
			    
			    return gameBoard;
			    
		  } catch (Exception e) {
			 logger.error(String.format("Can't load file, couldn't parsed because of {0} "), e.getMessage());
			 return null;
		  }
		  
	  }
	
	
}
