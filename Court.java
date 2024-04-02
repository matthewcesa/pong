package model;

import gui.Choix;
import gui.GameView;
import java.util.Timer;
import java.util.TimerTask;

public class Court {
    // public static Timer t;
    public static int temps;
    // instance parameters
    private RacketController playerA, playerB, playerC, playerD; //+2
    private double width, height; // m
    private double racketSpeed = 300.0; // m/s
    private double racketSize = 70.0; // m
    private double ballRadius = 10.0; // m
    // instance state
    private double racketA; // m
    private double racketB; // m
    Sounds s = new Sounds();
    public static Timer t;

	//+2
    private double racketC; // m
    private double racketD; // m

    private double ballX, ballY; // m
    private double ballSpeedX, ballSpeedY; // m
    private GameView gameView;

    public Court(RacketController playerA, RacketController playerB, double width, double height,int vitesse ) {
        this.playerA = playerA;
        this.playerB = playerB;
	    this.playerC = null;
	    this.playerD = null;
        this.width = width;
        this.height = height;
        this.ballSpeedX=vitesse;
        System.out.println(vitesse);
        reset();
    }
    public Court(RacketController playerA, RacketController playerB,RacketController playerC,RacketController playerD, double width, double height,int vitesse ) {
        this.playerA = playerA;
        this.playerB = playerB;
	    this.playerC = playerC;
	    this.playerD = playerD;
        this.width = width;
        this.height = height;
        this.ballSpeedX=vitesse;
        System.out.println(vitesse);
        reset();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRacketSize() {
        return racketSize;
    }

    public double getRacketA() {
        return racketA;
    }

    public double getRacketB() {
        return racketB;
    }

    public double getRacketC() {
        return racketC;
    }

    public double getRacketD() {
        return racketD;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }
    public RacketController getPlayerA() {
        return this.playerA;
    }
    public RacketController getPlayerB() {
        return this.playerB;
    }

    public void changeVitesse(int v){
        this.ballSpeedX=v;
        this.ballSpeedY=v;
    }
    public void update(double deltaT) {

         switch (playerA.getState()) {
            case GOING_UP:
                racketA -= racketSpeed * deltaT;
                if (racketA < 0.0) racketA = 0.0;
                break;
            case IDLE:
                break;
            case GOING_DOWN:
                racketA += racketSpeed * deltaT;
                if (racketA + racketSize > height) racketA = height - racketSize;
                break;
        }
        switch (playerB.getState()) {
            case GOING_UP:
                racketB -= racketSpeed * deltaT;
                if (racketB < 0.0) racketB = 0.0;
                break;
            case IDLE:
                break;
            case GOING_DOWN:
                racketB += racketSpeed * deltaT;
                if (racketB + racketSize > height) racketB = height - racketSize;
                break;
        }
	//+2 players
        if (playerC!=null){
            switch (playerC.getState()) {
                case GOING_UP:
                    racketC -= racketSpeed * deltaT;
                    if (racketC < 0.0) racketC = 0.0;
                    break;
                case IDLE:
                    break;
                case GOING_DOWN:
                    racketC += racketSpeed * deltaT;
                    if (racketC + racketSize > height) racketC = height - racketSize;
                    break;
            }
        }
        if(playerD!=null){ 
            switch (playerD.getState()) {
                case GOING_UP:
                    racketD -= racketSpeed * deltaT;
                    if (racketD < 0.0) racketD = 0.0;
                    break;
                case IDLE:
                    break;
                case GOING_DOWN:
                    racketD += racketSpeed * deltaT;
                    if (racketD + racketSize > height) racketD = height - racketSize;
                    break;
        }
    }
        if (updateBall(deltaT)) {
           if(ballX < 0) 
                playerB.incrementeScore();
            else
                playerA.incrementeScore();
              
                reset();
             if(playerA.getScore()>=10 || playerB.getScore()>=10){
                gameView.stoAnimation();
                gameView.gameOver();
            }
        }   
    }
    public void chrono(int c){
        t = new Timer(true);
        t.schedule(new TimerTask() {
            int tempsEnSecondes = c;
            public void run() {
                    int minutes = tempsEnSecondes/60;
                    int secondes = tempsEnSecondes%60;         
                    gameView.compteARebours.setText("Temps :" + minutes + ":" + secondes);
                    if(tempsEnSecondes>0){
                        tempsEnSecondes--;
                    }

                    if(playerA.getScore()>=5 || playerB.getScore()>=5){
                        t.cancel();
                        t.purge();
                    }      
                    if(minutes == 0){  
                        if(tempsEnSecondes <=10){
                            if(secondes == 0){
                                gameView.stoAnimation();
                                gameView.gameOver();
                                t.purge();
                                t.cancel(); // stop
                            }
                        }
                    }
                }
            }, 0,1000); 
    }



    public void resetChrono(int c){
        chrono(c);
    }
    public void setBallSpeedX(double value){
        this.ballSpeedX = value;
    }
    public void setBallSpeedY(double value){
        this.ballSpeedY = value;
    }
    
    /**
     * @return true if a player lost
     */
    private boolean updateBall(double deltaT) {
        // first, compute possible next position if nothing stands in the way
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;
        // next, see if the ball would meet some obstacle
        if (nextBallY < 0 || nextBallY > height) {
            s.playSFX(2);
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedY;
        }
        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + racketSize)
                || (nextBallX > width && nextBallY > racketB && nextBallY < racketB + racketSize)) {
                    s.playSFX(2);
            ballSpeedX = -ballSpeedX;
            //acceleration
            if(getPlayerA().estBot()==false && getPlayerB().estBot()==false){
                ballSpeedX = ballSpeedX/10 + ballSpeedX;
            }
            nextBallX = ballX + deltaT * ballSpeedX;
	    } else if ((nextBallX < 0 && nextBallY > racketC && nextBallY < racketC + racketSize)
                || (nextBallX > width && nextBallY > racketD && nextBallY < racketD + racketSize)) {
                    s.playSFX(2);
            ballSpeedX = -ballSpeedX;
            nextBallX = ballX + deltaT * ballSpeedX;
        } else if (nextBallX < 0) {
            return true;
        } else if (nextBallX > width) {
            return true;
        }
        
        ballX = nextBallX;
        ballY = nextBallY;

        return nextBallX < 0 || nextBallX > width;
    } 


    public double getBallRadius() {
        return ballRadius;
    }

    public void reset() {
        this.racketA = height / 2 - this.getRacketSize() /2;
        this.racketB = height / 2 - this.getRacketSize() /2;

	    if(playerC!=null && playerD!= null){
            this.racketC = height / 2;
            this.racketD = height / 2;
        }

        this.ballSpeedX = 200.0;
        this.ballSpeedY = 200.0;
        this.ballX = width / 2;
        this.ballY = height / 2;
    }
    

    public void setGameview(GameView gameView) {
        this.gameView=gameView;
    }

    public void resetScore() {
        this.playerA.resetScore();
        this.playerB.resetScore();
    }

    public void setWidth(double w){
        this.width = w;
    }
    public void setHeight(double h){
        this.height = h;
    }
    public void setRacketSize(double s){
        this.racketSize = s;
    }
}
