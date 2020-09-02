import javax.swing.*;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2020-September-02
 */

public class EndFrame extends JDialog {


    public EndFrame(JFrame parent){
        super(parent, "The End", true);


        setSize(200,100);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
