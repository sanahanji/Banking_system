import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class Admin {
	Scanner sc1=new Scanner(System.in);
	int accno;
	int balance,withdraw,deposit,transfer;
	
	String name;
	int cid;
	String pass;
	
	
	
	public Admin() {
		
		int opt2;
		balance=withdraw=deposit=transfer=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con1;
		try {
			con1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			//System.out.println("Connected");
			Statement st1=(Statement) con1.createStatement();
			//System.out.println("Connectedvfvbdfv");
			//st1.execute("alter table custlog add (accno int,name varchar(20),withdraw int,deposit int,transfer int,balance int) ;");
			//System.out.println("Table altered");
			
			
			do {
				System.out.println("ENter the operation to be performed:\n1.Create new customer.\n2.Search customer\n3.Modify customer.\n4.Balance enquiry.\n5.Close customer account.");
				opt2=sc1.nextInt();
				switch(opt2) {
				case 1:
					create();
					PreparedStatement ps=(PreparedStatement) con1.prepareStatement("insert into custlog (cid,password,accno,name,withdraw,deposit,transfer,balance,LFT) values(?,?,?,?,0,0,0,0,'');");
					ps.setInt(1, cid);
					ps.setString(2, pass);
					ps.setInt(3, accno);
					ps.setString(4,name);
					ps.executeUpdate();
					System.out.println("Account Created");
					
					
					
					break;
				case 2:
					System.out.println("ENter the account number");
					int accno=sc1.nextInt();
					
					ResultSet rs=(ResultSet) st1.executeQuery("select * from custlog where accno='"+accno+"';");
					if(rs.next())
					{
						System.out.println(rs.getInt(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(8));
					}
					else 
						System.out.println("Not found");
					
					break;
				case 3:
					System.out.println("ENter the account number");
					int accno2=sc1.nextInt();
					
					ResultSet rs1=(ResultSet) st1.executeQuery("select * from custlog where accno='"+accno2+"';");
					if(rs1.next())
					{
						System.out.println("Enter the new name to be modified ");
						String nam=sc1.next();
						st1.executeUpdate("update custlog set name='"+nam+"'where accno='"+accno2+"';  ");
						System.out.println("Modified");
						
						
					}
					else 
						System.out.println("Not found");
					
					
					
					
					break;
				case 4:
					System.out.println("ENter the account number");
					int accno3=sc1.nextInt();
					
					ResultSet rs3=(ResultSet) st1.executeQuery("select * from custlog where accno='"+accno3+"';");
					if(rs3.next())
					{
						
						
						System.out.println("Balance is:"+rs3.getInt(8));
						
						
					}
					else 
						System.out.println("Not found");
				
					
					break;
				case 5:
					System.out.println("ENter the account number");
					int accno4=sc1.nextInt();
					
					st1.execute("delete from custlog where accno='"+accno4+"'");
					System.out.println("Row deleted");
					
					break;
				default:
				 System.exit(0);
					
				}
			}
			while(true);
			
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
public void create() {
	
	
	System.out.println("Enter account number,name,id,password");
	accno=sc1.nextInt();
	name=sc1.next();
	cid=sc1.nextInt();
	pass=sc1.next();
	
}

	
	
	
	
	
	
	
	
}
