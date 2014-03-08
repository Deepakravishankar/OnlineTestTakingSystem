package com.ilp.otts.dao;

import com.ilp.otts.bean.*;
import com.ilp.otts.common.CommonResource;
import com.ilp.otts.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
public class AdminDao {
	
	
	public static LoginBean insert(AdminBean adminBean){

		int executeStatus=0;
		String newId;
		LoginBean loginBean=null;

		try {
			Connection con=DBConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement("insert into admin(adminid,firstname,middlename,lastname,dob,email,phonenumber,address) values(?,?,?,?,?,?,?,?)");
			newId=CommonResource.getNextId("admin");
			System.out.println("New ID"+newId);

			pstmt.setString(1,newId);			
			pstmt.setString(2, adminBean.getFirstname());
			pstmt.setString(3, adminBean.getMiddlename());
			pstmt.setString(4, adminBean.getLastname());
			pstmt.setString(5, adminBean.getDob());
			pstmt.setString(6, adminBean.getEmail());
			pstmt.setString(7, adminBean.getPhonenumber());
			pstmt.setString(8, adminBean.getAddress());

			executeStatus=pstmt.executeUpdate();
			System.out.println("inserted details Successfully");
			System.out.println("insert details Query Status: "+executeStatus);
			
			pstmt=con.prepareStatement("insert into login values(?,?,?,?,?,?)");
			
			String pwd=CommonResource.generatePassword();
			pstmt.setString(1, newId);			
			pstmt.setString(2, adminBean.getFirstname()+newId);
			pstmt.setString(3, pwd);
			pstmt.setString(4, "Admin");
			pstmt.setString(5, "1");
			pstmt.setString(6, "2");
			
			loginBean=new LoginBean();
			loginBean.setId(newId);
			loginBean.setUsername(adminBean.getFirstname()+newId);
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
			executeStatus=stmt.executeUpdate("delete from admin where adminid='"+id+"'");
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
	
	
	public static ArrayList<AdminBean> viewAdmins(){
		ResultSet rs=null;
		ArrayList<AdminBean> adminList=new ArrayList<AdminBean>();
		
		try {
			
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("select * from admin");
			
			while(rs.next()){
				AdminBean adminBean=new AdminBean();
				adminBean.setId(rs.getString(1));
				adminBean.setFirstname(rs.getString(2));
				adminBean.setMiddlename(rs.getString(3));
				adminBean.setLastname(rs.getString(4));
				adminBean.setDob(rs.getString(5));
				adminBean.setEmail(rs.getString(6));
				adminBean.setPhonenumber(rs.getString(7));
				adminBean.setAddress(rs.getString(8));
				adminList.add(adminBean);
				
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return adminList;
		
	}
	
	public static ArrayList<AdminBean> viewAdminId(String id){
		ResultSet rs=null;
		ArrayList<AdminBean> adminList=new ArrayList<AdminBean>();
		try {
					
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("select * from admin where adminid='"+id+"'");
			if(rs.next()){
				AdminBean adminBean=new AdminBean();
			adminBean.setId(rs.getString(1));
			adminBean.setFirstname(rs.getString(2));
			adminBean.setMiddlename(rs.getString(3));
			adminBean.setLastname(rs.getString(4));
			adminBean.setDob(rs.getString(5));
			adminBean.setEmail(rs.getString(6));
			adminBean.setPhonenumber(rs.getString(7));
			adminBean.setAddress(rs.getString(8));
			adminList.add(adminBean);
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return adminList;
	}
	
	
	public static int update(AdminBean adminBean,String id){
		int executeStatus=0;
		try {
			
			Connection con=DBConnection.getConnection();
			PreparedStatement pst=con.prepareStatement("update admin set email="+'?'+
					",phonenumber="+'?'+" where adminid="+'?');
			

			pst.setString(1, adminBean.getEmail());
			pst.setString(2, adminBean.getPhonenumber());

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
			PreparedStatement pst=con.prepareStatement("update admin set question="+'?'+
					",answer="+'?'+" where adminid="+'?');
			

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


	public static String checkEmail(String email) {
		// TODO Auto-generated method stub
		String result="false";
		try {
			
			Connection con=DBConnection.getConnection();
			PreparedStatement pst=con.prepareStatement("select firstname from admin where email=?");
			pst.setString(1, email);

			ResultSet rs=pst.executeQuery();
			if(rs.next())
				result="false";
			else
				result="true";
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
