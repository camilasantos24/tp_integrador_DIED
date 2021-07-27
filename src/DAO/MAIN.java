package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entidades.Estacion;

public class MAIN {

	public MAIN() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
	List<Integer> i = new ArrayList();
	i.add(1);
	i.add(2);
	i.add(4);
	i.add(7);
	
	for (int j =0; j<i.size(); j++) {
		System.out.println(i.get(j));
	}
	System.out.println("---------------------------");
	i.remove(2);
	
	for (int j =0; j<i.size(); j++) {
		System.out.println(i.get(j));
	}
	
	System.out.println("---------------------------");
	System.out.println(i.get(2));


	}

}
