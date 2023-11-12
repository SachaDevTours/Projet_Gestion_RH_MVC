/**
 * 
 */
package Authentification;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Model.model;
import View.view;
import Controller.controller;


public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginWindow() {
        setTitle("Connexion à la base de données");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Se connecter");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nom d'utilisateur:"));
        panel.add(usernameField);
        panel.add(new JLabel("Mot de passe:"));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                boolean loginSuccessful = verifyDatabaseLogin(username, password);

                if (loginSuccessful) {
                    
                    dispose();
                    startApplication();
                } else {
                    JOptionPane.showMessageDialog(null, "Échec de la connexion à la base de données. Veuillez réessayer.");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private boolean verifyDatabaseLogin(String username, String password) {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = "jdbc:mysql://localhost:3306/etudiant";
            String dbUser = username;
            String dbPass = password;

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
     
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur : Pilote JDBC introuvable.");
        } catch (SQLException e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(frame, "Erreur de connexion à la base de données : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private void startApplication() {
        model model = new model(null);

        DefaultTableModel tableModel = model.getEtudiantTableData();
        view view = new view(tableModel);

        controller controller = new controller();
        //controller.startApplication();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow();
            }
        });
    }
}

