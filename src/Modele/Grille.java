/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author p1212374
 */
public class Grille 
{
    private Case[][] tab_cases;
    private int longueur;
    private int hauteur;
    private final int nb_mines;
    
    public Grille(int _longueur,int _hauteur,int _nb_mines)
    {
        this.longueur = _longueur;
        this.hauteur = _hauteur;
        this.nb_mines = _nb_mines;
        initGrille();
    }
    
    public void initGrille()
    {
        tab_cases = new Case[hauteur][longueur];
        for(int i = 0; i < hauteur; i++)
        {
            for(int j = 0; j < longueur; j++)
            {
                Coordonnees c = new Coordonnees(j,i);
                tab_cases[i][j] = new Case(c,0);
            }
        }
        poserMines();
        genere_chiffre();

    }
    
    public void affGrille()
    {
        for(int i = 0; i < hauteur; i++)
        {
            for(int j = 0; j < longueur; j++)
            {
                System.out.print(tab_cases[i][j].getValeur() + " ");
            }
            System.out.println();
        }
    }
    
    public void poserMines()
    {
        for(int i = 0; i < nb_mines; i++)
        {
            Coordonnees c = getCoordonneesAleatoires();
            tab_cases[c.getY()][c.getX()].setValeur(-1); 
        }
        System.out.println();
    }
    
    public Coordonnees getCoordonneesAleatoires()
    {
        int x,y;
        boolean b = false;
        do
        {
            x = Tool.monRandom(longueur-1, 0);
            y = Tool.monRandom(hauteur-1, 0);
            
            if(tab_cases[y][x].getValeur() == 0)
            {
                b = true;
            }  
        }while(!b);
        
        Coordonnees c = new Coordonnees(x,y);
        return c;
    }
    
    public void genere_chiffre()
    {
        int nombre_mines = 0;
        for(int i=0;i<hauteur;i++)
        {
            for(int j=0;j<longueur;j++)
            {
                if(tab_cases[i][j].getValeur() != -1)
                {
                    Case[] tab_voisin = get_voisin(tab_cases[i][j].getC());
                    for(int k=0;k<tab_voisin.length;k++)
                    {
                        if(tab_voisin[k].getValeur() == -1)
                        {
                            nombre_mines++;
                        }
                    }
                    tab_cases[i][j].setValeur(nombre_mines);
                    nombre_mines = 0;
                }
            }
        }
    }
    
