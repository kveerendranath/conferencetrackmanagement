/**
 * 
 */
package com.myasgmt.conference.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import com.myasgmt.conference.dto.Talk;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myasgmt.conference.exception.InvalidInputException;

/**
 * @author Veerendranath
 *
 */
public class ParserTest {

	public static List<Talk> talkList = new ArrayList<Talk>();

	@Before
	public void setUp(){
		talkList = Parser.talkList;
	}

	@Test
	public void testParseLine() throws InvalidInputException {

		// test scenario to cover talk pattern 1
		Integer actual = Parser.parseLine("Writing Fast Tests Against Enterprise Rails 60min");
		assertEquals(Integer.valueOf(60), actual);
		// test scenario to cover talk pattern 2
		actual = Parser.parseLine("Rails for Python Developers lightning");
		assertEquals(Integer.valueOf(5), actual);
	}

	@Test(expected = InvalidInputException.class)
	public void testParseLineThrowsInvalidInputException() throws  InvalidInputException {
		// test scenario to handle invalid talk pattern
		Parser.parseLine("Writing Fast Tests Against Enterprise Rails 60min writing");

	}

	@Test
	public void testParseFile() throws InvalidInputException, IOException {
		Parser.parseFile("TalksData.txt");
		Integer talkSize = Parser.talkList.size();
		assertEquals(Integer.valueOf(27), talkSize);
	}

	@Test(expected = IOException.class)
	public void testParseFileInvalidFileName() throws InvalidInputException, IOException {
		Parser.parseFile("TalksDataFile.txt");
		Integer talkSize = Parser.talkList.size();
		assertEquals(Integer.valueOf(27), talkSize);
	}

	@After
	public void cleanUp(){
		talkList = null;
	}

}

