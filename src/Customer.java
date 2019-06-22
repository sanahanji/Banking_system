import java.sql.DriverManager;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class Customer {

	Scanner sc4=new Scanner(System.in);
	int opt3;
	
	
	public Customer() {
		String wt="Wt";
		String dp="Dt";int flag=0;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection conn1=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			Statement st4=(Statement) conn1.createStatement();
			

			
		do {
		
		
		System.out.println("ENter operation to be performed\n1.Deposit.\n2.Withdraw.\n3.Print Mini Statements.\n4.Transfer");
		opt3=sc4.nextInt();
		System.out.println("ENter account number");
		int accno8=sc4.nextInt();
		switch(opt3) {
		
		case 1:
			System.out.println("Enter the amount to be deposited");
			int amt=sc4.nextInt();
			
		    ResultSet ad=(ResultSet) st4.executeQuery("select balance,LFT from custlog where accno='"+accno8+"';" );
		    
			if(ad.next()) {
				 String l=ad.getString("LFT");
				String k=l.concat(dp);
				int newbal=ad.getInt("balance");
				newbal=newbal+amt;	
				st4.executeUpdate("update custlog set balance='"+newbal+"',deposit='"+amt+"',LFT='"+k+"'where accno='"+accno8+"';  ");
				System.out.println("Balance updated");
				
				
			}
	     break;
		case 2:
			System.out.println("Enter the amount to be withdrawn");
			int amt1=sc4.nextInt();
		    ResultSet rs1=(ResultSet) st4.executeQuery("select balance,LFT from custlog where accno='"+accno8+"';" );
			if(rs1.next()) {
				String l1=rs1.getString("LFT");
				String k1=l1.concat(wt);
				
				int newbal=rs1.getInt("balance");
				if(amt1>newbal) {
					System.out.println("LOw balance");
				}
				else {
				newbal=newbal-amt1;
				st4.executeUpdate("update custlog set balance='"+newbal+"',withdraw='"+amt1+"',LFT='"+k1+"'where accno='"+accno8+"';  ");
				System.out.println("Balance updated");
				}
				
			}
			
			break;
		case 3:
			String ms=""; 
			ResultSet lm=(ResultSet) st4.executeQuery("select LFT from custlog where accno='"+accno8+"';");
			if(lm.next()) {
				String input=lm.getString("LFT");
				if (input.length() > 10)
				{
				    ms = input.substring(input.length() - 10);
				}
				else
				{
				    ms = input;
				}
				 
				System.out.println("Mini statement:"+ms);
				
			}
			
			
			
			
			 break;
		case 4:
			System.out.println("Enter the amount to be transferred");
			int amt4=sc4.nextInt();
		    ResultSet rs2=(ResultSet) st4.executeQuery("select balance,LFT from custlog where accno='"+accno8+"';" );
			if(rs2.next()) {
				
				String l3=rs2.getString("LFT");
				String k2=l3.concat(wt);
				int newbal=rs2.getInt("balance");
				if(amt4>newbal) {
					System.out.println("Low balance");
				}
				else
				{
				newbal=newbal-amt4;
				st4.executeUpdate("update custlog set balance='"+newbal+"',withdraw='"+amt4+"',LFT='"+k2+"'where accno='"+accno8+"';  ");
				System.out.println("Amount transferred");
				}
			}
				System.out.println("ENter the account number of payee");
				int accno7=sc4.nextInt();
				ResultSet rs3=(ResultSet) st4.executeQuery("select balance,LFT from custlog where accno='"+accno7+"';" );
			if(rs3.next()) {
				String l4=rs3.getString("LFT");
				String k3=l4.concat(dp);
				int newbal1=rs3.getInt("balance");
				newbal1=newbal1+amt4;
				
				 st4.executeUpdate("update custlog set balance='"+newbal1+"',LFT='"+k3+"'where accno='"+accno7+"';");
				System.out.println("Balance updated");
				
			}
			
			break;
		default:
			System.exit(0);
		}
		
			
		
		
		
		
		}while(true);
		
		}
		catch(Exception e) {
			
		}
		
	}
	
	
}
