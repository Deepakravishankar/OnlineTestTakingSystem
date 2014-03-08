package com.ilp.otts.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.ilp.otts.db.DBConnection;

public class CommonResource {
	public static String generatePassword(){
		String pwd="";
		String  charlist="abcdefghijklmnopqrstuvwxyz";
		charlist+="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		charlist+="0123456789";
		charlist+="!@#$&*";

		
		Random random=new Random();
		pwd+=charlist.charAt(random.nextInt(26));
		pwd+=charlist.charAt(26+random.nextInt(26));
		pwd+=charlist.charAt(52+random.nextInt(10));
		pwd+=charlist.charAt(62+random.nextInt(6));
		for(int i=0;i<4;i++)
			pwd+=charlist.charAt(random.nextInt(charlist.length()));
		
		System.out.println(pwd);
		return pwd;
	}



	public static String getNextId(String tablename){
		int id=0;
		String newId=null,role=null;
		try{
			Connection con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			//generate unique id
			System.out.println("New Id technique implemented");
			ResultSet rs=stmt.executeQuery("select max("+tablename+"id) from "+tablename);
			rs.next();
			if(rs.getString(1)==null){
				if(tablename.equals("teacher"))
					role="ot";
				else if(tablename.equals("admin"))
					role="oa";
				else if(tablename.equals("student"))
					role="st";
				else if(tablename.equals("school"))
					role="sc";
				else if(tablename.equals("test"))
					role="ts";
				else if(tablename.equals("question"))
					role="oq";
				
			}else{
				role=rs.getString(1).substring(0, 2);
				id=Integer.parseInt(rs.getString(1).substring(2));
				System.out.println("OLD id: "+id);
			}
			id++;

			if(id<10)
				newId=role+"0"+id;
			else
				newId=role+id;
			con.close();
		}
		catch(SQLException ex){
			System.out.println("Creating ID went wrong ");
			ex.printStackTrace();
		}

		return newId;

	}
}
