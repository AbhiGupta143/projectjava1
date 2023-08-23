package views;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.UserService;
import service.sendOTPService;
import java.util.Scanner;
public class Welcome {
   public void welcomeScreen() {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Wecome to Application");
	    System.out.println("press 1 to login");
	    System.out.println("Press 2 to signup");
	    System.out.println("Press 0 to exit");
	    int choice = 0;
	    try {
	    	choice = Integer.parseInt(br.readLine());
	    }
	    catch (IOException ex) {
	    	ex.printStackTrace();
	    }
	    switch(choice) {
	    case 1 : login();
	    case 2 : signUp();
	    case 0 : System.exit(0);
	    }
   }

   

	private void login() {
	// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		try {
			if(UserDAO.isExists(email)) {
				String genOTP = GenerateOTP.getOTP();
				sendOTPService.sendOTP(email, genOTP);
				System.out.println("Enter the OTP");
				String OTP =sc.nextLine();
				if(OTP.equals(genOTP)) { 
					new UserView(email).home();
					
				}
				else {
					System.out.println("Wrong Otp");
				}
			}else {
  				System.out.println("user not found");
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			
		}
	
    }
	
	 private void signUp() {
			// TODO Auto-generated method stub
			   Scanner sc = new Scanner(System.in);
			   System.out.println("Enter Name: ");
			   String name = sc.nextLine();
			   System.out.println("Enter the Email: ");
			   String email = sc.nextLine();
			   String genOTP = GenerateOTP.getOTP();
				sendOTPService.sendOTP(email, genOTP);
				System.out.println("Enter the OTP");
				String OTP = sc.nextLine();
				
				if(OTP.equals(genOTP)) {
					User user = new User(name,email);
					int response = UserService.saveUser(user);
					switch(response) {
					case 0 : System.out.println("User Registered");
					case 1 : System.out.println("User already exists");
					}
					
				}
				else {
					System.out.println("Wrong Otp");
				}
		       }
}
