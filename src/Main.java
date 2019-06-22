import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class Main {
		
	public static void main(String[] args) {	
		
		Scanner sc=new Scanner(System.in);
		int opt1;
		int id,cuid;
		String Password,Pass;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			Statement st=(Statement) con.createStatement();
			//st.execute("create table adminlog (aid int,pass varchar(10));");
			//System.out.println("Table created");
			//st.execute("create table custlog (cid int,password varchar(10));");
			//System.out.println("Customer table created");
			//st.execute("alter table custlog add LFT varchar(20) ;");
			//st.executeUpdate("update custlog set LFT='"+"Nt"+"';");
			ResultSet rs;
			
			System.out.println("Login as:1.Admin\n 2.Customer.");
			opt1=sc.nextInt();
			switch(opt1) {
			case 1:
					System.out.println("Enter the id and password");
					id=sc.nextInt();
					Password=sc.next();
					  rs=(ResultSet) st.executeQuery("select aid from adminlog where aid='"+id+"' and pass='"+Password+"'");
					
					int count = 0;
			        while(rs.next()){
			            count = count+1;
			        }
			        if (count==1){
			            System.out.println("User, Found Access Granted!");
			            
			            Admin a=new Admin();
			            
			            
			            
			        }
			        else if (count>1){
			            System.out.println("Duplicate User");
			        } 
			            else {
			            System.out.println("User does not exist");
			             }

			         	
					
	
					
					
				break;
			case 2:
				System.out.println("Enter the id and password");
				cuid=sc.nextInt();
				Pass=sc.next();
				  rs=(ResultSet) st.executeQuery("select cid from custlog where cid='"+cuid+"' and password='"+Pass+"'");
				
				int count1 = 0;
		        while(rs.next()){
		            count1 = count1+1;
		        }
		        if (count1==1){
		            System.out.println("User, Found Access Granted!");
		            Customer c=new Customer();
		        }
		        else if (count1>1){
		            System.out.println("Duplicate User");
		        } 
		            else {
		            System.out.println("User does not exist");
		             }
			
			
			
			}
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

	}

}
