package features.todo.presentation.state;


public interface TodoStateListener {
    void onStateChanged(TodoState newState);
}
