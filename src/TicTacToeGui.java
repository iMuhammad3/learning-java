import javax.swing.*;
import java.awt.*;

public class TicTacToeGui extends JFrame{

    private JLabel turnLabel;

    public TicTacToeGui(){
        super("Tic Tac Toe");
        setSize(CommonConstants.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        addGuiComponent();
    }

    private void addGuiComponent(){
        JLabel barLabel = new JLabel();
        barLabel.setOpaque(true);
        barLabel.setBackground((CommonConstants.BAR_COLOR));
        barLabel.setBounds(0, 0, CommonConstants.FRAME_SIZE.width, 25);

        turnLabel = new JLabel(CommonConstants.X_LABEL);
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
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

        getContentPane().add(turnLabel);
        getContentPane().add(barLabel);
    }
}
