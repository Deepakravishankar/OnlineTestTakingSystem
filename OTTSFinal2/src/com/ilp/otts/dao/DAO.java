package com.ilp.otts.dao;
import java.sql.Connection;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp.otts.dao.DAO;
import com.ilp.otts.db.DBConnection;
import com.ilp.otts.bean.Bean;



public class DAO {
	// private static Logger log = Logger.getLogger({name of the java file}.class);

	private final static Logger log = Logger.getLogger(DAO.class);
	static Connection con=null;
	static ResultSet rs=null;
	static PreparedStatement pt=null;
	static Statement st=null;
	static ResultSet rs1=null;
	public static int i=0;
	public static int status;
	public static Bean view(Bean a) throws SQLException {
		try{ 

			String query="select * from result1 where studentid ='"+a.getStudentid()+"'";
			con=DBConnection.getConnection();
			pt=con.prepareStatement(query);
			System.out.println(query);
			System.out.println(a.getStudentid());
			rs=pt.executeQuery();
			System.out.println("dao");
			while(rs.next())
			{	i=1;
			a.setStudentid(rs.getString(2));
			a.setResult(rs.getString(3));
			a.setPercentage(rs.getString(5));
			}
			System.out.println(a.getStudentid());
		}
		catch(Exception e){

		}
		finally
		{
			con.close();
			pt.close();
		}
		return a;
	}

	public static ArrayList<Bean> ViewAll()throws Exception {
		ArrayList<Bean> b=new ArrayList<Bean>();
		try {

			con = DBConnection.getConnection();
			System.out.println("updateview");
			st= con.createStatement();
			String query ="select * from result1";
			rs = st.executeQuery(query);
			// extract data from the ResultSet
			while (rs.next()) {
				Bean a=new Bean();
				a.setStudentid(rs.getString(2));
				a.setResult(rs.getString(3));
				a.setPercentage(rs.getString(5));
				b.add(a);
			}
			System.out.println("got input");
		}
		catch (Exception e) {
			e.printStackTrace();
		} 

		finally {
			con.close();
			st.close();
		}
		return b; 
	}

