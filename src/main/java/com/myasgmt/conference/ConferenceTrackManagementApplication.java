package com.myasgmt.conference;

import com.myasgmt.conference.dto.Track;
import com.myasgmt.conference.service.ConferenceTrackManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Veerendranath
 */
@SpringBootApplication
public class ConferenceTrackManagementApplication {

	@Autowired
	public static ConferenceTrackManager conferenceTrackManager;

	public static void main(String[] args) {

		SpringApplication.run(ConferenceTrackManagementApplication.class, args);
		Scanner scanner = null;
		try{
			scanner = new Scanner( System.in );
			System.out.println("Welcome to Conference Track Management..Please provide input file name and Press enter..");
			String fileName = scanner.nextLine();
			System.out.println(conferenceTrackManager);
			List<Track> tracks = conferenceTrackManager.scheduleTracks(fileName);
			System.out.println("\nTest output:");
			if(null != tracks){
				tracks.forEach(System.out::println);
			}
		} catch(Exception _exp){
			System.out.println(_exp.getMessage());
		} finally {
			if(null != scanner){
				scanner.close();
			}
		}


	}

	/**
	 * method which runs when the jar is executed.
	 * @param args
	 */
	/*public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		System.out.println("Welcome to Conference Track Management..Please provide input file name and Press enter..");
		String fileName = scanner.nextLine();
		try{
			List<Track> tracks = conferenceTrackManager.scheduleTracks(fileName);
			System.out.println("\nTest output:");
			if(null != tracks){
				tracks.forEach(System.out::println);
			}
		} catch(Exception _exp){
			System.out.println(_exp.getMessage());
		} finally {
			if(null != scanner){
				scanner.close();
			}
		}
	}*/

}
