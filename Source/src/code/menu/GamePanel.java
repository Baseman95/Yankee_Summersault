package code.menu;

import code.game.World;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private final World world;

    public GamePanel(World world) {
        this.world = world;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.repaint((Graphics2D) g);
    }

}
