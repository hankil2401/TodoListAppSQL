package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	private static int count=0;

	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Add new Item to the list>\n"
				+ "New Title:   ");
		title = sc.next();
//		if (list.isDuplicate(title)) {
//			System.out.printf("The title is duplicated!!");
//			return;
//		}
		System.out.print("Category:    ");
		category = sc.next();
		sc.nextLine();
		System.out.print("Description: ");
		desc = sc.nextLine().trim();
		System.out.print("Due date:    ");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("New item added!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Delete Item from the list>\n"
				+ "Removing Title's number: ");
		int no = sc.nextInt();
		if(l.deleteItem(no)>0)
			System.out.println("The Item is removed!");
	}


	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Change Item in the list>\n"
				+ "Old Title's number:   ");
		int no = sc.nextInt();

		System.out.print("New Title:       ");
		new_title = sc.next().trim();
		System.out.print("New Category:    ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("Description:     ");
		new_desc = sc.nextLine().trim();
		System.out.print("New due date:    ");
		new_due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(no);
		if(l.updateItem(t)>0)
			System.out.println("The item is updated!");

	}

	public static void listAll(TodoList l) {
		System.out.println("<All List, "+count+" item(s) in total>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void find(TodoList l, String str) {
		int j=0;
		for(int i=0; i<count; i++) {
			TodoItem item = l.getList().get(i);
			if(item.getCategory().toLowerCase().contains(str.toLowerCase()) 
			|| item.getTitle().toLowerCase().contains(str.toLowerCase())
			|| item.getDesc().toLowerCase().contains(str.toLowerCase())) {
				System.out.print(i+1+". ");
				System.out.println(item.toString());
				j++;
			}
		}
		System.out.println("Found total of "+ j + " item(s).");
	}
	
	public static void find_cate(TodoList l, String str) {
		int j=0;
		for(int i=0; i<count; i++) {
			TodoItem item = l.getList().get(i);
			if(item.getCategory().toLowerCase().contains(str.toLowerCase())) {
				System.out.print(i+1+". ");
				System.out.println(item.toString());
				j++;
			}
		}
		System.out.println("Found total of "+ j + " item(s) of this category.");
	}
	
	public static void ls_cate(TodoList l) {
		HashSet<String> set = new HashSet<>();
		for (TodoItem item : l.getList()) {
			set.add(item.getCategory());
        }
		Iterator<String> itr=set.iterator();  
		int i=1;
		for(String cate : set) {
			System.out.print(itr.next());
			if(itr.hasNext()) {
				i++;
				System.out.print(" / ");
			}
		}
		System.out.println("\nTotal of "+ i + " categories are registered.");
	}

	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			count = 0;
			while((oneline = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String ct = st.nextToken();
				String t = st.nextToken();
				String td = st.nextToken();
				String dd = st.nextToken();
				String cd = st.nextToken();
				TodoItem i = new TodoItem(t, td, cd, ct, dd);
				l.addItem(i);
				count++;
			}
			System.out.println(count+" item(s) has been read!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item: l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
