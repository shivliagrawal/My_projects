package Challenge12;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 *
 *
 * Challenge12 -- Paint
 *
 * This program accepts user input, does some processing, then outputs to the console.
 *
 * @author Shivli Agrawal, lab sec 10
 *
 * @version April 15, 2022
 *
 */

public class Paint extends JComponent implements Runnable {
    Image image; // the canvas
    Graphics2D graphics2D;  // this will enable drawing
    int curX; // current mouse x coordinate
    int curY; // current mouse y coordinate
    int oldX; // previous mouse x coordinate
    int oldY; // previous mouse y coordinate

    JButton clrButton;
    JButton fillButton;
    JButton eraseButton;
    JButton randomButton;
    JButton hexButton;
    JButton rgbButton;
    JTextField hexText;
    JTextField rText;
    JTextField gText;
    JTextField bText;
    Color backgroundColor;
    Random random = new Random();


    Paint paint; // variable of the type SimplePaint

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Paint());
    }

    public void run() {
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        paint = new Paint();
        content.add(paint, BorderLayout.CENTER);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        clrButton = new JButton("Clear");
        fillButton = new JButton("Fill");
        eraseButton = new JButton("Erase");
        randomButton = new JButton("Random");
        JPanel panel = new JPanel();
        panel.add(clrButton);
        panel.add(fillButton);
        panel.add(eraseButton);
        panel.add(randomButton);
        content.add(panel, BorderLayout.NORTH);
        hexText = new JTextField("#", 10);
        hexText.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                hexText.setText("");
            }
            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        hexButton = new JButton("Hex");
        rText = new JTextField(5);
        gText = new JTextField(5);
        bText = new JTextField(5);
        rgbButton = new JButton("RGB");
        JPanel panel2 = new JPanel();
        panel2.add(hexText);
        panel2.add(hexButton);
        panel2.add(rText);
        panel2.add(gText);
        panel2.add(bText);
        panel2.add(rgbButton);
        content.add(panel2, BorderLayout.SOUTH);
        clrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                paint.clear();
                hexText.setText("#");
                rText.setText("");
                gText.setText("");
                bText.setText("");
            }
        });

        hexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String hex = hexText.getText();
                    Color color = Color.decode(hex);
                    paint.changePenColor(color);
                    rText.setText(String.valueOf(color.getRed()));
                    gText.setText(String.valueOf(color.getGreen()));
                    bText.setText(String.valueOf(color.getBlue()));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Not a valid Hex Value", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        rgbButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    if (rText.equals("")) {
                        rText.setText("0");
                    }
                    if (gText.equals("")) {
                        gText.setText("0");
                    }
                    if (bText.equals("")) {
                        bText.setText("0");
                    }
                    int r = Integer.valueOf(rText.getText());
                    int g = Integer.valueOf(gText.getText());
                    int b = Integer.valueOf(bText.getText());

                    if (r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255) {
                        String hex1 = String.format("#%02x%02x%02x", r, g, b);
                        hexText.setText(hex1);
                        Color color = new Color(r, g, b);
                        paint.changePenColor(color);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not a valid RGB Value",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Not a valid RGB Value", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                paint.fill();
                hexText.setText("#");
                rText.setText("");
                gText.setText("");
                bText.setText("");

            }
        });

        eraseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paint.erase();
            }
        });

        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                paint.random(color);
                rText.setText(String.valueOf(color.getRed()));
                gText.setText(String.valueOf(color.getGreen()));
                bText.setText(String.valueOf(color.getBlue()));
                hexText.setText(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
            }
        });

        frame.setVisible(true);

    }

    public void changePenColor(Color color) {
        graphics2D.setPaint(color);
        repaint();
    }

    public void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        backgroundColor = (Color) graphics2D.getPaint();
        graphics2D.setPaint(Color.black);
        repaint();

    }

    public void fill() {
        graphics2D.setPaint(graphics2D.getPaint());
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        backgroundColor = (Color) graphics2D.getPaint();
        graphics2D.setPaint(Color.black);
        repaint();
    }

    public void erase() {
        graphics2D.setPaint(backgroundColor);
        repaint();
    }

    public void random(Color color) {
        graphics2D.setPaint(color);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);

            // this lets us draw on the image (ie. the canvas)
            graphics2D = (Graphics2D) image.getGraphics();

            // gives us better rendering quality for the drawing lines
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // set canvas to white with default paint color
            graphics2D.setPaint(Color.white);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            backgroundColor = (Color) graphics2D.getPaint();
            graphics2D.setPaint(Color.black);
            graphics2D.setStroke(new BasicStroke(5));
            repaint();
        }
        g.drawImage(image, 0, 0, null);
    }

    public Paint() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // set oldX and oldY coordinates to beginning mouse press
                oldX = e.getX();
                oldY = e.getY();
            }
        });


        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // set current coordinates to where mouse is being dragged
                curX = e.getX();
                curY = e.getY();

                // draw the line between old coordinates and new ones
                graphics2D.drawLine(oldX, oldY, curX, curY);

                // refresh frame and reset old coordinates
                repaint();
                oldX = curX;
                oldY = curY;

            }
        });
    }
}
