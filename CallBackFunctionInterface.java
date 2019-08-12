package Assign1;

import java.util.*;

public interface CallBackFunctionInterface {
	void callBackStack(Stack<Student> s, Student student);
	void callBackList(ArrayList<Student> al, Student student);
	void callBackMap(TreeMap<Integer,Student> tm, Student student);
	void callBackPriorityQueue(PriorityQueue<Student> pq, Student student);
}
