package com.jobsity.tenpinbowling;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.entity.LineTO;

public class PerfectScoreIT extends BowlingIT {

	@Test
	public void perfectScore() {
		File perfectFile = new File(this.getClass().getResource("/perfectScore.txt").getFile());
		String pathFile = String.format("%s", perfectFile.getAbsolutePath().toString());

		GameBoardTO gameboard = this.gameBoardService.load(pathFile);
		Assert.assertNotNull(gameboard);

		gameboard = this.pointService.calculate(gameboard);
		Assert.assertNotNull(gameboard);
		LineTO bowler = gameboard.getLines().get(0);
		Assert.assertNotNull(bowler);

		List<FrameTO> frames = bowler.getFrames();
		Assert.assertNotNull(frames);

		FrameTO lastFrame = frames.get(FrameTO.MAX_NUMBER_OF_FRAMES - 1);
		Assert.assertNotNull(lastFrame);

		int maxScore = 300;
		Assert.assertTrue(lastFrame.getResult() == maxScore);
	}

}
