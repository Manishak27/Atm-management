
package atm1111;

import java.util.*;
import java.sql.Statement;
import java.lang.String;
import java.sql.*;

public class ATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			//connection
			 Class.forName("com.mysql.cj.jdbc.Driver");
		     // establish the connection
		     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Manisha");
		     Statement st = con.createStatement();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter your user id number");
			int uid = sc.nextInt();
			ResultSet rst= st.executeQuery("SELECT* FROM accounts WHERE id=" +uid);
			int c=0;
			while(rst.next()) {
				c++;
			} 
			if(c>0) {
			
			
			System.out.println("Enter your user pin number");
			int pin_num = sc.nextInt();
			ResultSet rs = st.executeQuery("SELECT* FROM accounts WHERE pin=" +pin_num);
			int card_number;
			int i=0;
			int balance=0;
			
			  // String user;
			    //user=sc.next();
			
				while(rs.next()) {
				//user=rs.getString(2);
				card_number=rs.getInt(3);
				balance=rs.getInt(5);
				i++;
			}
			
			 int op;
			 int wid=0;
			 int dep=0;
			
			 if(i>0) {
					
					//System.out.println("WELCOME!!! " +user );
				//  ResultSet rs1 = st.executeQuery("SELECT* FROM accounts WHERE name=" +user);
				 System.out.println("WELCOME!!!" );
					
				while(true) {
					 System.out.println("\nPlease select the operation:");
					 System.out.println("1->WIDRAW AMOUNT \n2->DEPOSIT AMOUNT \n3->CHECK AVAILABLE BALANCE \n4->EXIT");
					 op= sc.nextInt();
					 
					 switch(op) {
					 
					 case 1:
					 	System.out.println("Please enter the 'Amount' to be widrawn..");
					 	wid=sc.nextInt();
					 	if(balance>=wid) 
					 	{
					 		balance=balance-wid;
					 		int bal = st.executeUpdate("UPDATE accounts SET balance= "+balance+" WHERE pin= "+pin_num);
					 		System.out.println("Updated available balance is Rs. "+balance);
					 	}
					 	else
					 		System.out.println("Sorry..Request cannot be processed due to 'Insufficient funds");
					 		System.out.println("Available balace is Rs." +balance);
					 		break;
					 
					 case 2:
						System.out.println("Enter the deposit amount..");
						dep=sc.nextInt();
						balance=balance+dep;
						int add = st.executeUpdate("UPDATE accounts SET balance= "+balance+" WHERE pin= "+pin_num);
						System.out.println("Updated available balance is Rs. "+balance);
						break;
						
					 case 3:
						 System.out.println("Available balance is Rs. "+balance);
						 break;
						 
					 case 4:
						 System.out.println("THANK YOU FOR VISITING...");
						 break;
					  }
					 
				}
			}
				else
			    System.out.println("Please enter correct pin"); 
			
			} else
					System.out.println("Enter valid user id");
				 
			 
		}
		catch(Exception e) {
			System.out.println(e);
		}	

	}

}
