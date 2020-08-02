package com.myasgmt.conference.service;

import com.myasgmt.conference.dto.Talk;
import com.myasgmt.conference.dto.Track;
import com.myasgmt.conference.exception.InvalidInputException;
import com.myasgmt.conference.exception.InvalidTalkException;
import com.myasgmt.conference.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for scheduling tracks.
 * 
 * @author Veerendranath
 *
 */
@Component
public class ConferenceTrackManager {

	@Autowired
	TrackSchedulerService trackSchedulerService;


	/**
	 * method used to schedule tracks based on the talks written in the input file.
	 * @param fileName
	 * @return
	 */
	public List<Track> scheduleTracks(String fileName){
		try{
			// Step 1: invoking parser class for file parsing
			Parser.parseFile(fileName);
			System.out.println("\nTest input:");
			Parser.talkList.forEach(System.out::println);
			List<Talk> talkList = new ArrayList<Talk>();
			talkList.addAll(Parser.talkList);

			// Step 2: injecting strategy on order of talks, it will decide the order of output
			TalkOrderContext talkOrderContext = new TalkOrderContext(new DescTimeDurationOfTalk());
			talkOrderContext.orderTalks(talkList);

			// Step 3: create tracks
			return trackSchedulerService.createTracks(talkList);

		} catch(InvalidInputException _invInpExp){
			System.out.println(_invInpExp.getMessage());
		} catch(InvalidTalkException _invTalkExp){
			System.out.println(_invTalkExp.getMessage());
		} catch(Exception _exp){
			System.out.println(_exp.getMessage());
		}
		return null;

	}

}
