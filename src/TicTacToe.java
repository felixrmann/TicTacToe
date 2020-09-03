import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2020-Juni-04
 */

public class TicTacToe extends JFrame {
    private boolean finish;
    private JPanel mainPanel;
    private JPanel fieldPanel;
    private String[][] field = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
    };
    private char currentPlayer = 'X';
    private int cnt = 1;

    private TicTacToe(){
        mainPanel = new JPanel();
        fieldPanel = new JPanel();
        finish = true;

        init();

        paintImages();

        setTitle("TicTacToe");
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init(){
        getContentPane().add(mainPanel);

        mainPanel.setLayout(new BorderLayout(10,10));
        mainPanel.setPreferredSize(new Dimension(600,600));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(fieldPanel, BorderLayout.CENTER);

        fieldPanel.setLayout(new GridLayout(3,3));

        fieldPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int posY = getPosField(e.getY());
                int posX = getPosField(e.getX());
                if (field[posY][posX].equals(" ")){
                    field[posY][posX] = String.valueOf(currentPlayer);
                    paintImages();
                    checKTicTacToe();

                    if (finish){
                        if (cnt == 9){
                            makeZ();
                            fertig();
                        } else {
                            playerChange();
                            cnt++;
                        }
                    }
                }
            }
        });
    }

    private int getPosField(double Pos){
        switch ((int) Math.floor(Pos / 100)){
            case 0:
            case 1:
                return 0;
            case 2:
            case 3:
                return 1;
            case 4:
            case 5:
                return 2;
            default:
                return 0;
        }
    }

    private void paintImages(){
        fieldPanel.removeAll();
        URL path;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (field[y][x].equals("X")){
                    path = getClass().getResource("X.png");
                } else if (field[y][x].equals("O")){
                        path = getClass().getResource("O.png");
                } else {
                    path = getClass().getResource("_.png");
                }
                ImageIcon icon = new ImageIcon(path);
                Image image = icon.getImage();
                Image newImg = image.getScaledInstance(198, 198, Image.SCALE_SMOOTH);
                icon = new ImageIcon(newImg);
                JLabel img = new JLabel();
                img.setIcon(icon);
                img.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                fieldPanel.add(img);
            }
        }
        fieldPanel.revalidate();
    }

    private void playerChange(){
        if (currentPlayer == 'X'){
            currentPlayer = 'O';
        } else if (currentPlayer == 'O'){
            currentPlayer = 'X';
        }
    }

    private void makeZ(){
        currentPlayer = 'Z';
    }

    private void checKTicTacToe(){
        if (field[0][0].equals(field[0][1]) && field[0][0].equals(field[0][2]) && !field[0][0].equals(" ")) {
            fertig();
        } else if (field[1][0].equals(field[1][1]) && field[1][0].equals(field[1][2]) && !field[1][0].equals(" ")){
            fertig();
        } else if (field[2][0].equals(field[2][1]) && field[2][0].equals(field[2][2]) && !field[2][0].equals(" ")){
            fertig();
        } else if (field[0][0].equals(field[1][0]) && field[0][0].equals(field[2][0]) && !field[0][0].equals(" ")){
            fertig();
        } else if (field[0][1].equals(field[1][1]) && field[0][1].equals(field[2][1]) && !field[0][1].equals(" ")){
            fertig();
        } else if (field[0][2].equals(field[1][2]) && field[0][2].equals(field[2][2]) && !field[0][2].equals(" ")){
            fertig();
        } else if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(" ")){
            fertig();
        } else if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(" ")){
            fertig();
        }
    }

    private void fertig(){
        finish = false;
        String msg;
        if (currentPlayer == 'Z'){
            msg = "Draw";
        } else {
            msg = "Spieler " + currentPlayer + " gewinnt.";
        }
        JOptionPane.showMessageDialog(this, msg);
        new TicTacToe();
        this.dispose();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
