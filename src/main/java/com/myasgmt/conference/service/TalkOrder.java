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
public interface TalkOrder {
	public void order(List<Talk> talkList);
}

