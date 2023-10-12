import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGui extends JFrame implements ActionListener {

    private int xScore, oScore, moveCounter;
    // isPlayerOne - flag to indicate it current player is player x or not
    private boolean isPlayerOne;
    private JLabel turnLabel, scoreLabel;
    private JButton[][] board;

    public TicTacToeGui(){
        super("Tic Tac Toe");
        setSize(CommonConstants.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(CommonConstants.BACKGROUND_COLOR);

        //init vars
        board = new JButton[3][3];
        isPlayerOne = true;

        addGuiComponent();
    }

    private void addGuiComponent(){
        JLabel barLabel = new JLabel();
        barLabel.setOpaque(true);
        barLabel.setBackground((CommonConstants.BAR_COLOR));
        barLabel.setBounds(0, 0, CommonConstants.FRAME_SIZE.width, 25);

        turnLabel = new JLabel(CommonConstants.X_LABEL);
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
        turnLabel.setPreferredSize(new Dimension(100, turnLabel.getPreferredSize().height));
        turnLabel.setOpaque(true);
        turnLabel.setBackground(CommonConstants.X_COLOR);
        turnLabel.setForeground(CommonConstants.BOARD_COLOR);
        turnLabel.setBounds(
                (CommonConstants.FRAME_SIZE.width - turnLabel.getPreferredSize().width)/2,
                0,
                turnLabel.getPreferredSize().width,
                turnLabel.getPreferredSize().height
        );

        //score label
        scoreLabel = new JLabel(CommonConstants.SCORE_LABEL);
        scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(CommonConstants.BOARD_COLOR);
        scoreLabel.setBounds(0,
                turnLabel.getY() + turnLabel.getPreferredSize().height + 25,
                CommonConstants.FRAME_SIZE.width,
                scoreLabel.getPreferredSize().height
        );

        //game board
        GridLayout gridLayout = new GridLayout(3, 3);
        JPanel boardPanel = new JPanel(gridLayout);
        boardPanel.setBounds(0,
                scoreLabel.getY() + scoreLabel.getPreferredSize().height + 35,
                CommonConstants.BOARD_SIZE.width,
                CommonConstants.BOARD_SIZE.height
                );

        // create board
        for(int i = 0;i < board.length;i++){
            for(int j = 0;j < board[i].length; j++){
                JButton button = new JButton();
                button.setFont(new Font("Dialog", Font.PLAIN, 100));
                button.setPreferredSize(CommonConstants.BUTTON_SIZE);
                button.setBackground(CommonConstants.BACKGROUND_COLOR);
                button.setOpaque(true);
                button.addActionListener(this);
                button.setBorder(BorderFactory.createLineBorder(CommonConstants.BOARD_COLOR));

                // add button to board
                board[i][j] = button;
                boardPanel.add(board[i][j]);
            }

        }

        //reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Dialog", Font.PLAIN, 24));
        resetButton.addActionListener(this);
        resetButton.setBackground(CommonConstants.BOARD_COLOR);
        resetButton.setBounds(
                (CommonConstants.FRAME_SIZE.width - resetButton.getPreferredSize().width)/2,
                CommonConstants.FRAME_SIZE.height - 100,
                resetButton.getPreferredSize().width,
                resetButton.getPreferredSize().height
        );

        getContentPane().add(turnLabel);
        getContentPane().add(barLabel);
        getContentPane().add(scoreLabel);
        getContentPane().add((boardPanel));
        getContentPane().add(resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Reset")){
            resetGame();
        } else {
            // player move
            JButton button = (JButton) e.getSource();
            if(button.getText().equals((""))){
                moveCounter++;

                // mark the button with x/o if its not empty
                if(isPlayerOne){
                    button.setText(CommonConstants.X_LABEL);
                    button.setForeground(CommonConstants.X_COLOR);

                    // update turn label
                    turnLabel.setText(CommonConstants.O_LABEL);
                    turnLabel.setBackground(CommonConstants.O_COLOR);

                    //update turn
                    isPlayerOne = false;
                } else {
                    // player two
                    button.setText(CommonConstants.O_LABEL);
                    button.setForeground(CommonConstants.O_COLOR);

                    //update turn label
                    turnLabel.setText(CommonConstants.X_LABEL);
                    turnLabel.setBackground(CommonConstants.X_COLOR);

                    // update turn
                    isPlayerOne = true;
                }
            }
            repaint();
            revalidate();
        }
        // check win conditions
        if(isPlayerOne){
            // check if the last move rom O was the winning move
            checkOWin();
        }{
            checkXWin();
        }
    }

    private void resetGame(){
        // reset player back to x
        isPlayerOne = true;
        turnLabel.setText((CommonConstants.X_LABEL));
        turnLabel.setBackground(CommonConstants.X_COLOR);

        // reset score
        scoreLabel.setText(CommonConstants.SCORE_LABEL);

        // reset board
        for(int i = 0;i < board.length;i++){
            for(int j = 0;j < board[i].length; j++){
                board[i][j].setText("");
            }
        }
    }
}
