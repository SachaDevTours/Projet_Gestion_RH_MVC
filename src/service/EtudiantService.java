package service;

import model.Etudiant;
import repository.EtudiantRepository;

import javax.swing.table.DefaultTableModel;

public class EtudiantService {
    private EtudiantRepository etudiantRepository;

//    public EtudiantService(EtudiantRepository etudiantRepository) {
//        this.etudiantRepository = etudiantRepository;
//    }

    public EtudiantService(EtudiantRepository etudiantRepository) {
    	this.etudiantRepository = etudiantRepository;
	}

	public DefaultTableModel getEtudiantTableData() {
        return etudiantRepository.getEtudiantTableData();
    }

    public void updateEtudiant(int id, String nom, String prenom, String dateNaissance, String email, String matricule) {
        etudiantRepository.updateEtudiant(id, nom, prenom, dateNaissance, email, matricule);
    }

    public boolean deleteEtudiant(int id) {
        return etudiantRepository.deleteEtudiant(id);
    }

    public void addEtudiant(String nom, String prenom, String dateNaissance, String email, String matricule) {
        etudiantRepository.addEtudiant(nom, prenom, dateNaissance, email, matricule);
    }
}

