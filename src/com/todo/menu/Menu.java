package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<<Menu for ToDoList commands>>");
        System.out.println("1. add - insert new item");
        System.out.println("2. del - remove existing item");
        System.out.println("3. edit - update existing item");
        System.out.println("4. find ... - to search for item(s)");
        System.out.println("5. find_cate ... - to search for categories");
        System.out.println("6. comp ... - mark the item as completed");
        System.out.println("7. ls - list all the items");
        System.out.println("8. ls_cate - list all the categories");
        System.out.println("9. ls_comp - list all the completed item(s)");
        System.out.println("10. ls_name - list all items in ascending order of name");
        System.out.println("11. ls_name_desc - list all items in descending order of name");
        System.out.println("12.ls_date - list all items in ascending order of date");
        System.out.println("13.ls_date_desc - list all items in ascending order of date");
        System.out.println("14.exit - quit the program!");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand: ");
    }
}
