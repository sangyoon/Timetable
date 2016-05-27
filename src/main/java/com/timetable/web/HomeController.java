package com.timetable.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timetable.dao.StudentDao;
import com.timetable.vo.Student;

@Controller
public class HomeController
{
	private static final Logger logger = LoggerFactory.getLogger( HomeController.class );
	
	@Autowired
	StudentDao studentDao;

	@RequestMapping( value = "/" , method = RequestMethod.GET )
	public String home( Model model )
	{
		logger.info( "메인페이지에 접속했습니다." );
		
		List< Student > students = studentDao.getAll();		
		model.addAttribute( "students" , students );
		
		return "home";
	}	
}
