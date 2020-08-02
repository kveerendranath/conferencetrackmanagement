package com.myasgmt.conference.dto;

import java.io.Serializable;
import java.util.List;

public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Talk> talkList;

	/**
	 * @param talkList
	 */
	public Session(List<Talk> talkList) {
		super();
		this.talkList = talkList;
	}

	public List<Talk> getTalkList() {
		return talkList;
	}
}
