package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import playground.Position;
import ship.ShipFleet;
import ship.ShipShape;

public class ShipLoader {
    private final String fileName;

    public ShipLoader(final String fileName) {
        this.fileName = fileName;
    }

    public List<ShipFleet> loadFleets() {
        List<ShipFleet> fleets = null;
        URL url = getClass().getResource(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(new File(url.toURI())))) {
            fleets = readFleets(br);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return fleets;
    }

    private List<ShipFleet> readFleets(final BufferedReader br) throws IOException {
        List<ShipFleet> shipFleets = new ArrayList<>();
        List<Position> positions = new ArrayList<>();
        String s = br.readLine();
        int j = 0;
        while (s != null) {
            if (!s.matches("^\\d+")) {
                List<Position> shapeRow = readShapeRow(s, j);
                positions.addAll(shapeRow);
                j++;
            } else {
                ShipFleet newFleet = createFleet(positions, s);
                if (newFleet != null) {
                    shipFleets.add(newFleet);
                }
                j = 0;
            }
            s = br.readLine();
        }
        return shipFleets;
    }

    private List<Position> readShapeRow(final String s, final int j) {
        List<Position> positions = new ArrayList<>();
        int i = 0;
        String[] split = s.split(" ");
        for (String string : split) {
            if (string.equals("x")) {
                positions.add(new Position(i, j));
            }
            i++;
        }
        return positions;
    }

    private ShipFleet createFleet(final List<Position> positions, final String s) {
        ShipFleet fleet = null;
        if (positions.size() > 0) {
            Position first = positions.get(0);
            List<Position> normalPositions = new ArrayList<>();
            for (Position position : positions) {
                normalPositions.add(position.subtract(first));
            }
            fleet = new ShipFleet(new ShipShape(normalPositions), Integer.parseInt(s));
            positions.clear();
        }
        return fleet;
    }
}
