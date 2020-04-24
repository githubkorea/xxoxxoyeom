package gogogogogogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class GomPlayerDB {
	int length =0;
	static int pre;
	static String name;
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getConnection(int add) {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="javadb";
		String password="12345";
		String path = null;
		
		ArrayList<String> list = new ArrayList<String>();
		
		
		Connection con = null;
		ResultSet result =null;
		try {
			String sql = "select * from mp3path";
			con=DriverManager.getConnection(url);
			PreparedStatement pstmt = con.prepareStatement(sql);
			result=pstmt.executeQuery();
			
			System.out.println("DB연결성공");
			
			while(result.next()) {
				path = result.getString("path");
				list.add(path);
			}
			
			length = list.size();
			if (add>=length) {
				add=0;
			}else if(add<0) {
				add=length-1;
			}
			pre=add;
			name=list.get(add);
			return list.get(add);
			
		} catch (SQLException e) {
			System.out.println("연결실패");
			e.printStackTrace(); 
		}finally {
			try {
				if(con!=null) con.close();
				if(result!=null)result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}while(path!=null);
		return null;
	}

	public int Random() {
		Random random = new Random();
		int[] check = new int[length];
		int adder = 0;
		
		for(int r=0; r<length; r++) {
			check[r]=random.nextInt(length);

			for(int i=0;i<r;i++) {
				if(check[i]==check[r]) {
					check[r]=random.nextInt(length);
				}
			}
			adder=check[length-1];
		}
		return length;
	}		
	public int Pre() {
		return pre;
	}
		
	public String Name() {
		return name;
	}
}



