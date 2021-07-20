package edu.mygdx.model;

public class GameModel {
    Ship ship;

    public GameModel() {
        this.ship = new Ship(500, 540);
    }

    public Ship getShip() {
        return ship;
    }
}
