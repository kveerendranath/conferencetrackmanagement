/**
 * 
 */
package com.myasgmt.conference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;


import com.myasgmt.conference.dto.Talk;
import com.myasgmt.conference.dto.Track;
import com.myasgmt.conference.service.TrackSchedulerService;
import com.myasgmt.conference.service.DescTimeDurationOfTalk;
import com.myasgmt.conference.service.TalkOrderContext;
import com.myasgmt.conference.util.Parser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Veerendranath
 *
 */
public class ConferenceTrackManagementApplicationTest {

	public List<Talk> talkList = new ArrayList<Talk>();

	@Before
	public void setUpData() {
		String fileName = "TalksData.txt";
		try {
			Parser.parseFile(fileName);
			talkList.addAll(Parser.talkList);
		} catch(Exception _exp) {
			fail(_exp.getMessage());
		}
	}

	@Test
	public void applicationEmptyTalkTest() {

		List<Talk> talkList = new ArrayList<Talk>();
		TrackSchedulerService conferenceScheduler = new TrackSchedulerService();
		TalkOrderContext talkOrderContext = new TalkOrderContext(new DescTimeDurationOfTalk());
		talkOrderContext.orderTalks(new ArrayList<Talk>());
		try {
			List<Track> tracks = conferenceScheduler.createTracks(talkList);
			assertEquals(0, tracks.size());
		} catch(Exception _exp) {
			fail(_exp.getMessage());
		}
	}

	@Test
	public void applicationTest() {
		
		TrackSchedulerService conferenceScheduler = new TrackSchedulerService();
		TalkOrderContext talkOrderContext = new TalkOrderContext(new DescTimeDurationOfTalk());
		talkOrderContext.orderTalks(talkList);
		try {
			List<Track> tracks = conferenceScheduler.createTracks(talkList);
			tracks.forEach(System.out::println);
			// test case to check all talks are scheduled
			assertEquals(0, talkList.size());
			// to confirm scheduled tracks are scheduled properly
			assertEquals(3, tracks.size());
		} catch(Exception _exp) {
			fail(_exp.getMessage());
		}
	}

	@After
	public void cleanUp() {
		talkList = null;
	}
	
}

