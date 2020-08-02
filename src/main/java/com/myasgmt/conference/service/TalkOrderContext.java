/**
 * 
 */
package com.myasgmt.conference.service;

import java.util.List;

import com.myasgmt.conference.dto.Talk;

/**
 * @author Veerendranath
 *
 */
public class TalkOrderContext {
	private final TalkOrder talkOrder;
	
	public TalkOrderContext(TalkOrder talkOrder) {
		this.talkOrder = talkOrder;
	}

	public void orderTalks(List<Talk> talkList){
		talkOrder.order(talkList);
	}
}

