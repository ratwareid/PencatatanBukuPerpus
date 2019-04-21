package Connection;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrameApp {

	
	//Deklarasi variable ke private 
	//Penggunaan private akan membuat var tersebut hanya dapat diakses di kelas ini
	private JFrame frame;
	private JTextField TFnim;
	private JTextField TFnama;
	private JTextField TFtglpinjam;
	private javax.swing.JTable tblData;
	private javax.swing.JScrollPane jScrollPane1;
    private ResultSet rs;
    private JLabel lblNim;
    private JLabel lblNamaMahasiswa;
    private JLabel lblTanggalPinjam;
    private JTextField TFtglkembali;
    private JLabel lblTanggalKembali;
    private JLabel lblNamaBuku;
    private JTextField TFnamabuku;
    private JLabel lblPengarang;
    private JTextField TFpengarang;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnInsertNew;
    private SQLUtil sql;
    private JLabel lblCopyright;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { //Main method akan dijalankan setelah inisialisasi
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Try catch merupakan fungsi untuk menangkap pesan error,
        		// Ketika terdapat error yang terjadi didalam try
        		// Maka akan langsung masuk ke catch
        		// di dalam fungsi catch , dia akan menjalankan printstacktrace = mencetak pesan error ke log / console
				try {
					FrameApp window = new FrameApp(); // Deklarasi kelas FrameApp baru
					window.frame.setVisible(true); // Buat frameapp menjadi visible
				} catch (Exception e) {
					e.printStackTrace(); //Jika gagal cetak pesan error
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameApp() { // Ini adalah konstruktor di java
		// Konstruktor akan dijalankan pertama kali sebelum main method dijalankan
		// Ciri konstruktor ditandai dengan nama method yang sama dengan nama class yakni FrameApp tanpa deklarasi return nilai
		// seperti void , string , boolean dll.
		initialize(); // Kontruktor ini akan menjalankan method initialize()
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { // Method initialize disini berfungsi untuk memanggil semua komponen java yang diperlukan
		
		frame = new JFrame(); //Deklarasi frame baru
		frame.setBounds(100, 100, 600, 400); // Set x = 100 y = 100 width= 600 height = 400
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set on close : exit program
		frame.getContentPane().setLayout(null); // set layout = kosong, artinya tidak menggunakan layout. 
												//Dengan pendeklarasian ini maka kita harus mengeset posisi setiap komponen di frame
												// secara manual dengan fungsi setBounds(x,y,width,height)
		
		JLabel title = new JLabel("Aplikasi Pencatatan Peminjaman Buku Perpustakaan"); //Deklarasi var title sebagai jLabel dengan text tersebut
		title.setFont(new Font("Sitka Subheading", Font.BOLD, 17)); // ganti font title dan berikan cetak tebal BOLD
		title.setBounds(81, 11, 434, 28); // atur posisi dan ukuran title pada frame  setBounds(x,y,width,height)
		frame.getContentPane().add(title); // masukkan title ke dalam frame
		
		
		sql = new SQLUtil(); //Deklarasi var sql sebagai class SQLUtil baru
		
		tblData = new javax.swing.JTable(); //deklarasi tblData sebagai JTable
		tblData.addMouseListener(new java.awt.event.MouseAdapter() { // Buat fungsi tblData untuk membaca fungsi dari mouse
            public void mouseClicked(java.awt.event.MouseEvent evt) { //ketika mouse mengklik ke tblData
                tblDataMouseClicked(evt); //Maka akan menjalankan method tblDataMouseClicked
            }
        });
        
        jScrollPane1 = new javax.swing.JScrollPane(); //Deklarasi jScrollPane1 sebagai JScrollPane
        jScrollPane1.setViewportView(tblData); // Memasukkan tblData sebagai view dalam scrollpane
        jScrollPane1.setBounds(41, 202, 481, 132); //atur posisi dan ukuran jScrollPane1 pada frame  setBounds(x,y,width,height) 
        frame.getContentPane().add(jScrollPane1); // masukkan jScrollPane1 ke dalam frame
		
		TFnim = new JTextField(); //Deklarasi TFnim sebagai JTextField
		TFnim.setBounds(41, 75, 137, 20); //atur posisi dan ukuran TFnim pada frame  setBounds(x,y,width,height) 
		frame.getContentPane().add(TFnim); // masukkan TFnim ke dalam frame
		
		TFnama = new JTextField(); //Deklarasi TFnama sebagai JTextField
		TFnama.setBounds(41, 134, 137, 20); //atur posisi dan ukuran TFnama pada frame  setBounds(x,y,width,height) 
		frame.getContentPane().add(TFnama); // masukkan TFnama ke dalam frame
		
		
		TFtglpinjam = new JTextField(); //Deklarasi TFtglpinjam sebagai JTextField
		TFtglpinjam.setBounds(208, 75, 96, 20); //atur posisi dan ukuran TFnama pada frame  setBounds(x,y,width,height) 
		frame.getContentPane().add(TFtglpinjam); // masukkan TFtglpinjam ke dalam frame
		
        lblNim = new JLabel("Nim Mahasiswa"); //Deklarasi lblNim sebagai JLabel
        lblNim.setBounds(41, 50, 96, 14); //atur posisi dan ukuran lblNim pada frame  setBounds(x,y,width,height) 
        frame.getContentPane().add(lblNim); // masukkan lblNim ke dalam frame
        
        lblNamaMahasiswa = new JLabel("Nama Mahasiswa"); //Deklarasi lblNamaMahasiswa sebagai JLabel
        lblNamaMahasiswa.setBounds(41, 109, 109, 14); //atur posisi dan ukuran lblNamaMahasiswa pada frame  setBounds(x,y,width,height) 
        frame.getContentPane().add(lblNamaMahasiswa); // masukkan lblNamaMahasiswa ke dalam frame
        
        lblTanggalPinjam = new JLabel("Tanggal Pinjam");  //Deklarasi lblTanggalPinjam sebagai JLabel
        lblTanggalPinjam.setBounds(208, 50, 96, 14);//atur posisi dan ukuran lblTanggalPinjam pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(lblTanggalPinjam); // masukkan lblTanggalPinjam ke dalam frame
        
        TFtglkembali = new JTextField(); //Deklarasi TFtglkembali sebagai JTextField
        TFtglkembali.setBounds(208, 134, 96, 20); //atur posisi dan ukuran TFtglkembali pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(TFtglkembali); // masukkan TFtglkembali ke dalam frame
        
        lblTanggalKembali = new JLabel("Tanggal Kembali"); //Deklarasi lblTanggalKembali sebagai JLabel
        lblTanggalKembali.setBounds(208, 106, 102, 17); //atur posisi dan ukuran lblTanggalKembali pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(lblTanggalKembali); // masukkan lblTanggalKembali ke dalam frame
        
        lblNamaBuku = new JLabel("Nama Buku"); //Deklarasi lblNamaBuku sebagai JLabel
        lblNamaBuku.setBounds(349, 50, 89, 14); //atur posisi dan ukuran lblNamaBuku pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(lblNamaBuku); // masukkan lblNamaBuku ke dalam frame
        
        TFnamabuku = new JTextField(); //Deklarasi TFnamabuku sebagai JTextField
        TFnamabuku.setBounds(349, 75, 173, 20); //atur posisi dan ukuran TFnamabuku pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(TFnamabuku); // masukkan TFnamabuku ke dalam frame
        
        
        lblPengarang = new JLabel("Pengarang"); //Deklarasi lblPengarang sebagai JLabel
        lblPengarang.setBounds(349, 106, 89, 14); //atur posisi dan ukuran lblPengarang pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(lblPengarang); // masukkan lblPengarang ke dalam frame
        
        TFpengarang = new JTextField(); //Deklarasi TFpengarang sebagai JTextField
        TFpengarang.setBounds(349, 134, 173, 20); //atur posisi dan ukuran TFpengarang pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(TFpengarang);  // masukkan TFpengarang ke dalam frame
       
        
        JButton btnLoadList = new JButton("Load Data"); //Deklarasi btnLoadList sebagai JButton
        btnLoadList.addActionListener(new ActionListener() { // Menambahkan aksi ketika button di klik
        	public void actionPerformed(ActionEvent e) {
        		//Try catch merupakan fungsi untuk menangkap pesan error,
        		// Ketika terdapat error yang terjadi didalam try
        		// Maka akan langsung masuk ke catch
        		// di dalam fungsi catch , dia akan menjalankan printstacktrace = mencetak pesan error ke log / console
        		
        		try {
        			loadAllData(); // Menjalankan method loadAllData
				} catch (Exception e1) {
					e1.printStackTrace(); // mencetak pesan error ke log / console
				}
        	}
        });
        btnLoadList.setBounds(41, 168, 96, 23); //atur posisi dan ukuran btnLoadList pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(btnLoadList); // masukkan btnLoadList ke dalam frame
        
        btnEdit = new JButton("Edit Selected"); //Deklarasi btnEdit sebagai JButton
        btnEdit.addActionListener(new ActionListener() { // Menambahkan aksi ketika button di klik
        	public void actionPerformed(ActionEvent evt) {
        		try { 
					btnEditActionPerformed(evt); // Menjalankan method btnEditActionPerformed
				} catch (Exception e) {
					e.printStackTrace();// mencetak pesan error ke log / console
				}
        	}
        });
        btnEdit.setBounds(147, 168, 119, 23); //atur posisi dan ukuran btnEdit pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(btnEdit); // masukkan btnEdit ke dalam frame
        
        btnDelete = new JButton("Delete Selected"); //Deklarasi btnDelete sebagai JButton
        btnDelete.addActionListener(new ActionListener() { // Menambahkan aksi ketika button di klik
        	public void actionPerformed(ActionEvent evt) {
        		try {
					btnDeleteActionPerformed(evt); // Menjalankan method btnDeleteActionPerformed
				} catch (Exception e) {
				
					e.printStackTrace(); // mencetak pesan error ke log / console
				}
        	}
        });
        btnDelete.setBounds(276, 168, 130, 23); //atur posisi dan ukuran btnDelete pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(btnDelete);  // masukkan btnDelete ke dalam frame
        
        btnInsertNew = new JButton("Insert New"); //Deklarasi btnInsertNew sebagai JButton
        btnInsertNew.addActionListener(new ActionListener() {// Menambahkan aksi ketika button di klik
        	public void actionPerformed(ActionEvent evt) {
        		try {
        			btnAddActionPerformed(evt);// Menjalankan method btnAddActionPerformed
				} catch (Exception e) {
					 
					e.printStackTrace();// mencetak pesan error ke log / console
				}
        	}
        });
        btnInsertNew.setBounds(416, 168, 106, 23); //atur posisi dan ukuran btnInsertNew pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(btnInsertNew);// masukkan btnInsertNew ke dalam frame
        
        lblCopyright = new JLabel("Originial Design by Kelompok 2 - PBO Mercubuana - Teknik Informatika"); // Membuat label lblCopyright
        lblCopyright.setFont(new Font("Monospaced", Font.ITALIC, 11)); //Atur jenis font , style dan ukuran font 11
        lblCopyright.setBounds(41, 336, 528, 14); //atur posisi dan ukuran lblCopyright pada frame  setBounds(x,y,width,height)
        frame.getContentPane().add(lblCopyright); // masukkan lblCopyright ke dalam frame
	}
	
	public void loadAllData() throws Exception{ // method loadAlldata
		
		String kolom[] = {"ID","NIM","Nama MHS","Judul Buku","Pengarang","Tgl Pinjam","Tgl Kembali"}; //Array kolom
        DefaultTableModel dtm = new DefaultTableModel(null, kolom); //Deklarasi DTM dengan object kosong dan kolom kolom[]
        rs = sql.getData("peminjaman"); // isi nilai Resultset rs sebagai data dari sql.getData dengan parameter peminjaman
        								// Didalam fungsi sql.getData sudah ada query untuk select * data dengan nama table
        								// sesuai dengan yang dikirim oleh parameter , dimana nama tablenya disini peminjaman
        
        try {
            while(rs.next()) { // Looping rs
            	// rs.getString akan berurutan sesuai urutan dari database , disini urutanya id ,nim,nama,buku,pengarang,tglpinjam,tglkembali
                String id = rs.getString(1);  // Mengisi nilai id dengan rs urutan ke 1
                String nim = rs.getString(2); // Mengisi nilai id dengan rs urutan ke 2
                String nama = rs.getString(3); // Mengisi nilai id dengan rs urutan ke 3
                String buku = rs.getString(4); // Mengisi nilai id dengan rs urutan ke 4
                String pengarang = rs.getString(5); // Mengisi nilai id dengan rs urutan ke 5
                String tglPinjam = rs.getString(6); // Mengisi nilai id dengan rs urutan ke 6
                String tglKembali = rs.getString(7); // Mengisi nilai id dengan rs urutan ke 7
                String data[] = {id,nim,nama,buku,pengarang,tglPinjam,tglKembali};  // Mengisi array data dengan id ,nim,nama,buku,pengarang,tglpinjam,tglkembali
                dtm.addRow(data); // masukkan array kedalam object Datatablemodel 
                //Lanjutkan looping
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrameApp.class.getName()).log(Level.SEVERE, null, ex); // Tangkap pesan error dan print ke log
        }
        rs.close(); // tutul ResultSet
        tblData.setModel(dtm); //isi tblData dengan datatablemodel
	}
	
	private void tblDataMouseClicked(java.awt.event.MouseEvent evt){ //Method tblDataMouseClicked
       
        int baris = tblData.getSelectedRow(); //Mengambil baris data yang diklik dan simpan ke var baris
        if (baris != -1) { // Jika baris != -1 , -1 artinya belom ada baris yang di klik , karena urutannya dimulai dari 0
        	
        	
        	if (tblData.getValueAt(baris, 1) != null) { //jika data baris dipilih dan kolom 1 (id) isinya tidak null maka
        		TFnim.setText(tblData.getValueAt(baris, 1).toString());	 //set fild TFnim dengan data nim baris yg dipilih
        	}else {
        		TFnim.setText(""); //jika kosong maka set dengan string null / ""
        	}
           
            
            if (tblData.getValueAt(baris, 2) != null) {
            	TFnama.setText(tblData.getValueAt(baris, 2).toString());	
            }else {
            	TFnama.setText("");
            }
            
            
            if (tblData.getValueAt(baris, 3) != null) {
            	TFnamabuku.setText(tblData.getValueAt(baris, 3).toString());	
            }else {
            	TFnamabuku.setText("");
            }
            
            
            if (tblData.getValueAt(baris, 4) != null) {
            	TFpengarang.setText(tblData.getValueAt(baris, 4).toString());
            }else {
            	TFpengarang.setText("");
            }
            
            if (tblData.getValueAt(baris, 5) != null) {
            	TFtglpinjam.setText(tblData.getValueAt(baris, 5).toString());	
            }else {
            	TFtglpinjam.setText("");
            }
            
            if (tblData.getValueAt(baris, 6) != null) {
            	TFtglkembali.setText(tblData.getValueAt(baris, 6).toString());
            }else {
            	TFtglkembali.setText("");
            }
        }
    }
	
	private void btnEditActionPerformed(java.awt.event.ActionEvent evt) throws Exception{ //method btnEdit
        // equals merupakan fungsi untuk membandingkan 2 var dengan tipe sejenis
		// jika sama maka return true / 1 , jika tidak sama makan return false / 0
        
		if ("".equals(TFnim.getText()) || "".equals(TFnama.getText()) || 
                "".equals(TFnamabuku.getText()) || "".equals(TFtglpinjam.getText())) {
            JOptionPane.showMessageDialog(frame, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE); 
            //Jika TFnim atau TFnama atau TFnamabuku atau TFtglpinjam ada yang datanya kosong maka munculkan pesan error diatas 
        } else {
        	//Jika datanya lengkap maka
        	
        	int baris = tblData.getSelectedRow(); //ambil baris yang di pilih lalu simpan ke var baris
        	
        	if (baris != -1) { //nilai -1 artinya belum di klik atau belum di pilih baris , jika baris ada yg diklik maka :
        		
                String id = tblData.getValueAt(baris, 0).toString(); //Ambil data dari baris dipilih dan kolom 0 , lalu simpan ke id
                String nim = TFnim.getText().toString(); //Ambil data dari baris dipilih dan kolom 1 , lalu simpan ke nim
                String nama = TFnama.getText().toString(); //Ambil data dari baris dipilih dan kolom 2 , lalu simpan ke nama
                String namabuku = TFnamabuku.getText().toString(); // dan seterusnya
                String pengarang = TFpengarang.getText().toString();
                String tglpinjam = TFtglpinjam.getText().toString();
                String tglkembali = TFtglkembali.getText().toString();
                
                String SQLUpdate = "Update peminjaman set nim = '"+nim+"' , nama_mhs = '"+nama+"' , nama_buku = '"+namabuku+"'  "
                		+ ", pengarang = '"+pengarang+"' , tanggal_pinjam = '"+tglpinjam+"' , tanggal_kembali = '"+tglkembali+"' "
                				+ " where id = '"+id+"'"; //Ini query sql
                int status = sql.execute(SQLUpdate); // Jalankan fungsi sql.excecute dengan parameter query sql diatas
                									//sql.excecute akan mereturn nilai balikan
                									// Jika nilai balikkannya 1 berarti query berhasil , jika 0 query gagal
                if (status == 1) {
                    JOptionPane.showMessageDialog(frame, "Data berhasil diupdate", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Data gagal diupdate", "Sukses", JOptionPane.WARNING_MESSAGE);
                }
                clearField(); //Jalankan method clearField untuk menghapus field 
                loadAllData(); //Jalankan method loadAlldata untuk merefresh data kembali
            } else { // Jika data yang mau di edit tidak dipilih , maka tampilkan error berikut
                JOptionPane.showMessageDialog(frame, "Pilih Baris Data Terlebih dahulu", "Error", JOptionPane.WARNING_MESSAGE);
            } 
        }
    }
	
	
	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception{ //method button delete
        // TODO add your handling code here:
        int baris = tblData.getSelectedRow();//ambil baris yang di pilih lalu simpan ke var baris
        if (baris != -1) { //nilai -1 artinya belum di klik atau belum di pilih baris , jika baris ada yg diklik maka :
            String id = tblData.getValueAt(baris, 0).toString(); // Ambil id dari data yg diselect simpan ke var id
            String SQL = "DELETE FROM peminjaman WHERE id='"+id+"'"; // ini sql
            int status = sql.execute(SQL);
            if (status==1) {
                JOptionPane.showMessageDialog(frame, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Data gagal dihapus", "Gagal", JOptionPane.WARNING_MESSAGE);
            }
            clearField();//Jalankan method clearField untuk menghapus field 
            loadAllData();//Jalankan method loadAlldata untuk merefresh data kembali
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih Baris Data Terlebih dahulu", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
	
	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) throws Exception{
        
		if ("".equals(TFnim.getText()) || "".equals(TFnama.getText()) || 
                "".equals(TFnamabuku.getText()) || "".equals(TFtglpinjam.getText())) {
            JOptionPane.showMessageDialog(frame, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
          //Jika TFnim atau TFnama atau TFnamabuku atau TFtglpinjam ada yang datanya kosong maka munculkan pesan error diatas
        } else {
            String nim = TFnim.getText().toString(); //Ambil data text dari textfile TFnim simpan ke var nim
            String nama = TFnama.getText().toString(); //Ambil data text dari textfile TFnama simpan ke var nama
            String namabuku = TFnamabuku.getText().toString(); //Ambil data text dari textfile TFnamabuku simpan ke var namabuku
            String pengarang = TFpengarang.getText().toString(); // dan seterusnya
            String tglpinjam = TFtglpinjam.getText().toString();
            String tglkembali = TFtglkembali.getText().toString();
            
            String SQL = "INSERT INTO peminjaman (nim,nama_mhs,nama_buku,pengarang,tanggal_pinjam,tanggal_kembali) "
                    + "VALUES('"+nim+"','"+nama+"','"+namabuku+"',"
                    + "'"+pengarang+"','"+tglpinjam+"','"+tglkembali+"')"; // ini query 
          
            int status = sql.execute(SQL); // Jalankan fungsi sql.excecute
            if (status == 1) {
                JOptionPane.showMessageDialog(frame, "Data berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadAllData();//Jalankan method clearField untuk menghapus field 
                clearField();//Jalankan method loadAlldata untuk merefresh data kembali
            } else {
                JOptionPane.showMessageDialog(frame, "Data gagal ditambahkan", "Sukses", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
	
	public void clearField() throws Exception{ //Method clearField
		TFnim.setText(""); // mengubah text dari textfield TFnim menjadi string null atau string kosong
		TFnama.setText(""); // mengubah text dari textfield TFnama menjadi string null atau string kosong
		TFnamabuku.setText(""); // mengubah text dari textfield TFnamabuku menjadi string null atau string kosong
		TFpengarang.setText(""); // mengubah text dari textfield TFpengarang menjadi string null atau string kosong
		TFtglpinjam.setText(""); // mengubah text dari textfield TFtglpinjam menjadi string null atau string kosong
		TFtglkembali.setText(""); // mengubah text dari textfield TFtglkembali menjadi string null atau string kosong
	}
}
