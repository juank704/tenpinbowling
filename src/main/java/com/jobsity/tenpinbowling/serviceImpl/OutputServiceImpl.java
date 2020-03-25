package com.jobsity.tenpinbowling.serviceImpl;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.service.IOutputService;

/**
 * Output for bowling game
 * @author Juan_
 *
 */
@Service
public class OutputServiceImpl implements IOutputService {

	@Override
	public void output(GameBoardTO gameboard) {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(boardFrame());
		stringBuilder.append("\n");
		
		gameboard.getLines().forEach(action -> {
			stringBuilder.append(action.getBowler().getName());
			stringBuilder.append("\n");
			stringBuilder.append("Pinfalls");
			stringBuilder.append(pinFalls(action.getFrames()));
			stringBuilder.append("\n");
            stringBuilder.append("Score");
            stringBuilder.append(score(action.getFrames()));
            stringBuilder.append("\n");
		});
		
		System.out.println(stringBuilder.toString()); 
		
	}
	
	private String boardFrame() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Frame\t\t");
        IntStream.range(1, 11).forEach(e -> {
            stringBuilder.append(e);
            stringBuilder.append("\t\t");
        });
        return stringBuilder.toString();
    }
	
	 private String pinFalls(List<FrameTO> frame) {
	        StringBuilder stringBuilder = new StringBuilder();
	        
	        frame.forEach(score -> {
	        	score.getScore().forEach(point -> {
	        		String value = point.isStrike() ? "\tX" : point.isSpare() ? "/" : point.getScore();
	        		stringBuilder.append("\t" + value);
	        	});
	        });
	        
	        return stringBuilder.toString();

	    }
	 
	 private String score(List<FrameTO> frame) {
	        StringBuilder stringBuilder = new StringBuilder();
	        frame.forEach(score -> {
	            stringBuilder.append("\t\t");
	            stringBuilder.append(score.getResult());
	        });
	        return stringBuilder.toString();
	    }

}
