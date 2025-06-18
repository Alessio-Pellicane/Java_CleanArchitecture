package features.todo.domain.usecase;

import features.todo.domain.entity.TodoItem;
import features.todo.domain.repository.TodoRepository;

import java.util.List;

public class GetTodosUseCase {
    private final TodoRepository repository;

    public GetTodosUseCase(TodoRepository repository) {
        this.repository = repository;
    }

    public List<TodoItem> execute() {
        return repository.getAll();
    }
}

