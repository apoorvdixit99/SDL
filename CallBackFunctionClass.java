package Assign1;

import java.util.*;

public class CallBackFunctionClass implements CallBackFunctionInterface{

	@Override
	public void callBackList(ArrayList<Student> al, Student student) {
		al.add(student);
	}

	@Override
	public void callBackPriorityQueue(PriorityQueue<Student> pq, Student student) {
		pq.add(student);
	}

	@Override
	public void callBackStack(Stack<Student> s, Student student) {
		if(s.empty()==false) {
			if( s.peek().marks < student.marks )
				s.add(student);
		}
		else
			s.add(student);
		
	}

	@Override
	public void callBackMap(TreeMap<Integer, Student> tm, Student student) {
		tm.put(student.roll_no, student);
	}

	

}
