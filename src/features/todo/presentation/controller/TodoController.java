package features.todo.presentation.controller;

import features.todo.domain.entity.TodoItem;
import features.todo.domain.usecase.AddTodoUseCase;
import features.todo.domain.usecase.DeleteTodoUseCase;
import features.todo.domain.usecase.GetTodosUseCase;
import features.todo.presentation.state.TodoState;
import features.todo.presentation.state.TodoStateListener;

import java.util.ArrayList;
import java.util.List;

public class TodoController {
    private final GetTodosUseCase getTodosUseCase;
    private final AddTodoUseCase addTodoUseCase;
    private final DeleteTodoUseCase deleteTodoUseCase;

    private final List<TodoStateListener> listeners = new ArrayList<>();
    private TodoState currentState = new TodoState.Initial();

    public TodoController(GetTodosUseCase getTodosUseCase, AddTodoUseCase addTodoUseCase, DeleteTodoUseCase deleteTodoUseCase) {
        this.getTodosUseCase = getTodosUseCase;
        this.addTodoUseCase = addTodoUseCase;
        this.deleteTodoUseCase = deleteTodoUseCase;
    }

    public void addListener(TodoStateListener listener) {
        listeners.add(listener);
        listener.onStateChanged(currentState); // push initial
    }

    private void emit(TodoState newState) {
        this.currentState = newState;
        for (TodoStateListener listener : listeners) {
            listener.onStateChanged(newState);
        }
    }

    public void loadTodos() {
        emit(new TodoState.Loading());

        try {
            List<TodoItem> todos = getTodosUseCase.execute();

            if (todos.isEmpty()) {
                emit(new TodoState.Error("La liste est vide..."));
                return;
            }

            emit(new TodoState.Loaded(todos));
        } catch (Exception e) {
            emit(new TodoState.Error("Erreur lors du chargement des tâches"));
        }
    }

    public void addTodo(String name, String type) {
        try {
            addTodoUseCase.execute(name, type);
            loadTodos(); // reload after adding
        } catch (Exception e) {
            emit(new TodoState.Error("Erreur lors de l'ajout d'une tâche"));
        }
    }

    public void deleteTodo(TodoItem item) {
        try {
            deleteTodoUseCase.execute(item);
            loadTodos(); // reload after deleting
        } catch (Exception e) {
            emit(new TodoState.Error("Erreur lors de la suppression d'une tâche"));
        }
    }
}
