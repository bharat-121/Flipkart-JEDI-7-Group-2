package com.flipkart.application;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.business.*;
import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;

public class StudentCRSMenu {
	Scanner sc = new Scanner(System.in);
	RegistartionInterface registrationInterface = RegistrationOperation.getInstance();
	ProfessorInterface professorInterface = ProfessorOperations.getInstance();
	StudentInterface studentInterface = StudentOperations.getInstance();
	NotificationInterface notificationInterface=NotificationOperation.getInstance();
	private boolean is_registered=true;
	
	/**
	 * Method to generate Student Menu for course registration, addition, removal and fee payment 
	 * @param studentId student id 
	 */
	public void createMenu(String studentId)
	{

		is_registered = getRegistrationStatus(studentId);
		while (CRSApplication.loggedin) 
		{
			System.out.println("*****************************");
			System.out.println("**********Student Menu*********");
			System.out.println("*****************************");
			System.out.println("1. Course Registration");
			System.out.println("2. Add Course");
			System.out.println("3. Drop Course");
			System.out.println("4. View Course");
			System.out.println("5. View Registered Courses");
			System.out.println("6. View grade card");
			System.out.println("7. Make Payment");
			System.out.println("8. Logout");
			System.out.println("*****************************");

			int choice = sc.nextInt();

			switch (choice) {
				case 1: 
						registerCourses(studentId);
						break;
						
				case 2: 
						addCourse(studentId);
						break;

				case 3:
						
						dropCourse(studentId);
						break;

				case 4:
						viewCourse(studentId);
						break;

				case 5:
						viewRegisteredCourse(studentId);
						break;

				case 6:
						viewGradeCard(studentId);
						break;
						
				case 7:
						make_payment(studentId);
						break;
						
				case 8: 
						CRSApplication.loggedin = false;
						return;
						
				default:
					System.out.println("***** Wrong Choice *****");
			}
		}
	}
	

	/**
	 * Select course for registration
	 * @param studentId
	 */
	private void registerCourses(String studentId)
	{
			if(is_registered)
			{
				System.out.println(" Registration is already completed");
				return;
			}
			
			int count = 0;
			while(count < 6)
			{
					List<Course> courseList=viewCourse(studentId);
					
					if(courseList==null)
						return;
					
					System.out.println("Enter Course Code : " + (count+1));
					String courseCode = sc.next();
					
					if(registrationInterface.addCourse(courseCode,studentId,courseList))
					{
						System.out.println("Course " + courseCode + " registered sucessfully.");
						count++;
					}
					else
					{
						System.out.println(" You have already registered for Course : " + courseCode);
					}
				
			}
			
			System.out.println("Registration Successful");	
		    is_registered = true;
		    
			registrationInterface.setRegistrationStatus(studentId);
		
		
	}
	
	/**
	 * Add course for registration
	 * @param studentId
	 */
	private void addCourse(String  studentId)
	{
		if(is_registered)
		{
			List<Course> availableCourseList=viewCourse(studentId);
			
			if(availableCourseList==null)
				return;
	
			System.out.println("Enter Course Code : " );
			String courseCode = sc.next();
			if(registrationInterface.addCourse(courseCode, studentId,availableCourseList))
			{
				System.out.println(" You have successfully registered for Course : " + courseCode);
			}
			else
			{
				System.out.println(" You have already registered for Course : " + courseCode);
			}
			
		}
		else 
		{
			System.out.println("Please complete registration");
		}

		
	}
	
	/**
	 * Method to check if student is already registered
	 * @param studentId
	 * @return Registration Status
	 */
	private boolean getRegistrationStatus(String studentId)
	{
			return registrationInterface.getRegistrationStatus(studentId);
	
	}
	
	/**
	 * Drop Course
	 * @param studentId
	 */
	private void dropCourse(String  studentId)
	{
		if(is_registered)
		{
			List<Course> registeredCourseList=viewRegisteredCourse(studentId);
			
			if(registeredCourseList==null)
				return;
			
			System.out.println("Enter the Course Code : ");
			String courseCode = sc.next();
			
			registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
			System.out.println("You have successfully dropped Course : " + courseCode);
				

		}
		else
		{
			System.out.println("Please complete registration");
		}

	}
	
	/**
	 * View all available Courses 
	 * @param studentId
	 * @return List of available Courses 
	 */
	private List<Course> viewCourse(String  studentId)
	{
		List<Course> course_available=null;
		course_available = registrationInterface.viewCourses(studentId);

	
		if(course_available.isEmpty())
		{
			System.out.println("NO COURSE AVAILABLE");
			return null;
		}
		

		System.out.println(String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
		for(Course obj : course_available)
		{
			System.out.println(String.format("%-20s %-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId(), obj.getSeats()));
		}
		
		return course_available;

	}
	
	/**
	 * View Registered Courses
	 * @param studentId
	 * @return List of Registered Courses
	 */
	private List<Course> viewRegisteredCourse(String  studentId)
	{
		List<Course> course_registered=null;
		course_registered = registrationInterface.viewRegisteredCourses(studentId);

		if(course_registered.isEmpty())
		{
			System.out.println("You haven't registered for any course");
			return null;
		}
		
		System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
		
		for(Course obj : course_registered)
		{
			System.out.println(String.format("%-20s %-20s %-20s ",obj.getCourseCode(), obj.getCourseName(),professorInterface.getProfessorById(obj.getInstructorId())));
		}
		
		return course_registered;
	}
	
	/**
	 * View grade card for particular student  
	 * @param studentId
	 */
	private void viewGradeCard(String  studentId)
	{
		
		
		List<GradeCard> grade_card=null;
		grade_card = studentInterface.viewGradeCard(studentId);

		
		System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "GRADE"));
		
		if(grade_card.isEmpty())
		{
			System.out.println("You haven't registered for any course");
			return;
		}
		
		for(GradeCard obj : grade_card)
		{
			System.out.println(String.format("%-20s %-20s %-20s",obj.getStudentId(), obj.getSemester(),obj.getCgpa()));
		}
	}
	
	/**
	 * Make Payment for selected courses. Student is provided with an option to pay the fees and select the mode of payment.
	 * @param studentId
	 */
	private void make_payment(String  studentId)
	{
		
		double fee =0.0;
		
			fee=registrationInterface.calculateFee(studentId);
		


		if(fee == 0.0)
		{
			System.out.println("You have not  registered for any courses yet");
		}
		else
		{
			
			System.out.println("Your total fee  = " + fee);
			System.out.println("Want to continue Fee Payment(y/n)");
			String ch = sc.next();
			if(ch.equals("y"))
			{
				System.out.println("Select Mode of Payment:");
				
				int index = 1;
				for(ModeOfPayment mode : ModeOfPayment.values())
				{
					System.out.println(index + " " + mode);
					index = index + 1;
				}
				
				ModeOfPayment mode = ModeOfPayment.getModeofPayment(sc.nextInt());
				
				if(mode == null)
					System.out.println("Invalid Input");
				else
				{
						notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, mode, fee);

				}
					
			}
			
		}
		
	}
}
