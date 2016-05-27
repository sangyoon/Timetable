package com.timetable.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.timetable.dao.StudentDao;
import com.timetable.vo.Student;

public class StudentDaoImpl extends SqlSessionDaoSupport implements StudentDao
{
	public int insert( Student student )
	{
		return getSqlSession().insert( "studentDao.insert" , student );
	}

	public int update( Student student )
	{
		return getSqlSession().update( "studentDao.update" , student );
	}

	public int delete( String sid )
	{
		return getSqlSession().delete( "studentDao.delete" , sid );
	}

	public Student getOne( String sid )
	{
		return ( Student ) getSqlSession().selectOne( "studentDao.getOne" , sid );
	}

	@SuppressWarnings( "unchecked" )
	public List< Student > getAll()
	{
		return getSqlSession().selectList( "studentDao.getAll" );
	}
}
