package com.myasgmt.conference.service;

import com.myasgmt.conference.dto.Talk;
import com.myasgmt.conference.dto.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create sessions, each session is organized into number of talks.
 * 
 * @author Veerendranath
 *
 */
@Service
public class SessionOrganizerService {

	public static final int MORNING_SESSION_LENGTH = 180;
	public static final int AFTERNOON_SESSION_LENGTH = 240;

	public SessionOrganizerService() {}

	/**
	 * method used to look for all the possibilities to schedule talks for a session.
	 * @param talksList
	 * @param morningSession
	 * @return possibleCombinationsOfTalks
	 */
	public Session createSession(List<Talk> talksList, boolean morningSession) {
		if(null == talksList || talksList.size() == 0){
			return null;
		}
		List<Talk> talksInASession = null;
		// as minimum duration of morning and afternoon session is same
		int minSessionDuration = MORNING_SESSION_LENGTH;
		// assign max duration of morning session to MORNING_SESSION_LENGTH and afternoon session is AFTERNOON_SESSION_LENGTH
		int maxSessionDuration = (morningSession) ? MORNING_SESSION_LENGTH : AFTERNOON_SESSION_LENGTH;
		boolean isValidCombination = false;
		for(int i = 0; i < talksList.size(); i++) {
			isValidCombination = false;
			int startIndex = i;
			int totalTime = 0;
			talksInASession = new ArrayList<Talk>();
			// check time duration of each talk in the list and add it to the list of possible talks 
			while(startIndex != talksList.size()) {
				Talk currentTalk = talksList.get(startIndex++);
				// if the talk is already scheduled then continue with next talk in the list
				if(currentTalk.isScheduled())
					continue;
				// check if current talk duration or, current duration and totalTime is crossing the max duration.
				// if yes, skip the talk and continue with next talk
				if(currentTalk.getDuration() > maxSessionDuration || currentTalk.getDuration() + totalTime > maxSessionDuration) {
					continue;
				}
				totalTime += currentTalk.getDuration();
				talksInASession.add(currentTalk);
				// check if the list reaches its maximum duration in that session with the current talk being added.
				// If yes, then come out of the loop 
				if(morningSession) {
					if(totalTime == maxSessionDuration)
						break;
				} else if(totalTime >= minSessionDuration) { //aftenoon
					// though afternoon session is >= mintotalduration, still checking for the possibility of scheduling another  
					// talk which would better fit in the session 
					if(totalTime == maxSessionDuration) {
						break;
					}
					continue;
				}
			}
			// check if the combination is valid or not, based on the total time duration get out of the possible talks list 
			isValidCombination = (morningSession) ? (totalTime == maxSessionDuration || (talksInASession.size() == talksList.size() && totalTime < maxSessionDuration))
					: ((totalTime >= minSessionDuration && totalTime <= maxSessionDuration) || (talksInASession.size() == talksList.size() && totalTime < maxSessionDuration));
			// if the possible talks list is valid then mark all the assigned talks as scheduled
			if(isValidCombination) {
				this.markScheduled(talksInASession);
				break;
			}
			// continue loop and execute inner loop by increment startIndex to next index
		}
		// remove already scheduled talks from the input talk list

		return new Session(talksInASession);
	}

	private void markScheduled(List<Talk> talksInASession){
		for(Talk talk : talksInASession){
			talk.setScheduled(true);
		}
	}

}
