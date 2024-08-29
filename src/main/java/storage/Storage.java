package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {

    private File data;

    public Storage(String filePath) {
        this.data = new File(filePath);
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating the file");
            }
        }
    }

    public List<Task> load() {
        try {
            Scanner dataReader = new Scanner(data);
            List<Task> loadedData = new ArrayList<>();
            while (dataReader.hasNextLine()) {
                String line = dataReader.nextLine();
                char taskType = line.charAt(0);
                boolean done = false;
                switch (taskType) {
                    case 'T':
                        done = line.charAt(1) == '1';
                        loadedData.add(new Todo(line.substring(2), done));
                        break;
                    case 'D':
                        done = line.charAt(1) == '1';
                        String deadline = line.substring(line.indexOf('%') + 1);
                        loadedData.add(new Deadline(line.substring(2, line.indexOf('%')),
                                LocalDate.parse(deadline), done));
                        break;
                    case 'E':
                        done = line.charAt(1) == '1';
                        String start = line.substring(line.indexOf('%') + 1, line.indexOf('|'));
                        String end = line.substring(line.indexOf('|') + 1);
                        loadedData.add(new Event(line.substring(2, line.indexOf('%')),
                                LocalDate.parse(start), LocalDate.parse(end), done));
                }
            }
            return loadedData;
        } catch (FileNotFoundException e) {
            System.out.println("Scanner failed in load() method of storage.Storage-type object");
            return null;
        }
    }

    public void write(List<Task> newData) {
        try {
            FileWriter data = new FileWriter("./src/data/chatData.txt");
            newData.forEach(task -> {
                try {
                    data.write(task.toData() + "\n");
                } catch (IOException e) {
                    System.out.println("An error in writing has occured");
                }
            });
            data.close();
        } catch (IOException e) {
            System.out.println("An error in writing new Data has occurred");
        }
    }
}
