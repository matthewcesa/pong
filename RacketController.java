package model;

public interface RacketController {
    enum State { GOING_UP, IDLE, GOING_DOWN }
    State getState();
    void incrementeScore();
    int getScore();
    void resetScore();
    void setRacketDirection(double posBallY, double posBallX, double posRacketY, double courtWidth, boolean leftPlayer);
    boolean estBot();    
}
