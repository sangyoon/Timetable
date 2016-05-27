package com.timetable.vo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.validator.constraints.NotBlank;

public class Student
{
	@NotBlank private String sid;
	@NotBlank private String password;	
	private String name;
	private String email;
	private int grade;
	private String major;
	
	public Student() {}
	public Student( String sid , String password , String name , String email , int grade , String major )
	{
		this.sid = sid;
		this.password = password;
		this.name = name;
		this.email = email;
		this.grade = grade;
		this.major = major;
	}
	
	public String getSid() { return sid; }
	public void setSid( String sid ) { this.sid = sid; }
	
	public String getPassword() { return password; }
	public void setPassword( String password ) { this.password = password; }
	
	public String getName() { return name; }
	public void setName( String name ) { this.name = name; }
	
	public String getEmail() { return email; }
	public void setEmail( String email ) { this.email = email; }
	
	public int getGrade() { return grade; }
	public void setGrade( int grade ) { this.grade = grade; }
	
	public String getMajor() { return major; }
	public void setMajor( String major ) { this.major = major; }
	
	public String getHash( String password )
	{
		String hashStr = null;
		
		try
		{
			MessageDigest md = MessageDigest.getInstance( "SHA-1" );
			md.update( password.getBytes() );

			BigInteger hash = new BigInteger( 1 , md.digest() );
			hashStr = hash.toString( 16 );
			
			return hashStr;
		}
		catch( NoSuchAlgorithmException e )
		{
			e.printStackTrace();
		}
	     
	    return hashStr;
	 }
}
