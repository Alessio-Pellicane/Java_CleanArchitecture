package core;

import features.todo.data.datasource.FileDataSource;
import features.todo.data.repository.TodoRepositoryImpl;
import features.todo.domain.repository.TodoRepository;
import features.todo.domain.usecase.AddTodoUseCase;
import features.todo.domain.usecase.DeleteTodoUseCase;
import features.todo.domain.usecase.GetTodosUseCase;
import features.todo.presentation.controller.TodoController;

public class DI {
    private static final String FILE_PATH = "todos.txt";

    // SINGLETONS
    private static FileDataSource dataSource;
    private static TodoRepository repository;
    private static AddTodoUseCase addUseCase;
    private static DeleteTodoUseCase deleteUseCase;
    private static GetTodosUseCase getUseCase;
    private static TodoController todoCubit;

    // Getter statiques pour les composants

    public static TodoController getTodoController() {
        if (todoCubit == null) {
            todoCubit = new TodoController(getTodosUseCase(), getAddTodoUseCase(), getDeleteTodoUseCase());
        }
        return todoCubit;
    }

    public static AddTodoUseCase getAddTodoUseCase() {
        if (addUseCase == null) {
            addUseCase = new AddTodoUseCase(getTodoRepository());
        }
        return addUseCase;
    }

    public static DeleteTodoUseCase getDeleteTodoUseCase() {
        if (deleteUseCase == null) {
            deleteUseCase = new DeleteTodoUseCase(getTodoRepository());
        }
        return deleteUseCase;
    }

    public static GetTodosUseCase getTodosUseCase() {
        if (getUseCase == null) {
            getUseCase = new GetTodosUseCase(getTodoRepository());
        }
        return getUseCase;
    }

    public static TodoRepository getTodoRepository() {
        if (repository == null) {
            repository = new TodoRepositoryImpl(getTodoDataSource());
        }
        return repository;
    }

    public static FileDataSource getTodoDataSource() {
        if (dataSource == null) {
            dataSource = new FileDataSource(FILE_PATH);
        }
        return dataSource;
    }
}
