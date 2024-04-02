package gui;


import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Court;
import model.RacketController;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;

public class Menu {
    private static double btnPosition;
    private Pane root;
    private int largeur, hauteur;
    private double paneWidthTmp,paneHeightTmp;
    // private final double xMargin = 50.0;

    public Sounds s = new Sounds();
    public Sounds sfx = new Sounds();


    public Menu(Pane root, int l, int h){
        this.root=root;
        root.setMinWidth(l);
        root.setMinHeight(h);
        setBackImage("bouge1.gif"); 
        paneWidthTmp = l;
        paneHeightTmp = h;
        root.heightProperty().addListener(e-> {
            btnPosition = (150 * h)/paneHeightTmp;});
    }

    public void ajouterLien(Stage stage, Scene scene,Pane rt, String name,boolean bot,boolean vbot,Scene menu){
        
        Button btn = new Button(name);
            Image boutton = new Image ("file:Image/logo.jpg");
            BackgroundImage bImg = new BackgroundImage(boutton,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
            
            root.widthProperty().addListener(e ->{
                btn.setMinWidth((300*root.getWidth()) / paneWidthTmp);
                btn.setLayoutX((400*root.getWidth()) / paneWidthTmp);
            });

            root.heightProperty().addListener(e -> {
                btn.setMinHeight((50 * root.getHeight()) / paneHeightTmp);
                btn.setLayoutY((btnPosition * root.getHeight()) / paneHeightTmp);
                btnPosition+=80 ;
            });

            btn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e){
                    
                    if(bot){
                        Vitesse vitesse=new Vitesse(rt,1100,600);
                        Pane choix=new Pane();
                        Scene s=new Scene(choix);
                        
                        vitesse.btnspinner(stage, s, choix,bot,vbot,menu);
                    }else{
                        Choix choix=new Choix (rt,1100,600);
                        choix.btnTerrain(stage, scene,bot,vbot,400,menu);
                    }
                    sfx.playSFX(7);
                    s.stopMusic();
                    stage.setScene(scene);
                    stage.show();
                    stage.setScene(scene);
                    stage.show();
                }
            });
            this.root.getChildren().add(btn);
            
        }
        public void misEnForme(Stage stage ,Scene scene,int l){
            //titre
            Text title = new Text(" PONG ");
            s.playMusic(0);
            Button btn = new Button("Mode de jeu ");
            Button btn2 = new Button("Parametres");
        root.widthProperty().addListener(e->{
            title.setFont(Font.font("Lucida Sans Unicode", FontPosture.ITALIC, 50));
            title.setX(root.getWidth()/2-100);
            btn.setMinWidth((300 * root.getWidth()) / paneWidthTmp);
            btn.setLayoutX((400 * root.getWidth()) / paneWidthTmp);
            btn2.setMinWidth((125 * root.getWidth()) / paneWidthTmp);
        });
        root.heightProperty().addListener(e -> {
            title.setY(root.getHeight()/5);
            btn.setMinHeight((50 * root.getHeight()) / paneHeightTmp);
            btn.setLayoutY((btnPosition * root.getHeight()) / paneHeightTmp);
            btn2.setMinHeight((30 * root.getHeight()) / paneHeightTmp);
            btnPosition+=80;
        });

        //titre
        this.root.getChildren().add(title);
        this.root.getChildren().add(btn);
        this.root.getChildren().add(btn2);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                sfx.playSFX(7);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
   
    public void setBackImage(String imgName){
        Image img = new Image("file:image/"+imgName, root.getWidth(), root.getHeight(), false, true);
        ImageView bg = new ImageView();
        bg.setImage(img);
        root.widthProperty().addListener(e->{
            bg.setFitWidth(root.getWidth());
        });
        root.heightProperty().addListener(e->{
            bg.setFitHeight(root.getHeight());
        });
        root.getChildren().add(bg);
    }
}
