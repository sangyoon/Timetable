package com.timetable.dao;

import java.util.List;

import com.timetable.vo.Student;

public interface StudentDao
{
	public int insert( Student student );
	public int update( Student student );
	public int delete( String sid );
	
	public Student getOne( String sid );
	public List< Student > getAll();
}