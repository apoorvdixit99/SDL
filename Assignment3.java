package Assign1;

import java.io.*;
import java.sql.*;
import java.util.concurrent.*;

public class Assignment3 extends Assignment1{
   
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/t31106db";
   static final String USERNAME = "root";
   static final String PASSWORD = "ubuntu123456";
   
   static Connection conn;
   static Statement stmt;
   static String sql;
   static ResultSet rs;
   
   static CallBackFunctionInterface cbf;
   
   Assignment3(){
	   super();
	   this.cbf = new CallBackFunctionClass();
	   conn = null;
	   stmt = null;
	   openConnections();
	   fetchRecordsFromDB();
   }

   static void closeConnections() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
   static void openConnections() {
	   try {
		Class.forName(JDBC_DRIVER);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   try {
		conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
		stmt = conn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   
   public static void main(String[] args) throws IOException {
	   Assignment3 a3 = new Assignment3();
	   //a3.insertRecordIntoDB();
	   a3.displayList();
   }
   
   public static void displayDB() throws SQLException {

	    System.out.println("Roll No\t\tName\t\t\tMarks");
	   	sql = "SELECT Roll_No, Name, Marks FROM Student";
	    rs = stmt.executeQuery(sql);
	   	while(rs.next()){
			
	         int r  = rs.getInt("Roll_No");
	         String n = rs.getString("Name");
	         int m  = rs.getInt("Marks");
	         
	         Student <String>student = new Student<String>(r,n,m);
	         addToDataStructures(student);
	
	         System.out.println(r+"\t\t"+n+"\t\t\t"+m);
	    }
	   
   }
      
   public static void insertRecordIntoDB() throws IOException{
	   	
	   	Student <String>arg = new Student<String>();
		System.out.println("Enter the Roll No :: ");
		int r = Integer.parseInt(br.readLine());
		System.out.println("Enter the Name :: ");
		String n = br.readLine();
		System.out.println("Enter the Marks :: ");
		int m = Integer.parseInt(br.readLine());
		arg.initialise(r, n, m);
	   	addToDataStructures(arg);
	   	sql = "INSERT INTO Student "+
	   			"VALUES ( "+r+", '"+n+"', "+m+" )";
	    try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
   }

   
   public static void insertRecordIntoDB(Student arg) {
	   	
	   	addToDataStructures(arg);
	   	sql = "INSERT INTO Student "+
	   			"VALUES ( "+arg.roll_no+", '"+arg.name+"', "+arg.marks+" )";
	    try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
   }

   //Asynchronous Callback functions
   //Called from insertRecordIntoDB()
   public static void addToDataStructures(Student student) {

	   if(cbf!=null) {
		   cbf.callBackList(list, student);
		   cbf.callBackMap(studentmap, student);
		   cbf.callBackPriorityQueue(library_facility, student);
		   cbf.callBackStack(recently_topped, student);
	   }
		 
   }
   
   
   //Multi threading and Concurrency
   //EXECUTORSERVICE THREADPOOL because multiple entries
   //Synchronous Callback functions
   public static void fetchRecordsFromDB(){
	   int coreCount = Runtime.getRuntime().availableProcessors();
	    ExecutorService service = Executors.newFixedThreadPool(coreCount);	
	   sql = "SELECT Roll_No, Name, Marks FROM Student";
	    try {
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				
		         int r  = rs.getInt("Roll_No");
		         String n = rs.getString("Name");
		         int m  = rs.getInt("Marks");
		         Student <String>student = new Student<String>(r,n,m);
		         
		         Thread t1 = new Thread(new Runnable()
				 {
					 public void run() {
						 if(cbf!=null)
							 cbf.callBackList(list, student);
						 //list.add(student);
						 //System.out.print("t1");
					 }
				 },"Adding to List");
				 
				 Thread t2 = new Thread(new Runnable()
						 {
				 			 public void run() {
								 if(cbf!=null)
									 cbf.callBackMap(studentmap, student);
				 				//studentmap.put(student.roll_no, student );
				 				//System.out.println("t2");
				 			 }
						 },"Adding to Map");
				 
				 Thread t3 = new Thread(new Runnable()
						 {
				 			 public void run() {
								 if(cbf!=null)
									 cbf.callBackPriorityQueue(library_facility, student);
				 				//library_facility.add(student);
				 				//System.out.println("t3");
				 			 }
						 },"Adding to PQueue");
				
				 Thread t4 = new Thread(new Runnable()
						 {
				 			 public void run() {
								 if(cbf!=null)
									 cbf.callBackStack(recently_topped, student);
				 				//addToStack(student);
				 				//System.out.println("t4");
				 			 }
						 },"Adding to Stack");
		         
		         service.execute(t1);
		         service.execute(t2);
		         service.execute(t3);
		         service.execute(t4);
		 		 	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }

   

}