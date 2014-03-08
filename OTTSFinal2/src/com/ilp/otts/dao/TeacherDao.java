package com.ilp.otts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp.otts.bean.TeacherBean;
import com.ilp.otts.bean.LoginBean;
import com.ilp.otts.common.CommonResource;
import com.ilp.otts.db.DBConnection;

public class TeacherDao {
	public static LoginBean insert(TeacherBean teacherBean){

		int executeStatus=0;
		String newId;
		LoginBean loginBean=null;

		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("insert into teacher(teacherid,firstname,middlename,lastname,dob,email,phonenumber,address) values(?,?,?,?,?,?,?,?)");
			newId=CommonResource.getNextId("teacher");
			System.out.println("New ID"+newId);

			pstmt.setString(1,newId);			
			pstmt.setString(2, teacherBean.getFirstname());
			pstmt.setString(3, teacherBean.getMiddlename());
			pstmt.setString(4, teacherBean.getLastname());
			pstmt.setString(5, teacherBean.getDob());
			pstmt.setString(6, teacherBean.getEmail());
			pstmt.setString(7, teacherBean.getPhonenumber());
			pstmt.setString(8, teacherBean.getAddress());

			executeStatus=pstmt.executeUpdate();
			System.out.println("inserted details Successfully");
			System.out.println("insert details Query Status: "+executeStatus);
			
			pstmt=con.prepareStatement("insert into login values(?,?,?,?,?,?)");
			
			String pwd=CommonResource.generatePassword();
			pstmt.setString(1, newId);			
			pstmt.setString(2, teacherBean.getFirstname()+newId);
			pstmt.setString(3, pwd);
			pstmt.setString(4, "Teacher");
			pstmt.setString(5, "1");
			pstmt.setString(6, "3");
			
			loginBean=new LoginBean();
			loginBean.setId(newId);
			loginBean.setUsername(teacherBean.getFirstname()+newId);
			loginBean.setPassword(pwd);

			executeStatus=pstmt.executeUpdate();
			System.out.println("inserted details Successfully");
			System.out.println("insert details Query Status: "+executeStatus);
			
			con.commit();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginBean;

	}
	
	
	public static int delete(String id){
		
		int executeStatus=0;
		
		try {
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			executeStatus=stmt.executeUpdate("delete from teacher where teacherid='"+id+"'");
			System.out.println("Delete details :"+executeStatus);
			
			executeStatus=stmt.executeUpdate("delete from login where loginid='"+id+"'");
			System.out.println("Delete login :"+executeStatus);
			
			con.commit();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return executeStatus;
	}
	
	
	public static ArrayList<TeacherBean> viewTeachers(){
		ResultSet rs=null;
		ArrayList<TeacherBean> adminList=new ArrayList<TeacherBean>();
		
		try {
			
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("select * from teacher");
			
			while(rs.next()){
				TeacherBean teacherBean=new TeacherBean();
				teacherBean.setId(rs.getString(1));
				teacherBean.setFirstname(rs.getString(2));
				teacherBean.setMiddlename(rs.getString(3));
				teacherBean.setLastname(rs.getString(4));
				teacherBean.setDob(rs.getString(5));
				teacherBean.setEmail(rs.getString(6));
				teacherBean.setPhonenumber(rs.getString(7));
				teacherBean.setAddress(rs.getString(8));
				adminList.add(teacherBean);
				
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return adminList;
		
	}
	
	public static ArrayList<TeacherBean> viewTeacherId(String id){
		ResultSet rs=null;
		ArrayList<TeacherBean> adminList=new ArrayList<TeacherBean>();
		try {
					
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("select * from teacher where teacherid='"+id+"'");
			if(rs.next()){
			TeacherBean teacherBean=new TeacherBean();
			teacherBean.setId(rs.getString(1));
			teacherBean.setFirstname(rs.getString(2));
			teacherBean.setMiddlename(rs.getString(3));
			teacherBean.setLastname(rs.getString(4));
			teacherBean.setDob(rs.getString(5));
			teacherBean.setEmail(rs.getString(6));
			teacherBean.setPhonenumber(rs.getString(7));
			teacherBean.setAddress(rs.getString(8));
			adminList.add(teacherBean);
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return adminList;
	}
	
	
	public static int update(TeacherBean teacherBean,String id){
		int executeStatus=0;
		try {
			
			Connection con=DBConnection.getConnection();
			PreparedStatement pst=con.prepareStatement("update teacher set email="+'?'+
					",phonenumber="+'?'+" where teacherid="+'?');
			

			pst.setString(1, teacherBean.getEmail());
			pst.setString(2, teacherBean.getPhonenumber());

			pst.setString(3, id);
			executeStatus=pst.executeUpdate();
			con.commit();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeStatus;
	}


	public static int updateSecretQuestion(String id, String question,
			String answer) {
		int executeStatus=0;
		try {
		
			Connection con=DBConnection.getConnection();
			PreparedStatement pst=con.prepareStatement("update teacher set question="+'?'+
					",answer="+'?'+" where teacherid="+'?');
			

			pst.setString(1, question);
			pst.setString(2, answer);

			pst.setString(3, id);
			executeStatus=pst.executeUpdate();
			con.commit();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeStatus;
	}
}
