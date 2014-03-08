package com.ilp.otts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp.otts.common.CommonResource;
import com.ilp.otts.db.DBConnection;
import com.ilp.otts.bean.SchoolBean;

public class SchoolDao {

	public static int insert(SchoolBean sb){


		Connection con=DBConnection.getConnection();
		if(con==null)
			return 0;
		try {
			PreparedStatement pt=con.prepareStatement("insert into school values(?,?,?,?,?,?)");
			pt.setString(1,CommonResource.getNextId("school"));
			pt.setString(2,sb.getName());
			pt.setString(3,sb.getLocation());
			pt.setString(4,sb.getPhone());
			pt.setString(5,sb.getBoard());
			pt.setString(6,sb.getEmail());
			
			int status=pt.executeUpdate();
			con.commit();
			con.close();
			return status;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return 0;

	}

	public static int delete(String id){

		Connection con=DBConnection.getConnection();
		if(con==null)
			return 0;
		try {
			PreparedStatement pt=con.prepareStatement("delete from school where schoolid=?");
			pt.setString(1,id);

			int status=pt.executeUpdate();
			con.commit();
			con.close();
			return status;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public static int update(SchoolBean sb){

		Connection con=DBConnection.getConnection();
		if(con==null)
			return 0;
		try {
			String query="update school set location="+'?'+
					",email="+'?'+
					",phonenumber="+'?'+
					" where schoolid="+'?';
			System.out.println(query);
			PreparedStatement pt=con.prepareStatement(query);
			pt.setString(1,sb.getLocation());
			pt.setString(2,sb.getEmail());
			pt.setString(3,sb.getPhone());
			pt.setString(4,sb.getSchoolid());
			System.out.println(sb.getLocation());
			System.out.println(sb.getEmail());
			System.out.println(sb.getPhone());
			System.out.println(sb.getSchoolid());
			int status=pt.executeUpdate();
			con.commit();
			con.close();
			return status;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public static ArrayList<SchoolBean> view(String id){

		Connection con=DBConnection.getConnection();
		SchoolBean sb=new SchoolBean();
		ArrayList<SchoolBean> list=new ArrayList<SchoolBean>();
		if(con==null)
			return null;
		try {
			Statement pt=con.createStatement();
			ResultSet rs=pt.executeQuery("select * from school where schoolid='"+id+"'");
			if(rs.next()){

				sb.setSchoolid(rs.getString(1));
				sb.setName(rs.getString(2));
				sb.setLocation(rs.getString(3));
				sb.setPhone(rs.getString(4));
				sb.setBoard(rs.getString(5));
				sb.setEmail(rs.getString(6));
				list.add(sb);

			}

			//con.commit();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}


	public static ArrayList<SchoolBean> viewSchools(){

		Connection con=DBConnection.getConnection();
		
		ArrayList<SchoolBean> list=new ArrayList<SchoolBean>();
		if(con==null)
			return null;
		try {
			Statement pt=con.createStatement();
			ResultSet rs=pt.executeQuery("select * from school");
			//rs.next();
		while(rs.next()){
			
			SchoolBean sb=new SchoolBean();
				sb.setSchoolid(rs.getString(1));
				sb.setName(rs.getString(2));
				sb.setLocation(rs.getString(3));
				sb.setPhone(rs.getString(4));
				sb.setBoard(rs.getString(5));
				sb.setEmail(rs.getString(6));
				list.add(sb);
			}
			//con.commit();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
