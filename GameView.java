package gui;

import java.net.URL;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Court;
import javafx.application.Platform;
import javax.management.timer.Timer;
public class GameView {
    private VBox finParti;
    // class parameters
    private final Court court;
    private final VBox fl;
    private final Pane gameRoot; // main node of the game
    private final double scale;
    private  double xMargin = 50.0, racketThickness = 10.0; // pixels
    public Text scor1,scor,gover,compteARebours;
    Button replay ;
    private final boolean is2v2;

    Sounds sounds = new Sounds();
    Sounds sfx = new Sounds();
    HBox menu;

    // children of the game main node
    private final Rectangle racketA, racketB, racketC, racketD;
    private final Circle ball;
    
    // timer 
    AnimationTimer timer;
    static Scene menu1;

    private double widthtmp,heighttmp;
    static Hyperlink  hyperlink1;
    Hyperlink hyperlink2;
    Stage stage;
    /**
     * @param court le "modèle" de cette vue (le terrain de jeu de raquettes et tout ce qu'il y a dessus)
     * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera affiché
     * @param scale le facteur d'échelle entre les distances du modèle et le nombre de pixels correspondants dans la vue
     */
     
     public Scene getMenu1(){
        return menu1;
     }

    public GameView(Stage stage ,Court court, VBox root, double scale,Scene menu,boolean is2v2) {  
        this.stage=stage;
        this.court = court;
        this.gameRoot = new Pane();
        this.scale = scale;
        fl=root;
        menu1=menu;
        this.is2v2=is2v2;
        sounds.playMusic(6);
      
        this.menu=new HBox();
        this.compteARebours = new Text(" "); // on l'affiche tout le temps
        racketA = new Rectangle();
        racketB = new Rectangle();
        double racketSize = court.getRacketSize();
        this.widthtmp = 1000;
        this.heighttmp = 400; 
            fl.widthProperty().addListener(e->{
            court.setWidth(fl.getWidth()-2*xMargin);
            gameRoot.setMinWidth(court.getWidth() * scale);
            racketA.setWidth((racketThickness)*fl.getWidth()/widthtmp);
            racketA.setX((xMargin - racketThickness));
            racketB.setWidth(((racketThickness)*fl.getWidth()/widthtmp));
            racketB.setX(fl.getWidth()-xMargin);
        });
        fl.heightProperty().addListener(e->{
            court.setHeight(fl.getHeight()-xMargin);
            gameRoot.setMinHeight(court.getHeight() * scale);
            court.setRacketSize(racketSize * fl.getHeight()/heighttmp);
            racketA.setHeight(court.getRacketSize());
            racketA.setY((court.getRacketA() * scale)*fl.getHeight()/heighttmp);
            racketB.setY((court.getRacketB() * scale)*fl.getHeight()/heighttmp);
            racketB.setHeight(court.getRacketSize());
        });
        
        racketA.setFill(Color.RED);
        racketB.setFill(Color.GREEN);
	//+2    
        ball = new Circle();
        ball.setFill(Color.YELLOW);
        racketC = new Rectangle();
        racketD = new Rectangle();
        racketC.setFill(Color.BLUE);
        racketD.setFill(Color.BLUE);
        if(is2v2){
            gameRoot.getChildren().addAll(racketC, racketD);
        }
        fl.widthProperty().addListener(e->{
            ball.setRadius((court.getBallRadius())*fl.getWidth()/widthtmp);
            ball.setCenterX((court.getBallX() * scale)*fl.getWidth()/widthtmp);
            racketC.setWidth((racketThickness)*fl.getWidth()/widthtmp);
            racketC.setX((xMargin - racketThickness));
            racketD.setWidth((racketThickness)*fl.getWidth()/widthtmp);
            racketD.setX(fl.getWidth()-xMargin);
        });
        fl.heightProperty().addListener(e->{
            ball.setRadius((court.getBallRadius())*fl.getHeight()/heighttmp);
            ball.setCenterY((court.getBallY() * scale)*fl.getHeight()/heighttmp);
            racketC.setHeight(court.getRacketSize());
            racketC.setY(((court.getRacketC()) * scale)*fl.getHeight()/heighttmp);
            racketD.setHeight(court.getRacketSize());
            racketD.setY((court.getRacketD() * scale)*fl.getHeight()/heighttmp);

        });
        this.scor=new Text("00 : 00");
        scor.setFill(Color.BLACK);
        
        fl.heightProperty().addListener(e->{
            scor.setY((80*fl.getHeight())/heighttmp);
            compteARebours.setY((80*fl.getHeight())/heighttmp);
        });
        hyperlink1 = new Hyperlink("Pause");
        scor.setFont (Font.font ("Arial", FontWeight.BOLD, 36));
        compteARebours.setFont (Font.font ("Arial", FontWeight.BOLD, 36));
        hyperlink2 = new Hyperlink("Menu");
        gameRoot.getChildren().addAll(racketA, racketB, ball);
        initHB();
        initPane();
        hyperlink1.setOnAction(e -> {
            court.t.cancel();
            court.t.purge();
            pausee(menu1);
        });
        hyperlink2.setOnAction(e -> {
            fl.getChildren().removeAll(this.finParti);
            stoAnimation();
           changeScene(stage, menu1);
        });
    }

    public Hyperlink getHyperLink1(){
        return hyperlink1;
    }

    public void changeScene( Stage stage , Scene scene  ){
        stage.setScene(scene);
           stage.show();
    }

