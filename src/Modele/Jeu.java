/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Observable;

public class Jeu extends Observable
{
    private Grille g;
    private int nb_mines; // Nombre de mines courant de la grille   
    public Jeu()
    {
        nb_mines = 30;
        g = new Grille(15,15, nb_mines);
        //g.affGrille();
    }
    public boolean perdu(Coordonnees c) 
    {	
	if (g.getTab_cases()[c.getY()][c.getX()].getValeur()== -1)
	{
	   return true;	
	}	
	else 
        {
            return false;
	}
    }
    public void MettreDrapeau(Coordonnees c)
    {
        if(!g.getTab_cases()[c.getY()][c.getX()].getEtat()) // Si la case n'est pas dévoilée
        {
            g.getTab_cases()[c.getY()][c.getX()].setValeur_bouton(1);
            int nb = getNb_mines();
            if(nb > 0)
                setNb_mines(nb-1);
        }
    }
    
    public void MettrePointInterrogation(Coordonnees c)
    {
        g.getTab_cases()[c.getY()][c.getX()].setValeur_bouton(2);
        int nb = getNb_mines();
        setNb_mines(nb+1);
    }
    
    public void MettreCaseVide(Coordonnees c)
    {
        g.getTab_cases()[c.getY()][c.getX()].setValeur_bouton(0);
    }
    
    public void ClicCase(Coordonnees c)
    {
        g.getTab_cases()[c.getY()][c.getX()].setEtat(true);
        g.devoilement(c);
    }
   
    
    /**
     * @return the g
     */
    public Grille getG() {
        return g;
    }

    /**
     * @return the nb_mines
     */
    public int getNb_mines() {
        return nb_mines;
    }

    /**
     * @param nb_mines the nb_mines to set
     */
    public void setNb_mines(int nb_mines) {
        this.nb_mines = nb_mines;
    }
}
