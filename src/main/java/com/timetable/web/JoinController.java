package com.timetable.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
@RequestMapping( "/join" )
public class JoinController
{
	@Autowired
	StudentDao studentDao;
	
	@RequestMapping( value = "" , method = RequestMethod.GET )
	public String joinForm( HttpSession session , Model model )
	{
		Student student = new Student();
		model.addAttribute( "joinStudent" , student );
		
		List< Integer > gradeList = new ArrayList< Integer >( Arrays.asList( 1 , 2, 3 , 4 ) );
		model.addAttribute( "gradeList" , gradeList );
		
		List< String > majorList = new ArrayList< String >(
				Arrays.asList(
					"신문방송학과" , "다이나믹미디어학과" , "동화미디어콘텐츠학과" , "커뮤니케이션문화학부" , "영미어문학과 " ,
					"패션디자인학부" , "산업디자인학부" , "시각영상디자인학부" , "실내디자인학과" , "회화학과" ,
					"영어학과" , "경영경제학부" , "국제통상·문화학부" ,
					"경찰학과" , "행정복지학부" , "문헌정보학과" , "유아교육과" , "자율전공학부" ,
					"녹색기술융합학과" , "나노전자기계공학과" , "컴퓨터공학과" , "스포츠과학부" ,
					"의생명화학과" , "생명공학과" , "식품생명과학부" , "의학공학부" , "간호학과"
				)
		);
		model.addAttribute( "majorList" , majorList );
			
		return "/member/join";
	}
	
	@RequestMapping( value = "" , method = RequestMethod.POST )
	public String joinProcess( @Valid @ModelAttribute( "joinStudent" ) Student student , Errors errors , HttpSession session , Model model )
	{
		Student existStudent = studentDao.getOne( student.getSid() );
		
		if( existStudent != null )
			return "/member/join";
		else
		{			
			Student newStudent = new Student( student.getSid() , student.getHash( student.getPassword() ) , student.getName(), student.getEmail() , student.getGrade() , student.getMajor() );			
			studentDao.insert( newStudent );			
		}		
		
		return "redirect:/login";
	}

}
