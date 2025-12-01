import java.awt.BorderLayout; // Import BorderLayout
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.util.ImageUtils;

public class WebCamSaif {

    public static void main(String[] args) throws IOException, Exception {
        captureWithPanel();
    }

    public static void captureWithPanel() throws IOException, Exception {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setImageSizeDisplayed(true);

        // Create a JPanel for button layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);

        JButton button = new JButton("Click Here");
        button.setBounds(20, 20, 100, 30); // Adjusted size for visibility

        JButton button1 = new JButton("Size");
        button1.setBounds(130, 20, 100, 30); // Adjusted size for visibility

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage image = webcam.getImage();
                    ImageIO.write(image, ImageUtils.FORMAT_JPG, new File("H:\\code\\WebCamSaif.jpg"));
                    button.setText("Captured");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    System.out.println("PICTURE IS CLICKED");
                }
            }
        });

        buttonPanel.add(button);
        buttonPanel.add(button1);

        // Add the button panel to the JFrame
        JFrame window = new JFrame("Webcam");
        window.setLayout(new BorderLayout()); // Use BorderLayout
        window.add(panel, BorderLayout.CENTER);
        window.add(buttonPanel, BorderLayout.SOUTH);

        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
