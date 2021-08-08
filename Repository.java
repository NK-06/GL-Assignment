package com.assignment;
import java.sql.*;
import java.util.*;
public class Repository {
		
	ResultSet rs;
	PreparedStatement ps;
	Statement st;
	
	String query ;
	public static Connection getCon() 
	{
		Connection con = null;
		try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","xm");
			  //System.out.println("Connected...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	    return con;
	}
      
	public static boolean saveEmp(Employee e, Department d) 
	{		
		int i = 0,j= 0;
		try {
				Connection con = getCon();	    
				PreparedStatement pse = con.prepareStatement("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)");
				
				pse.setInt(1, e.getEmp_id());
				pse.setString(2, e.getFirst_name());
				pse.setString(3, e.getLast_name());
			    pse.setInt(4, d.getDept_id());
				pse.setInt(5, e.getSalary());
				pse.setString(6, e.getSkills());
				 i = pse.executeUpdate();
				 
				pse.close();
				
				PreparedStatement psd = con.prepareStatement("INSERT INTO department VALUES(?,?)");
				psd.setString(2, d.getDept_name());
				psd.setInt(1, d.getDept_id());
				j = psd.executeUpdate();
				
				psd.close();
				con.close();
				
		}catch(Exception e1) {
			e1.printStackTrace();
		}
				
		if(i > 0 && j > 0)
			 return true;
				else
					return false;
	}
	
	public static boolean check() throws Exception
	{		
	    
		Connection con = getCon();
		Statement st = con.createStatement();
		
		String q = "SELECT * FROM employee";
		
		ResultSet rs = st.executeQuery(q);
				
		if(rs.next())
		{
			st.close();
			con.close();
		 return true;
		}
		else 
		{
			st.close();
		   con.close();
		 return false;
		}
	
	}
	
	public static List<Employee> showList() throws Exception           // Returns Complete List of Employees
 	{
		List<Employee>el = new ArrayList<Employee>();
		Connection con = getCon();
		Statement st = con.createStatement();
		String q = "Select * FROM employee";
		ResultSet rs = st.executeQuery(q);
		
		while(rs.next())
		{
		  Employee e = new Employee();
		  e.setEmp_id(rs.getInt(1));
		  e.setFirst_name(rs.getString(2));
		  e.setLast_name(rs.getString(3));
		  e.setDept_id(rs.getInt(4));
		  e.setSalary(rs.getInt(5));
		  e.setSkills(rs.getString(6));
		  
		  el.add(e);
		}
		con.close();
		return el;
	}
	
	public static List<Employee> showSalaryList() throws Exception          // Returns List of Employees having Skills >= 3
	{
		List<Employee>el = new ArrayList<Employee>();
		Connection con = getCon();
		Statement st = con.createStatement();
		String q = "Select * FROM employee ORDER BY last_name";
		ResultSet rs = st.executeQuery(q);
		
		while(rs.next())
		{
			String s = rs.getString(6);
			String []str = s.split(",");
		  if(str.length > 2) {
		  Employee e = new Employee();
		  e.setEmp_id(rs.getInt(1));
		  e.setFirst_name(rs.getString(2));
		  e.setLast_name(rs.getString(3));
		  e.setDept_id(rs.getInt(4));
		  e.setSalary(rs.getInt(5));
		  e.setSkills(rs.getString(6));
		  
		  el.add(e);
		  }
		}
		con.close();
		return el;
	}
	
	public static List<String> showCombinedList() throws Exception          // Returns List of Employees having salalry >= 5000 & Dept_Name like Deshops & Engg
	{
		HashMap<Employee, Department>ed = new HashMap<Employee, Department>();
		Connection con = getCon();
		Statement st = con.createStatement();
		String q = "SELECT e.Last_name, d.dept_id FROM employee e JOIN department d "
				+ "ON e.dept_id = d.dept_id Where e.salary >= 5000  AND d.dept_Name LIKE 'Devshops' OR d.dept_Name LIKE 'Engg'";
		ResultSet rs = st.executeQuery(q);
		
		while(rs.next())
		{		
		  Employee e1 = new Employee();
		  Department d1 = new Department();
		  		  
		  e1.setLast_name(rs.getString(1));		  
		  d1.setDept_id(rs.getInt(2));
		  
		  ed.put(e1, d1);
		}
		con.close();
		List<String> str = new ArrayList<>();
		
		for(Map.Entry<Employee, Department> mp : ed.entrySet())
			{
			   str.add(mp.getKey().getLast_name() + "-" + mp.getValue().getDept_id());
			}
		return str;
	}
}
