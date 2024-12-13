package com.ortega.helper;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.logging.Logger;

public class MapLoader {
    private Logger logger = Logger.getLogger(MapLoader.class.getName());
    private BufferedImage mapImage;

    public MapLoader(String mapPath) {
        try {
            // Load stream input from resources folder.
            InputStream input = MapLoader.class.getResourceAsStream(mapPath);
            assert input != null;

            // Read image as Buffered Image.
            mapImage = ImageIO.read(input);
            logger.info("Image loaded successfully");
        } catch (Exception e) {
            logger.info(e.getMessage());
            System.exit(1);
        }
    }

    public BufferedImage getMapImage() {
        return mapImage;
    }

    public int getMapWidth() {
        logger.info("Getting map width");
        return mapImage.getWidth();
    }

    public int getMapHeight() {
        logger.info("Getting map height");
        return mapImage.getHeight();
    }

    // Get color on map image with RGB.
    public int getColorAt(int x, int y) {
        return mapImage.getRGB(x, y);
    }

}
