package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;

public class Vitesse {
   // private static int btnPosition=150;
    private Pane root;
    Hyperlink hyperlink1;
    Button Nfacile;
    Button NDificile;
    Button NMoyen;
    private double paneWidthTmp,paneHeightTmp;

    public Sounds sfx = new Sounds();

    // private int largeur, hauteur;
    // private Court court;

    public Vitesse(Pane root, int l, int h){
        hyperlink1 = new Hyperlink("Menu");
        this.root=root;
        root.setMinWidth(l);
        root.setMinHeight(h);
        paneWidthTmp = l;
        paneHeightTmp = h;
        Text txt = new Text("CHOISIS TON NIVEAU DE JEU");
        root.widthProperty().addListener(e->{
            txt.setLayoutX(10*root.getWidth()/paneWidthTmp);
            txt.setX(root.getWidth()/2-100);
        });
        root.heightProperty().addListener(e->{
            txt.setY(80*root.getHeight()/paneHeightTmp);
        });
        Font fon = new Font ("Serif",20);
        txt.setFont(fon);
        this.root.getChildren().add(txt);
        setBackImage("bouge1.gif");
    }
    public void btnspinner(Stage stage, Scene gamechoixTerrain,Pane p,boolean bot,boolean vbot,Scene menu){
       
        NMoyen = new Button("niveau Moyen");
        NMoyen.setLayoutX(400);
        NMoyen.setLayoutY(400);


        NDificile = new Button("niveau Difficile");
        Nfacile = new Button("niveau Facile");

        this.root.widthProperty().addListener(e->{
            NMoyen.setMinWidth((150*this.root.getWidth())/paneWidthTmp);
            Nfacile.setMinWidth((150*this.root.getWidth())/paneWidthTmp);
            NDificile.setMinWidth((150*this.root.getWidth())/paneWidthTmp);
            Nfacile.setLayoutX((100*this.root.getWidth())/paneWidthTmp);
            NMoyen.setLayoutX((100*this.root.getWidth())/paneWidthTmp);
            NDificile.setLayoutX((100*this.root.getWidth())/paneWidthTmp);
        });
        this.root.heightProperty().addListener(e -> {
            NMoyen.setMinHeight((25 * this.root.getHeight())/paneHeightTmp);
            Nfacile.setMinHeight((25 * this.root.getHeight())/paneHeightTmp);
            NDificile.setMinHeight((25 * this.root.getHeight())/paneHeightTmp);
            Nfacile.setLayoutY((150 * this.root.getHeight())/paneHeightTmp);
            NMoyen.setLayoutY((230 * this.root.getHeight())/paneHeightTmp);
            NDificile.setLayoutY((310 * this.root.getHeight())/paneHeightTmp);
        });
            this.root.getChildren().addAll(NMoyen,NDificile,Nfacile);
       
        Choix choix = new Choix(p, 1100, 600);
        
        actionbtn(choix,stage,gamechoixTerrain,this.Nfacile,200,menu,bot,vbot);
        actionbtn(choix,stage,gamechoixTerrain,this.NMoyen,320,menu,bot,vbot);
        actionbtn(choix,stage,gamechoixTerrain,this.NDificile,400,menu,bot,vbot);
        hyperlink1.setOnAction(e -> {
            sfx.playSFX(7);
            efface();
            stage.setScene(menu);
            stage.show();
            });
        this.root.getChildren().add(hyperlink1);
        this.root.widthProperty().addListener(e->{
            Nfacile.setLayoutX(500*root.getWidth()/paneWidthTmp);
            NMoyen.setLayoutX(500*root.getWidth()/paneWidthTmp);
            NDificile.setLayoutX(500*root.getWidth()/paneWidthTmp);
        });
        this.root.heightProperty().addListener(e -> {
            Nfacile.setLayoutY(150*root.getHeight()/paneHeightTmp);
            NMoyen.setLayoutY(230*root.getHeight()/paneHeightTmp);
            NDificile.setLayoutY(310*root.getHeight()/paneHeightTmp);
        });
    }
    private void efface(){
        this.root.getChildren().removeAll(this.NDificile,this.NMoyen,this.Nfacile,this.hyperlink1);
    }

    public void actionbtn(Choix c,Stage s,Scene sc,Button b,int vitesse,Scene m,boolean bot,boolean vbot){
        b.setOnAction(e-> {
                sfx.playSFX(7);
                efface();
                c.btnTerrain(s, sc,bot,vbot,vitesse,m);
               
                s.setScene(sc);
                s.show();
         
        });
    }
    public void setBackImage(String imgName){
        BackgroundSize bg= new BackgroundSize(root.getWidth(), root.getHeight(),false,false,true,true);
        Image img = new Image("file:image/"+imgName, root.getWidth(), root.getHeight(), false, false);
        BackgroundImage bImg = new BackgroundImage(img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            bg);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);
    }

}
