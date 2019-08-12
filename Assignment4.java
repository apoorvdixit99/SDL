package Assign1;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class Assignment4 extends Assignment3 implements ActionListener{
	
	JFrame jFrame;
	JButton addRecord,
			displayList,
			displayPQ_OTF,
			displayMap_OTF,
			displayStack_OTF,
			displayStudentInfo,
			dequePQ;
	JTextField rollNoTextField,
			rollNoTextField2,
			nameTextField,
			marksTextField,
			dequeTextField;
	JLabel roll, name, marks;
	static JTextArea outputTextArea;
	
	Assignment4(){
		super();
		jFrame = new JFrame();
		jFrame.setSize(1000,1000);
		jFrame.setTitle("Assignment4GUI");
		jFrame.setLayout(null);  
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

		roll=new JLabel("Roll No ");
		roll.setBounds(600, 50, 90, 30);
		jFrame.add(roll);
		rollNoTextField=new JTextField("Enter Roll Number");
		rollNoTextField.setBounds(700, 50, 200, 30); //x,y,width,height
		jFrame.add(rollNoTextField);

		name=new JLabel("Name ");
		name.setBounds(600, 100, 50, 30);
		jFrame.add(name);
		nameTextField=new JTextField("Enter Name");
		nameTextField.setBounds(700, 100, 200, 30); //x,y,width,height
		jFrame.add(nameTextField);
		
		marks=new JLabel("Marks ");
		marks.setBounds(600, 150, 50, 30);
		jFrame.add(marks);
		marksTextField=new JTextField("Enter Marks");
		marksTextField.setBounds(700, 150, 200, 30); //x,y,width,height
		jFrame.add(marksTextField);
		
		addRecord=new JButton("Add Record");
		addRecord.setBounds(600,200,300,30);
		addRecord.addActionListener(this);
		jFrame.add(addRecord);
		
		
		displayList=new JButton("Admission List");
		displayList.setBounds(600,400,300,30);
		displayList.addActionListener(this);
		jFrame.add(displayList);
		
		displayPQ_OTF=new JButton("Library Facility Queue");
		displayPQ_OTF.setBounds(600,450,300,30);
		displayPQ_OTF.addActionListener(this);
		jFrame.add(displayPQ_OTF);
		
		displayMap_OTF=new JButton("Roll Number List");
		displayMap_OTF.setBounds(600,500,300,30);
		displayMap_OTF.addActionListener(this);
		jFrame.add(displayMap_OTF);
		
		displayStack_OTF=new JButton("Recently Topped");
		displayStack_OTF.setBounds(600,550,300,30);
		displayStack_OTF.addActionListener(this);
		jFrame.add(displayStack_OTF);0
		
		displayStudentInfo=new JButton("Get Student Info");
		displayStudentInfo.setBounds(700,700,200,30);
		displayStudentInfo.addActionListener(this);
		jFrame.add(displayStudentInfo);
		
		dequePQ=new JButton("Grant Library Facility");
		dequePQ.setBounds(700,750,200,30);
		dequePQ.addActionListener(this);
		jFrame.add(dequePQ);
		
		outputTextArea=new JTextArea();
		outputTextArea.setBounds(50,50,450,850);
		jFrame.add(outputTextArea);
		outputTextArea.setEditable(false);
		outputTextArea.setText("");
		
		dequeTextField=new JTextField();
		dequeTextField.setBounds(600,750,90,30); //x,y,width,height
		jFrame.add(dequeTextField);
		
		rollNoTextField2=new JTextField();
		rollNoTextField2.setBounds(600,700,90,30); //x,y,width,height
		jFrame.add(rollNoTextField2);
		
	}
	
	public static void main(String args[]) {
		
		Assignment4 a4 = new Assignment4();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==addRecord)
			displayNewRecord();
		else if(e.getSource()==displayList)
			displayList();
		else if(e.getSource()==displayPQ_OTF)
			displayPriorityQueue();
		else if(e.getSource()==displayMap_OTF)
			displayMap();
		else if(e.getSource()==displayStack_OTF)
			displayStack();
		else if(e.getSource()==displayStudentInfo)
			fetchRecordFromMap();
		else if(e.getSource()==dequePQ) {
			int num = Integer.parseInt(dequeTextField.getText());
			dequePriorityQueue(num);
			displayPriorityQueue();
	        dequeTextField.setText("");
		}
		
	}

	void displayNewRecord() {
		int r = Integer.parseInt(rollNoTextField.getText());
		String n = nameTextField.getText();
		int m = Integer.parseInt(marksTextField.getText());
		Student <String> newStudentFromGUI = new Student<String>(r,n,m);
		insertRecordIntoDB(newStudentFromGUI);
		outputTextArea.setText("Roll No - "+r+"\nName - "+n+"\nMarks - "+m);
	}
	
	//List Functionality Done
	static void displayList() {
		outputTextArea.setText("");
		for(int i=0;i<list.size();i++) {
			int r = list.get(i).roll_no;
			String n = (String) list.get(i).name;
			int m = list.get(i).marks;
			String ota = outputTextArea.getText();
			outputTextArea.setText(ota+r+"\t"+n+"\t\t"+m+"\n");
		}
	}
	
	//Priority Queue Functionality Done
	static void displayPriorityQueue() {
		outputTextArea.setText("");
		PriorityQueue <Student> temp = new <Student> PriorityQueue(library_facility);
		while(!temp.isEmpty()) {
			Student <String> tempStudent = temp.poll();
			int r = tempStudent.roll_no;
			String n = (String) tempStudent.name;
			int m = tempStudent.marks;
			String ota = outputTextArea.getText();
			outputTextArea.setText(ota+r+"\t"+n+"\t\t"+m+"\n");
		}
	}
	
	//Stack functionality Done
	static void displayStack() {
		outputTextArea.setText("");
		Stack <Student> temp = new <Student> Stack();
		temp.addAll(recently_topped);
		while(!temp.isEmpty()) {
			Student <String> tempStudent = temp.pop();
			int r = tempStudent.roll_no;
			String n = (String) tempStudent.name;
			int m = tempStudent.marks;
			String ota = outputTextArea.getText();
			outputTextArea.setText(ota+r+"\t"+n+"\t\t"+m+"\n");
		}
	}
	
	//Map functionality Done
	static void displayMap() {
		outputTextArea.setText("");
		Set <Integer> keyset = studentmap.keySet();
        Iterator<Integer> itr = keyset.iterator();
        while(itr.hasNext()) {
            int r = itr.next();
            int m = studentmap.get(r).marks;
            String n = (String) studentmap.get(r).name;
            String ota = outputTextArea.getText();
            outputTextArea.setText(ota+r+"\t"+n+"\t\t"+m+"\n");
		}
	}
	
	void fetchRecordFromMap() {
		outputTextArea.setText("");
		int r = Integer.parseInt(rollNoTextField2.getText());
        int m = studentmap.get(r).marks;
        String n = (String) studentmap.get(r).name;
        String ota = outputTextArea.getText();
        outputTextArea.setText(ota+r+"\t"+n+"\t\t"+m+"\n");
        rollNoTextField2.setText("");
	}
	
}
