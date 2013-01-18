package org.less.cli;

import org.junit.Assert;
import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;
import org.lesscss.cli.Arguments;

public class ArgumentsTest {
	@Test
	public void testInputAndOutputFilenames() throws CmdLineException {
		String[] args = new String[] {"testInputFilename", "testOutputFilename"};
		Arguments arguments = Arguments.parse(args);
		Assert.assertEquals(args[0], arguments.getInputFilename());
		Assert.assertEquals(args[1], arguments.getOutputFilename());
	}

	@Test
	public void testInputFilenameOnly() throws CmdLineException {
		String[] args = new String[] {"testInputFilename"};
		Arguments arguments = Arguments.parse(args);
		Assert.assertEquals(args[0], arguments.getInputFilename());
		Assert.assertEquals(null, arguments.getOutputFilename());
	}

	@Test(expected=RuntimeException.class)
	public void testNoArguments() throws CmdLineException {
		String[] args = new String[] {};
		Arguments.parse(args);
	}
}