    private void pausee(Scene menus){
        sounds.pause();
        sounds.playSFX(7);
        Stage s=new Stage();
        VBox pause=new VBox();
        pause.setMinWidth(300);
        pause.setMinHeight(250);
        Scene pau=new Scene(pause);
        Button nvParti=new Button(" Nouvelle Partie ");
        Button repprendre =new Button(" Reprendre ");
        Button menuB =new Button(" Menu ");
        pause.getChildren().addAll(nvParti,repprendre,menuB);
        pause.setAlignment(Pos.CENTER);
        pause.setSpacing(20);
        stoAnimation();
        pause.widthProperty().addListener(e->{
            nvParti.setMinWidth((250*pause.getWidth())/300);
            repprendre.setMinWidth((250*pause.getWidth())/300);
            menuB.setMinWidth((250*pause.getWidth())/300);
        });
        pause.heightProperty().addListener(e->{
            nvParti.setMinHeight((30/pause.getHeight())/250);
            repprendre.setMinHeight((30/pause.getHeight())/250);
            menuB.setMinHeight((30/pause.getHeight())/250);
        });
        menuB.setOnAction(a->{
            sfx.playSFX(7);
            s.close();
            sounds.stopMusic();
           changeScene(stage, menus);
        });
        repprendre.setOnAction(a->{
            court.t.cancel();
            court.t.purge();
            sfx.playSFX(7);
            s.close();
            sounds.resume(6);
            animate();
        });
        nvParti.setOnAction(a->{
            court.t.cancel();
            court.t.purge();
            sfx.playSFX(7);
            sounds.stopMusic();
            sounds.playMusic(6);
            s.close();
            court.resetScore();
            court.reset();
            court.resetChrono(Choix.getchoixTemps());
            animate();
        });
        s.setScene(pau);
        s.show();
    }
    private void effaceTout(){
        fl.getChildren().removeAll(this.menu,gameRoot);
    }
    private void initHB(){
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(scor,hyperlink1, compteARebours);
        menu.setSpacing(100);
    }

     private void findejeu(Scene menus){
         finParti=new VBox();
         this.scor1=new Text("00 : 00");
         finParti.setMinWidth(1100);
         finParti.setMinHeight(600);
         finParti.setAlignment(Pos.CENTER);
         scor1.setText(scor.getText());
         scor1.setFill(Color.BLACK);
         //game over 
         this.gover=new Text(" FIN DE JEU ");
         gover.setFill(Color.GREEN);
         //replay
         replay=new Button("Rejouer");
         fl.widthProperty().addListener(e->{
            gover.setFont(Font.font("Lucida Sans Unicode", FontPosture.ITALIC, 50*fl.getWidth()/widthtmp));
            scor1.setFont (Font.font ("Arial", FontWeight.BOLD, 30*fl.getWidth()/widthtmp));
            finParti.setSpacing(50);
            replay.setMinWidth((100*fl.getWidth())/1100);
            });
            fl.heightProperty().addListener(e->{
                gover.setFont(Font.font("Lucida Sans Unicode", FontPosture.ITALIC, 50*fl.getHeight()/heighttmp));
                scor1.setFont (Font.font ("Arial", FontWeight.BOLD, 30*fl.getHeight()/heighttmp));
                replay.setMinHeight((30*fl.getHeight())/600);
        });

        replay.setOnAction(e -> {
            sfx.playSFX(7);
            sounds.stopMusic();
            replay();
            sounds.playMusic(6);
        });
        
        Platform.runLater(new Runnable() {
            public void run() {
                finParti.getChildren().addAll(gover,scor1,replay,hyperlink2);
                fl.getChildren().add(finParti);
                effaceTout();
            }
        });
    }

    private void createTimers(){
        timer = new AnimationTimer() {
            long last = 0;
            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                court.update((now - last) * 1.0e-9); // convert nanoseconds to seconds
                last = now;
                racketA.setY(court.getRacketA() * scale);
                racketB.setY(court.getRacketB() * scale);
                if(is2v2){
                    racketC.setY(court.getRacketC() * scale);
                    racketD.setY(court.getRacketD() * scale);  
                }
                ball.setCenterX(court.getBallX() * scale + xMargin);
                ball.setCenterY(court.getBallY() * scale);
                court.getPlayerA().setRacketDirection(court.getBallY(), court.getBallX(), racketA.getY(), court.getWidth(), true);
                court.getPlayerB().setRacketDirection(court.getBallY(), court.getBallX(), racketB.getY(), court.getWidth(), false);
        
                scor.setText( court.getPlayerA().getScore()+" \t:\t  "+court.getPlayerB().getScore());
            }
        };
        court.chrono(Choix.getchoixTemps());
    }
    private void initPane(){
        fl.getChildren().addAll(this.menu,gameRoot);

    }

    private void replay() {
        fl.getChildren().remove(this.finParti);
        initPane();
        this.court.resetScore();
        animate();
        court.resetChrono(Choix.getchoixTemps());
    }
    
    
    public void stoAnimation() {
        timer.stop();
    }

    public void animate() {
        createTimers();
        timer.start();
        
    }
    public void gameOver() {
        findejeu(menu1);
    }
    // fonction pour changer le background
    public void setBackImage(String imgName){
        Image img = new Image("file:image/"+imgName, court.getWidth() + 2 * xMargin / scale, court.getHeight(), false, false);
        BackgroundImage bImg = new BackgroundImage(img,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        gameRoot.setBackground(bGround);
    }
}
