package main.java.gui;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import java.lang.Object;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class StartPage {
    private Pane root;
    private Button btn;
    private int largeur,longueur;
    private double paneWidthTmp,paneHeightTmp;

    public StartPage(Pane root, int l, int h){
        this.root = root; 
        root.setMinWidth(l);
        root.setMinHeight(h);
        setBackImage( );
        longueur = h;
        largeur = l;
        paneWidthTmp = largeur;
        paneHeightTmp = longueur;
    }

    public void goStartLeJeu(Stage stage, Scene scene, Pane root){
        Text title = new Text("PONG ");
        Text credit = new Text("@SuperGroupeGenial");
        btn = new Button("START");
        root.widthProperty().addListener(e -> {
                credit.setFont(Font.font("Lucida Sans Unicode", FontPosture.ITALIC, (30*root.getWidth())/paneWidthTmp));
                credit.setX(root.getWidth()/2 + root.getWidth()/5 );
                title.setFont(Font.font("Lucida Sans Unicode", FontPosture.ITALIC, (150*root.getWidth())/paneWidthTmp));
                title.setX((root.getWidth()/2) - (root.getWidth()/6));
                btn.setMinWidth((100*root.getWidth())/paneWidthTmp);
                btn.setLayoutX((root.getWidth()/2) - (root.getWidth()/25));
            });
        
            title.setY(80);
            root.heightProperty().addListener(e-> {
                title.setY((root.getHeight()/3));
                credit.setY(root.getHeight()-root.getHeight()/15);
                btn.setMinHeight((50*root.getHeight())/paneHeightTmp);
                btn.setLayoutY(root.getHeight()-root.getHeight()/3);
            });
            this.root.getChildren().add(credit);
            this.root.getChildren().add(title);
            this.root.getChildren().add(btn);
        
         btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    public void setBackImage(){
        Image img = new Image("file:src/main/java/gui/image/background.gif", root.getWidth(), root.getHeight(), false, false);
        ImageView bg = new ImageView(img);
        root.widthProperty().addListener(e->{

            bg.setFitWidth(root.getWidth());
        });
        root.heightProperty().addListener(e->{
            bg.setFitHeight(root.getHeight());
        });
        root.getChildren().add(bg);
    }

}