	public static ArrayList<Bean> EligibleAll()throws Exception {
		ArrayList<Bean> b=new ArrayList<Bean>();
		try {

			con = DBConnection.getConnection();
			System.out.println("updateview");
			st= con.createStatement();
			String query ="select * from result1 where average>=90";
			rs = st.executeQuery(query);
			// extract data from the ResultSet
			while (rs.next()) {
				Bean a=new Bean();
				a.setStudentid(rs.getString(1));
				a.setResult(rs.getString(2));
				a.setPercentage(rs.getString(3));
				b.add(a);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 

		finally {
			con.close();
			st.close();
		}
		return b; 
	}

	//------
	public static ArrayList<Bean> getAwards() throws SQLException {
		try{ 
			String query="select * from award";
			con=DBConnection.getConnection();
			pt=con.prepareStatement(query);
			rs=pt.executeQuery();

			System.out.println("dao");

			ArrayList<Bean> list=new ArrayList<Bean>();

			while(rs.next())
			{	Bean ab=new Bean();
			ab.setAwardid(rs.getString(1));
			ab.setAwardtype(rs.getString(2));
			ab.setAwardname(rs.getString(3));
			ab.setAwardamount(rs.getString(4));	
			list.add(ab);
			}
			return list;

		}
		catch(Exception e){

		}
		finally
		{
			con.close();
			pt.close();
		}
		return null;
	}

	public static ArrayList<Bean> getEligible(String awardid){
		// TODO Auto-generated method stub

		try {

			String query="select low,high,awardname from award where awardid='"+awardid+"'";

			con=DBConnection.getConnection();
			pt=con.prepareStatement(query);
			rs=pt.executeQuery();

			rs.next();
			int low=rs.getInt(1);
			int high=rs.getInt(2);
			String awardname=rs.getString(3);

			query="select testid,studentid,schoolid from result1 where average between "+low+" and "+high;
			System.out.println(query);

			pt=con.prepareStatement(query);
			rs=pt.executeQuery();

			ArrayList<Bean> list=new ArrayList<Bean>();
			while(rs.next()){
				Bean rb=new Bean();
				rb.setTestid(rs.getString(1));
				rb.setStudentid(rs.getString(2));
				rb.setSchoolid(rs.getString(3));
				list.add(rb);

			}

			con.close();
			return list;


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}

	public static String getAwardName(String awid) {
		try{ 
			String query="select awardname from award where awardid='"+awid+"'";
			con=DBConnection.getConnection();
			pt=con.prepareStatement(query);
			rs=pt.executeQuery();
			rs.next();
			String name=rs.getString(1);
			con.close();
			return name;


		}
		catch(Exception e){

		}
		finally
		{
			try {
				con.close();
				pt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public static int allocateAward(String id[]) {
		// TODO Auto-generated method stub
		String testid=id[0];
		String studentid=id[1];
		String awardid=id[2];
		String awardname=id[3];
		String schoolid;
		System.out.println(testid+studentid+awardid+awardname);


		try{ 
			System.out.println("in try");
			String query="select schoolid from result1 where testid=? and studentid=?";
			con=DBConnection.getConnection();
			pt=con.prepareStatement(query);
			pt.setString(1, testid);
			pt.setString(2, studentid);
			rs=pt.executeQuery();
			rs.next();



			schoolid=rs.getString(1);
			System.out.println(schoolid);
			String query1="insert into awardstudent values(?,?,?,?,?);";
			Statement stmt=con.createStatement();
			query="insert into awardstudent values('"+testid+"','"+studentid+"','"+awardid+"','"+schoolid+"','"+awardname+"')";
			System.out.println(query);
			stmt.executeQuery(query);
			con.commit();



		}
		catch(Exception e){

		}
		finally
		{
			try {
				con.close();
				pt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return 0;

	}



	public static int allocateSchools(String id[]) {
		String schoolid=id[0];
		System.out.println("in DAO school award");
		System.out.println(schoolid);
		String testid="",awardid="",awardname="";

		try{ 
			con=DBConnection.getConnection();
			System.out.println("in try");
			String query1="select count(schoolid) from awardschool where schoolid=?";
			pt=con.prepareStatement(query1);
			pt.setString(1, schoolid);
			rs=pt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0){

				String query="select * from awardstudent where schoolid=?";

				pt=con.prepareStatement(query);
				pt.setString(1, schoolid);

				rs=pt.executeQuery();
				while(rs.next()){
					testid=rs.getString("testid");
					awardid=rs.getString("awardid");
					schoolid=rs.getString("schoolid");
					awardname=rs.getString("awardname");
					System.out.println(schoolid);
				}		
				Statement stmt=con.createStatement();
				query="insert into awardschool values('"+testid+"','"+awardid+"','"+schoolid+"','"+awardname+"')";
				System.out.println(query);
				stmt.executeQuery(query);
				con.commit();
				i=1;
			}
			else{
				System.out.println("in else");
				i=2;
			}

		}
		catch(Exception e){

		}
		finally
		{
			try {
				con.close();
				pt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}System.out.println(i);
		return i;


	}



	public static ArrayList<Bean> viewStudents() throws SQLException {
		ArrayList<Bean> list=new ArrayList<Bean>();
		Bean bean=null;
		try{ 
			String query="select count(schoolid),schoolid from awardstudent group by schoolid ";
			con=DBConnection.getConnection();
			pt=con.prepareStatement(query);
			System.out.println(query);


			rs=pt.executeQuery();

			while(rs.next())
			{
				if(rs.getInt(1)>=3){
					bean=new Bean();
					String a=rs.getString(2);
					bean.setSchoolid(a);
					list.add(bean);
				}

			}

		}
		catch(Exception e){

		}
		finally
		{
			con.close();
			//	pt.close();
		}
		return list;
	}


	public static void deleteAward(Bean bean) {
		try{ 
			String query1="select * from awardstudent where studentid='"+bean.getStudentid()+"'";
			String query="delete from awardstudent where studentid='"+bean.getStudentid()+"'";
			con=DBConnection.getConnection();
			pt=con.prepareStatement(query1);
			rs=pt.executeQuery();

			if(rs.next())
			{
				i=1;
				String name=rs.getString(1);
			}
			else
			{
				i=2;
				System.out.println("error");
			}


			pt=con.prepareStatement(query);
			rs=pt.executeQuery();

			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
				//pt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	public static String getNextId(String tablename){
		int id=0;
		String newId=null,role=null;
		try{
			con=DBConnection.getConnection();
			Statement stmt=con.createStatement();
			//generate unique id
			System.out.println("New Id technique implemented");
			ResultSet rs=stmt.executeQuery("select max("+tablename+"id) from "+tablename);
			rs.next();
			if(rs.getString(1)==null){
				if(tablename.equals("award"))
					role="aw";

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(newId);
		return newId;

	}


	public static Bean create(Bean beanobj) throws Exception {

		try {
			log.info("Before inserting into table award");
			con = DBConnection.getConnection();
			String query = "insert into award values(?,?,?,?,?,?,?,?)";
			pt = con.prepareStatement(query);
			pt.setString(1, getNextId("award"));
			pt.setString(2, beanobj.getAwardtype());
			pt.setString(3, beanobj.getAwardname());
			pt.setString(4, beanobj.getAwardamount());
			pt.setString(5, beanobj.getAwardreason());	
			pt.setString(6, beanobj.getAwarddetails());
			pt.setString(7, beanobj.getLow());
			pt.setString(8, beanobj.getHigh());
			pt.executeUpdate();
			//con.commit();
			log.info("After inserting into table award");
		} catch (Exception e) {
			log.error("Exception", e);
			e.printStackTrace();
		} finally {
			con.close();
			pt.close();

		}
		return beanobj;
	}
	public static int deleteDetails(Bean beanobj)throws Exception{


		try {
			con = DBConnection.getConnection();
			String query = "delete from award where awardid='"+beanobj.getAwardid()+"'";

			st = con.createStatement();
			i=st.executeUpdate(query);




			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			st.close();

		}
		return i;
	}
	public static ArrayList<Bean> viewAward()throws Exception  {

		ArrayList<Bean> list= new ArrayList<Bean>();
		try
		{


			String query="select * from award ";
			System.out.println(query);
			con=DBConnection.getConnection();

			pt=con.prepareStatement(query);
			rs=pt.executeQuery();
			while(rs.next())
			{

				Bean bean=new Bean();	
				bean.setAwardid(rs.getString(1));
				bean.setAwardtype(rs.getString(2));
				bean.setAwardname(rs.getString(3));
				bean.setAwardamount(rs.getString(4));
				bean.setAwardreason(rs.getString(5));
				bean.setAwarddetails(rs.getString(6));

				list.add(bean);
			}		
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			con.close();
			pt.close();
		}
		return list;

	}
	public static 	Bean update(Bean beanobj)throws Exception
	{
		try
		{
			System.out.println("in dao update");
			System.out.println(beanobj.display());
			con = DBConnection.getConnection();

			String query="update award  set type='"+beanobj.getAwardtype()+
			"', awardname='"+beanobj.getAwardname()+
			"', awardamt='"+beanobj.getAwardamount()+
			"', reasonforaward='"+beanobj.getAwardreason()+
			"', awarddetails='"+beanobj.getAwarddetails()+
			"' where  awardid='"+beanobj.getAwardid()+"'";

			System.out.println(query);
			System.out.println("into DAO view");
			pt=con.prepareStatement(query);
			System.out.println(query);
			int a=pt.executeUpdate();
			System.out.println(a);
			con.commit();

		} 
		catch (Exception e) 
		{

			e.printStackTrace();
		}
		finally {
			con.close();
			pt.close();

		}return beanobj;
	}


	public static ArrayList<Bean> viewone(Bean vw) throws Exception
	{
		ArrayList<Bean> list= new ArrayList<Bean>();
		try
		{
			System.out.println("into dao view");
			String query="select * from award where awardid=?";
			con=DBConnection.getConnection();
			pt=con.prepareStatement(query);
			pt.setString(1, vw.getAwardid());
			rs=pt.executeQuery();
			if(rs.next()==false){
				i=0;
			}
			else{
				i=1;
				vw.setAwardid(rs.getString(1));
				vw.setAwardtype(rs.getString(2));
				vw.setAwardname(rs.getString(3));
				vw.setAwardamount(rs.getString(4));
				vw.setAwardreason(rs.getString(5));
				vw.setAwarddetails(rs.getString(6));
				System.out.println(rs.getString(3));
				System.out.println("out of dao view");
				list.add(vw);
			}
			System.out.println(i);
		}



		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return list;
	}
	public static ArrayList<Bean> generateStudents(Bean beanobj) throws NullPointerException, SQLException {
		ArrayList<Bean> r=new ArrayList<Bean>();
		Bean rb=null;
		try {

			con = DBConnection.getConnection();
			String query = "select * from teststudent where testid='"+beanobj.getTestid()+"'";
			st = con.createStatement();
			rs=st.executeQuery(query);

			while(rs.next()){
				i=1;
				System.out.println("jeju");
				rb = new Bean();
				rb.setStudentid(rs.getString("studentid"));
				System.out.println(rb.getStudentid());
				r.add(rb);
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return r;	
	}




	public static int generateResult(Bean beanobj) throws NullPointerException, SQLException {

		int total=0;
		try {

			con = DBConnection.getConnection();
			//String query1 = "select question.answer,testresult.capturedanswer from question,testresult,test where test.testid='"+beanobj.getTestid()+"'and test.questionid=question.questionid and testresult.questionid=question.questionid and testresult.studentid='"+beanobj.getStudentid()+"'";
			String query1="select question.answer,testresult.capturedanswer from question,testresult where testresult.testid='"+beanobj.getTestid()+"' and testresult.studentid='"+beanobj.getStudentid()+"' and testresult.questionid=question.questionid";
			String query2 = "insert into result1(testid,studentid,score,schoolid,average) values(?,?,?,?,?)";

			st = con.createStatement();
			rs=st.executeQuery(query1);

			/*if(rs.next()){
				i=1;
			}*/
			while(rs.next()){
				i=1;
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				if(rs.getString(1).equalsIgnoreCase(rs.getString(2)))
				{
					System.out.println("answer is correct give 1 mark");
					total++;

				}
				else
				{
					System.out.println("answer is not correct give 0 mark");
					//total=total+0;
				}

			}
			System.out.println(total);

			pt = con.prepareStatement(query2);
			int avg1=(total*100);
			int avg=avg1/10;
			System.out.println(avg);

			pt.setString(1, beanobj.getTestid());
			pt.setString(2, beanobj.getStudentid());
			pt.setInt(3,total);
			pt.setString(4,beanobj.getSchoolid());
			pt.setFloat(5,avg);

			pt.executeUpdate();
			con.commit();


		} catch (Exception e) {
			i=10;
			return i;

		} finally {
			con.close();



		}


		return total;	

	}


	public static ArrayList<Bean> viewResult()throws Exception  {

		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean=null;
		try
		{
			i=0;

			String query="select * from result1 ";
			con=DBConnection.getConnection();

			pt=con.prepareStatement(query);
			rs=pt.executeQuery();


			while(rs.next())
			{
				i=1;

				bean=new Bean();	

				bean.setTestid(rs.getString(1));
				bean.setStudentid(rs.getString(2));
				bean.setScore(rs.getString(3));
				bean.setSchoolid(rs.getString(4));
				bean.setAverage(rs.getString(5));
				System.out.println(bean.getScore());
				list.add(bean);
			}


		}

		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			con.close();
		}
		return list;
	}
	public static Bean deleteDetail(Bean beanobj)throws Exception{


		try {
			con = DBConnection.getConnection();
			String query = "delete from result1 where testid='"+beanobj.getTestid()+"' and studentid='"+beanobj.getStudentid()+"'";
			System.out.println(query);	
			st = con.createStatement();
			i=st.executeUpdate(query);

			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			st.close();

		}
		return beanobj;
	}

	public static ArrayList<Bean> viewoneResult(Bean beanobj) throws Exception
	{
		ArrayList<Bean> list= new ArrayList<Bean>();
		Bean bean=null;
		try
		{
			System.out.println("into dao view");
			String query = "select * from result1 where testid='"+beanobj.getTestid()+"' and studentid='"+beanobj.getStudentid()+"' ";
			System.out.println(query);
			con=DBConnection.getConnection();
			st=con.createStatement();
			rs=st.executeQuery(query);
			String s2=beanobj.getStudentid();

			//System.out.println(rs.getString(1));
			if(rs.next()){

				System.out.println("in");


				i=1;
				System.out.println("inside rs of viewoneresult");	
				bean=new Bean();	

				bean.setTestid(rs.getString(1));
				bean.setStudentid(rs.getString(2));
				bean.setScore(rs.getString(3));
				bean.setAverage(rs.getString(5));
				//System.out.println(bean.getScore());
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(5));
				list.add(bean);
			}

			else
			{
				i=2;
				System.out.println("inside unsuccess");
			}
		}

		catch(Exception e)
		{
		}
		finally
		{
			con.close();
		}
		return list;
	}	


	public static Bean storeUpdate(Bean beanobj) throws Exception
	{

		try
		{
			con=DBConnection.getConnection();
			System.out.println(beanobj.getScore());
			String query = "update result1 set score='"+beanobj.getScore()+"',average='"+beanobj.getAverage()+"' where testid='"+beanobj.getTestid()+"' and studentid='"+beanobj.getStudentid()+"'";
			//String query2="select score from result1 where testid='"+beanobj.getTestid()+"' and studentid='"+beanobj.getStudentid()+"'";
			System.out.println(query);
			System.out.println(beanobj.getScore());
			pt=con.prepareStatement(query);
			pt.executeUpdate(); 


			st=con.createStatement();

			con.commit();
		}
		catch(Exception e)
		{
		}
		finally
		{
			con.close();
		}
		return beanobj;
	}	
	public static ArrayList<Bean> viewallocatedaward() throws Exception {
		ArrayList<Bean> list=new ArrayList<Bean>();
		try{
			System.out.println("in viewallocatedaward dao");
			con=DBConnection.getConnection();
			String query="select * from awardstudent";
			pt=con.prepareStatement(query);
			rs=pt.executeQuery();
			while(rs.next()){
				Bean obj=new Bean();
				obj.setTestid(rs.getString(1));
				obj.setStudentid(rs.getString(2));
				obj.setAwardid(rs.getString(3));
				obj.setSchoolid(rs.getString(4));
				obj.setAwardname(rs.getString(5));
				list.add(obj);
				System.out.println(obj.getTestid());

			}

			con.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{	rs.close();
		pt.close();
		con.close();
		}

		return list;
	}

	public static ArrayList<Bean> viewallocateschoolaward() throws SQLException {
		System.out.println("in viewallocatedschoolaward DAO");
		ArrayList<Bean> list=new ArrayList<Bean>();
		try{
			con=DBConnection.getConnection();
			String query="select * from awardschool";
			pt=con.prepareStatement(query);
			rs=pt.executeQuery();
			while(rs.next()){
				Bean obj=new Bean();
				obj.setTestid(rs.getString(1));
				obj.setAwardid(rs.getString(2));
				obj.setSchoolid(rs.getString(3));
				obj.setAwardname(rs.getString(4));
				list.add(obj);
			}
			con.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{	rs.close();
		pt.close();
		con.close();
		}

		return list;
	}


	public static void deleteschoolaward(Bean beanobj)throws Exception{


		try{ 
			String query1="select * from awardschool where schoolid='"+beanobj.getSchoolid()+"'";
			String query = "delete from awardschool where schoolid='"+beanobj.getSchoolid()+"'";
			con=DBConnection.getConnection();
			pt=con.prepareStatement(query1);
			rs=pt.executeQuery();

			if(rs.next())
			{
				i=1;
				String name=rs.getString(1);
			}
			else
			{
				i=2;
				System.out.println("error");
			}


			pt=con.prepareStatement(query);
			rs=pt.executeQuery();

			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
				//pt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	public static ArrayList<Bean> viewupdate(Bean obj) throws Exception {
		ArrayList<Bean> list=new ArrayList<Bean>();
		try{
			System.out.println("IN DAO");
			con=DBConnection.getConnection();
			String query="select * from awardstudent where awardid='"+obj.getAwardid()+"'";
			pt=con.prepareStatement(query);
			rs=pt.executeQuery();
			if(rs.next()){
				i=1;
				obj.setTestid(rs.getString(1));
				obj.setStudentid(rs.getString(2));
				obj.setAwardid(rs.getString(3));
				obj.setSchoolid(rs.getString(4));
				obj.setAwardname(rs.getString(5));
				list.add(obj);
			}
			else{
				i=0;
			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{	rs.close();
		pt.close();
		con.close();
		}

		return list;
	}

	public static void updatestudentaward(Bean a) throws SQLException {
		try{
			System.out.println("IN DAO");
			con=DBConnection.getConnection();
			String query="update awardstudent set testid='"+a.getTestid()+"',studentid='"+a.getStudentid()+"',schoolid='"+a.getSchoolid()+"',awardname='"+a.getAwardname()+"' where awardid='"+a.getAwardid()+"'";
			pt=con.prepareStatement(query);
			pt.executeUpdate(); 
			con.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{	
			pt.close();
			con.close();
		}

	}

	public static ArrayList<Bean> viewschoolupdate(Bean obj) throws SQLException {
		ArrayList<Bean> list=new ArrayList<Bean>();
		try{
			System.out.println("IN DAO");
			con=DBConnection.getConnection();
			String query="select * from awardschool where awardid='"+obj.getAwardid()+"'";
			pt=con.prepareStatement(query);
			rs=pt.executeQuery();
			if(rs.next()){
				i=1;
				obj.setTestid(rs.getString(1));
				obj.setAwardid(rs.getString(2));
				obj.setSchoolid(rs.getString(3));
				obj.setAwardname(rs.getString(4));
				list.add(obj);
			}
			else{
				i=0;
			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{	rs.close();
		pt.close();
		con.close();
		}

		return list;

	}

	public static void updateschoolaward(Bean a) throws SQLException {
		try{
			System.out.println("IN DAO");
			con=DBConnection.getConnection();
			String query="update awardschool set testid='"+a.getTestid()+"',schoolid='"+a.getSchoolid()+"',awardname='"+a.getAwardname()+"' where awardid='"+a.getAwardid()+"'";
			pt=con.prepareStatement(query);
			pt.executeUpdate(); 
			con.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{	
			pt.close();
			con.close();
		}

	}
}