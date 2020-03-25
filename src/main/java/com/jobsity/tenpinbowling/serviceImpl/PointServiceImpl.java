package com.jobsity.tenpinbowling.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.entity.LineTO;
import com.jobsity.tenpinbowling.entity.ScoreTO;
import com.jobsity.tenpinbowling.service.IFrameService;
import com.jobsity.tenpinbowling.service.IPointService;
import com.jobsity.tenpinbowling.util.ScoreUtil;

/**
 * Calculate the points of bowling game
 * @author Juan_
 *
 */
@Service
public class PointServiceImpl implements IPointService {
	
	/**
	  * Frame service to determine the type of frame
	  */
	  private IFrameService fs;
	  
	  @Autowired
	  public PointServiceImpl(final IFrameService fs) {
	    this.fs = fs;
	  }
	
	@Override
	public GameBoardTO calculate(GameBoardTO gameboard) {
		List<LineTO> lines = gameboard.getLines();

	    for (LineTO line:lines) {
	      List<FrameTO> frames = line.getFrames();
	      frames = this.calculatePoint(frames);
	    }

	    return gameboard;
	}

	@Override
	public List<FrameTO> calculatePoint(List<FrameTO> frames) {
		
		 if (frames == null || (frames != null && frames.isEmpty())) {
		        return null;
		      }
		 
		 int lastFrameResult = 0;
		
		 for (FrameTO frame:frames) {
		        int result = 0;
		        if (fs.isLastFrame(frame.getNumber())) {
		          result = this.calculateOpenFrame(frames, frame,lastFrameResult);
		        }

		        else if (fs.isStrike(frame)) {
		          result = this.calculateStrikeFrame(frames, frame,lastFrameResult);
		        }

		        else if(fs.isSparkFrame(frame)) {
		          result = this.calculateSparkFrame(frames, frame,lastFrameResult);
		        }

		        else if (fs.isOpenFrame(frame)) {
		          result = this.calculateOpenFrame(frames, frame,lastFrameResult);
		        }
		        lastFrameResult = result;
		        frame.setResult(result);
		      }
		      return frames;
	}
	
	
	 public int calculateSparkFrame(List<FrameTO> frames, FrameTO cf, int beforeResult) {
		    return this.calculateFrame(frames, cf, 1, beforeResult);
		  }
	 
	 public int calculateStrikeFrame(List<FrameTO> frames,FrameTO cf, int beforeResult) {
		    return this.calculateFrame(frames, cf, 2, beforeResult);
		  }
	 
	 public int calculateOpenFrame(List<FrameTO> frames,FrameTO f, int beforeResult) {
		    if (isNotCalculableFrame(frames,f)) {
		      return 0;
		    }
		    return f.getNumber() == 1 ? f.getPoint() : f.getPoint() + beforeResult;
		  }
	 
	  public boolean isNotCalculableFrame(List<FrameTO> frames,FrameTO cf) {
		    return  (frames == null || (frames != null && frames.isEmpty()) || cf == null );
		  }
	  
	  public int calculateFrame(List<FrameTO> frames,FrameTO cf, int offset, int beforeResult) {

		    if (this.isNotCalculableFrame(frames, cf)) {
		      return 0;
		    }

		    int currentFrame = cf.getNumber();

		    if (currentFrame <0 || currentFrame > FrameTO.MAX_NUMBER_OF_FRAMES || offset < 0 ){
		      return 0;
		    }

		    List<ScoreTO> ts = new ArrayList<ScoreTO>(2);
		    int framesSize = frames.size();

		    //  get the throws from frames;
		    for (int i=currentFrame; i<(currentFrame+offset); i++) {

		      if (i == framesSize) {
		        break;
		      }

		      FrameTO f = frames.get(i);
		      if (ts.size() == offset) {
		        break;
		      }

		      List<ScoreTO> cts = f.getScore();
		      ts.addAll(cts);

		    }
		    // sumarize the next painfalls scores
		    int nextResults = ts.subList(0,offset)
		            .stream()
		            .map(t -> ScoreUtil.getScoreInt(t.getScore())).reduce(0, Integer::sum);

		    if (cf.getNumber() == 1) {
		      return (cf.getPoint() + nextResults);
		    }

		    return cf.getPoint() +  nextResults + beforeResult;
		  }

}
