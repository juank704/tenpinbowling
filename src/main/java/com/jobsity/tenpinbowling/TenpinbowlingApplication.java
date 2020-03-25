package com.jobsity.tenpinbowling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.service.IGameBoardService;
import com.jobsity.tenpinbowling.service.IOutputService;
import com.jobsity.tenpinbowling.service.IPointService;



/**
 * 
 * @author Juan_
 * Main Application Class
 * Implements commandLineRunner to display on console
 */
@SpringBootApplication
public class TenpinbowlingApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(TenpinbowlingApplication.class);
	
	@Autowired
	private IGameBoardService gameBoardService;
	
	@Autowired
	private IPointService pointService;
	
	@Autowired
	private IOutputService outputService;
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	/**
	 * Entry point application
	 * @param args 
	 */
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(TenpinbowlingApplication.class);
		app.run(args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
			//getting file from console arguments
			String fileName = args[0];
			
			//process bowling game
			this.bowlingGame(fileName);
			
			// Exit application
			SpringApplication.exit(context, () -> 0);
			
		} catch (Exception e) {
			logger.error(" Error getting file {0} ", e);
		}
		
		
	}

	/**
	 * Process bowling game
	 * @param fileName
	 */
	 private void bowlingGame(String fileName) {
		 
		 	//load file and return bowling frames
		    GameBoardTO gameBoard =  this.gameBoardService.load(fileName);
		    
		    //Calculate total score giving bowling frames 
		    gameBoard = this.pointService.calculate(gameBoard);
		    
		    //Print result for game
		    outputService.output(gameBoard);
		  }
	
}
