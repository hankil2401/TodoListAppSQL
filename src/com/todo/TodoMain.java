package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
	
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "find":
				String str = sc.nextLine().trim();
				TodoUtil.findList(l, str);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;

			case "ls_name":
				System.out.println("The list is sorted by name.");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("The list is sorted by name - reversed.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("The list is sorted by date.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("The list is sorted by date - reversed.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "comp":
				int index = sc.nextInt();
				TodoUtil.completeItem(l, index);
				break;
				
			case "ls_comp":
				System.out.println("The list where all items are completed.");
				TodoUtil.listAll(l, 1);
				break;

			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("Please enter the correct command (to diplay menu: [help])");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
