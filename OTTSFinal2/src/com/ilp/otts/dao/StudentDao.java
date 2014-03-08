package com.ilp.otts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp.otts.bean.LoginBean;
import com.ilp.otts.bean.StudentBean;
import com.ilp.otts.bean.TeacherBean;
import com.ilp.otts.common.CommonResource;
import com.ilp.otts.db.DBConnection;

public class StudentDao {
	public static LoginBean insert(StudentBean studentBean){

		int executeStatus=0;
		String newId;
		LoginBean loginBean=null;

		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?)");
			newId=CommonResource.getNextId("student");
			System.out.println("New ID"+newId);
			
			
			pstmt.setString(1,newId);			
			pstmt.setString(2, studentBean.getFirstname());
			pstmt.setString(3, studentBean.getMiddlename());
			pstmt.setString(4, studentBean.getLastname());
			pstmt.setString(5, studentBean.getDob());
			pstmt.setString(6, studentBean.getGender());
			pstmt.setString(7, studentBean.getEmail());
			pstmt.setString(8, studentBean.getPhonenumber());
			pstmt.setString(9, studentBean.getAddress());
			pstmt.setString(10, studentBean.getSchool());

			executeStatus=pstmt.executeUpdate();
			System.out.println("inserted details Successfully");
			System.out.println("insert details Query Status: "+executeStatus);
			
			pstmt=con.prepareStatement("insert into login values(?,?,?,?,?,?)");
			
			String pwd=CommonResource.generatePassword();
			pstmt.setString(1, newId);			
			pstmt.setString(2, studentBean.getFirstname()+newId);
			pstmt.setString(3, pwd);
			pstmt.setString(4, "Student");
			pstmt.setString(5, "0");
			pstmt.setString(6, "4");
			
			loginBean=new LoginBean();
			loginBean.setId(newId);
			loginBean.setUsername(studentBean.getFirstname()+newId);
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
			executeStatus=stmt.executeUpdate("delete from student where studentid='"+id+"'");
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
	
	
	public static ArrayList<StudentBean> viewStudents(){
		ResultSet rs=null;
		ArrayList<StudentBean> list=new ArrayList<StudentBean>();
		
		try {
			
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("select * from student");
			
			while(rs.next()){
				StudentBean StudentBean=new StudentBean();
				StudentBean.setId(rs.getString(1));
				StudentBean.setFirstname(rs.getString(2));
				StudentBean.setMiddlename(rs.getString(3));
				StudentBean.setLastname(rs.getString(4));
				StudentBean.setDob(rs.getString(5));
				StudentBean.setGender(rs.getString(6));
				StudentBean.setEmail(rs.getString(7));
				StudentBean.setPhonenumber(rs.getString(8));
				StudentBean.setAddress(rs.getString(9));
				StudentBean.setSchool(rs.getString(10));
				list.add(StudentBean);
				
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public static ArrayList<StudentBean> viewStudentId(String id){
		ResultSet rs=null;
		ArrayList<StudentBean> list=new ArrayList<StudentBean>();
		try {
					
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("select * from student where studentid='"+id+"'");
			if(rs.next()){
				StudentBean StudentBean=new StudentBean();
				StudentBean.setId(rs.getString(1));
				StudentBean.setFirstname(rs.getString(2));
				StudentBean.setMiddlename(rs.getString(3));
				StudentBean.setLastname(rs.getString(4));
				StudentBean.setDob(rs.getString(5));
				StudentBean.setGender(rs.getString(6));
				StudentBean.setEmail(rs.getString(7));
				StudentBean.setPhonenumber(rs.getString(8));
				StudentBean.setAddress(rs.getString(9));
				StudentBean.setSchool(rs.getString(10));
				list.add(StudentBean);
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static int update(StudentBean StudentBean){
		int executeStatus=0;
		try {
			
			Connection con=DBConnection.getConnection();
			PreparedStatement pst=con.prepareStatement("update student set email="+'?'+
					",schoolid="+'?'+
					",phonenumber="+'?'+
					" where studentid="+'?');
			
			pst.setString(1, StudentBean.getEmail());
			pst.setString(2, StudentBean.getSchool());
			pst.setString(3, StudentBean.getPhonenumber());
			pst.setString(4, StudentBean.getId());

			
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
