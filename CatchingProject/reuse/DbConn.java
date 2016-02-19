package reuse;
import java.sql.*;
public class DbConn{
	//////////////DB IP ADDRESS/////////////////
	static String DbIPaddr="localhost";
	////////////////////////////////////////////
	static String DbUserName="root";
	static String DbPasswrd="root";
	static int DbPort=3306;
	///////////////////////////////////////////////
	static String DbName="catchdb";
	static String s;
	static int a;
	static Connection con = null;
	static ResultSet rs;


	public static void updatedetail(String sql)
	{
		  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://"+DbIPaddr+":"+DbPort+"/"+DbName,DbUserName,DbPasswrd);
		  try{
		  PreparedStatement pst = con.prepareStatement(sql);
		  pst.executeUpdate();
		  con.close();
		  }
		  catch (SQLException s){
		  System.out.println(s);
		  }
		  }
		  catch (Exception e){
		  e.printStackTrace();
		  }
	 }

	public static String selectdetail(String nodename,int cno)
	{
		  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://"+DbIPaddr+":"+DbPort+"/"+DbName,DbUserName,DbPasswrd);
		  try{
		  String sql = "select * from catchtab where NodeName='"+nodename+"'";
		  PreparedStatement pst = con.prepareStatement(sql);
		  ResultSet rs = pst.executeQuery();
		  if(rs.next()){
			  			s=rs.getString(cno);
						}
		  con.close();
		  }
		  catch (SQLException s1){
		  System.out.println(s1);
		  }
		  }
		  catch (Exception e){
		  e.printStackTrace();
		  }
		  return s;
	 }

	public static int selectint(String nodename,int cno)
	{
		  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://"+DbIPaddr+":"+DbPort+"/"+DbName,DbUserName,DbPasswrd);
		  try{
		  String sql = "select * from catchtab where NodeName='"+nodename+"'";
		  PreparedStatement pst = con.prepareStatement(sql);
		  ResultSet rs = pst.executeQuery();
		  if(rs.next()){
			  			a=rs.getInt(cno);
						}
		  con.close();
		  }
		  catch (SQLException s1){
		  System.out.println(s1);
		  }
		  }
		  catch (Exception e){
		  e.printStackTrace();
		  }
		  return a;
	 }


	public static ResultSet selectRs()
	{
		  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://"+DbIPaddr+":"+DbPort+"/"+DbName,DbUserName,DbPasswrd);
		  try{
		  String sql = "select * from catchtab";
		  PreparedStatement pst = con.prepareStatement(sql);
		  rs = pst.executeQuery();
		  //con.close();
		  }
		  catch (SQLException s1){
		  System.out.println(s1);
		  }
		  }
		  catch (Exception e){
		  e.printStackTrace();
		  }
		  return rs;
	 }

}





