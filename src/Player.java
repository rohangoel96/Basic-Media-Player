import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class Player {

	    private final JFrame frame;
	    private final JButton rewindButton;
	    private final JButton pauseButton;
	    private final JButton forwardButton;
	    
	    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

	    public static void main(final String[] args) {
	        new NativeDiscovery().discover();
	        
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new Player(args);
	            }
	        });
	    }

	    public Player(String[] args) {
	    	JPanel contentPane = new JPanel();
	    	contentPane.setLayout(new BorderLayout());

	    	mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	    	contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

	    	JPanel controlsPane = new JPanel();
	    	pauseButton = new JButton("Pause");
	    	controlsPane.add(pauseButton);
	    	rewindButton = new JButton("Rewind");
	    	controlsPane.add(rewindButton);
	    	forwardButton = new JButton("Forward");
	    	controlsPane.add(forwardButton);
	    	contentPane.add(controlsPane, BorderLayout.SOUTH);
	    	
	    	frame = new JFrame("Rohan's Media Player");
	        frame.setBounds(100, 100, 600, 400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setContentPane(contentPane);
	    	frame.setVisible(true);
	    	
	        mediaPlayerComponent.getMediaPlayer().playMedia(args[0]);

	    }
	}
