/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import javafx.scene.layout.StackPane;

/**
 *
 * @author p1212374
 */
public class Case 
{
    private boolean etat; // True-Case dévoilée False-Case non dévoilée
    private int valeur_bouton; // 0-Rien 1-Drapeau 2-Point d'intérrogation
    private int valeur; // -1 si c'est une mine
    private Coordonnees c;
    
    public Case(Coordonnees _c,int _valeur)
    {
        etat = false;
        valeur_bouton = 0;
        valeur = _valeur;
        this.c = _c;
    }


    /**
     * @return the etat
     */
    public boolean getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    /**
     * @return the valeur
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * @param valeur the valeur to set
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * @return the c
     */
    public Coordonnees getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Coordonnees c) {
        this.c = c;
    }

    /**
     * @return the valeur_bouton
     */
    public int getValeur_bouton() {
        return valeur_bouton;
    }

    /**
     * @param valeur_bouton the valeur_bouton to set
     */
    public void setValeur_bouton(int valeur_bouton) {
        this.valeur_bouton = valeur_bouton;
    }
    
    
}
