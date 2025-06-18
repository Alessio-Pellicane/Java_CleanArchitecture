package features.todo.data.repository;

import features.todo.data.datasource.FileDataSource;
import features.todo.data.model.TodoItemModel;
import features.todo.domain.entity.TodoItem;
import features.todo.domain.repository.TodoRepository;
import java.util.*;
import java.util.stream.Collectors;

public class TodoRepositoryImpl implements TodoRepository {
    private final FileDataSource dataSource;
    private final List<TodoItem> cache = new ArrayList<>();

    public TodoRepositoryImpl(FileDataSource dataSource) {
        this.dataSource = dataSource;
        loadFromFile();
    }

    private void loadFromFile() {
        List<TodoItemModel> models = dataSource.load();
        for (TodoItemModel model : models) {
            cache.add(new TodoItem(model.name, model.type));
        }
    }

    private void saveToFile() {
        List<TodoItemModel> model = new ArrayList<>();
        for (TodoItem item : cache) {
            model.add(new TodoItemModel(item.getName(), item.getType()));
        }
        dataSource.save(model);
    }


    @Override
    public List<TodoItem> getAll() {
        return new ArrayList<>(cache);
    }

    @Override
    public void add(TodoItem item) {
        cache.add(item);
        saveToFile();
    }

    @Override
    public void delete(TodoItem item) {
        cache.removeIf(todo -> todo.getName().equals(item.getName()) && todo.getType().equals(item.getType()));
        saveToFile();
    }
}
