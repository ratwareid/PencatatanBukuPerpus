package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLUtil {
	
	// Menyiapkan paramter JDBC untuk koneksi ke datbase
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //Driver default untuk Java ke Mysql
    static final String DB_URL = "jdbc:mysql://localhost/perpustakaan"; // localhost = hostname , perpustakaan = nama database
    static final String USER = "root"; //username
    static final String PASS = "JKFsjhdsuiahwdj2132"; //password

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn; 
    static Statement stmt;
    static ResultSet rs;
    
    SQLUtil(){ // Ini adalah konstruktor , fungsi yang akan dijalankan pertamakali saat class dipanggil
    	//Try catch merupakan fungsi untuk menangkap pesan error,
		// Ketika terdapat error yang terjadi didalam try
		// Maka akan langsung masuk ke catch
		// di dalam fungsi catch , dia akan menjalankan printstacktrace = mencetak pesan error ke log / console
    	try {
    		Class.forName(JDBC_DRIVER); // Mengeset driver JDBC_DRIVER sebagai driver untuk class
            conn = DriverManager.getConnection(DB_URL, USER, PASS); // Fungsi untuk mengeset url , user dan password ke dalam 
            stmt = conn.createStatement(); // Menjalankan statement connection
            
    	}catch (Exception e){
    		e.printStackTrace(); //jika gagal, print pesan error 
    	}
    
    }
    
    public ResultSet getData(String tableName){ // Method getdata dengan parameter namatable
    	//Method ini akan mengebalikan nilai Resultset sebagai data yang didapat dari query
    	//Parameter tableName digunakan sebagai acuan query ke table sesuai parameter
    
        try {
			rs = stmt.executeQuery("select * from "+tableName+""); // menjalankan statment excecutequery dengan table sesuai parameter

		} catch (SQLException e) {
			e.printStackTrace(); // Jika gagal tampilkan pesan error
		}
        
        return rs; // kembalikan nilai Resultset
    }
   
	 public static int execute(String SQL) { //method excecute untuk excecute query khusus selain getData
		 // Method ini mengembalikan nilai berupa int dimana resultnya adalah status dari pengeksekusian query
		 // jika berhasil makan akan return 1 , jika gagal maka akan return 0
	        int status = 0; 
	        try {
	            status = stmt.executeUpdate(SQL); //Jalankan query sesuai sql yang diterima oleh parameter 
	        } catch (SQLException ex) {
	            Logger.getLogger(SQLUtil.class.getName()).log(Level.SEVERE, null, ex);// Jika gagal , print pesan error
	        }
	        return status; // return nilai status (0 / 1)
	}
}
