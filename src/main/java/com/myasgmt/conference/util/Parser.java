package com.myasgmt.conference.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.myasgmt.conference.dto.Talk;
import com.myasgmt.conference.exception.InvalidInputException;

/**
 * Class used to parse the given file and create talks from it.
 * @author Veerendranath
 *
 */
public class Parser {

	private static final String TALK_PATTERN = "^(.+)\\s(\\d+)?\\s?((min)|(lightning))$";
	private static final String LIGHTNING = "lightning";
	public static List<Talk> talkList = null;

	/**
	 * method used to parse given file
	 * @param fileName
	 * @throws IOException
	 * @throws InvalidInputException
	 */
	public static void 	parseFile(String fileName) throws InvalidInputException, IOException {
		talkList = new ArrayList<Talk>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				talkList.add(new Talk(line, parseLine(line)));
			}
		} catch (IOException _ioExp) {
			throw _ioExp;
		}
	}

	/**
	 * method to parse given input line and return time duration of the talk
	 * @param line
	 * @return int
	 * @throws InvalidInputException
	 */
	static int parseLine(String line) throws InvalidInputException {
		Pattern p = Pattern.compile(TALK_PATTERN);
		Matcher m = p.matcher(line);
		if(m.find()) {
			if(m.group(3).equals(LIGHTNING))
				return 5;
			else
				return Integer.parseInt(m.group(2));
		} else {
			throw new InvalidInputException("Invalid pattern in talk: " + line);
		}
	}

}
