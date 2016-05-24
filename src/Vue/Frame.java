 package Vue;
	
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import Modele.Coordonnees;
import Modele.Grille;
import Modele.Jeu;
import controlleur.case_vue;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;



public class Frame extends Application {
	 
	 Jeu jeu;
	 
	 public Frame()
	 {   
		 this.jeu = new Jeu();
	 }
	 
	public Jeu getJeu() {
		return jeu;
	}

	
	@Override
	public void start(Stage primaryStage) 
        {		
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,520,630,Color.rgb(0,175,240));
            primaryStage.setScene(scene);
            GridPane gridpane = new GridPane();
      	    root.getChildren().add(gridpane);
      	    primaryStage.setTitle("Démineur");
            primaryStage.setResizable(false);
		
            Image flag=new Image("img/flag.png");
            Image mine=new Image("img/bmb.png");
            Grille g = jeu.getG();
            root.getChildren().clear();
        for(int i=0;i<g.getHauteur();i++)
        {
            for(int j=0;j<g.getLongueur();j++)
            { 
            	case_vue rectangle = new case_vue(i,j,primaryStage,this);
            	if(g.getTab_cases()[i][j].getEtat()) // Si la case est devoilee
            	{  	
	               rectangle.setX(36+(j*30));
	               rectangle.setY(150+(i*30));
	               rectangle.setWidth(28);	
	               rectangle.setHeight(28);	               
	               rectangle.setFill(Color.WHITE);  
	               root.getChildren().add(rectangle);
	               if(g.getTab_cases()[i][j].getValeur() != 0 && g.getTab_cases()[i][j].getValeur() != -1) //si c'est un chiffre 
	           	{
	           		Text text = new Text();
	    	               	text.setFont(new Font(20));
	    	               	text.setX(45+(j*30));
	    	               	text.setY(170+(i*30));
	    	               	text.setTextAlignment(TextAlignment.JUSTIFY);
	    	               	text.setText(Integer.toString(g.getTab_cases()[i][j].getValeur()));
	    	               	root.getChildren().add(text);
	    	               	
	           	}  
	               else if (g.getTab_cases()[i][j].getValeur() == -1)// si c'est une bombe 
	               {              
         	               root.getChildren().remove(rectangle);
          	               rectangle.setFill(new ImagePattern(mine));  
          	               root.getChildren().add(rectangle);
	               }	   
            	}
            	else // Si la case n'est pas d�voil�e
            	{
            		switch(g.getTab_cases()[i][j].getValeur_bouton())
            		{
            			case 0:
         	               rectangle.setX(36+(j*30));
         	               rectangle.setY(150+(i*30));
         	               rectangle.setWidth(28);	
         	               rectangle.setHeight(28);	               
         	               rectangle.setFill(Color.BLACK);  
         	               root.getChildren().add(rectangle);
            				break;
            			case 1: // Drapeau
         	               rectangle.setX(36+(j*30));
         	               rectangle.setY(150+(i*30));
         	               rectangle.setWidth(28);	
         	               rectangle.setHeight(28);	               
         	               rectangle.setFill(new ImagePattern(flag));  
         	               root.getChildren().add(rectangle);
            				break;
            			case 2:
            			   rectangle.setX(36+(j*30));
          	               rectangle.setY(150+(i*30));
          	               rectangle.setWidth(28);	
          	               rectangle.setHeight(28);	               
          	               rectangle.setFill(new ImagePattern(mine));  
          	               root.getChildren().add(rectangle);
            				break;
            			default:
            				break;
            		}
            	}
            }
        }
        primaryStage.show();
       }
	public static void Lancer() {
		launch();
	}
}
