package com.ortega;

import com.ortega.component.ExitDoor;
import com.ortega.component.PathWay;
import com.ortega.component.Shelve;
import com.ortega.component.Wall;
import com.ortega.helper.MapColor;
import com.ortega.helper.MapLoader;
import sim.engine.SimState;
import sim.field.grid.SparseGrid2D;

import java.util.logging.Logger;

public class SupermarketEvacuationSim extends SimState {
    private final Logger logger = Logger.getLogger("SupermarketEvacuationSim");
    public SparseGrid2D supermarketGrid;


    public SupermarketEvacuationSim(long seed) {
        super(seed);
    }

    @Override
    public void start() {
        super.start();

        // Initialize supermarket grid.
        initializeSupermarketGrid("/plan/plan_1000_x_500.png");

        // Initialize agents (1)
        initializeAgents(1);
    }

    private void initializeSupermarketGrid(String mapPath) {
        logger.info("Initializing Supermarket Grid with map path: " + mapPath);

        // Load map image.
        MapLoader mapLoader = new MapLoader(mapPath);

        int width = mapLoader.getMapWidth();
        int height = mapLoader.getMapHeight();

        // Initialize supermarket grid.
        logger.info("Initializing Supermarket Grid with map width: " + width + " height: " + height);
        supermarketGrid = new SparseGrid2D(width, height);

        // Create matrice of map.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // Get pixel of loaded map image.
                int pixel = mapLoader.getColorAt(x, y);
                if (pixel == MapColor.BLACK) {
                    supermarketGrid.setObjectLocation(new Wall(), x, y);
                } else if (pixel == MapColor.WHITE) {
                    supermarketGrid.setObjectLocation(new PathWay(), x, y);
                } else if (pixel == MapColor.DARK_VIOLET) {
                   supermarketGrid.setObjectLocation(new Shelve(), x, y);
                } else if (pixel == MapColor.GREEN) {
                    supermarketGrid.setObjectLocation(new ExitDoor(), x, y);
                }
            }
        }
    }

    private void initializeAgents(int nbrOfAgents) {
        logger.info("Initializing Supermarket Agents with " + nbrOfAgents + " agents");
        // TODO: Logic to initialize agents.
    }
}
