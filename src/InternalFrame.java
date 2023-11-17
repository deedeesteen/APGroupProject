import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class InternalFrame extends JFrame {

    JDesktopPane desktopPane = new JDesktopPane();

    JInternalFrame internalFrame = new JInternalFrame("JInternal Frame");

    public InternalFrame() {
        internalFrame.setSize(320, 240);
        internalFrame.setVisible(true);
        internalFrame.setMaximizable(true);
        internalFrame.setIconifiable(true);
        internalFrame.setResizable(true);
        internalFrame.setClosable(true);
        internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        desktopPane.add(internalFrame);
        add(desktopPane);

        /* */
    }

    public void inititialise() {
        InternalFrame iFrame = new InternalFrame();
        iFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iFrame.setSize(640, 480);

        iFrame.setVisible(true);
    }

    public static void main(String[] args) {

        /*
         * InternalFrame iFrame = new InternalFrame();
         * iFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         * 
         * iFrame.setSize(640, 480);
         * 
         * iFrame.setVisible(true);
         * 
         */
    }

}
