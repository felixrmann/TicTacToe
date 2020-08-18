package TicTacToe;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2020-Juni-04
 */

public class TicTacToe extends JFrame {
    private JPanel mainPanel;
    private String[][] field = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
    };
    private String[] hoch = {" "," "," "};
    private JTable table;
    private char currentPlayer = 'X';
    private int cnt = 1;

    private TicTacToe(){
        mainPanel = new JPanel();
        table = new JTable(field, hoch){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        init();

        setTitle("TicTacToe");
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        for (int i = 0; i < 3; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setWidth(100);
            table.setRowHeight(i, 100);
        }
    }

    private void init(){
        getContentPane().add(mainPanel);

        mainPanel.setLayout(new BorderLayout(10,10));
        mainPanel.setPreferredSize(new Dimension(300,300));
        mainPanel.add(table, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (cnt == 9){
                    makeZ();
                    fertig();
                }
                if (field[table.getSelectedRow()][table.getSelectedColumn()].equals(" ")){
                    field[table.getSelectedRow()][table.getSelectedColumn()] = String.valueOf(currentPlayer);
                    checKTicTacToe();
                    playerChange();
                    cnt++;
                }
            }
        });
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
        System.out.println("Spieler " + currentPlayer + " gewinnt.");
        new TicTacToe();
        this.dispose();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
