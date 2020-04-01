package com.jobsity.tenpinbowling.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.entity.LineTO;
import com.jobsity.tenpinbowling.entity.ScoreTO;
import com.jobsity.tenpinbowling.service.IFrameService;
import com.jobsity.tenpinbowling.service.IInputValidationService;
import com.jobsity.tenpinbowling.util.ScoreUtil;

/**
 * Validation service Implementation
 * 
 * @author Juan_
 *
 */
@Service
public class InputValidationServiceImpl implements IInputValidationService {

	protected Logger logger = LoggerFactory.getLogger(InputValidationServiceImpl.class);

	private IFrameService frameService;

	@Autowired
	public InputValidationServiceImpl(final IFrameService frameService) {
		this.frameService = frameService;
	}

	@Override
	public boolean isValidGameBoard(GameBoardTO gameboard) {

		List<LineTO> lines = gameboard.getLines();

		for (LineTO l : lines) {
			List<FrameTO> frames = l.getFrames();
			for (FrameTO f : frames) {
				boolean isValidFrame = this.isValidFrame(f);
				if (!isValidFrame) {
					return Boolean.FALSE;
				}
			}
		}

		return Boolean.TRUE;

	}

	@Override
	public boolean isValidFrame(FrameTO frame) {

		if (frame == null) {
			return Boolean.FALSE;
		}

		List<ScoreTO> scores = frame.getScore();

		if (scores == null || (scores != null && scores.isEmpty())) {
			return Boolean.FALSE;
		}

		int scoresNumber = scores.size();
		int frameNumber = frame.getNumber();
		boolean isLastFrame = this.frameService.isLastFrame(frameNumber);
		boolean isStrikeFrame = this.frameService.isStrike(frame);
		boolean isOpenFrame = this.frameService.isOpenFrame(frame);

		if (!(frameNumber > 0) && !(frameNumber <= FrameTO.MAX_NUMBER_OF_FRAMES)) {
			logger.error(String.format("The frame number :%s is not valid", frameNumber));
			return Boolean.FALSE;
		}

		if (!isLastFrame && scoresNumber == ScoreTO.NUMBER_OF_THROWS_FOR_LAST_FRAME) {
			logger.error(
					String.format("The frame %s is not the last frame but have more than two throws", frameNumber));
			return Boolean.FALSE;
		}

		if (!isStrikeFrame && scoresNumber == ScoreTO.THRWOS_PER_STRIKE) {
			logger.error(String.format("The frame %s is not strike but have just one throw", frameNumber));
			return Boolean.FALSE;
		}

		if (isOpenFrame && scoresNumber != ScoreTO.THROWS_PER_OPEN_FRAME) {
			logger.error(String.format("The frame %s is an open frame but have throws diferents to two ", frameNumber));
			return Boolean.FALSE;
		}

		for (ScoreTO s : scores) {

			boolean isValidScore = this.isValidScore(s);

			if (!isValidScore) {
				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;

	}

	@Override
	public boolean isValidScore(ScoreTO score) {

		if (score == null) {
			return Boolean.FALSE;
		}

		int point = ScoreUtil.getScoreInt(score.getScore());

		if (point < 0 || point > 10) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

}
