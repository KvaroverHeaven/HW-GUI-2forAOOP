import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ReTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
                    | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        var reFrame = new ReFrame();
        reFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        reFrame.setSize(1280, 720);
        reFrame.setLocationByPlatform(true);
        reFrame.setVisible(true);
    }
}
