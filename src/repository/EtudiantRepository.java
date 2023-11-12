/**
 * 
 */
package repository;
import javax.swing.table.DefaultTableModel;

public interface EtudiantRepository {
 DefaultTableModel getEtudiantTableData();
 void updateEtudiant(int id, String nom, String prenom, String dateNaissance, String email, String matricule);
 boolean deleteEtudiant(int id);
 void addEtudiant(String nom, String prenom, String dateNaissance, String email, String matricule);
}