    /* Retourne un tableau contenabnt les voisins d'une case passée en paramètres */
    public Case[] get_voisin(Coordonnees c)
    {
        Case[] tab_voisins;
        int longueur = this.longueur;
        int hauteur = this.hauteur;
        int x = c.getX();
        int y = c.getY();
        
        if(x == 0 && y == 0) // Coin Supérieur Gauche
        {
            tab_voisins = new Case[3];
            tab_voisins[0] = tab_cases[0][1];
            tab_voisins[1] = tab_cases[1][1];
            tab_voisins[2] = tab_cases[1][0];
        }
        else if(x == longueur-1 && y == 0) // Coin Supérieur Droite
        {
            tab_voisins = new Case[3];
            tab_voisins[0] = tab_cases[0][longueur-2];
            tab_voisins[1] = tab_cases[1][longueur-2];
            tab_voisins[2] = tab_cases[1][longueur-1];
        }
        else if(x == 0 && y == hauteur-1) // Coin Inférieur Gauche
        {
            tab_voisins = new Case[3];
            tab_voisins[0] = tab_cases[hauteur-1][1];
            tab_voisins[1] = tab_cases[hauteur-2][1];
            tab_voisins[2] = tab_cases[hauteur-2][0];
        }
        else if(x == longueur-1 && y == hauteur-1) // Coin Inférieure Droite
        {
            tab_voisins = new Case[3];
            tab_voisins[0] = tab_cases[hauteur-1][longueur-2];
            tab_voisins[1] = tab_cases[hauteur-2][longueur-2];
            tab_voisins[2] = tab_cases[hauteur-2][longueur-1];
        }
        else if(x == 0) // Limite Gauche
        {
            tab_voisins = new Case[5];
            tab_voisins[0] = tab_cases[y+1][x];
            tab_voisins[1] = tab_cases[y+1][x+1];
            tab_voisins[2] = tab_cases[y][x+1];
            tab_voisins[3] = tab_cases[y-1][x+1];
            tab_voisins[4] = tab_cases[y-1][x];
        }
        else if(x == longueur-1) // Limite Droite
        {
            tab_voisins = new Case[5];
            tab_voisins[0] = tab_cases[y+1][x];
            tab_voisins[1] = tab_cases[y+1][x-1];
            tab_voisins[2] = tab_cases[y][x-1];
            tab_voisins[3] = tab_cases[y-1][x-1];
            tab_voisins[4] = tab_cases[y-1][x];
        }
        else if(y == 0) // Limite Supérieure
        {
            tab_voisins = new Case[5];
            tab_voisins[0] = tab_cases[y][x-1];
            tab_voisins[1] = tab_cases[y+1][x-1];
            tab_voisins[2] = tab_cases[y+1][x];
            tab_voisins[3] = tab_cases[y+1][x+1];
            tab_voisins[4] = tab_cases[y][x+1];
        }
        else if(y == hauteur-1) // Limite Inférieure
        {
            tab_voisins = new Case[5];
            tab_voisins[0] = tab_cases[y][x-1];
            tab_voisins[1] = tab_cases[y-1][x-1];
            tab_voisins[2] = tab_cases[y-1][x];
            tab_voisins[3] = tab_cases[y-1][x+1];
            tab_voisins[4] = tab_cases[y][x+1];
        }
        else
        {
            tab_voisins = new Case[8];
            tab_voisins[0] = tab_cases[y-1][x-1];
            tab_voisins[1] = tab_cases[y][x-1];
            tab_voisins[2] = tab_cases[y+1][x-1];
            tab_voisins[3] = tab_cases[y+1][x];
            tab_voisins[4] = tab_cases[y+1][x+1];
            tab_voisins[5] = tab_cases[y][x+1];
            tab_voisins[6] = tab_cases[y-1][x+1];
            tab_voisins[7] = tab_cases[y-1][x];
        }
        
        return tab_voisins;
    }
    
    public void devoile_tout()
    {
        for(int i = 0 ; i < hauteur ; i++)
        {
            for(int j = 0 ; j < longueur ; j++)
            {
                tab_cases[i][j].setEtat(true);
            }
        }
    }
    
    /*Fonction de dévoilement*/
    public void devoilement(Coordonnees c)
    {
        Case[] voisin = get_voisin(c);
        boolean mine = false;
        Case current_case ;
        current_case = tab_cases[c.getY()][c.getX()] ;
        
         if (current_case.getValeur()!= -1 && current_case.getValeur()!= 0)
         {
        	 current_case.setEtat(true); 
         }
         if(current_case.getValeur() == 0)
         {
             current_case.setEtat(true);
	         for(int i = 0; i < voisin.length; i++)
	         {
                     if(!voisin[i].getEtat())
                     {
                         devoilement(voisin[i].getC());
                     }
	         }
         }
    }
    
    /**
     * @return the tab_cases
     */
    public Case[][] getTab_cases() {
        return tab_cases;
    }

    /**
     * @param tab_cases the tab_cases to set
     */
    public void setTab_cases(Case[][] tab_cases) {
        this.tab_cases = tab_cases;
    }

    /**
     * @return the longueur
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * @param longueur the longueur to set
     */
    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    /**
     * @return the hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * @param hauteur the hauteur to set
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * @return the nb_mines
     */
    public int getNb_mines() {
        return nb_mines;
    }

    
}
