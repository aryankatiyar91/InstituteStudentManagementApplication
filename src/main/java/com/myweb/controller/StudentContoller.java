package com.myweb.controller;

import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myweb.model.*;

@Controller
public class StudentContoller 
{
	// Creating Student object for edit & update operation:
	Student sForm=null;
	
	// Setting the SessionFactory and configuring the configuration.xml file:
	SessionFactory sf=new Configuration().configure("student.cfg.xml").buildSessionFactory();
	// Starting the session:
	Session s=sf.openSession();
	// Creating static String to display username
	public static String user=null;
	
	
	@RequestMapping("login")
	public ModelAndView adminLogin(String tbUser,String tbPass)		
	{
		ModelAndView mv=new ModelAndView();
		
		// Validating user by username and password inputs:
		List res=s.createQuery("from Admin a where a.username='"+tbUser+"' and a.password='"+tbPass+"'").list();
		
		if(!res.isEmpty())
		{
			user=tbUser.substring(0, 1).toUpperCase()+tbUser.substring(1);
			
			// Calling studentHomePage method:
			mv=studentHomePage();
		}
		else 
		{
			// We need to send "logFail" string to admin-login.jsp page:
			mv.addObject("admin","logFail");
			// Redirect to admin-login.jsp page:
			mv.setViewName("admin-login.jsp");
		}
		
		return mv;
	}
	
	@RequestMapping("student")
	public ModelAndView studentHomePage()
	{
		ModelAndView mv=new ModelAndView();
		// We need to send user data to student.jsp page:
		mv.addObject("admin",user);
		
		// Redirect to student.jsp page:
		mv.setViewName("student.jsp");
		
		return mv;
	}
	
	
	@RequestMapping("register")
	public ModelAndView adminRegistration(String tbUser,String tbPass,String tbRePass)		
	{
		ModelAndView mv=new ModelAndView();
		
		if(tbPass!=null)
		{
			// Checking-> is same admin is already present or not:
			List<Admin> res=s.createQuery("from Admin a where a.username='"+tbUser+"'").list();
			
			if(tbPass.equals(tbRePass) && res.isEmpty())
			{
				// Begin the transaction:
				Transaction t=s.beginTransaction();
				
				// Passing method parameters and fetching the data enter by user:
				Admin a=new Admin(tbUser, tbPass);
				
				// Save the inserted values in table:- 
				s.save(a);
				t.commit();
				
				// Redirect to admin-login.jsp page:
				mv.setViewName("admin-login.jsp");
				
				// We need to send "aReg" string to admin-login.jsp:
				mv.addObject("admin","aReg");
			}
			else 
			{
				// We need to send "regFail" string to register-new-admin.jsp page:
				mv.addObject("admin","regFail");
				// Redirect to register-new-admin.jsp page:
				mv.setViewName("register-new-admin.jsp");
			}
		}
		else 
		{
			// Redirect to register-new-admin.jsp page
			mv.setViewName("register-new-admin.jsp");
		}
		
		return mv;
	}
	
	
	
