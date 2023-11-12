package Controller;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import Model.model;
import View.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controller {
    private model model;
    private view view;
    private static boolean isApplicationStarted = false;

    public controller() {
        model model = new model(view);

        DefaultTableModel tableModel = model.getEtudiantTableData();
        view = new view(tableModel);
        
        this.model = model;
        this.view = view;

        view.addModifyButtonListener(new ModifyButtonListener());
        view.addDeleteButtonListener(new DeleteButtonListener());
        view.addAddButtonListener(new AddButtonListener());
        view.refreshTable(model.getEtudiantTableData());
    }

    public void startApplication() {
        if (!isApplicationStarted) {

            model = new model(view);

            DefaultTableModel tableModel = model.getEtudiantTableData();
            view = new view(tableModel);

            view.addModifyButtonListener(new ModifyButtonListener());
            view.addDeleteButtonListener(new DeleteButtonListener());
            view.addAddButtonListener(new AddButtonListener());
            view.refreshTable(model.getEtudiantTableData());

            isApplicationStarted = true;
        }
    }

    class ModifyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int selectedRow = view.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un étudiant à modifier.");
                return;
            }

            int id = (int) view.getValueAt(selectedRow, 1);

            String nom = view.getNom();
            System.out.println(nom);
            String prenom = view.getPrenom();
            String dateNaissance = view.getDateNaissance();
            String email = view.getEmail();
            String matricule = view.getMatricule();
            System.out.println(nom);
           
            model.updateEtudiant(id, nom, prenom, dateNaissance, email, matricule);

            view.refreshTable(model.getEtudiantTableData());
        }
    }

    class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un étudiant à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = (int) view.getValueAt(selectedRow, 1);

            boolean deleted = model.deleteEtudiant(id);

            if (deleted) {
                view.refreshTable(model.getEtudiantTableData());
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'étudiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nom = view.getNom();
            String prenom = view.getPrenom();
            String dateNaissance = view.getDateNaissance();
            String email = view.getEmail();
            String matricule = view.getMatricule();

            model.addEtudiant(nom, prenom, dateNaissance, email, matricule);
            view.refreshTable(model.getEtudiantTableData());

            view.clearInputFields();
        }
    }

    public static void main(String[] args) {
        controller controller = new controller();
        controller.startApplication();
    }
}
