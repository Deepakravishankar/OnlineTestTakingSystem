package com.ilp.otts.dao;


import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ilp.otts.bean.*;
import com.ilp.otts.db.DBConnection;



public class QuestionDAO{
	static String ln;
	static String questionid;
	static String question;
	static String category;
	static String option1;
	static String option2;
	static String option3;
	static String option4;
	static String answer;
	static ResultSet rs=null;
	static Connection con = null;
	static PreparedStatement pt = null;
	static Statement st = null;
	//private final static Logger log = Logger.getLogger(QuestionDAO.class);
	QuestionBean obj1=new QuestionBean();
	ArrayList<QuestionBean> s=new ArrayList<QuestionBean>();

	public ArrayList<QuestionBean> view()
	{
		ArrayList<QuestionBean> sl=new ArrayList<QuestionBean>();
		try
		{	
			con=DBConnection.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from question");


			while(rs.next())
			{	
				QuestionBean obj1=new QuestionBean();
				obj1.setQuestionid(rs.getString(1));
				obj1.setQuestion(rs.getString(2));
				obj1.setCategory(rs.getString(3));
				obj1.setOption1(rs.getString(4));
				obj1.setOption2(rs.getString(5));
				obj1.setOption3(rs.getString(6));
				obj1.setOption4(rs.getString(7));
				obj1.setAnswer(rs.getString(8));
				sl.add(obj1);		

			}
			st.close();

			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sl;
	}


	public static void createQuestion(QuestionBean q) {

		try{

			con = DBConnection.getConnection();
			String query = "insert into question values(?,?,?,?,?,?,?,?)";
			pt = con.prepareStatement(query);
			pt.setString(1, q.getQuestionid());
			pt.setString(2, q.getQuestion());
			pt.setString(3, q.getCategory());
			pt.setString(4,q.getOption1());
			pt.setString(5,q.getOption2());
			pt.setString(6,q.getOption3());
			pt.setString(7,q.getOption4());
			pt.setString(8,q.getAnswer());
			pt.executeUpdate();
			con.commit();		
			con.close();
			pt.close();

		}catch (Exception e) {
			e.printStackTrace();
		} finally {

		}	
	}
	public static QuestionBean DeleteQuestions(QuestionBean beanobj)
	throws Exception {
		try {

			con = DBConnection.getConnection();
			st = con.createStatement();
			//log.info("Before deleting from table");
			String query = "delete from question where QuestionId='"
				+ beanobj.getQuestionid() + "' ";
			st.executeUpdate(query);
			con.commit();

		} 
		catch (Exception e) {
			//log.error("Exception", e);
			e.printStackTrace();
		} finally {
			con.close();
			st.close();

		}
		return beanobj;
	}


	public static QuestionBean UpdateQuestions(QuestionBean beanobj)
	throws Exception {

		try {

			con = DBConnection.getConnection();

			String query = "update question set QUESTION="+'?'+",CATEGORY="+'?'+",OPTION1="+'?'+",OPTION2="+'?'+",OPTION3="+'?'+",OPTION4="+'?'+",ANSWER="+'?'+" where QUESTIONID="+'?'+"";

			pt = con.prepareStatement(query);
			pt.setString(8, beanobj.getQuestionid());
			pt.setString(1, beanobj.getQuestion());
			pt.setString(2, beanobj.getCategory());
			pt.setString(3, beanobj.getOption1());
			pt.setString(4, beanobj.getOption2());
			pt.setString(5, beanobj.getOption3());
			pt.setString(6, beanobj.getOption4());
			pt.setString(7, beanobj.getAnswer());

			pt.executeQuery();
			con.commit();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			con.close();
			pt.close();
		}
		return beanobj;
	}

	public static String viewQuestion(String qid)
	throws Exception{

		try{

			con = DBConnection.getConnection();
			String query="select * from question where QUESTIONID=?";
			pt=con.prepareStatement(query);
			pt.setString(1,qid);
			rs=pt.executeQuery();
			while(rs.next()){
				questionid= rs.getObject(1).toString();
				question=rs.getObject(2).toString();
				category=rs.getObject(3).toString();
				option1=rs.getObject(4).toString();
				option2=rs.getObject(5).toString();
				option3=rs.getObject(6).toString();
				option4=rs.getObject(7).toString();
				answer=rs.getObject(8).toString();
				ln= questionid+"."+category+"."+ question+"."+option1+"."+option2+"."+option3+"."+option4+"."+answer+".";


			}

			con.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
			pt.close();
		}
		return ln;

	}
	public String getquestionid(){
		return questionid;
	}
	public String getcategory(){
		return category;

	}
	public String getquestion(){
		return question;
	}
	public String getoption1(){
		return option1;
	}
	public String getoption2(){
		return option2;
	}
	public String getoption3(){
		return option3;
	}
	public String getoption4(){
		return option4;
	}
	public String getanswer(){
		return answer;
	}


}
