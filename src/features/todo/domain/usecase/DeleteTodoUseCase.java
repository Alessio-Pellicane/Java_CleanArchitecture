package features.todo.domain.usecase;


import features.todo.domain.entity.TodoItem;
import features.todo.domain.repository.TodoRepository;

public class DeleteTodoUseCase {
    private final TodoRepository repository;

    public DeleteTodoUseCase(TodoRepository repository) {
        this.repository = repository;
    }

    public void execute(TodoItem item) {
        repository.delete(item);
    }
}
