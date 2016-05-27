package com.timetable.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timetable.dao.TimetableDao;
import com.timetable.vo.Timetable;

@Controller
@RequestMapping( "/mypage" )
public class TimetableController
{
	@Autowired
	TimetableDao timetableDao;
	
	private String[] weekName = { "월" , "화" , "수" , "목" , "금" };
	private String[] timeSlot = {
			"08:00-08:30" , "08:30-09:00" , "09:00-09:30" , "09:30-10:00" ,
			"10:00-10:30" , "10:30-11:00" ,	"11:00-11:30" , "11:30-12:00" ,
			"12:00-12:30" , "12:30-13:00" ,	"13:00-13:30" , "13:30-14:00" ,
			"14:00-14:30" , "14:30-15:00" ,	"15:00-15:30" , "15:30-16:00" ,
			"16:00-16:30" , "16:30-17:30" ,	"17:30-18:00" , "18:00-18:30"
	};
	
	@RequestMapping( value = "" , method = RequestMethod.GET )
	public String classList( Model model )
	{	
		List< Timetable > timetableList = timetableDao.getAll();
		
		List< Object > timetable = new ArrayList< Object >();
		for( Timetable obj : timetableList )
		{			
			timetable.add( Arrays.asList(
					obj.getIdx() ,
					obj.getSubject() ,
					weekName[ obj.getWeek() - 1 ] ,
					timeSlot[ Integer.parseInt( obj.getTime().split( "-" )[ 0 ] ) - 1 ].split( "-" )[ 0 ] + " - " + timeSlot[ Integer.parseInt( obj.getTime().split( "-" )[ 1 ] ) - 1 ].split( "-" )[ 1 ] ,
					obj.getPlace() ,
					obj.getProfessor()
				) );
		}
		
		model.addAttribute( "timetables" , timetable );
		
		return "/mypage/list";
	}
	
	@RequestMapping( value = "" , method = RequestMethod.POST )
	public String classSearch( HttpServletRequest request , Model model )
	{
		String subject = request.getParameter( "subject" );
				
		List< Timetable > timetableList = timetableDao.getBySubject( subject );
		
		List< Object > timetable = new ArrayList< Object >();
		for( Timetable obj : timetableList )
		{			
			timetable.add( Arrays.asList(
					obj.getIdx() ,
					obj.getSubject() ,
					weekName[ obj.getWeek() - 1 ] ,
					timeSlot[ Integer.parseInt( obj.getTime().split( "-" )[ 0 ] ) - 1 ].split( "-" )[ 0 ] + " - " + timeSlot[ Integer.parseInt( obj.getTime().split( "-" )[ 1 ] ) - 1 ].split( "-" )[ 1 ] ,
					obj.getPlace() ,
					obj.getProfessor()
				) );
		}
		
		model.addAttribute( "timetables" , timetable );
		model.addAttribute( "keyword" , subject );
		
		return "/mypage/list";
	}
	
	
	
	@RequestMapping( value = "/{place}" , method = RequestMethod.GET )
	public String detailPage( @PathVariable String place , Model model )
	{		
		Object[][] timetable = new Object[ 20 ][ 6 ];
		List< Timetable > timetableList = timetableDao.getByPlace( place );
		
		for( int i = 0 ; i < 20 ; ++i )
			timetable[ i ][ 0 ] = ( i + 1 ) + "교시<br />" + timeSlot[ i ];
		
		for( int i = 0 ; i < 20 ; ++i )
			for( int j = 1 ; j < 6 ; ++j )
				timetable[ i ][ j ] = "<td></td>";
		
		for( Timetable item : timetableList )
		{
			int week = item.getWeek();
			int startTime = Integer.parseInt( item.getTime().split( "-" )[ 0 ] );
			int endTime = Integer.parseInt( item.getTime().split( "-" )[ 1 ] );
			
			int diff = endTime - startTime + 1;
			
			timetable[ startTime - 1 ][ week ] = "<td rowspan=\"" + diff + "\">" + item.getSubject() + "<br />(" + item.getProfessor() + ")</td>"; 
			for( int i = startTime ; i < endTime ; ++i )
				timetable[ i ][ week ] = ""; 
		}				
		
		
		String[] startTimeSlot = new String[ 20 ];
		String[] endTimeSlot = new String[ 19 ];
		
		for( int i = 0 ; i < 20 ; ++i )
			startTimeSlot[ i ] = ( i + 1 ) + "교시(" + timeSlot[ i ] + ")";
		
		for( int i = 0 ; i < 19 ; ++i )
			endTimeSlot[ i ] = ( i + 2 ) + "교시(" + timeSlot[ i + 1 ] + ")";
		
		model.addAttribute( "slot" , timeSlot );
		model.addAttribute( "timetable" , timetable );		
		model.addAttribute( "start" , startTimeSlot );
		model.addAttribute( "end" , endTimeSlot );
		
		return "/mypage/detail";
	}
	
