package features.todo.domain.usecase;

import features.todo.domain.entity.TodoItem;
import features.todo.domain.repository.TodoRepository;

public class AddTodoUseCase {
    private final TodoRepository repository;

    public AddTodoUseCase(TodoRepository repository) {
        this.repository = repository;
    }

    public void execute(String name, String type) {
        TodoItem item = new TodoItem(name, type);
        repository.add(item);
    }
}
