package it.polito.tdtd.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDb {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=alessandra";
         //se avessi sbagliato il nome del db, con il try mi salta fuori un' eccezione 
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
		Statement st= conn.createStatement();
				String sql= "SELECT DISTINCT shape " 
						+"FROM sighting "
						+"WHERE shape<>'' "
						+"ORDER BY  shape ASC";
				ResultSet res= st.executeQuery(sql);
				List <String>formeUfo= new ArrayList<String>();
				while(res.next()) {
					String forma= res.getString("shape");
					formeUfo.add(forma);
					
				}
				
				System.out.println(formeUfo);
				
				String sql2= "SELECT COUNT(*) AS cnt FROM sighting WHERE shape =?";
       String shapeScelta="circle";
       PreparedStatement st2= conn.prepareStatement(sql2);
       st2.setString(1, shapeScelta);
       ResultSet re2 =st2.executeQuery();
       re2.first();
       int count =re2.getInt("cnt");
       st2.close();
       
       System.out.println("UFO DI FORMA"+shapeScelta+"sono"+count);
				conn.close();
         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
