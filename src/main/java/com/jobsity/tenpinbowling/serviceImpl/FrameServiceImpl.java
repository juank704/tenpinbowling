package com.jobsity.tenpinbowling.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.ScoreTO;
import com.jobsity.tenpinbowling.service.IFrameService;
import com.jobsity.tenpinbowling.util.ScoreUtil;

/**
 * Implements the method for calculate the scores frame
 * @author Juan_
 *
 */
@Service
public class FrameServiceImpl implements IFrameService {
	
	 protected Logger logger = LoggerFactory.getLogger(FrameServiceImpl.class);

	/**
	 * Create a frame with n number of throws, according with scores
	 */
	@Override
	public FrameTO createFrame(int number, List<String> scoresList) {
		FrameTO frame = new FrameTO(number);

	    List<ScoreTO> scores = IntStream.range(0, scoresList.size())
	      .mapToObj(i -> new ScoreTO(scoresList.get(i),i,false,false))
	      .collect(Collectors.toList());
	    frame.setScore(scores);
	    if(frame.getScore().size() == 2 && this.isSparkFrame(frame)) {
	    	frame.getScore().get(1).setSpare(true);
	    }
	    if(frame.getScore().size() == 1 && this.isStrike(frame)) {
	    	frame.getScore().get(0).setStrike(true);
	    }
	    return frame;
	}

	@Override
	public List<FrameTO> createFrames(List<String> scores) {
		
		List<FrameTO> frames = new ArrayList<FrameTO>();
		
		try {

	    if (scores == null || (scores != null && scores.isEmpty())) {
	      return frames;
	    }

	    int currentFrame = 1;
	    for (int i=0; i<scores.size(); i++) {
	      String score = scores.get(i);

	      if (this.isLastFrame(currentFrame)) {
	        int secondIndex = i+1; //TODO Bug:
	        int thirdIndex  = i+2;
	        String secondScore = scores.get(secondIndex);
	        String thirdScore  = scores.get(thirdIndex);
	        List<String> scs = this.createScores(new String[]{score, secondScore, thirdScore});

	        FrameTO frame = this.createFrame(currentFrame,scs);
	        int frameScore = this.getFrameScore(scs);
	        frame.setPoint(frameScore);
	        frames.add(frame);
	        i = i+2;
	      } else if(this.isStrike(score)) {
	        List<String> scs= this.createScores(new String[]{score});
	        int frameScore = this.getFrameScore(scs);
	        FrameTO frame = this.createFrame(currentFrame,scs);
	        frame.setPoint(frameScore);
	        frames.add(frame);
	      } else {

	        int secondIndex = i+1;
	        String secondScore = scores.get(secondIndex);
	        List<String >scs =Arrays.asList(new String[]{score, secondScore});
	        int frameScore = this.getFrameScore(scs);
	        FrameTO frame = this.createFrame(currentFrame,scs);
	        frame.setPoint(frameScore);
	        frames.add(frame);
	        i++; // because I created a new throw with that score.
	      }
	      currentFrame++;
	    }
	    
		}catch (Exception e) {
			this.logger.error(String.format("Error creating Frame {0}", e.getMessage()));
			return null;
		}
	    
	    
	    return frames;
	  }
	

	@Override
	public boolean isLastFrame(int currentFrame) {
		 return currentFrame == FrameTO.LAST_FRAME;
	}

	@Override
	public boolean isStrike(String score) {
		 return ScoreTO.STRIKE_SCORE_STR.equals(score);
	}

	@Override
	public boolean isStrike(FrameTO frame) {
		  if (frame == null) {
		      return Boolean.FALSE;
		    }

		    List<ScoreTO> scores = frame.getScore();

		    if (scores == null || (scores != null && scores.isEmpty()) ) {
		      return Boolean.FALSE;
		    }

		    int throwsNumber = scores.size();
		    if (throwsNumber != 1) {
		      return Boolean.FALSE;
		    }

		    int score = this.sumirezeThrows(scores, throwsNumber);

		    if (ScoreTO.MAX_SCORE_PER_THROW == score) {
		      return Boolean.TRUE;
		    }

		    return Boolean.FALSE;
	}

	@Override
	public boolean isSparkFrame(FrameTO frame) {
		if (frame == null) {
		      return Boolean.FALSE;
		    }

		    List<ScoreTO> trows = frame.getScore();

		    if (trows == null || (trows != null && trows.isEmpty())) {
		      return Boolean.FALSE;
		    }

		    int throwsNumber = trows.size();


		    int score = this.sumirezeThrows(trows, throwsNumber);

		    if (ScoreTO.MAX_SCORE_PER_THROW != score) {
		      return Boolean.FALSE;
		    }
		    return Boolean.TRUE;
	}
	
	
	

	@Override
	public boolean isOpenFrame(FrameTO frame) {
		 if (frame == null) {
		        return Boolean.FALSE;
		      }

		      List<ScoreTO> trows = frame.getScore();

		      if (trows == null || (trows != null && trows.isEmpty())) {
		        return Boolean.FALSE;
		      }

		      int throwsNumber = trows.size();

		      if (throwsNumber != ScoreTO.THROWS_PER_OPEN_FRAME) {
		        return Boolean.FALSE;
		      }

		      int score = this.sumirezeThrows(trows, throwsNumber);

		      if (score >= 0  || score < ScoreTO.MAX_SCORE_PER_THROW) {
		          return Boolean.TRUE;
		      }

		      return Boolean.FALSE;
	}
	
	
	 private int sumirezeThrows(List<ScoreTO> scores,int throwsNumber) {

	      if (scores == null || (scores != null && scores.isEmpty())) {
	        return 0;
	      }
	      //if the sublist is bigger than throws return -1
	      if (throwsNumber > scores.size()) {
	        return -1;
	      }

	      return scores.subList(0, throwsNumber)
	          .stream().map(t -> ScoreUtil.getScoreInt(t.getScore()))
	          .reduce(0,Integer::sum);
	  }
	
	 private List<String> createScores(String[] scores) {
		    return Arrays.asList(scores);
		  }
	 
	 private int getFrameScore(List<String> scores) {

		    if (scores == null || (scores != null && scores.isEmpty())) {
		      return 0;
		    }

		    return scores.stream()
		           .map( s -> ScoreUtil.getScoreInt(s)).reduce(0, Integer::sum);
	 }
	 

}
