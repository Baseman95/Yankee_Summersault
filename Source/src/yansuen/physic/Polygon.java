package yansuen.physic;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Link162534
 */
public class Polygon extends ArrayList<CartesianVector> {

    public int x[];
    public int y[];
    public CartesianVector[] vertices;
    public int vertexCount;

    public Polygon(CartesianVector... vertices) {

    }

    public void move(double x, double y) {
        parallelStream().forEach((vertex) -> {
            vertex.x += (int) x;
            vertex.y += (int) y;
        });
    }

    public void print(Graphics g) {
        
        g.drawPolygon(
                x,
                y,
                vertexCount);
    }
}
