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
public class StartTimeAppenderService {

	public static final int LUNCH_HOUR_LENGTH = 60;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat ("hh:mma");

	public StartTimeAppenderService() {}
	/**
	 * method used to append start time of each talk
	 * and also add lunch and networking talks.
	 * @param track
	 */
	public void appendStartTime(Track track) {
		Calendar cal = this.getStartTimeOfConference();
		String scheduledTime = dateFormat.format(cal.getTime());
		for(Talk talk : track.getTalkList()){
			talk.setScheduledTime(scheduledTime);
			scheduledTime = getNextScheduledTime(cal, talk.getDuration());
		}
	}

	/**
	 * method used to get the time of next talk which is in the schedule. 
	 * @param cal
	 * @param timeDuration
	 * @return String
	 */
	private String getNextScheduledTime(Calendar cal, int timeDuration) {
		Date date = cal.getTime();
		date.setTime(date.getTime() + (timeDuration * 60 * 1000));
		cal.setTime(date);
		return dateFormat.format(date);
	}
	
	/**
	 * method used to get the calendar object by setting start time of the meeting.
	 * @return cal
	 */
	private Calendar getStartTimeOfConference() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR, 9);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.AM_PM, 0);
		return cal;
	}


}
