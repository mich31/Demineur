package controlleur;

import java.awt.event.MouseListener;

import Modele.Coordonnees;
import Modele.Jeu;
import Vue.Frame;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class case_vue extends Rectangle {
int x;
int y;
Stage st;
Frame fenetre;
	public case_vue(int x,int y,Stage s,Frame f) 
        {
	
	this.x=x;
	this.y=y;
	this.st = s;
	this.fenetre=f;
	this.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override
 		public void handle(MouseEvent mouseEvent) 
                {
                    Jeu m = f.getJeu();
 		    if(mouseEvent.getButton().equals(MouseButton.SECONDARY))
 		    {   
                        boolean clikc1=false;
 		        if(mouseEvent.getClickCount() == 1 && !m.getG().getTab_cases()[x][y].getEtat())
 		         {
                           Coordonnees c=new Coordonnees(y,x);
 		            m.MettreDrapeau(c);	
 		          }
 		         else if( mouseEvent.getClickCount() == 2  && !m.getG().getTab_cases()[x][y].getEtat())
                         {
 		            Coordonnees c=new Coordonnees(y,x);
 		            m.MettrePointInterrogation(c);   
 		         } 
                        f.start(st);
 		    }
 		    
 		    else if (mouseEvent.getButton().equals(MouseButton.PRIMARY) )
 		    {    
 		    	Coordonnees c=new Coordonnees(y,x);
 		    	m.ClicCase(c);
                        if(m.perdu(c))
                        {
                            m.getG().devoile_tout();
                        }
                        f.start(st);
                    }
                    
	    }
            
 		});
        this.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent k)
            {
               if(k.getText().equals("n"))
               {
                   //f.Lancer();
               }
            }
        });
	}

	}