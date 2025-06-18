package features.todo.domain.repository;

import features.todo.domain.entity.TodoItem;

import java.util.List;

public interface TodoRepository {
    List<TodoItem> getAll();
    void add(TodoItem item);
    void delete(TodoItem item);
}

