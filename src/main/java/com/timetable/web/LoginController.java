package com.timetable.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timetable.dao.StudentDao;
import com.timetable.vo.Student;

@Controller
@RequestMapping( "/login" )
public class LoginController
{
	@Autowired
	StudentDao studentDao;
	
	@RequestMapping( value = "" , method = RequestMethod.GET )
	public String loginForm( HttpSession session , Model model )
	{
		if( session.getAttribute( "student" ) == null )
		{
			Student student = new Student();
			model.addAttribute( "loginStudent" , student );
			
			return "/member/login";
		}
		else
			return "redirect:/mypage";
	}
	
	@RequestMapping( value = "" , method = RequestMethod.POST )
	public String loginProcess( @Valid @ModelAttribute( "loginStudent" ) Student student , Errors errors , HttpSession session , Model model )
	{		
		if( errors.hasErrors() )			
			return "/member/login";
		else
		{		
			Student loginStudent = studentDao.getOne( student.getSid() );
			
			if( loginStudent == null )
				return "/member/login";
			else
			{
				String chkPasswd = student.getHash( student.getPassword() );
				boolean check = chkPasswd.equalsIgnoreCase( loginStudent.getPassword() );
				
				if( check == true )
				{
		            session.setAttribute( "student" , loginStudent );
		            
					return "redirect:/login";
				}
				else
					return "/member/login";
			}
		}
	}
	
	@RequestMapping( value = "/mypage" , method = RequestMethod.GET )
	public String myPage( Model model )
	{		
		return "redirect:/mypage";
	}
	
	@RequestMapping( value = "/logout" , method = RequestMethod.GET )
	public String logoutPage( HttpSession session )
	{		
		session.setAttribute( "student" , null );
		session.invalidate();
		
        return "redirect:/login";
	}
	
	
}