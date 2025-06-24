package features.todo.presentation.view;

import features.todo.domain.entity.TodoItem;
import features.todo.presentation.controller.TodoController;
import features.todo.presentation.state.TodoState;
import features.todo.presentation.state.TodoStateListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TodoPage extends JFrame implements View, TodoStateListener {
    private JPanel mainPanel;
    private JTextField addTodoTextField;
    private JButton addTodoButton;
    private JButton deleteTodoButton;
    private JTable todoTable;
    private JScrollPane todoScrollPane;
    private JComboBox comboBox1;
    private JLabel typeLabel;
    private JLabel itemLabel;
    private final TodoController todoController;

    public TodoPage(TodoController todoController) {
        super("Liste de todos");
        this.todoController = todoController;

        configureComponent();
        configureTable();
        configureFrame();
        configureListeners();

        this.todoController.addListener(this);
    }

    private void configureComponent() {
        addTodoButton.setText("Ajouter la tâche");
        comboBox1.removeAllItems();
        comboBox1.addItem("Prioritaire");
        comboBox1.addItem("Non-prioritaire");
        comboBox1.addItem("Personnel");
        comboBox1.addItem("Travail");
    }

    private void configureTable() {
        String[] columnNames = {"Tâches", "Type", "Actions"};
        DefaultTableModel customTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        todoTable.setModel(customTableModel);
    }

    private void configureListeners() {
        addTodoButton.addActionListener(e -> {
            String todoName = addTodoTextField.getText();
            if (todoName != null && !todoName.trim().isEmpty()) {
                todoController.addTodo(todoName, "Personnel");
                addTodoTextField.setText("");
            }
        });

        todoTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = todoTable.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / todoTable.getRowHeight();

                if (row < todoTable.getRowCount() && row >= 0 && column == 2) { // Actions column
                    String todoName = (String) todoTable.getValueAt(row, 0);
                    String todoType = (String) todoTable.getValueAt(row, 1);
                    TodoItem itemToDelete = new TodoItem(todoName, todoType);
                    todoController.deleteTodo(itemToDelete);
                }
            }
        });


    }

    private void configureFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
    }

    @Override
    public void run() {
        setVisible(true);
        todoController.loadTodos();
    }

    @Override
    public void onStateChanged(TodoState newState) {
        SwingUtilities.invokeLater(() -> {
            if (newState instanceof TodoState.Loading) {
                addTodoButton.setEnabled(false);

            } else if (newState instanceof TodoState.Loaded) {
                addTodoButton.setEnabled(true);

                displayTodos(((TodoState.Loaded) newState).todoList);
            } else if (newState instanceof TodoState.Error) {
                addTodoButton.setEnabled(true);

                JOptionPane.showMessageDialog(this, ((TodoState.Error) newState).message, "Erreur", JOptionPane.ERROR_MESSAGE);
                if (todoTable.getRowCount() > 0) {
                    displayTodos(List.of());
                }
            }
        });
    }

    private void displayTodos(List<TodoItem> todos) {
        DefaultTableModel model = (DefaultTableModel) todoTable.getModel();
        model.setRowCount(0);
        for (TodoItem todo : todos) {
            model.addRow(new Object[]{todo.getName(), todo.getType(), "Supprimer"});
        }
    }
}