	@RequestMapping( value = "/{place}" , method = RequestMethod.POST )
	public String serachPage( @PathVariable String place , HttpServletRequest request , Model model )
	{	
		int start = Integer.parseInt( request.getParameter( "start" ) ) - 1;
		int end = Integer.parseInt( request.getParameter( "end" ) ) - 1;
		String weekday = request.getParameter( "week" );
		
		if( start > end )
		{
			int tmp = start;
			start= end;
			end = tmp;
		}		
		
		int weekNumber = 0;		
		for( int i = 0 ; i < 5 ; ++i )
			if( weekName[ i ].equalsIgnoreCase( weekday ) )
				weekNumber = i;
		
		Object[][] temp = new Object[ 20 ][ 5 ];
		Object[][] timetable = new Object[ 20 ][ 6 ];
		List< Timetable > timetableList = timetableDao.getByPlace( place );
		
		for( int i = 0 ; i < 20 ; ++i )
			timetable[ i ][ 0 ] = ( i + 1 ) + "교시<br />" + timeSlot[ i ];
		
		for( int i = 0 ; i < 20 ; ++i )
			for( int j = 1 ; j < 6 ; ++j )
				timetable[ i ][ j ] = "<td></td>";
				
		for( Timetable item : timetableList )
		{
			int week = item.getWeek();
			int startTime = Integer.parseInt( item.getTime().split( "-" )[ 0 ] );
			int endTime = Integer.parseInt( item.getTime().split( "-" )[ 1 ] );
			
			int diff = endTime - startTime + 1;
			
			timetable[ startTime - 1 ][ week ] = "<td rowspan=\"" + diff + "\" class=\"bordered\">" + item.getSubject() + "<br />(" + item.getProfessor() + ")</td>"; 
			for( int i = startTime ; i < endTime ; ++i )
				timetable[ i ][ week ] = ""; 
		}
		
		for( Timetable item : timetableList )
		{
			int week = item.getWeek() - 1;
			int startTime = Integer.parseInt( item.getTime().split( "-" )[ 0 ] );
			int endTime = Integer.parseInt( item.getTime().split( "-" )[ 1 ] );
			
			for( int i = startTime - 1 ; i < endTime ; ++i )
				temp[ i ][ week ] = item.getSubject();
		}
		
		boolean isEmpty = true;
		for( int i = start ; i <= end ; ++i )
			if( temp[ i ][ weekNumber] != null )
				isEmpty = false;
		
		if( isEmpty == true )
			for( int i = start ; i <= end ; ++i )
				if( temp[ i ][ weekNumber ] == null )
					timetable[ i ][ weekNumber + 1 ] = "<td class=\"highlight\"></td>";
		
		String[] startTimeSlot = new String[ 20 ];
		String[] endTimeSlot = new String[ 19 ];
		
		for( int i = 0 ; i < 20 ; ++i )
			startTimeSlot[ i ] = ( i + 1 ) + "교시(" + timeSlot[ i ] + ")";
		
		for( int i = 0 ; i < 19 ; ++i )
			endTimeSlot[ i ] = ( i + 2 ) + "교시(" + timeSlot[ i + 1 ] + ")";
		
		model.addAttribute( "slot" , timeSlot );
		model.addAttribute( "timetable" , timetable );		
		model.addAttribute( "usable" , isEmpty );		
		model.addAttribute( "start" , startTimeSlot );
		model.addAttribute( "end" , endTimeSlot );		
		model.addAttribute( "s" , start + 1 );
		model.addAttribute( "e" , end + 1 );
		model.addAttribute( "week" , weekNumber );
		model.addAttribute( "place" , place );
		
		return "/mypage/detail";
	}
}
