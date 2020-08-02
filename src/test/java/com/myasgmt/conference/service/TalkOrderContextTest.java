/**
 * 
 */
package com.myasgmt.conference.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myasgmt.conference.dto.Talk;
import com.myasgmt.conference.util.Parser;

/**
 * @author Veerendranath
 *
 */
public class TalkOrderContextTest {
	public List<Talk> talkList = new ArrayList<Talk>();

	@Before
	public void setUpData() {
		talkList.addAll(Arrays.asList(new Talk("Talk 1", 50), new Talk("Talk 2", 40), new Talk("Talk 3", 60)));
	}
	
	@Test
	public void descTimeDurationOfTalkTest() {
		TalkOrderContext talkOrderContext = new TalkOrderContext(new DescTimeDurationOfTalk());
		talkOrderContext.orderTalks(talkList);
		assertTrue("Ordered in Descending", (talkList.get(0).getDuration() == 60 && 
				talkList.get(1).getDuration() == 50 && 
				talkList.get(2).getDuration() == 40));
	}
	
	@Test
	public void createdOrderOfTalkTest() {
		TalkOrderContext talkOrderContext = new TalkOrderContext(new InputOrderOfTalk());
		talkOrderContext.orderTalks(talkList);
		assertTrue("Ordered as per Input", (talkList.get(0).getDuration() == 50 && 
				talkList.get(1).getDuration() == 40 && 
				talkList.get(2).getDuration() == 60));
	}
	
	@After
	public void cleanUp() {
		talkList = null;
	}

}

