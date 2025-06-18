package features.todo.domain.entity;

public class TodoItem {
    private final String name;
    private final String type;

    public TodoItem(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
