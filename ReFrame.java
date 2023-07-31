import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class ReFrame extends JFrame {

    private static final long serialVersionUID = 8960776536082733527L;

    public ReFrame() {
        super("GUI HW 2");
        add(new RePanel(), BorderLayout.CENTER);
    }
}

class RePanel extends JPanel {
    private static final long serialVersionUID = 2227698942748682783L;
    private Image image;
    private static final String imageFilename = "Irena.png";
    private Point begin;
    private Point current = new Point(0, 0);
    int deltaX;
    int deltaY;

    public RePanel() {
        super();

        try {
            var bufferedImage = ImageIO.read(new File(imageFilename));
            image = bufferedImage.getScaledInstance(560, 320, Image.SCALE_SMOOTH);

            MouseInputAdapter handler = new MouseInputAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if ((e.getX() >= current.getX()) && (e.getX() <= (current.getX() + image.getWidth(RePanel.this)))
                            && (e.getY() >= current.getY())
                            && (e.getY() <= (current.getY() + image.getHeight(RePanel.this)))) {
                        begin = e.getPoint();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    begin = null;
                    deltaX = 0;
                    deltaY = 0;
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (begin != null) {
                        Component component = e.getComponent();
                        deltaX = e.getX() - begin.x;
                        deltaY = e.getY() - begin.y;
                        current = component.getLocation();
                        current.x = current.x + deltaX;
                        current.y = current.y + deltaY;
                        repaint();
                    }
                }
            };
            this.addMouseListener(handler);
            this.addMouseMotionListener(handler);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var graphics2D = (Graphics2D) g.create();
        graphics2D.drawImage(image, (int) current.getX(), (int) current.getY(), this);
        graphics2D.dispose();
    }

}