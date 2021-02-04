import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    static final int MAX_NO_OF_TASKS = 100;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Here are the things that i can do: ");
        System.out.println("1. Echo");
        System.out.println("2. Add task to your list & Mark task as done");
        System.out.println("-1: Quit Duke");

        Scanner in = new Scanner(System.in);
        boolean run = true;
        while (run){
            int choice = in.nextInt();
            switch (choice) {
            case 1:
                System.out.println("Please input something and I will repeat it back to you");
                Scanner a = new Scanner(System.in);
                String sentence = a.nextLine();
                while (true) {
                    if (sentence.equalsIgnoreCase("bye")){
                        break;
                    } else{
                        System.out.println(sentence);
                        sentence = a.nextLine();
                    }
                }
                System.out.println("Thank you for using echo, " +
                        "you can now choose other function :)");
                break;
            case 2:
                System.out.println("Please enter the task you want to add in to the task list.");
                Task[] tasks = new Task[MAX_NO_OF_TASKS];
                int index = 0;
                String task;
                Scanner b = new Scanner(System.in);
                task = b.nextLine();
                while (true) {
                    if (task.equalsIgnoreCase("bye")) {
                        break;
                    } else if (task.equals("list")) {
                        for (int i=0; i<index; i++){
                            System.out.println((i+1) + "." + tasks[i].toString());
                        }
                    } else if (task.contains("done")) {
                        try {
                            String taskNo = task.substring(task.length() - 1);
                            int new_taskNo = Integer.parseInt(taskNo);
                            tasks[new_taskNo - 1].setTaskStatus(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(tasks[new_taskNo - 1].toString());
                        } catch (StringIndexOutOfBoundsException e){
                            System.out.println(":( Opppss!! The number of task to be done" +
                                    "cannot be empty");
                        }
                    } else if (task.contains("todo")){
                        try{
                            String taskName = task.substring(5);
                            tasks[index] = new Todo(taskName, 'T');
                            index = addTaskMessage(index, tasks[index]);
                        } catch (StringIndexOutOfBoundsException e){
                            System.out.println(":( Opppss!! The description of a" +
                                    "todo cannot be empty");
                        }
                    } else if (task.contains("deadline")){
                        try {
                            int slash_sign = task.indexOf("/");
                            String taskName1 = task.substring(9, slash_sign - 1);
                            String by = task.substring(slash_sign + 3);
                            tasks[index] = new Deadline(taskName1, 'D', by);
                            index = addTaskMessage(index, tasks[index]);
                        } catch (StringIndexOutOfBoundsException e){
                            System.out.println(":( Opppss!! The description of a" +
                                    "deadline cannot be empty");
                        }
                    } else if (task.contains("event")) {
                        try {
                            int slash_sign = task.indexOf("/");
                            String taskName2 = task.substring(6, slash_sign - 1);
                            String at = task.substring(slash_sign + 3);
                            tasks[index] = new Event(taskName2, 'E', at);
                            index = addTaskMessage(index, tasks[index]);
                        } catch (StringIndexOutOfBoundsException e){
                            System.out.println(":( Opppss!! The description of a" +
                                    "event cannot be empty");
                        }
                    } else{
                        System.out.println(":( Opppss!! I'm sorry, " +
                                "but i don't know what that means...");
                    }
                    /* else{
                        //add tasks with no specific task type
                        tasks[index] = new Task(task);
                        index = addTaskMessage(index, tasks[index]);
                    } */
                    task = b.nextLine();
                }
                System.out.println("Thank you for using Tasks function, " +
                        "you can now choose other function :)");
                break;
            case (-1):
                System.out.println("Goodbye, hope to see you again!");
                run = false;
            }
        }
    }
    private static int addTaskMessage(int index, Task tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + tasks.toString());
        System.out.println("Now you have " + (index+1) + " tasks in the list. ");
        index++;
        return index;
        }
    }