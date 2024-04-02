package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.awt.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Court;
import model.RacketController;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.control.*;

public class Parametre{
    private static int btnPosition=150;
    private Pane root;
    private BorderPane root2;
    private double paneWidthTmp,paneHeightTmp;

    Button modeDeJeu;
    boolean b1,b2,b3,b4,b5,b6,b7,b8;
    Label label1,label2,label3,label4,label5,label6,label7,label8;
    Sounds s = new Sounds();

    public Parametre(Stage stage , Scene menu ,Pane roots, int l, int h, Scene parametre){
        this.root=roots;
        root.setMinWidth(l);
        root.setMinHeight(h);
        paneWidthTmp = l;
        paneHeightTmp = h;
        Button exit=new Button("Menu");
        root.widthProperty().addListener(e-> {
            exit.setTranslateX((1000 * l)/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            exit.setTranslateY((20 * h)/paneHeightTmp);
        });
        root.getChildren().add(exit);
        
        exit.setOnAction(e -> {
            stage.setScene(menu);
            stage.show();
        });
        this.label1 = new Label("Touche pour monter joueur 1 :  "+ModeDeJeu.p1UpKey);
        root.widthProperty().addListener(e-> {
            label1.setLayoutX(300*roots.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            label1.setLayoutY(100*roots.getHeight()/paneHeightTmp);
        });
        Button changer1 = new Button("changer");
        changer1.setOnAction(ev -> {
            this.b1=true; 
            this.b2=false; 
            this.b3=false;
            this.b4=false;
            this.b5=false;
            this.b6=false;
            this.b7=false;
            this.b8=false;
        } );
        this.label2 = new Label("Touche pour descendre joueur 1 :  "+ModeDeJeu.p1DownKey);

        root.widthProperty().addListener(e-> {
            changer1.setLayoutX(550*root.getWidth()/paneWidthTmp);
            // changer1.setWidth(changer1.getWidth()*root.getWidth()/paneWidthTmp);
            label2.setLayoutX(300*root.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            changer1.setLayoutY(95*root.getHeight()/paneHeightTmp);
            label2.setLayoutY(150*root.getHeight()/paneHeightTmp);
        });

        Button changer2 = new Button("changer");
        changer2.setOnAction(ev -> {
            this.b1=false; 
            this.b2=true; 
            this.b3=false;
            this.b4=false;
            this.b5=false;
            this.b6=false;
            this.b7=false;
            this.b8=false;
        } );
        this.label3 = new Label("Touche pour monter joueur 2 :  "+ModeDeJeu.p2UpKey);
        root.widthProperty().addListener(e-> {
            changer2.setLayoutX(550*root.getWidth()/paneWidthTmp);
            label3.setLayoutX(300*root.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            changer2.setLayoutY(145*root.getHeight()/paneHeightTmp);
            label3.setLayoutY(200*root.getHeight()/paneHeightTmp);
        });

        Button changer3 = new Button("changer");
        changer3.setOnAction(ev -> {
            this.b1=false; 
            this.b2=false; 
            this.b3=true;
            this.b4=false;
            this.b6=false;
            this.b7=false;
            this.b8=false;
        } );
        this.label4 = new Label("Touche pour descendre joueur 2 :  "+ModeDeJeu.p2DownKey);

        root.widthProperty().addListener(e-> {
            changer3.setLayoutX(550*root.getWidth()/paneWidthTmp);
            label4.setLayoutX(300*root.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            changer3.setLayoutY(195*root.getHeight()/paneHeightTmp);
            label4.setLayoutY(250*root.getHeight()/paneHeightTmp);
        });
        Button changer4 = new Button("changer");
        changer4.setOnAction(ev -> {
            this.b1=false; 
            this.b2=false; 
            this.b3=false;
            this.b4=true;
            this.b5=false;
            this.b6=false;
            this.b7=false;
            this.b8=false;
        } );
        this.label5 = new Label("Touche pour monter joueur 3 :  "+ModeDeJeu.p3UpKey);
        root.widthProperty().addListener(e-> {
            label5.setLayoutX(300*root.getWidth()/paneWidthTmp);
           changer4.setLayoutX(550*root.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            changer4.setLayoutY(245*root.getHeight()/paneHeightTmp);
            label5.setLayoutY(300*root.getHeight()/paneHeightTmp);
        });
        
        Button changer5 = new Button("changer");
        changer5.setOnAction(ev -> {
            this.b1=false; 
            this.b2=false; 
            this.b3=false;
            this.b4=false;
            this.b5=true;
            this.b6=false;
            this.b7=false;
            this.b8=false;
        } );
        this.label6 = new Label("Touche pour descendre joueur 3 :  "+ModeDeJeu.p3DownKey);

        root.widthProperty().addListener(e-> {
            changer5.setLayoutX(550*root.getWidth()/paneWidthTmp);
            label6.setLayoutX(300*root.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            changer5.setLayoutY(295*root.getHeight()/paneHeightTmp);
            label6.setLayoutY(350*root.getHeight()/paneHeightTmp);
        });
        Button changer6 = new Button("changer");
        changer6.setOnAction(ev -> {
            this.b1=false; 
            this.b2=false; 
            this.b3=false;
            this.b4=false;
            this.b5=false;
            this.b6=true;
            this.b7=false;
            this.b8=false;
        } );
        this.label7 = new Label("Touche pour monter joueur 4 :  "+ModeDeJeu.p4UpKey);
        root.widthProperty().addListener(e-> {
            changer6.setLayoutX(550*root.getWidth()/paneWidthTmp);
            label7.setLayoutX(300*root.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            changer6.setLayoutY(345*root.getHeight()/paneHeightTmp);
            label7.setLayoutY(400*root.getHeight()/paneHeightTmp);
        });
        
        Button changer7 = new Button("changer");
        changer7.setOnAction(ev -> {
            this.b1=false; 
            this.b2=false; 
            this.b3=false;
            this.b4=false;
            this.b5=false;
            this.b6=false;
            this.b7=true;
            this.b8=false;
        } );
        this.label8 = new Label("Touche pour descendre joueur 4 :  "+ModeDeJeu.p4DownKey);

        root.widthProperty().addListener(e-> {
            changer7.setLayoutX(550*root.getWidth()/paneWidthTmp);
            label8.setLayoutX(300*root.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            changer7.setLayoutY(395*root.getHeight()/paneHeightTmp);
            label8.setLayoutY(450*root.getHeight()/paneHeightTmp);
        });
        Button changer8 = new Button("changer");
        changer8.setOnAction(ev -> {
            this.b1=false; 
            this.b2=false; 
            this.b3=false;
            this.b4=false;
            this.b5=false;
            this.b6=false;
            this.b7=false;
            this.b8=true;
        } );
        root.widthProperty().addListener(e-> {
            changer8.setLayoutX(550*root.getWidth()/paneWidthTmp);
        });
        root.heightProperty().addListener(e-> {
            changer8.setLayoutY(445*root.getHeight()/paneHeightTmp);
        });

        root.getChildren().addAll(label1,label2,label3,label4,label5,label6,label7,label8);
        root.getChildren().addAll(changer1,changer2,changer3,changer4,changer5,changer6,changer7,changer8);
        parametre.setOnKeyPressed(ev ->{
            if(b1){
                ModeDeJeu.p1UpKey=ev.getCode();
                this.label1.setText("Touche pour monter joueur 1 :  "+ModeDeJeu.p1UpKey);
            }

            if(b2){
                ModeDeJeu.p1DownKey=ev.getCode();
                this.label2.setText("Touche pour descendre joueur 1 :  "+ModeDeJeu.p1DownKey);
            }

            if(b3){
                ModeDeJeu.p2UpKey=ev.getCode();
                this.label3.setText("Touche pour monter joueur 2 :  "+ModeDeJeu.p2UpKey);
            }

            if(b4){
                ModeDeJeu.p2DownKey=ev.getCode();
                this.label4.setText("Touche pour descendre joueur 2 :  "+ModeDeJeu.p2DownKey);
            }

            if(b5){
                ModeDeJeu.p3UpKey=ev.getCode();
                this.label5.setText("Touche pour monter joueur 3 :  "+ModeDeJeu.p3UpKey);
            }

            if(b6){
                ModeDeJeu.p3DownKey=ev.getCode();
                this.label6.setText("Touche pour descendre joueur 3 :  "+ModeDeJeu.p3DownKey);
            }

            if(b7){
                ModeDeJeu.p4UpKey=ev.getCode();
                this.label7.setText("Touche pour monter joueur 4 :  "+ModeDeJeu.p4UpKey);
            }

            if(b8){
                ModeDeJeu.p4DownKey=ev.getCode();
                this.label8.setText("Touche pour descendre joueur 4 :  "+ModeDeJeu.p4DownKey);
            }
        });
    }
}
