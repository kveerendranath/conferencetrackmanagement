Application: Conference Track Management	

Problem Statement: 
You are planning a big programming conference and have received many proposals which have passed the initial screen process but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities!

•	The conference has multiple tracks each of which has a morning and afternoon session.
•	Each session contains multiple talks.
•	Morning sessions begin at 9am and must finish by 12 noon, for lunch.
•	Afternoon sessions begin at 1pm and must finish in time for the networking event.
•	The networking event can start no earlier than 4:00 and no later than 5:00.
•	No talk title has numbers in it.
•	All talk lengths are either in minutes (not hours) or lightning (5 minutes).
•	Presenters will be very punctual; there needs to be no gap between sessions.


Softwares required:
	jdk 1.8
	maven
	Eclipse or IntelliJ (optional)

Project folder: conferencetrackmanagement
It Consists of the following artifacts:

	1. src - source code of the project
	2. pom.xml - maven configuration file
	3. TalksData.txt - text file with input data provided in the problem statement

Running Application through Command promt:
1. Inorder to run the application, execute the following maven command in the project folder (conferencetrackmanagement) in command editor.
	>mvn clean package
   This will create executable jar file named, "conference-track-management-0.0.1-SNAPSHOT.jar" in target folder.
2. To run the application, execute the below command from the project folder.
	>java -jar ./target/conference-track-management-0.0.1-SNAPSHOT.jar
   It will ask for the input file name.
   Enter file name as "TalksData.txt" and press enter
In the command editor, we will able to see the list Tracks on the screen with list of talks scheduled.

Running Application through IDE:
1. Import this project into an IDE (eclipse/IntelliJ) as a maven project.
2. After importing, download all dependencies required by right click on pom.xml
3. Go to src/main/java/com/myasgmt/conference/ folder and open "ConferenceTrackManagementApplication.java" file and run the file as Java Application.
4. Open Console and provide file name, "TalksData.txt" as input and press enter.
5. Output will be displayed in the console.

Details on project files:
1. ConferenceTrackManagementApplication.java: Takes the input file and execute the process.
    This object is responsible for delegating control to all the composed objects for execution to get the required output.

2. Parser.java: Responsibility of this class is to parse the given file and create list of talks out of it.

3. TalkOrderContext.java: As I have used Strategy pattern to order the given list of talks, this is the Context object where in we can inject right strategy into it and 
			  get the list of talks ordered based on the provided strategy.
   AscTimeDurationOfTalk.java: This is a strategy class, in which talks are ordered in ascending manner based on the time duration of the talk.
   DescTimeDurationOfTalk.java: This is a strategy class, in which talks are ordered in descending manner based on the time duration of the talk.
   InputOrderOfTalk.java: This is a strategy class, in which talks are ordered in the way they are given in the input.

4. ConferenceSchedulerService.java: This class is responsible for scheduling talks given in the input. 

