import core.DI;
import features.todo.presentation.view.TodoPage;
import features.todo.presentation.view.View;

public class Main {
    public static void main(String[] args) {
       //TODO: Gestion des évenement dans la vue

        View view = new TodoPage(DI.getTodoController());
        view.run();
    }
}