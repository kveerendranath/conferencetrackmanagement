package com.myasgmt.conference.service;

import com.myasgmt.conference.dto.Session;
import com.myasgmt.conference.dto.Talk;
import com.myasgmt.conference.dto.Track;
import org.springframework.stereotype.Service;


import java.util.*;

/**
 * This class is responsible for creating and organizing tracks, each track consists of talks.
 * 
 * @author Veerendranath
 *
 */
@Service
public class TrackSchedulerService {

	private SessionOrganizerService sessionOrganizerService;
	private TrackOrganizerService trackOrganizerService;
	private StartTimeAppenderService startTimeAppenderService;

	public TrackSchedulerService() {
		sessionOrganizerService = new SessionOrganizerService();
		trackOrganizerService = new TrackOrganizerService();
		startTimeAppenderService = new StartTimeAppenderService();
	}

	/**
	 * method used to create tracks tracks and return all the tracks to the invoker.
	 * Each track represents a day and consists of list talks scheduled in a day with start time
	 * @param talksList
	 * @return trackList
	 * @throws Exception
	 */
	public List<Track> createTracks(List<Talk> talksList) throws Exception {
		if(talksList.size() == 0) {
			System.out.println("Empty talks..!!");
			return new ArrayList<Track>();
		}
		List<Track> trackList = new ArrayList<Track>();
		List<Talk> talksInMorningSession = null;
		List<Talk> talksInAfternoonSession = null;
		Session morningSession = null;
		Session afternoonSession = null;

		int trackCount = 0;
		Track track = null;
		// loop will continue its iteration till all the talks in the list are scheduled
		while(talksList.size() > 0) {
			// create morning session talks
			morningSession = sessionOrganizerService.createSession(talksList, true);
			removeScheduledTalks(talksList);
			// create afternoon session talks
			afternoonSession = sessionOrganizerService.createSession(talksList, false);
			removeScheduledTalks(talksList);
			track = trackOrganizerService.prepareTracks(morningSession, afternoonSession);
			if(null != track) {
				startTimeAppenderService.appendStartTime(track);
				track.setName("Track " + ++trackCount);
				trackList.add(track);
			}
		}
		return trackList;
	}
	
	/**
	 * method used to remove talks that are already scheduled and assigned to a track.
	 * @param talksList
	 */
	private static void removeScheduledTalks(List<Talk> talksList) {
		talksList.removeIf(t -> t.isScheduled());
	}

}
