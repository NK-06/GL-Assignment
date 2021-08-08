package com.assignment;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Service
 */
@WebServlet("/Service")
public class Service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Service() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Employee e = new Employee();
		Department d = new Department();
		
		int emp_id, dept_id, salary;
		
			if(request.getParameter("button1") != null)              // To Print Complete List 
		   	{						  
					 out.println("<form  action='Service' method = 'post'>");
					    out.print("<input style = 'margin: 10px' type = 'submit' name = 'b1'  value = 'Add New Employee'>");
					    out.print("<input style = 'margin: 10px' type = 'submit' name = 'button2'  value = 'List of Employees having Skills >= 3'>");
					    out.print("<input style = 'margin: 10px' type = 'submit' name = 'button3'  value = 'List of Employees who are in Devshops & Engg Department'>");					     
					 out.println( "</form>");
					
					try {	
		             
						  if(Repository.check())
						   {							  
							List<Employee> el = Repository.showList();	
							out.print("<h3>Complete Employee List : </h3>");
							out.print("<table border= '1' width = '100%'>");
							out.print("<tr><th>Emp_Id</th><th>First_Name</th><th>Last_Name</th><th>Dept_Id</th><th>Salary</th><th>Skills</th></tr>");
							
							for(Employee e1 : el)
							{
								out.print("<tr><td>"+e1.getEmp_id()+"</td><td>"+e1.getFirst_name()+"</td><td>"+e1.getLast_name()+"</td><td>"+e1.getDept_id()+"</td><td>"+e1.getSalary()+"</td><td>"+e1.getSkills()+ "</td></tr>");
							}
							out.print("</table>");
							out.close();
							
						 }
		   				else
							{
		   					   out.println("<p>Table is Empty, Please Add Employee...</p>");
		   					//request.getRequestDispatcher("Controller.jsp").include(request, response);
							}
				     } catch(Exception e1) {e1.printStackTrace();}					
		}
		 else if(request.getParameter("button2")!= null)                   // To Print List having Skills >= 3
		 {
			   out.println("<form  action='Service' method = 'post'>");
			     out.print("<input style = 'margin: 10px' type = 'submit' name = 'b1'  value = 'Add New Employee'>");
			     out.print("<input style = 'margin: 10px' type = 'submit' name = 'button1'  value = 'Complete Employee List'>");
			     out.print("<input style = 'margin: 10px' type = 'submit' name = 'button3'  value = 'List of Employees who are in Devshop & Engg Department'>");
			     
			   out.print( "</form>");		
				try {	             
						  if(Repository.check())
						   {				
													
							List<Employee> el = Repository.showSalaryList();	
							
							out.print("<h3>List of Employees having Skills >= 3 : </h3>");
							out.print("<table border= '1' width = '100%'>");
							out.print("<tr><th>Emp_Id</th><th>First_Name</th><th>Last_Name</th><th>Dept_Id/th><th>Salary</th><th>Skills</th></tr>");
							
							for(Employee e1 : el)
							{
								out.print("<tr><td>"+e1.getEmp_id()+"</td><td>"+e1.getFirst_name()+"</td><td>"+e1.getLast_name()+"</td><td>"+e1.getDept_id()+"</td><td>"+e1.getSalary()+"</td><td>"+e1.getSkills()+ "</td></tr>");
							}
							out.print("</table>");
							out.close();							
						}
						else
							{
							   out.println("<p>No such Entry exist in the Table, Please Add Employee...</p>");
							 //  request.getRequestDispatcher("Controller.jsp").include(request, response);
							}
		     	  } catch(Exception e1) {e1.printStackTrace();}		
		}
		else if(request.getParameter("button3")!= null)                        // To Print List having Dept_Name Devops & Engg with slalry >= 5000 
		{
				out.println("<form  action='Service' method = 'post'>");
				  out.print("<input style = 'margin: 10px' type = 'submit' name = 'b1'  value = 'Add New Employee'>");
				    out.print("<input style = 'margin: 10px' type = 'submit' name = 'button1'  value = 'Complete Employee List'>");
				    out.print("<input style = 'margin: 10px' type = 'submit' name = 'button2'  value = 'List of Employees having Skills >= 3'>");
				 out.print( "</form>");		
			try {
					  if(Repository.check())
					   {
						List<String> str = Repository.showCombinedList();	
						
						out.print("<table border= '1' width = '100%'>");
						out.print("<tr><th>Emp_Last_Nmae - Dept_Id</th></tr>");
						
						for(String s : str)
						{
							out.print("<tr><td>"+s+"</td></tr>");
						}
						out.print("</table>");
						out.close();							
					}
					else
						{
						   out.println("<p>No such Entry exist in the Table, Please Add Employee...</p>");
						 //  request.getRequestDispatcher("Controller.jsp").include(request, response);
						}
	     	  } catch(Exception e1) {e1.printStackTrace();}
		}
		else if(request.getParameter("b1")!= null) 
			request.getRequestDispatcher("AddEmployee.html").include(request, response);
			
		emp_id = Integer.parseInt(request.getParameter("emp_id"));
		dept_id = Integer.parseInt(request.getParameter("dept_id"));
    	salary = Integer.parseInt(request.getParameter("salary"));
		
		e.setEmp_id(emp_id);
		 e.setFirst_name(request.getParameter("first_name"));
		e.setLast_name(request.getParameter("last_name"));
		e.setDept_id(dept_id);
		e.setSkills(request.getParameter("skills"));
		e.setSalary(salary);		
		
		d.setDept_id(dept_id);
		d.setDept_name(request.getParameter("dept_name"));
		
		try {
			boolean status = Repository.saveEmp(e, d) ;
			
			if(status)
			{
				out.print("<p>Added Succcessfully</p>");				  
			}
			else
				out.print("<p>Sorry! Unable to Add, Please try again</p>");
			
			request.getRequestDispatcher("Controller.jsp").include(request, response);
				
		} catch (Exception e1) 
		{		
			e1.printStackTrace();
		}
		
		out.close();
	}

}
