package lojeprojekt;

import java.sql.*;
import com.mysql.jdbc.Connection;

public class lidhjaDB {
	private Connection lidhja;
	public lidhjaDB() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		this.lidhja=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lojezaradb","root","");
		}
		catch(Exception e) {
			System.out.println("not connected");
		}
	}
	// Shton te dhenat e lojtarit ne DB , kthen true nqs shtohet me sukses ose false nqs deshton shtimi
	public boolean shto(String emri,String mbiemri,String username,int mosha,int piket) {
	      try {	    	  
		    Statement stmt=lidhja.createStatement();
		    String sql="INSERT INTO lojtaret (username,emri,mbiemri,mosha,piket_max) VALUES";
		    sql+="('"+username+"','"+emri+"','"+mbiemri+"','"+mosha+"','"+piket+"')";
		    stmt.executeUpdate(sql);
		    stmt.close();
	    	return true;	
	      }
	      catch(Exception e) {
	    	  System.out.println("Ka gabime ne sql");
	    	  return false;
	      }
	}
	// Kthen false nqs uname (username qe shkruhet gjate regjistrimit) aktualisht eshte i perdorur ne rekordet DB
	public boolean kontrolloUsernameUnik(String uname) {
		  try {	     	  
			    Statement stmt=lidhja.createStatement();
			    String sql="SELECT * FROM lojtaret WHERE username='"+uname+"'";
			    ResultSet rs=stmt.executeQuery(sql);
			    if(rs.next()) {
			    	return false;
			    }
			    else
		    	return true;	
		      }
		      catch(Exception e) {
		    	  System.out.println("Ka gabimen ne sql "+e);
		    	  return false;
		      }
	}
	// logohu() kontrollon nqs uname(username qe shkruhet gjate logimit) ndodhet ne rekordet e DB
	// Nqs ndodhet kthen nje objekt te klases Lojtar me te gjitha atributet e tij ne perputhje me rekordin e tij ne DB
	// Nqs nuk ndodhet kthen null
	public Lojtar logohu(String uname) {
		  try {	     	  
			    Statement stmt=lidhja.createStatement();
			    String sql="SELECT * FROM lojtaret WHERE username='"+uname+"'";
			    ResultSet rs=stmt.executeQuery(sql);
			    if(rs.next()) {
			    	int mosha = rs.getInt("mosha");
			        String emri = rs.getString("emri");
			        String mbiemri = rs.getString("mbiemri");
			        int piket=rs.getInt("piket_max");
			        return new Lojtar(emri,mbiemri,uname,mosha,piket);
			    }
			    else
		    	return null;	
		      }
		      catch(Exception e) {
		    	  System.out.println("Ka gabimen ne sql "+e);
		    	  return null;
		      }
	}
	// Updaton piket maksimale per perdoruesin me username 'username' ne rast se eshte tejkaluar rekordi personal i pikeve
	public void updatePiketMax(String username,int piketReja) {
		try {	     	  
		    Statement stmt=lidhja.createStatement();
		    String sql="SELECT * FROM lojtaret WHERE username='"+username+"'";
		    ResultSet rs=stmt.executeQuery(sql);
		    rs.next();
		    int piket=rs.getInt("piket_max");   
		    if(piketReja>piket) {
		    	sql="UPDATE lojtaret SET piket_max='"+piketReja+"' WHERE username='"+username+"'";
			    stmt.executeUpdate(sql);
		    }
		     	
	      }
	      catch(Exception e) {
	    	  System.out.println("Ka gabimen ne sql ");
	      }
	}
}
