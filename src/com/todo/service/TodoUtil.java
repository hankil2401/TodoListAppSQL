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
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<Add new Item to the list>\n"
				+ "New Title:   ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("The title is duplicated!!");
			return;
		}
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
	
	public static void completeItem(TodoList l, int index) {
		if(l.completeItem(index)>0)
			System.out.println("The item is completed!");
	}

	public static void listAll(TodoList l) {
		System.out.println("<All List, "+l.getCount()+" item(s) in total>");
		for (TodoItem item : l.getList()) {
			if(item.getIs_completed()==1)
				System.out.println(item.toString1());
			else
				System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("<All List, "+l.getCount()+" item(s) in total>");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			if(item.getIs_completed()==1)
				System.out.println(item.toString1());
			else
				System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int one) {
		for(TodoItem item : l.getList(one)) {
			System.out.println(item.toString1());
		}
	}
	
	public static void findList(TodoList l, String str) {
		int count=0;
		for(TodoItem item : l.getList(str)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.println("Found total of "+ count + " item(s).");
	}
	
	public static void findCateList(TodoList l, String str) {
		int count=0;
		for(TodoItem item : l.getListCategory(str)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("Found total of "+ count + " item(s) of this category.");
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\nTotal of "+ count + " categories are registered.");
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
