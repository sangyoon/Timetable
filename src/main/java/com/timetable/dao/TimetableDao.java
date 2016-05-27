package com.timetable.dao;

import java.util.List;

import com.timetable.vo.Timetable;

public interface TimetableDao
{
	public int insert( Timetable timetable );
	public int update( Timetable timetable );
	public int delete( int idx );
		
	public Timetable getOne( int idx );
	public List< Timetable > getAll();
	
	public List< Timetable > getByPlace( String place );
	public List< Timetable > getBySubject( String subject );
}
