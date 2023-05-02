package com.myweb.controller;

import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myweb.model.Student;

@Controller
public class StudentContoller 
{
	Student sForm=null;
	
	// Setting the SessionFactory and configuring the configuration.xml file
	SessionFactory sf=new Configuration().configure("student.cfg.xml").buildSessionFactory();
	Session s=sf.openSession();
	
	
	@RequestMapping("student")
	public ModelAndView getStudentHomePage()
	{
		ModelAndView mv=new ModelAndView();
		
		// We need to redirect to register-student.jsp page
		mv.setViewName("student.jsp");
		
		return mv;
	}
	
	
	@RequestMapping("login")
	public ModelAndView adminLogin(String tbUser,String tbPass)		
	{
		ModelAndView mv=new ModelAndView();
		
		// Validating user by username and password inputs
		List res=s.createQuery("from Admin a where a.username='"+tbUser+"' and a.password='"+tbPass+"'").list();
		
		if(!res.isEmpty())
		{
			// Redirect to student.jsp page
			mv.setViewName("student.jsp");
		}
		else 
		{
			// Redirect to admin-login.jsp page
			mv.setViewName("admin-login.jsp");
		}
		
		return mv;
	}
	
	@RequestMapping(value="student",params="reg")
	public ModelAndView getRegisterPage()
	{
		ModelAndView mv=new ModelAndView();
		
		// We need to redirect to register-student.jsp page
		mv.setViewName("register-student.jsp");
		
		return mv;
	}
	
	
	@RequestMapping("insert")
	public ModelAndView insertStudentServlet(String tbName,String tbEmail,String tbPass,long tbMob)
	{
		ModelAndView mv=new ModelAndView();
		Transaction t=s.beginTransaction();
		
		// Passing method parameters and fetching the data enter by user 
		Student s1=new Student(tbName, tbEmail, tbPass, tbMob);
		
		// Save inserted values in table:- 
		s.save(s1);
		t.commit();
		mv=displayStudentList();
		
		return mv;
	}
	
	
	@RequestMapping(value="student",params="show")
	public ModelAndView displayStudentList()
	{
		ModelAndView mv=new ModelAndView();
		
		// Read the all table data from DB
		Query q=s.createQuery("from Student");
		List<Student> alStud=q.list();
		
		// We need to redirect to display.jsp with List Data:
		mv.addObject("student", alStud);
		mv.setViewName("display-student-list.jsp");
		
		return mv;
	}
	
	
	@RequestMapping("editForm")
	public ModelAndView editStudentdata(@RequestParam("id") int id)
	{
		ModelAndView mv=new ModelAndView();
		
		// Fetch the row which we need to edit in the table:-
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
		Transaction t=s.beginTransaction();
		
		// Update the data in DB
		sForm.setName(tbName);
		sForm.setEmail(tbEmail);
		sForm.setPassword(tbPass);
		sForm.setMobile(tbMob);
		
		// Save the updated Student object in DB
		s.update(sForm);
		t.commit();
		
		mv=displayStudentList();
		
		return mv;
	}
	
	
	@RequestMapping("delete")
	public ModelAndView deleteStudent(@RequestParam("id") int id)		
	{
		ModelAndView mv=new ModelAndView();
		Transaction t=s.beginTransaction();
		
		// Fetch the row which we need to eliminate from table:-
		Student s2=s.get(Student.class, id);
		
		// Delete one row from database based on id
		s.delete(s2);
		t.commit();
		
		mv=displayStudentList();
		
		return mv;
	}
	
	
	@RequestMapping("logout")
	public ModelAndView exit()
	{
		ModelAndView mv=new ModelAndView();
		// Closing the session and SessionFactory
		s.close();
		sf.close();
		mv.setViewName("admin-login.jsp");
		return mv;
	}
}