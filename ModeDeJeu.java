package gui;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Court;
import model.RacketController;
import model.RacketController.State;
public class ModeDeJeu {
    public static KeyCode p1UpKey = KeyCode.Z;
    public static KeyCode p1DownKey = KeyCode.S;
    
    public static KeyCode p2UpKey = KeyCode.P;
    public static KeyCode p2DownKey = KeyCode.M;

    public static KeyCode p3UpKey = KeyCode.CONTROL;
    public static KeyCode p3DownKey = KeyCode.TAB;

    public static KeyCode p4UpKey = KeyCode.UP;
    public static KeyCode p4DownKey = KeyCode.DOWN;
    Sounds s = new Sounds();
    static class Player implements RacketController {
        State state = State.IDLE;
        private int score=0;
        private boolean bot=true;
        
        public Player(boolean bot ){
            this.bot=bot;
        }

        @Override
        public State getState() {
            return state;
        }
        @Override
        public int getScore(){
            return this.score;
        }
        @Override
        public void incrementeScore(){
            this.score++;
        }
        @Override
        public void resetScore(){
            this.score=0;
        }
        public boolean estBot(){
            return this.bot;
        }

        public void setRacketDirection(double posBallY, double posBallX, double posRacketY, double courtWidth, boolean leftPlayer) {
            if(!bot) return;

            double racketCenter = posRacketY + 100/2;
            boolean doitDeplacer = (leftPlayer && posBallX < courtWidth / 2) || 
                (!leftPlayer && posBallX > courtWidth / 2);

            if(doitDeplacer && posBallY > racketCenter){
                this.state= RacketController.State.GOING_DOWN;
            }
            else if(doitDeplacer && posBallY < racketCenter){
                this.state= RacketController.State.GOING_UP;
            }else{
                this.state= RacketController.State.IDLE;
            }
        }
    }
    
    public static void game1v1(Stage stage ,Scene scene, VBox root, boolean bot,int vitesse,Scene menus){
        
        Player playerA = new Player(false);
        Player playerB = new Player(bot);
        scene.setOnKeyPressed(ev -> {
            if(!playerA.estBot()){
                if(ev.getCode()==p1UpKey){
                    playerA.state = RacketController.State.GOING_UP;
                }
                if(ev.getCode()==p1DownKey){
                    playerA.state = RacketController.State.GOING_DOWN;
                }
            }

            if(!playerB.estBot()){
                if(ev.getCode()==p2UpKey){
                    playerB.state = RacketController.State.GOING_UP;
                }
                if(ev.getCode()==p2DownKey){
                    playerB.state = RacketController.State.GOING_DOWN;
                }
            }
        });
       //action a faire quand on n'apuie pas (on souleve les doigt) les boutton alt , up , dowm , ctrl
        scene.setOnKeyReleased(ev -> {
            if(!playerA.estBot()){
                if(ev.getCode()==p1UpKey){
                    if (playerA.state == RacketController.State.GOING_UP) playerA.state = RacketController.State.IDLE;
                }
                if(ev.getCode()==p1DownKey){
                    if (playerA.state == RacketController.State.GOING_DOWN) playerA.state = RacketController.State.IDLE;
                }
            }

            if(!playerB.estBot()){
                if(ev.getCode()==p2UpKey){
                    if (playerB.state == RacketController.State.GOING_UP) playerB.state = RacketController.State.IDLE;
                }
                if(ev.getCode()==p2DownKey){
                    if (playerB.state == RacketController.State.GOING_DOWN) playerB.state = RacketController.State.IDLE;
                }
            }
        });
        var court = new Court(playerA, playerB, 1000, 600,vitesse);
        var gameView = new GameView(stage,court, root, 1.0,menus,false);
        court.setGameview(gameView);
        gameView.animate();
    }
    
	

    public static void game2v2(Stage stage,Scene scene, VBox root,Scene menus,int vitesse){
        var playerA = new Player(false);
        var playerB = new Player(false);
	    var playerC = new Player(false);
        var playerD = new Player(false);
        scene.setOnKeyPressed(ev -> {
            if(ev.getCode()==p1UpKey){
                playerA.state = RacketController.State.GOING_UP;
            }
            if(ev.getCode()==p1DownKey){
                playerA.state = RacketController.State.GOING_DOWN;
            }

            if(ev.getCode()==p2UpKey){
                playerB.state = RacketController.State.GOING_UP;
            }
            if(ev.getCode()==p2DownKey){
                playerB.state = RacketController.State.GOING_DOWN;
            }

            if(ev.getCode()==p3UpKey){
                playerC.state = RacketController.State.GOING_UP;
            }
            if(ev.getCode()==p3DownKey){
                playerC.state = RacketController.State.GOING_DOWN;
            }

            if(ev.getCode()==p4UpKey){
                playerD.state = RacketController.State.GOING_UP;
            }
            if(ev.getCode()==p4DownKey){
                playerD.state = RacketController.State.GOING_DOWN;
            }
        });

        scene.setOnKeyReleased(ev -> {			//trigger joueur
            if(ev.getCode()==p1UpKey){
                if (playerA.state == RacketController.State.GOING_UP) playerA.state = RacketController.State.IDLE;
            }
            if(ev.getCode()==p1DownKey){
                if (playerA.state == RacketController.State.GOING_DOWN) playerA.state = RacketController.State.IDLE;
            }

            if(ev.getCode()==p2UpKey){
                if (playerB.state == RacketController.State.GOING_UP) playerB.state = RacketController.State.IDLE;
            }
            if(ev.getCode()==p2DownKey){
                if (playerB.state == RacketController.State.GOING_DOWN) playerB.state = RacketController.State.IDLE;
            }

            if(ev.getCode()==p3UpKey){
                if (playerC.state == RacketController.State.GOING_UP) playerC.state = RacketController.State.IDLE;
            }
            if(ev.getCode()==p3DownKey){
                if (playerC.state == RacketController.State.GOING_DOWN) playerC.state = RacketController.State.IDLE;
            }

            if(ev.getCode()==p4UpKey){
                if (playerD.state == RacketController.State.GOING_UP) playerD.state = RacketController.State.IDLE;
            }
            if(ev.getCode()==p4DownKey){
                if (playerD.state == RacketController.State.GOING_DOWN) playerD.state = RacketController.State.IDLE;
            }
        });
        var court = new Court(playerA, playerB, playerC, playerD, 1000, 600,vitesse); //taille du terrain
        var gameView2v2 = new GameView(stage,court, root, 1.0, menus,true);
        court.setGameview(gameView2v2);
        gameView2v2.animate();
    }
}
