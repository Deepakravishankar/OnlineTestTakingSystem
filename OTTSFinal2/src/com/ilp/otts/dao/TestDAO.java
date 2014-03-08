package com.ilp.otts.dao;

import java.util.*;
import java.sql.*;

import javax.servlet.http.HttpSession;

import com.ilp.otts.bean.*;
import com.ilp.otts.db.*;



/**
 * Servlet implementation class TestDAO
 */
public class TestDAO {
	static Connection con = null;
	static PreparedStatement pt = null;
	static Statement st = null;
	public static TestBean createTest1(TestBean beanObj) throws Exception{
		try{
			
			con = DBConnection.getConnection();
			String query = "insert into Test values(?,?,?,?,?)";
			pt = con.prepareStatement(query);
			pt.setString(1, beanObj.getTestid());
			pt.setString(2, beanObj.getTestName());
			pt.setString(3, beanObj.getDuration());
			pt.setString(4,beanObj.getCategory());
			pt.setString(5,beanObj.getNoofStudents() );
			pt.executeUpdate();
			con.commit();		
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pt.close();

		}
		
		return beanObj;
	}
	public static ArrayList<TestTeacherBean> ViewTeacherTest(TestBean beanObj)throws Exception{
		ArrayList<TestTeacherBean> ttb=new ArrayList<TestTeacherBean>();
		try{
			con = DBConnection.getConnection();
			System.out.println("intest");
			String query = "select teacherid,firstname from teacher";
			st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()){
				TestTeacherBean t=new TestTeacherBean();
				t.setTeacherid(rs.getString(1));
				t.setTeacherName(rs.getString(2));
				ttb.add(t);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	return ttb;
	}
	public static void addTestTeacher(ArrayList<TestTeacherBean> obj)throws Exception{
			try{
			con = DBConnection.getConnection();
			for(int i=0;i<obj.size();i++){
			String query=("insert into testteacher values(?,?)");
			pt = con.prepareStatement(query);
			pt.setString(1, obj.get(i).getTestid());
			pt.setString(2, obj.get(i).getTeacherid());
			pt.executeUpdate();
			con.commit();		
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			con.close();
			pt.close();

		}
	}
	public static ArrayList<TestQuestionBean> viewTestQuestion(String category, String testid)throws Exception{
		ArrayList<TestQuestionBean> tqb=new ArrayList<TestQuestionBean>();
		try{
			con = DBConnection.getConnection();
			String query = "select Questionid,Question from question where Category='"+category+"'";
			st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()){
				TestQuestionBean t=new TestQuestionBean();
				t.setQuestionid(rs.getString(1));
				t.setQuestionName(rs.getString(2));
				t.setTestid(testid);
				tqb.add(t);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	return tqb;
	}
	public static void addTestQuestion(ArrayList<TestQuestionBean> obj)throws Exception{
		try{
		con = DBConnection.getConnection();
		for(int i=0;i<obj.size();i++){
		String query=("insert into testquestion values(?,?)");
		pt = con.prepareStatement(query);
		pt.setString(1, obj.get(i).getTestid());
		pt.setString(2, obj.get(i).getQuestionid());
		pt.executeUpdate();
		con.commit();		
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally {
		con.close();
		pt.close();

	}
	}
	public static TestBean updateViewTest(String testid)throws Exception{
		TestBean t=new TestBean();
		try{
			con = DBConnection.getConnection();
			String query=("select * from test where testid='"+testid+"'");
			System.out.println(testid);
			st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()){
				t.setTestid(rs.getString(1));
				t.setTestName(rs.getString(2));
				t.setDuration(rs.getString(3));
				t.setCategory(rs.getString(4));
				t.setNoofStudents(rs.getString(5));
				
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally {
		con.close();
		st.close();

	}
	return t;
	}
	public static void updateTest(TestBean t){
		try{
			con = DBConnection.getConnection();
			String query=("update  test set testname=? duration=? noofstudents=? where testid=?");
			pt = con.prepareStatement(query);
			pt.setString(1,t.getTestName() );
			pt.setString(2,t.getDuration());
			pt.setString(3,t.getNoofStudents());
			pt.setString(4,t.getTestid());
			pt.executeUpdate();
			con.commit();
			con.close();
			pt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			

		}
	}
	public static ArrayList<TestBean> getStudentTests(){
		ArrayList<TestBean> t=new ArrayList<TestBean>();
		try{
			con = DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select testid,testname from test");
			while(rs.next()){
				TestBean td=new TestBean();
				td.setTestid(rs.getString(1));
				td.setTestName(rs.getString(2));
				t.add(td);
			}
			con.close();
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	return t;
	}
	public static ArrayList<TestStudentBean> getStudents(String testid){
		ArrayList<TestStudentBean> t=new ArrayList<TestStudentBean>();
		try{
			con = DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select studentid,firstname from student");
			while(rs.next()){
				TestStudentBean tsb=new TestStudentBean();
				tsb.setStudentid(rs.getString(1));
				tsb.setTestid(testid);
				tsb.setStudentid(rs.getString(1));
				tsb.setStudentName(rs.getString(2));
				t.add(tsb);
			}
			con.close();
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return t;
	}
	public static void addStudentTest(ArrayList<TestStudentBean> obj){
		try{
			con = DBConnection.getConnection();
			for(int i=0;i<obj.size();i++){
			String query=("insert into teststudent values(?,?)");
			pt = con.prepareStatement(query);
			pt.setString(1, obj.get(i).getTestid());
			pt.setString(2, obj.get(i).getStudentid());
			pt.executeUpdate();
			con.commit();		
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public static ArrayList<TestBean> viewUpdateTest(){
		ArrayList<TestBean> a=new ArrayList<TestBean>();
		try{
			con=DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select testid,testname from test");
			while(rs.next()){
				TestBean t=new TestBean();
				t.setTestid(rs.getString(1));
				t.setTestName(rs.getString(2));
				a.add(t);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	public static ArrayList<String> updateTestQuestion1(String testid){
		ArrayList<String> a=new ArrayList<String>();
		try{
			con=DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select questionid from testquestion where testid='"+testid+"'");
			while(rs.next()){
				String t=rs.getString(1);
				a.add(t);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	public static ArrayList<TestQuestionBean> updateTestQuestion2(String testid){
		ArrayList<TestQuestionBean> a=new ArrayList<TestQuestionBean>();
		try{
			con=DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select category from test where testid='"+testid+"'");
			String category=null;
			if(rs.next()){
				category=rs.getString(1);
			}
			
			ResultSet rs1=st.executeQuery("select questionid,question from question where category='"+category+"'");
			while(rs1.next()){
				TestQuestionBean t=new TestQuestionBean();
				t.setTestid(testid);
				t.setQuestionid(rs1.getString(1));
				t.setQuestionName(rs1.getString(2));
				System.out.println(rs1.getString(1));
				a.add(t);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	public static void updateTestQuestionFinal(ArrayList<TestQuestionBean> a){
		try{
		con = DBConnection.getConnection();
		st=con.createStatement();
		st.executeQuery("delete from testquestion where testid='"+a.get(1).getTestid()+"'");
		con.commit();
		for(int i=0;i<a.size();i++){
			String query=("insert into testquestion values(?,?)");
			pt = con.prepareStatement(query);
			pt.setString(1, a.get(i).getTestid());
			pt.setString(2, a.get(i).getQuestionid());
			pt.executeUpdate();
			con.commit();		
						
		}
		con.close();
		st.close();
		pt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static ArrayList<String> updateTestTeacher1(String t){
		ArrayList<String> a=new ArrayList<String>();
		try{
			con=DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select teacherid from testteacher where testid='"+t+"'");
			while(rs.next()){
				String s=rs.getString(1);
				a.add(s);
			}
			
	}catch(Exception e){
		e.printStackTrace();
	}
	return a;
	}
	public static ArrayList<TestTeacherBean> updateTestTeacher2(){
		ArrayList<TestTeacherBean> a=new ArrayList<TestTeacherBean>();
		try{
			con=DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select teacherid,firstname from teacher ");
			while(rs.next()){
				TestTeacherBean ttb=new TestTeacherBean();
				ttb.setTeacherid(rs.getString(1));
				ttb.setTeacherName(rs.getString(2));
				a.add(ttb);
			}
			
	}catch(Exception e){
		e.printStackTrace();
	}
	return a;
	}
	public static void updateTestTeacherFinal(ArrayList<TestTeacherBean> a){
		try{
			con=DBConnection.getConnection();
			st=con.createStatement();
			st.executeQuery("delete from testteacher where testid='"+a.get(1).getTestid()+"'");
			con.commit();
			for(int i=0;i<a.size();i++){
				String query=("insert into testteacher values(?,?)");
				pt = con.prepareStatement(query);
				pt.setString(1, a.get(i).getTestid());
				pt.setString(2, a.get(i).getTeacherid());
				pt.executeUpdate();
				con.commit();		
							
			}
			con.close();
			st.close();
			pt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static ArrayList<TestBean> viewTests(){
		ArrayList<TestBean> t=new ArrayList<TestBean>();
		
		try{
			
			con =DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs2=st.executeQuery("select * from test");
			while(rs2.next()){
				TestBean tb=new TestBean();
				tb.setTestid(rs2.getString(1));
				tb.setTestName(rs2.getString(2));
				tb.setDuration(rs2.getString(3));
				tb.setCategory(rs2.getString(4));
				tb.setNoofStudents(rs2.getString(5));
				t.add(tb);
				}
			st.close();
			con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return t;
	}
	public static void deleteTest(String x){
		try{
		con = DBConnection.getConnection();
		String query = "delete from test where testid="+'?';
		pt = con.prepareStatement(query);
		pt.setString(1, x);
		pt.executeUpdate();
		con.commit();
		System.out.println(x);
		query = "delete from testquestion where testid=?";
		pt = con.prepareStatement(query);
		pt.setString(1, x);
		pt.executeUpdate();
		con.commit();
		query = "delete from testteacher where testid=?";
		pt = con.prepareStatement(query);
		pt.setString(1, x);
		pt.executeUpdate();
		con.commit();
		query = "delete from teststudent where testid=?";
		pt = con.prepareStatement(query);
		pt.setString(1, x);
		pt.executeUpdate();
		con.commit();
		
		con.close();
		pt.close();
		
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		

	}
	}
	public static ArrayList<QuestionBean> getQuestions(String testid) {
		// TODO Auto-generated method stub
		try {
			ArrayList<String> qidlist=new ArrayList<String>();
			ArrayList<QuestionBean> qlist=new ArrayList<QuestionBean>();
			con=DBConnection.getConnection();
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery("select questionid from testquestion where testid='"+testid+"'");
			while(rs.next()){
				qidlist.add(rs.getString(1));
			}
			for(String questionid: qidlist){
				QuestionBean qb=new QuestionBean();
				rs=stmt.executeQuery("select * from question where QUESTIONID='"+questionid+"'");
				rs.next();
				qb.setQuestionid(rs.getString(1));
				qb.setQuestion(rs.getString(2));
				qb.setOption1(rs.getString(4));
				qb.setOption2(rs.getString(5));
				qb.setOption3(rs.getString(6));
				qb.setOption4(rs.getString(7));
				qlist.add(qb);
			}

			con.close();
			return qlist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static int saveTest(ArrayList<QuestionBean> qlist, String testid,String studentid) {
		// TODO Auto-generated method stub
		try {
			int status=1;
			con=DBConnection.getConnection();
			PreparedStatement stmt=con.prepareStatement("insert into testresult values(?,?,?,?)");
			for(QuestionBean qb: qlist){				
				stmt.setString(1, testid);
				stmt.setString(2, studentid);
				stmt.setString(3, qb.getQuestionid());
				stmt.setString(4, qb.getAnswer());
				status*=stmt.executeUpdate();				
			}
			con.commit();
			con.close();
			return status;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static ArrayList<String> getTests(String studentid) {
		// TODO Auto-generated method stub
		try {
			ArrayList<String> list=new ArrayList<String>();
			con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select testid from teststudent where studentid='"+studentid+"'");
			while(rs.next()){
				list.add(rs.getString(1));
			}
			con.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public static int getDuration(String testid) {
		int duration = 0;
		try {
			
			con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select duration from test where testid='"+testid+"'");
			if(rs.next()){
				duration=Integer.parseInt(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return duration;
	}
	public static TestBean viewSingleTest(String testid){
		TestBean t=new TestBean();
		try {
			
			con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from test where testid='"+testid+"'");
			if(rs.next()){
				t.setTestid(rs.getString(1));
				t.setTestName(rs.getString(2));
				t.setCategory(rs.getString(3));
				t.setDuration(rs.getString(4));
				t.setNoofStudents(rs.getString(5));
				
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	return t;
	}
	
}

