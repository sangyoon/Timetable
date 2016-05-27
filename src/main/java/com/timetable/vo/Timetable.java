package com.timetable.vo;

public class Timetable
{
	private int idx;
	private String subject;
	private String professor;
	private int week;
	private String time;
	private String place;
	
	public int getIdx() { return idx; }
	public void setIdx(int idx) { this.idx = idx; }
	
	public String getSubject() { return subject; }
	public void setSubject(String subject) { this.subject = subject; }
	
	public String getProfessor() { return professor; }
	public void setProfessor(String professor) { this.professor = professor; }
	
	public int getWeek() { return week; }
	public void setWeek( int week ) { this.week = week; }
	
	public String getTime() { return time; }
	public void setTime( String time ) { this.time = time; }
	
	public String getPlace() { return place; }
	public void setPlace( String place ) { this.place = place; }
}
