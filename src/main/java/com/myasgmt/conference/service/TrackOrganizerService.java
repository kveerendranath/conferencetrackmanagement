package com.myasgmt.conference.service;

import com.myasgmt.conference.dto.Session;
import com.myasgmt.conference.dto.Talk;
import com.myasgmt.conference.dto.Track;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class is used to schedule given talks.
 * 
 * @author Veerendranath
 *
 */
@Service
public class TrackOrganizerService {

	public static final int LUNCH_HOUR_LENGTH = 60;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat ("hh:mma");

	public TrackOrganizerService() {}
	/**
	 * method used to create list of talks in a particular track by combining both morning and afternoon session
	 * and also add lunch and networking talks.
	 * @param morningSession
	 * @param afternoonSession
	 * @return trackTalkList
	 */
	public Track prepareTracks(Session morningSession, Session afternoonSession) {
		List<Talk> trackTalkList = null;

		if(null != morningSession && null != morningSession.getTalkList() && morningSession.getTalkList().size() > 0) {
			trackTalkList = new ArrayList<Talk>();
			trackTalkList.addAll(morningSession.getTalkList());
			int totalSessionTime = morningSession.getTalkList().stream().map(talk -> talk.getDuration()).reduce(0, Integer::sum);
			if(totalSessionTime == 180){
				trackTalkList.add(new Talk("Lunch", LUNCH_HOUR_LENGTH));

				if(null != afternoonSession && null != afternoonSession.getTalkList() && afternoonSession.getTalkList().size() > 0) {
					trackTalkList.addAll(afternoonSession.getTalkList());
					totalSessionTime = afternoonSession.getTalkList().stream().map(talk -> talk.getDuration()).reduce(0, Integer::sum);
					if(totalSessionTime >= 180){
						trackTalkList.add(new Talk("Networking Event", LUNCH_HOUR_LENGTH));
					}
				}
			}
			return new Track(trackTalkList);
		}
		return null;
	}


}
