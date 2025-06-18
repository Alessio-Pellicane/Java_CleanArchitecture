package features.todo.presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TodoPage extends JFrame implements View{
    private JPanel mainPanel;
    private JTextField addTodoTextField;
    private JButton addTodoButton;
    private JButton deleteTodoButton;
    private JTable todoTable;
    private JScrollPane todoScrollPane;

    public TodoPage() {
        super("Liste de todos");                          // Permet de donner un titre à la fenêtre
        configureComponent();
        configureTable();
        configureFrame();

    }

    private void configureComponent() {
        // Label button
        addTodoButton.setText("Ajouter la tâche");      // Initialiser le label du bouton
        deleteTodoButton.setText("Supprimer");

    }


    private void configureTable() {
        String[] columnNames = {"Tâches", "Types"};                                      // Initialisation des titres de colonnes (dans un tableau)
        DefaultTableModel customTableModel = new DefaultTableModel(columnNames,0);      // Création d'un modèle pour structure le tableau
        todoTable.setModel(customTableModel);


    }


    private void configureFrame() {
        setContentPane(mainPanel);                             // Défini le contenu principal dans cette fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // Définition de l'action à effectuer lorsqu'on quitte la fenêtre
        setLocationRelativeTo(null);                           // Centre la fenêtre au milieu de l'écran lorsque le programme se lance
        setSize(400, 200);                        // Définition des dimension de la fenêtre
    }

    @Override
    public void run() {
        setVisible(true);                                      // Rend la fenêtre visible à l'écran
    }

}
