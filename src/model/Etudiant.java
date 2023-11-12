/**
 * 
 */
package model;

public class Etudiant {
 private int id;
 private String nom;
 private String prenom;
 private String dateNaissance;
 private String email;
 private String matricule;

 public Etudiant(int id, String nom, String prenom, String dateNaissance, String email, String matricule) {
     this.id = id;
     this.nom = nom;
     this.prenom = prenom;
     this.dateNaissance = dateNaissance;
     this.email = email;
     this.matricule = matricule;
 }

 public int getId() {
     return id;
 }

 public String getNom() {
     return nom;
 }

 public void setNom(String nom) {
     this.nom = nom;
 }

 public String getPrenom() {
     return prenom;
 }

 public void setPrenom(String prenom) {
     this.prenom = prenom;
 }

 public String getDateNaissance() {
     return dateNaissance;
 }

 public void setDateNaissance(String dateNaissance) {
     this.dateNaissance = dateNaissance;
 }

 public String getEmail() {
     return email;
 }

 public void setEmail(String email) {
     this.email = email;
 }

 public String getMatricule() {
     return matricule;
 }

 public void setMatricule(String matricule) {
     this.matricule = matricule;
 }
}

