/**
 * 
 */
package controller;

import service.EtudiantService;
import view.EtudiantView;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtudiantController {
 private EtudiantService etudiantService;
 private EtudiantView etudiantView;

 public EtudiantController(EtudiantService etudiantService, EtudiantView etudiantView) {
     this.etudiantService = etudiantService;
     this.etudiantView = etudiantView;

     etudiantView.addModifyButtonListener(new ModifyButtonListener());
     etudiantView.addDeleteButtonListener(new DeleteButtonListener());
     etudiantView.addAddButtonListener(new AddButtonListener());
     etudiantView.refreshTable(etudiantService.getEtudiantTableData());
 }

 class ModifyButtonListener implements ActionListener {
     public void actionPerformed(ActionEvent e) {
         int selectedRow = etudiantView.getSelectedRow();

         if (selectedRow == -1) {
             JOptionPane.showMessageDialog(null, "Veuillez sélectionner un étudiant à modifier.");
             return;
         }

         int id = (int) etudiantView.getValueAt(selectedRow, 1);
         String nom = etudiantView.getNom();
         String prenom = etudiantView.getPrenom();
         String dateNaissance = etudiantView.getDateNaissance();
         String email = etudiantView.getEmail();
         String matricule = etudiantView.getMatricule();

         etudiantService.updateEtudiant(id, nom, prenom, dateNaissance, email, matricule);
         etudiantView.refreshTable(etudiantService.getEtudiantTableData());
     }
 }

 class DeleteButtonListener implements ActionListener {
     public void actionPerformed(ActionEvent e) {
         int selectedRow = etudiantView.getSelectedRow();

         if (selectedRow == -1) {
             JOptionPane.showMessageDialog(null, "Veuillez sélectionner un étudiant à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
             return;
         }

         int id = (int) etudiantView.getValueAt(selectedRow, 1);

         boolean deleted = etudiantService.deleteEtudiant(id);

         if (deleted) {
             etudiantView.refreshTable(etudiantService.getEtudiantTableData());
         } else {
             JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'étudiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
         }
     }
 }

 class AddButtonListener implements ActionListener {
     public void actionPerformed(ActionEvent e) {
         String nom = etudiantView.getNom();
         String prenom = etudiantView.getPrenom();
         String dateNaissance = etudiantView.getDateNaissance();
         String email = etudiantView.getEmail();
         String matricule = etudiantView.getMatricule();

         etudiantService.addEtudiant(nom, prenom, dateNaissance, email, matricule);
         etudiantView.refreshTable(etudiantService.getEtudiantTableData());

         etudiantView.clearInputFields();
     }
 }
}

