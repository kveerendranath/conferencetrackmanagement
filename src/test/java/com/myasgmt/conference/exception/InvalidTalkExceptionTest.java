package com.myasgmt.conference.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidTalkExceptionTest extends Exception{

	@Test
	public void getMessageTest(){
		InvalidTalkException invalidTalkException = new InvalidTalkException("Exception Occured");
		assertEquals("Exception Occured", invalidTalkException.getMessage());
	}

}
