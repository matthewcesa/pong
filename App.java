package gui;

import javax.print.attribute.standard.PageRanges;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import javafx.scene.Scene;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import main.java.gui.StartPage;
import model.Court;
import model.RacketController;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class App extends Application {
    @Override
    public void start(Stage Stage) {
      
        Pane rootMenu = new Pane();
        Pane rootStartPage = new Pane();
        Pane choixVitesse=new Pane();
        Pane choix=new Pane();
        Pane modeDeJeuP=new Pane();
        // Scene choixS=new Scene(choix);
        Pane rootParametre=new Pane();
        Scene parametreScene=new Scene(rootParametre);
       
       //choix
        Scene choiceScene = new Scene(rootMenu);
        Scene gameChoixVitesse = new Scene(choixVitesse);
        Scene choixS=new Scene(choix);
        Scene modeDejeuS=new Scene(modeDeJeuP);
        Scene StartPageScene = new Scene(rootStartPage);

        StartPage startpage = new StartPage(rootStartPage, 1100, 600);
        Menu menu = new Menu(rootMenu, 1100, 600);

        startpage.goStartLeJeu(Stage, choiceScene, rootStartPage);
        menu.ajouterLien(Stage, gameChoixVitesse,choixVitesse ,"1 Joueur",true,false,choiceScene);
        menu.ajouterLien(Stage, choixS,choix,"2 Joueur",false,false,choiceScene);
        menu.ajouterLien(Stage, choixS,choix, "4 joueurs",false,true, choiceScene);
        Parametre p=new Parametre(Stage , choiceScene ,rootParametre, 1100, 600,parametreScene);
        menu.misEnForme(Stage, parametreScene, 1100);
        Image logo = new Image ("file:Image/logo.jpg");
        Stage.getIcons().add(logo);
        Stage.setScene(StartPageScene); 
        Stage.show();
    }
}
