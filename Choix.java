package gui;

import java.io.Console;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Choix{
    private static int btnPosition=150;
    private Pane rootChoix;
    private static int choixTemps;
    private double paneWidthTmp,paneHeightTmp;

    Sounds s = new Sounds();
    public Sounds sfx = new Sounds();

    public Choix (Pane root, int l, int h){
        this.rootChoix=root;
        root.setMinWidth(l);
        root.setMinHeight(h);
        paneWidthTmp = l;
        paneHeightTmp = h;
        s.playMusic(3);
        setBackImage("bouge1.gif");
    }
    public void btnTerrain(Stage stage,Scene scene,boolean bot ,boolean vbot,int vitesse,Scene menu){
        VBox root1v1 = new VBox();
        VBox root1vbot = new VBox();
        VBox root2v2=new VBox();
        Scene gameScene2v2=new Scene(root2v2);
        Scene gameScene1v1 = new Scene(root1v1);
        Scene gameScene1vbot = new Scene(root1vbot);
        Image res = new Image("file:Image/nouveaux/terrain pong 1.png");
        Image res2 = new Image("file:Image/nouveaux/terrain pong 2.png");
        Image res3 = new Image("file:Image/nouveaux/terrain pong 3.png");
        ImageView imageview = new ImageView(res);
        ImageView imageview2 = new ImageView(res2);
        ImageView imageview3 = new ImageView(res3);

        Spinner<Integer> spin = new Spinner(1,10,1);
        choixTemps = 1*60;
        spin.getValueFactory().wrapAroundProperty().set(true);
        spin.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spin.setEditable(false);
        spin.valueProperty().addListener(new ChangeListener<Integer>() {
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue){
                choixTemps = spin.getValue()*60;
            }
        });
        spin.setEditable(false);
        Text label = new Text("Choisir la durée de la partie (en mn)");
        Button btn = new Button("Terrain 1");
        Button btn1 = new Button("Terrain 2");         
        Button btn3 = new Button("Terrain 3");    
        rootChoix.widthProperty().addListener(e ->{
            imageview.setX((150 *  rootChoix.getWidth()) / paneWidthTmp);
            imageview.setFitWidth((200*  rootChoix.getWidth()) / paneWidthTmp);
            imageview2.setX((480*  rootChoix.getWidth()) / paneWidthTmp);
            imageview2.setFitWidth((200*  rootChoix.getWidth()) / paneWidthTmp);
            imageview3.setX((800*  rootChoix.getWidth()) / paneWidthTmp);
            imageview3.setFitWidth((200 * rootChoix.getWidth()) / paneWidthTmp);
            
            spin.setLayoutX((500 * rootChoix.getWidth()) / paneWidthTmp);
            spin.setMinWidth((150 * rootChoix.getWidth()) / paneWidthTmp);
            label.setFont(Font.font("Lucida Sans Unicode", FontPosture.ITALIC, 20));
            label.setLayoutX((230 * rootChoix.getWidth()) / paneWidthTmp);

            btn.setMinWidth((200 * rootChoix.getWidth()) / paneWidthTmp);
            btn.setLayoutX((150 * rootChoix.getWidth()) / paneWidthTmp); 
            btn1.setMinWidth((200 * rootChoix.getWidth()) / paneWidthTmp);
            btn3.setMinWidth((200 * rootChoix.getWidth()) / paneWidthTmp);
            btn1.setLayoutX((480 * rootChoix.getWidth()) / paneWidthTmp); 
            btn3.setLayoutX((800 * rootChoix.getWidth()) / paneWidthTmp); 
        });

        rootChoix.heightProperty().addListener(e->{
            imageview.setY((200 * rootChoix.getHeight()) / paneHeightTmp);
            imageview.setFitHeight((200 * rootChoix.getHeight()) / paneHeightTmp);
            imageview2.setY((200 * rootChoix.getHeight()) / paneHeightTmp);
            imageview2.setFitHeight((200 * rootChoix.getHeight()) / paneHeightTmp);
            imageview3.setY((200 * rootChoix.getHeight()) / paneHeightTmp);
            imageview3.setFitHeight((200 * rootChoix.getHeight()) / paneHeightTmp); 

            spin.setLayoutY((rootChoix.getHeight()/8 * rootChoix.getHeight()) / paneHeightTmp);
            spin.setMinHeight((50 * rootChoix.getHeight()) / paneHeightTmp);
            label.setLayoutY((rootChoix.getHeight()/8 * rootChoix.getHeight()) / paneHeightTmp + rootChoix.getHeight()/25);

            btn3.setMinHeight((50 * rootChoix.getHeight()) / paneHeightTmp);
            btn.setMinHeight((50 * rootChoix.getHeight()) / paneHeightTmp);
            btn.setLayoutY((410 * rootChoix.getHeight()) / paneHeightTmp);
            btn1.setMinHeight((50 * rootChoix.getHeight()) / paneHeightTmp);
            btn1.setLayoutY((410 * rootChoix.getHeight()) / paneHeightTmp);
            btn3.setLayoutY((410 * rootChoix.getHeight()) / paneHeightTmp);  
        });
        this.rootChoix.getChildren().addAll(spin,label,btn,btn1,btn3,imageview,imageview2,imageview3)  ; 
        btn.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e){
        if(bot){
            ModeDeJeu.game1v1(stage,gameScene1vbot, root1vbot, bot ,vitesse,menu);
            stage.setScene(gameScene1vbot);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root1vbot.getWidth(), root1vbot.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 1.png ", root1vbot.getWidth(), root1vbot.getHeight(), false, false);
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
            Background bGround = new Background(bImg);
            root1vbot.setBackground(bGround);
        }else if(vbot){
            ModeDeJeu.game2v2(stage, gameScene2v2, root2v2, menu, vitesse);
            stage.setScene(gameScene2v2);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root2v2.getWidth(), root2v2.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 1.png ", root2v2.getWidth(), root2v2.getHeight(), false, false);
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
            Background bGround = new Background(bImg);
        }else{

            ModeDeJeu.game1v1(stage,gameScene1v1, root1v1, bot ,200,menu);
            stage.setScene(gameScene1v1);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root1v1.getWidth(), root1v1.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 1.png", root1vbot.getWidth(), root1v1.getHeight(), false, false);
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
            Background bGround = new Background(bImg);
            root1v1.setBackground(bGround);
        }
       
        stage.show();
    
    }
});
btn1.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e){
        if(bot){
            ModeDeJeu.game1v1(stage,gameScene1vbot, root1vbot, bot ,vitesse,menu);
            stage.setScene(gameScene1vbot);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root1vbot.getWidth(), root1vbot.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 2.png ", root1vbot.getWidth(), root1vbot.getHeight(), false, false);
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
            Background bGround = new Background(bImg);
            root1vbot.setBackground(bGround);
        }else if(vbot){
            ModeDeJeu.game2v2(stage, gameScene2v2, root2v2, menu, vitesse);
            stage.setScene(gameScene2v2);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root2v2.getWidth(), root2v2.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 2.png ",  root2v2.getWidth(),  root2v2.getHeight(), false, false);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
            Background bGround = new Background(bImg);
            root2v2.setBackground(bGround);
        }else{

            ModeDeJeu.game1v1(stage,gameScene1v1, root1v1, bot ,200,menu);
            stage.setScene(gameScene1v1);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root1v1.getWidth(), root1v1.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 2.png", root1v1.getWidth(), root1v1.getHeight(), false, false);
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
                Background bGround = new Background(bImg);
                root1v1.setBackground(bGround);
        }
                stage.show();
         
    }
});
btn3.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e){
        if(bot){
            ModeDeJeu.game1v1(stage,gameScene1vbot, root1vbot, bot ,vitesse,menu);
            stage.setScene(gameScene1vbot);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root1vbot.getWidth(), root1vbot.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 3.png ", root1vbot.getWidth(), root1vbot.getHeight(), false, false);
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
        }else if(vbot){
            ModeDeJeu.game2v2(stage, gameScene2v2, root2v2, menu, vitesse);
            stage.setScene(gameScene2v2);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root2v2.getWidth(), root2v2.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 3.png",  root2v2.getWidth(),  root2v2.getHeight(), false, false);
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
            Background bGround = new Background(bImg);
            root2v2.setBackground(bGround);
        }else{
            ModeDeJeu.game1v1(stage,gameScene1v1, root1v1, bot ,200,menu);
            stage.setScene(gameScene1v1);
            sfx.playSFX(7);
            s.stopMusic();
            BackgroundSize bg= new BackgroundSize(root1v1.getWidth(), root1v1.getHeight(),false,false,true,true);
            Image img = new Image("file:Image/nouveaux/terrain pong 3.png", root1vbot.getWidth(), root1v1.getHeight(), false, false);
            BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                bg);
            Background bGround = new Background(bImg);
            root1v1.setBackground(bGround);
        }
                stage.show();
    }
});

    }        
    
    public void setBackImage(String imgName){
        BackgroundSize bg= new BackgroundSize(rootChoix.getWidth(), rootChoix.getHeight(),false,false,true,true);
        Image img = new Image("file:image/"+imgName, rootChoix.getWidth(), rootChoix.getHeight(), false, false);
        BackgroundImage bImg = new BackgroundImage(img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            bg);
        Background bGround = new Background(bImg);
        rootChoix.setBackground(bGround);
    }
   
    public static int getchoixTemps(){
        return choixTemps; 
    }
}
