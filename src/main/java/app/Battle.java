package app;

import java.util.List;

import playground.Field;
import playground.LimitedSizeField;
import playground.Position;
import ship.ShipFleet;

public class Battle {

    private static final int SIZE = 20;

    public static void main(final String[] args) {
        // Set up field
        Field field = new LimitedSizeField(SIZE, SIZE);
        ShipPositioner shipPositioner = new ShipPositioner(field);

        ShipLoader shipLoader = new ShipLoader("/ships.txt");
        List<ShipFleet> loadFleets = shipLoader.loadFleets();

        for (ShipFleet shipFleet : loadFleets) {
            shipPositioner.addShips(shipFleet);
        }

        System.out.println(field);

        // hit ships
        boolean hit;
        for (int i = 0; i < SIZE / 3; i++) {
            for (int j = 0; j < SIZE; j++) {
                Position pos = new Position(i, j);
                hit = field.fire(pos);
                if (hit == true) {
                    System.out.println("hit at: " + pos);
                }
            }
        }
        System.out.println(field);
    }
}
