package features.todo.data.datasource;

import features.todo.data.model.TodoItemModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataSource {
    private final File file;

    public FileDataSource(String filename) {
        this.file = new File(filename);
    }

    public List<TodoItemModel> load() {
        List<TodoItemModel> items = new ArrayList<>();
        if (!file.exists()) return items;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    items.add(new TodoItemModel(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void save(List<TodoItemModel> items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (TodoItemModel item : items) {
                bw.write(item.name + ";" + item.type);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
