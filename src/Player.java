import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class Player {

	    private final JFrame frame;
	    private final JButton rewindButton;
	    private final JButton pauseButton;
	    private final JButton forwardButton;
	    private final JLabel sourceLabel;
	    private final JTextField videoSource;	//enter the source of the video for example C:\a.mkv
	    private JLabel status;
	    
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
	    	
	    	JPanel sourcePane = new JPanel();
	    	sourceLabel = new JLabel("Video Source : ");
	    	sourcePane.add(sourceLabel);
	    	videoSource = new JTextField(20);
	    	sourcePane.add(videoSource);
	    	status = new JLabel("...");
	    	sourcePane.add(status);
	    	contentPane.add(sourcePane, BorderLayout.NORTH);
	    	
	    	
	    	frame = new JFrame("Rohan's Media Player");
	        frame.setBounds(100, 100, 600, 400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setContentPane(contentPane);
	    	frame.setVisible(true);
	    	
	       

	        
	        pauseButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                mediaPlayerComponent.getMediaPlayer().pause();
	                if(status.getText()=="Paused...")
	                	status.setText("Playing...");
	                else
	                	status.setText("Paused...");
	            }
	        });
	        
	        rewindButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                mediaPlayerComponent.getMediaPlayer().skip(-10000);
	               
	            }
	        });
	        
	        forwardButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                mediaPlayerComponent.getMediaPlayer().skip(10000);
	            }
	        });
	        
	        videoSource.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					
					mediaPlayerComponent.getMediaPlayer().playMedia(videoSource.getText());
						 
				}
			});
	        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			    @Override
			    public void playing(MediaPlayer mediaPlayer) {
			    	status.setText("Playing...");
			    }

			    @Override
			    public void finished(MediaPlayer mediaPlayer) {
			    	
			    	status.setText("Finshed Playing...");
			    }

			    @Override
			    public void error(MediaPlayer mediaPlayer) {
			    	status.setText("Enter a Valid File Source");
			    }
			});
	        
	        
	    }
	}
