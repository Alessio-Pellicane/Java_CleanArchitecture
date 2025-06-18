package features.todo.presentation.state;

import features.todo.domain.entity.TodoItem;

import java.util.List;

public abstract class TodoState {
    public final List<TodoItem> todoList;

    protected TodoState(List<TodoItem> todoList) {
        this.todoList = todoList;
    }

    public static final class Initial extends TodoState {
        public Initial() { super(List.of()); }
    }

    public static final class Loading extends TodoState {
        public Loading() { super(List.of()); }
    }

    public static final class Loaded extends TodoState {
        public Loaded(List<TodoItem> todos) { super(todos); }
    }

    public static final class Error extends TodoState {
        public final String message;
        public Error(String message) {
            super(List.of());
            this.message = message;
        }
    }
}
