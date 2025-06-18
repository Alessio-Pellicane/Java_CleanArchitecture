package features.todo.data.model;

public class TodoItemModel {
    public String name;
    public String type;

    public TodoItemModel() {} // Nécessaire pour la désérialisation

    public TodoItemModel(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
