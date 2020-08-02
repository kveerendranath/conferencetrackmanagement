/**
 * 
 */
package com.myasgmt.conference.service;

import java.util.Comparator;
import java.util.List;

import com.myasgmt.conference.dto.Talk;

/**
 * @author Veerendranath
 *
 */
public class DescTimeDurationOfTalk implements TalkOrder {

	/* (non-Javadoc)
	 * @see com.myasgmt.conference.service.TalkOrder#order(java.util.List)
	 */
	@Override
	public void order(List<Talk> talkList) {
		talkList.sort(Comparator.comparingInt(Talk::getDuration).reversed());
	}

}