import java.awt.*;
import javax.swing.*;


public class LinkNodeVisualized extends JPanel{
    //Global variables
    Color topColor = new Color(17, 166, 15);
    Color bottomColor = new Color(140, 8, 39);
    Font addressFont = new Font(Font.SANS_SERIF, Font.PLAIN, 8);
    Font contentFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
    Dimension size;
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel top;
    JLabel content;
    JLabel bottom;
    int smallHeight;

    //Constructor
    LinkNodeVisualized(int width, int height, String previousAddress, String containedContent, String nextAddress){
        //Creating a dimension object to reference
        size = new Dimension(width, height);

        //Setting sizes and layout
        setMinimumSize(size);
        setPreferredSize(size);
        setLayout(new GridBagLayout());

        //Constraints for a gridbaglayout
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 1;
        gbc.gridy = 1;

        //Splitting the vertical height into 3 separate boxes with two being half the size of the third
        smallHeight = height / 4;

        //Top JLabel for the previous memory address
        top = new JLabel(previousAddress, SwingConstants.CENTER); //Adding content
        //Setting sizing
        top.setMinimumSize(new Dimension(width, smallHeight));
        top.setPreferredSize(new Dimension(width, smallHeight));
        //Styling
        top.setFont(addressFont);
        top.setOpaque(true);
        top.setBackground(topColor);

        //JLabel for the content in the node
        content = new JLabel(containedContent, SwingConstants.CENTER);
        content.setMinimumSize(new Dimension(width, smallHeight * 2));
        content.setPreferredSize(new Dimension(width, smallHeight * 2));
        content.setFont(contentFont);
        content.setOpaque(true);

        //JLabel for the next memory address in the link
        bottom = new JLabel(nextAddress, SwingConstants.CENTER);
        bottom.setMinimumSize(new Dimension(width, smallHeight));
        bottom.setPreferredSize(new Dimension(width, smallHeight));
        bottom.setFont(addressFont);
        bottom.setOpaque(true);
        bottom.setBackground(bottomColor);

        //Adding items to the class JPanel
        add(top, gbc);
        gbc.gridy = 2;
        add(content, gbc);
        gbc.gridy = 3;
        add(bottom, gbc);

    }
}
