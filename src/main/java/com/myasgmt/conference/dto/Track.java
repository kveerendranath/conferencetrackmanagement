package com.myasgmt.conference.dto;

import java.io.Serializable;
import java.util.List;

public class Track implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private List<Talk> talkList;

	/**
	 * @param name
	 * @param talkList
	 */
	public Track(String name, List<Talk> talkList) {
		super();
		this.name = name;
		this.talkList = talkList;
	}

	public Track(List<Talk> talkList) {
		this.talkList = talkList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Talk> getTalkList() {
		return talkList;
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder(name).append("\n");
		if(null != talkList && talkList.size() > 0) {
			for(Talk talk : talkList) {
				stb.append(talk.getScheduledTime()).append(" ").append(talk.getName()).append("\n");
			}
		}
		return stb.toString();
	}
}
