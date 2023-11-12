/**
 * 
 */
package Model;

/**
 * 
 */
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import View.view;


public class model {
	private View.view view;
    private Connection connection;
    public model(View.view view) {
    	this.view = view;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/etudiant", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public DefaultTableModel getEtudiantTableData() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; 
                }
                return super.getColumnClass(columnIndex);
            }
        };
        tableModel.addColumn("Supprimer");
        tableModel.addColumn("ID");
        tableModel.addColumn("Nom");
        tableModel.addColumn("Prénom");
        tableModel.addColumn("Date de Naissance");
        tableModel.addColumn("Email");
        tableModel.addColumn("Matricule");

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM etudiants";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String dateNaissance = resultSet.getString("date_naissance");
                String email = resultSet.getString("email");
                String matricule = resultSet.getString("matricule");

                tableModel.addRow(new Object[]{false, id, nom, prenom, dateNaissance, email, matricule});
            }

            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tableModel;
    }

    public void updateEtudiant(int id, String nom, String prenom, String dateNaissance, String email, String matricule) {
        try {
        	nom = view.getNom();
        	//prenom = view.getPrenom();
        	//dateNaissance = view.getDateNaissance();
        	//email = view.getEmail();
        	//matricule = view.getMatricule();

        	System.out.println("ID: " + id);
        	System.out.println("Nom: " + nom);
        	System.out.println("Prénom: " + prenom);
        	System.out.println("Date de Naissance: " + dateNaissance);
        	System.out.println("Email: " + email);
        	System.out.println("Matricule: " + matricule);
            // Construire la requête SQL en utilisant des paramètres conditionnels
            StringBuilder queryBuilder = new StringBuilder("UPDATE etudiants SET");
            ArrayList<Object> parameters = new ArrayList<>();
            boolean hasUpdates = false;

            if (nom != null && !nom.isEmpty()) {
                queryBuilder.append(" nom=?,");
                parameters.add(nom);
                hasUpdates = true;
            }

            if (prenom != null && !prenom.isEmpty()) {
                queryBuilder.append(" prenom=?,");
                parameters.add(prenom);
                hasUpdates = true;
            }

            if (dateNaissance != null && !dateNaissance.isEmpty()) {
                queryBuilder.append(" date_naissance=?,");
                parameters.add(dateNaissance);
                hasUpdates = true;
            }

            if (email != null && !email.isEmpty()) {
                queryBuilder.append(" email=?,");
                parameters.add(email);
                hasUpdates = true;
            }

            if (matricule != null && !matricule.isEmpty()) {
                queryBuilder.append(" matricule=?,");
                parameters.add(matricule);
                hasUpdates = true;
            }

            if (hasUpdates) {
                
                int length = queryBuilder.length();
                if (queryBuilder.charAt(length - 1) == ',') {
                    queryBuilder.deleteCharAt(length - 1);
                }

                queryBuilder.append(" WHERE id=?");
                parameters.add(id);

                String query = queryBuilder.toString();
                System.out.println("Requête SQL : " + query); 
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                for (int i = 0; i < parameters.size(); i++) {
                    preparedStatement.setObject(i + 1, parameters.get(i));
                }

                int rowsUpdated = preparedStatement.executeUpdate();
                preparedStatement.close();

                if (rowsUpdated > 0) {
                    System.out.println("L'étudiant a été modifié avec succès.");
                } else {
                    System.out.println("Aucune modification effectuée pour l'étudiant.");
                }
            } else {
                System.out.println("Aucune modification effectuée pour l'étudiant.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public boolean deleteEtudiant(int id) {
        try {
            String query = "DELETE FROM etudiants WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void addEtudiant(String nom, String prenom, String dateNaissance, String email, String matricule) {
        try {
            String query = "INSERT INTO etudiants (nom, prenom, date_naissance, email, matricule) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, dateNaissance);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, matricule);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


