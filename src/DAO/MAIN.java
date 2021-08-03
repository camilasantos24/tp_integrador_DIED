package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import Entidades.Estacion;

public class MAIN {

	public MAIN() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
	try {
		List<Object[]> o= EstacionDAO.getInstance().get_page_rank();
		
		for (int i=0; i<o.size(); i++) {
			System.out.println(o.get(i)[1]);
		}
		
		o.sort((o1, o2) -> ((Comparable<Integer>) o1[1]).compareTo((Integer) o2[1]));
		
		System.out.println("--------------------------------------");
		for (int i=0; i<o.size(); i++) {
			System.out.println(o.get(i)[1]);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	}

}
