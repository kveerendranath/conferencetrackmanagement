package com.myasgmt.conference.dto;

import java.io.Serializable;

public class Talk implements Serializable {
	private static final long serialVersionUID = 1L;

	String name;
	int duration;
	boolean scheduled = false;
	String scheduledTime;

	public Talk(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}

	public Talk(String name, int duration, String scheduledTime) {
		super();
		this.name = name;
		this.duration = duration;
		this.scheduledTime = scheduledTime;
	}

	/**
	 * To set the flag scheduled.
	 * @param scheduled
	 */
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	/**
	 * To get flag scheduled.
	 * If talk scheduled then returns true, else false.
	 * @return
	 */
	public boolean isScheduled() {
		return scheduled;
	}

	/**
	 * Set scheduled time for the talk. in format - hr:min AM/PM.
	 * @param scheduledTime
	 */
	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	/**
	 * To get scheduled time.
	 * @return
	 */
	public String getScheduledTime() {
		return scheduledTime;
	}

	/**
	 * To get time duration  for the talk.
	 * @return
	 */
	public int getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
