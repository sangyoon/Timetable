package com.timetable.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.timetable.dao.TimetableDao;
import com.timetable.vo.Timetable;

public class TimetableDaoImpl extends SqlSessionDaoSupport implements TimetableDao
{
	public int insert( Timetable timetable )
	{
		return getSqlSession().insert( "timetableDao.insert" , timetable );
	}

	public int update( Timetable timetable )
	{
		return getSqlSession().update( "timetableDao.update" , timetable );
	}

	public int delete( int idx )
	{		
		return getSqlSession().delete( "timetableDao.delete" , idx );
	}

	public Timetable getOne( int idx )
	{		
		return ( Timetable ) getSqlSession().selectOne( "timetableDao.getOne" , idx );
	}
	
	@SuppressWarnings( "unchecked" )
	public List< Timetable > getAll()
	{
		return getSqlSession().selectList( "timetableDao.getAll" );
	}

	@SuppressWarnings( "unchecked" )
	public List< Timetable > getByPlace( String place )
	{
		return getSqlSession().selectList( "timetableDao.getByPlace" , place );
	}

	@SuppressWarnings( "unchecked" )
	public List< Timetable > getBySubject( String subject )
	{
		return getSqlSession().selectList( "timetableDao.getBySubject" , subject );
	}	
}
