package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EtudiantView {
    private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton refreshButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton addButton;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField dateNaissanceField;
    private JTextField emailField;
    private JTextField matriculeField;

    public EtudiantView(DefaultTableModel tableModel) {
        this.tableModel = tableModel;

        frame = new JFrame("Gestion des étudiants");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("icon1.png").getImage());

        table = new JTable(tableModel);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(0).setMaxWidth(30);
        columnModel.getColumn(0).setMinWidth(30);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel inputPanel = new JPanel(new FlowLayout());

        nomField = new JTextField(15);
        prenomField = new JTextField(15);
        dateNaissanceField = new JTextField(15);
        emailField = new JTextField(15);
        matriculeField = new JTextField(15);

        JLabel nomLabel = new JLabel("Nom:");
        JLabel prenomLabel = new JLabel("Prénom:");
        JLabel dateNaissanceLabel = new JLabel("Date de Naissance:");
        JLabel emailLabel = new JLabel("Email");
        JLabel matriculeLabel = new JLabel("Matricule:");

        addButton = new JButton("Ajouter");
        modifyButton = new JButton("Modification");
        deleteButton = new JButton("Supprimer");
        refreshButton = new JButton("Actualiser");

        inputPanel.add(nomLabel);
        inputPanel.add(nomField);
        inputPanel.add(prenomLabel);
        inputPanel.add(prenomField);
        inputPanel.add(dateNaissanceLabel);
        inputPanel.add(dateNaissanceField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(matriculeLabel);
        inputPanel.add(matriculeField);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(refreshButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);

        JPanel tableAndButtonPanel = new JPanel(new BorderLayout());
        tableAndButtonPanel.add(scrollPane, BorderLayout.CENTER);
        tableAndButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(tableAndButtonPanel, BorderLayout.CENTER);

        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    public void refreshTable(DefaultTableModel newModel) {
        table.setModel(newModel);
        table.repaint();
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public Object getValueAt(int row, int column) {
        return table.getValueAt(row, column);
    }

    public void addModifyButtonListener(ActionListener listener) {
        modifyButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public String getNom() {
        return nomField.getText();
    }

    public String getPrenom() {
        return prenomField.getText();
    }

    public String getDateNaissance() {
        return dateNaissanceField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getMatricule() {
        return matriculeField.getText();
    }

    public void clearInputFields() {
        nomField.setText("");
        prenomField.setText("");
        dateNaissanceField.setText("");
        emailField.setText("");
        matriculeField.setText("");
    }
}

