package com.jobsity.tenpinbowling.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.service.IScoreService;


/**
 * Implementation for ScoreService
 * @author Juan_
 *
 */
@Service
public class ScoreServiceImpl implements IScoreService {

	private Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);
	
	//parse scores to a map -> (bowler, scores list) by each bowler
	@Override
	public Map<String, List<String>> parse(List<String> lines) {
		
		if(lines.stream().anyMatch(s -> (s == null || s.equals("")))){
		    return null;
		}
		 
		Map<String,List<String>> scores = new LinkedHashMap<String, List<String>>();

		  for (int i=0; i<lines.size(); i++) {

		    String line = lines.get(i);

		    String[] record = line.split("\t");
		    if (record != null && record.length == 2) {
		      String bowler = record[0];
		      String score = record[1];
		      scores = this.addScore(scores, bowler, score);
		    } else {
		       logger.error(String.format("Can't read line {0} ",line));
		      }
		    }
		    return scores;
	
	}

	//add Score to map -> (bowler, scores list)
	@Override
	public Map<String, List<String>> addScore(Map<String, List<String>> scores, String bowler, String score) {
		if (this.isPlayer(scores, bowler)) {
		      List<String> scoresList = scores.get(bowler);
		      scoresList.add(score);
		    } else {
		      List<String> scoresList = new ArrayList<String>();
		      scoresList.add(score);
		      scores.put(bowler, scoresList);
		    }
		    return scores;
	}
	
	//Check is the bowler name is already mapped
	private boolean isPlayer(Map<String, List<String>> scores, String bowler) {
		if (scores == null) {
		    return Boolean.FALSE;
		}
		return scores.containsKey(bowler);
	}
		
	
	

}
