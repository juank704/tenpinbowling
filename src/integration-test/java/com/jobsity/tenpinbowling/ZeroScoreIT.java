package com.jobsity.tenpinbowling;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jobsity.tenpinbowling.entity.FrameTO;
import com.jobsity.tenpinbowling.entity.GameBoardTO;
import com.jobsity.tenpinbowling.entity.LineTO;

public class ZeroScoreIT extends BowlingIT {

	@Test
	public void processZeroScore() {
		File perfectFile = new File(this.getClass().getResource("/zeroScore.txt").getFile());
		String pathFile = String.format("%s", perfectFile.getAbsolutePath().toString());

		GameBoardTO gameBoard = this.gameBoardService.load(pathFile);
		Assert.assertNotNull(gameBoard);

		gameBoard = this.pointService.calculate(gameBoard);
		Assert.assertNotNull(gameBoard);

		LineTO zeroLine = gameBoard.getLines().get(0);
		Assert.assertNotNull(zeroLine);

		List<FrameTO> zeroFrames = zeroLine.getFrames();
		Assert.assertNotNull(zeroFrames);

		FrameTO zeroLastFrame = zeroFrames.get(FrameTO.MAX_NUMBER_OF_FRAMES - 1);
		Assert.assertNotNull(zeroLastFrame);

		int zeroScore = 0;
		Assert.assertTrue(zeroLastFrame.getResult() == zeroScore);
	}

}
