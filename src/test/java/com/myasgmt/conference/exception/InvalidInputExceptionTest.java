package com.myasgmt.conference.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidInputExceptionTest extends Exception {

	@Test
	public void getMessageTest(){
		InvalidInputException invalidInputException = new InvalidInputException("Exception Occured");
		assertEquals("Exception Occured", invalidInputException.getMessage());
	}

}