	@RequestMapping(value="register",params="del")
	public ModelAndView deleteAdmin(String tbUser,String tbPass,String tbRePass)		
	{
		ModelAndView mv=new ModelAndView();
		
		// Checking-> is same admin already present or not:-
		List<Admin> res=s.createQuery("from Admin a where a.username='"+tbUser+"' and a.password='"+tbPass+"'").list();
		
		
		if(tbPass.equals(tbRePass) && !res.isEmpty())
		{
			// Begin the transaction:
			Transaction t=s.beginTransaction();
			
			for(Admin a:res)
			{
				// Fetch the row which we need to eliminate from table:-
				Admin ad=s.get(Admin.class, a.getId());
				
				// Delete one row from database based on id
				s.delete(ad);
				t.commit();
			}
			
			// Redirect to admin-login.jsp page
			mv.setViewName("admin-login.jsp");
			
			// We need to send "aDelete" string to admin-login.jsp page:
			mv.addObject("admin","aDelete");
		}
		else 
		{
			// We need to send "delFail" string to register-new-admin.jsp page:
			mv.addObject("admin","delFail");
			// Redirect to register-new-admin.jsp page:
			mv.setViewName("register-new-admin.jsp");
		}
		
		return mv;
	}
	
	
	@RequestMapping(value="student",params="reg")
	public ModelAndView getRegisterPage()
	{
		ModelAndView mv=new ModelAndView();
		
		// We need to redirect to register-student.jsp page:
		mv.setViewName("register-student.jsp");
		
		return mv;
	}
	
	
	@RequestMapping("insert")
	public ModelAndView insertStudentServlet(String tbName,String tbEmail,String tbPass,long tbMob)
	{
		ModelAndView mv=new ModelAndView();
		// Checking-> is same Student already present or not:
		List<Student> res=s.createQuery("from Student s where s.email='"+tbEmail+"'").list();
		
		if(res.isEmpty())
		{
			// Begin the transaction:
			Transaction t=s.beginTransaction();
			
			// Passing method parameters and fetching the data enter by user: 
			Student s1=new Student(tbName, tbEmail, tbPass, tbMob);
			
			// Save inserted values in table:- 
			s.save(s1);
			t.commit();
			
			// Call displayStudentList method:
			mv=displayStudentList();
			
			// We need to send "sAdded" string to display-student-list.jsp page:
			mv.addObject("student","sAdded");
		}
		else
		{
			// Calling getRegisterPage method:
			mv=getRegisterPage();
			// We need to send "fail" string to register-student.jsp:
			mv.addObject("student","fail");
		}
		
		return mv;
	}
	
	
	@RequestMapping(value="student",params="show")
	public ModelAndView displayStudentList()
	{
		ModelAndView mv=new ModelAndView();
		
		// Reading the all table data from DB:
		Query q=s.createQuery("from Student");
		List<Student> alStud=q.list();
		
		// We need to redirect to display.jsp with List Data:
		mv.addObject("allStudent", alStud);
		mv.setViewName("display-student-list.jsp");
		
		return mv;
	}
	
	
	@RequestMapping("editForm")
	public ModelAndView editStudentdata(@RequestParam("id") int id)
	{
		ModelAndView mv=new ModelAndView();
		
		// Fetch the row which we need to edit:-
		sForm=s.get(Student.class, id);
		
		// We need to redirect to display.jsp page with List Data:
		mv.addObject("oneStud", sForm);
		mv.setViewName("register-student.jsp");
		return mv;
	}
	
	
	@RequestMapping("update")
	public ModelAndView updateStudentData(String tbName,String tbEmail,String tbPass,long tbMob)		
	{
		ModelAndView mv=new ModelAndView();
		
		// Verifying is enter email and already present email are same or not:
		if(tbEmail.equals(sForm.getEmail()))
		{
			// Begin the transaction:
			Transaction t=s.beginTransaction();
			
			// Updating the data in the DB without Email:
			sForm.setName(tbName);
			sForm.setPassword(tbPass);
			sForm.setMobile(tbMob);
			
			// Save the updated Student data in the DB with present Email:
			s.update(sForm);
			t.commit();
			
			// Call displayStudentList method:
			mv=displayStudentList();
			
			// We need to send "sUpdated" string to display-student-list.jsp page:
			mv.addObject("student","sUpdated");
		}
		else
		{
			// Checking is entered email already present or not in the DB: 
			List<Student> res=s.createQuery("from Student s where s.email='"+tbEmail+"'").list();
			// Checking is res empty or not:
			if(res.isEmpty())
			{
				// Begin the transaction:
				Transaction t=s.beginTransaction();
				
				// Updating the data in the DB with new Email:
				sForm.setName(tbName);
				sForm.setEmail(tbEmail);
				sForm.setPassword(tbPass);
				sForm.setMobile(tbMob);
				
				// Save the updated Student data in the DB with updated Email:
				s.update(sForm);
				t.commit();
				
				// Call displayStudentList method:
				mv=displayStudentList();
				
				// We need to send "sUpdated" string to display-student-list.jsp page:
				mv.addObject("student","sUpdated");
			}
			else
			{
				// Calling editStudentdata method with id no:
				mv=editStudentdata(sForm.getSno());
				// We need to send "fail" string to register-student.jsp page:
				mv.addObject("student","fail");
			}
		}
		
		return mv;
	}
	
	
	@RequestMapping("delete")
	public ModelAndView deleteStudent(@RequestParam("id") int id)		
	{
		ModelAndView mv=new ModelAndView();
		// Begin the transaction:
		Transaction t=s.beginTransaction();
		
		// Fetch the row which we need to eliminate from table:-
		Student s2=s.get(Student.class, id);
		
		// Delete one row from database based on id:
		s.delete(s2);
		t.commit();
		
		// Call displayStudentList method:
		mv=displayStudentList();
		
		// We need to send "sDeleted" string to display-student-list.jsp page:
		mv.addObject("student","sDeleted");
		
		return mv;
	}
	
	
	@RequestMapping("logout")
	public ModelAndView exit()
	{
		ModelAndView mv=new ModelAndView();
		// Closing the session and SessionFactory:
		s.close();
		sf.close();
		mv.setViewName("admin-login.jsp");
		return mv;
	}
}