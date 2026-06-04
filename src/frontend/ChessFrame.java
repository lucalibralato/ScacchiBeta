package frontend;

import javax.swing.*;

public class ChessFrame extends JFrame {

    public ChessFrame() {
        super("Scacchi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new ChessboardPanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessFrame::new);
    }
}
