import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class Player {

	    private final JFrame frame;

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
	        frame = new JFrame("Rohan's Media Player");
	        frame.setBounds(100, 100, 600, 400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	        frame.setContentPane(mediaPlayerComponent);
	        frame.setVisible(true);
	        mediaPlayerComponent.getMediaPlayer().playMedia(args[0]);
	    }
	}
