package org.less.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lesscss.LessException;
import org.lesscss.cli.Main;

public class MainTest {
	@BeforeClass
	static public void setUp() throws FileNotFoundException, LessException {
		Main.skipAnnoyingEnvjsOutput();
	}

	final String input = "@color: #4D926F; #header { color: @color; }";
	final String expectedOutput = "#header {\n  color: #4d926f;\n}\n";

	@Test
	public void testCompilationToFile() throws IOException {
		File inputFile = File.createTempFile("lesscss-cli-", ".less");
		FileUtils.writeStringToFile(inputFile, input);
		File outputFile = File.createTempFile("lesscss-cli-", ".css");
		try {
			Main.compile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
			String output = FileUtils.readFileToString(outputFile);
			Assert.assertEquals(expectedOutput, output);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		} finally {
			inputFile.delete();
			outputFile.delete();
		}
	}

	@Test
	public void testCompilationToStandardOutput() throws IOException {
		PrintStream originalSystemOut = new PrintStream(System.out);
		File inputFile = File.createTempFile("lesscss-cli-", ".less");
		FileUtils.writeStringToFile(inputFile, input);
		File outputFile = File.createTempFile("lesscss-cli-", ".stdout");
		try {
			System.setOut(new PrintStream(outputFile));
			Main.compile(inputFile.getAbsolutePath(), null);
			String output = FileUtils.readFileToString(outputFile);
			Assert.assertEquals(expectedOutput, output);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		} finally {
			System.setOut(originalSystemOut);
			inputFile.delete();
			outputFile.delete();
		}
	}
}
