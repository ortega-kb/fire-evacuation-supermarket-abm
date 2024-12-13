package com.ortega;

import com.ortega.component.ExitDoor;
import com.ortega.component.PathWay;
import com.ortega.component.Shelve;
import com.ortega.component.Wall;
import com.ortega.helper.MapColor;
import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.grid.SparseGridPortrayal2D;
import sim.portrayal.simple.RectanglePortrayal2D;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;


public class SupermarketEvacuationSimGUI extends GUIState {
    private final Logger logger = Logger.getLogger("SupermarketEvacuationSimGUI");

    public Display2D display;
    public JFrame frame;
    public SparseGridPortrayal2D supermarketPortrayal2D = new SparseGridPortrayal2D();

    public SupermarketEvacuationSimGUI(SimState state) {
        super(state);
    }

    @Override
    public void start() {
        super.start();
        // Initialize portrayals
        initializePortrayals();


        // Displays actions
        display.reset();
        display.repaint();

    }

    @Override
    public void init(Controller controller) {
        super.init(controller);

        display = new Display2D(1000, 500, this);
        frame = display.createFrame();

        controller.registerFrame(frame);
        frame.setVisible(true);
        display.attach(
                supermarketPortrayal2D,
                "Supermarket fire evacuation",
                true
        );
    }

    private void initializePortrayals() {
        logger.info("Initializing portrayals");

        SupermarketEvacuationSim sim = (SupermarketEvacuationSim) state;
        supermarketPortrayal2D.setField(sim.supermarketGrid);

        supermarketPortrayal2D.setPortrayalForClass(Wall.class, new RectanglePortrayal2D(new Color(MapColor.BLACK)));
        supermarketPortrayal2D.setPortrayalForClass(ExitDoor.class, new RectanglePortrayal2D(new Color(MapColor.GREEN)));
        supermarketPortrayal2D.setPortrayalForClass(Shelve.class, new RectanglePortrayal2D(new Color(MapColor.DARK_VIOLET)));
        supermarketPortrayal2D.setPortrayalForClass(PathWay.class, new RectanglePortrayal2D(new Color(MapColor.WHITE)));
    }

    public static void main(String[] args) {
        new SupermarketEvacuationSimGUI(new SupermarketEvacuationSim(System.currentTimeMillis())).createController();
    }
}
