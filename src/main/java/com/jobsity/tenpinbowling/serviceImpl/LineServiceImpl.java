package com.jobsity.tenpinbowling.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.LineTO;
import com.jobsity.tenpinbowling.service.IFrameService;
import com.jobsity.tenpinbowling.service.ILineService;



/**
 * Parse each line of file to get score
 * @author Juan_
 *
 */
@Service
public class LineServiceImpl implements ILineService {
	
	private IFrameService frameService;
	
	@Autowired
	  public LineServiceImpl(IFrameService frameService) {
	    this.frameService = frameService;
	  }
	
	@Override
	public List<LineTO> parse(Map<String, List<String>> scores) {
		if (scores == null || (scores != null && scores.isEmpty())) {
		      return null;
		    }

		    List<LineTO> lines = new ArrayList<LineTO>();
		    for (Map.Entry<String, List<String>> entry : scores.entrySet()) {
		        String bowler = entry.getKey();
		        List<String> scoresList = entry.getValue();
		        LineTO line = new LineTO(bowler);
		        List<FrameTO> frames = this.frameService.createFrames(scoresList);
		        line.setFrames(frames);
		        lines.add(line);
		    }
		    return lines;
	}
	

}
