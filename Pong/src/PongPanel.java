import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;


public class PongPanel extends JPanel implements ActionListener, KeyListener{
	
	private final static Color BACKGROUND_COLOUR = Color.BLACK;
	private final static int TIMER_DELAY = 5;
	Ball ball;
	Paddle paddle1, paddle2;
	//boolean gameInitialised = false;
	GameState gameState = GameState.Initialising;
	private final static int BALL_MOVEMENT_SPEED = 3;
	private final static int POINTS_TO_WIN = 3;
	int player1Score = 0, player2Score = 0;
	Player gameWinner;
	private final static int SCORE_TEXT_X = 100;
	private final static int SCORE_TEXT_Y = 100;
	private final static int SCORE_FONT_SIZE = 50;
	private final static String SCORE_FONT_FAMILY = "Serif";
	private final static int WIN_TEXT_X = 200;
	private final static int WIN_TEXT_Y = 200;
	private final static int WIN_FONT_SIZE = 50;
	private final static String WIN_FONT_FAMILY = "Serif";
	private final static String WINNER_TEXT = "Winner!";
	
	public PongPanel() {
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());	
	}
	
	private void update() {
		switch(gameState) {
			case Initialising: {
				createObjects();
				gameState = GameState.Playing;
				ball.setXVelocity(BALL_MOVEMENT_SPEED);
				ball.setYVelocity(BALL_MOVEMENT_SPEED);
				break;
			}
			case Playing: {
				moveObject(paddle1);	//	Move paddle one
				moveObject(paddle2);	//	Move paddle two
				moveObject(ball);		//	Move the ball
				checkWallBounce();		//	Check for wall bounce
				checkPaddleBounce();	//	Check for paddle bounce
				checkWin();				//	Check for win
				break;
			}
			case GameOver: {
				break;
			}
		}
		
		/***if(!gameInitialised) {
			createObjects();
			gameInitialised = true;
		}***/
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setColor(Color.WHITE);
		//g.fillRect(20, 20, 100, 100);
		paintDottedLine(g);
		if(gameState != GameState.Initialising) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
			paintWinner(g);
		}
	}

	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
	}
	
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		if(event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setYVelocity(-10);
		} else if(event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setYVelocity(10);
		}
		
		
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setYVelocity(-10);
		} else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setYVelocity(10);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setYVelocity(0);
		}
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setYVelocity(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		update();
		repaint();
	}

	private void moveObject(Sprite object) {
		object.setXPosition(object.getXPosition() + object.getXVelocity(), getWidth());
		object.setYPosition(object.getYPosition() + object.getYVelocity(), getHeight());
	}
	
	private void checkWallBounce() {
		if(ball.getXPosition() <= 0) {			// Hit left side of screen
			ball.setXVelocity(-ball.getXVelocity());
			addScore(Player.Two);				// Increase Player Twos score
			resetBall();
		} else if(ball.getXPosition() >= getWidth() - ball.getWidth()) {		// Hit right side of screen
			ball.setXVelocity(-ball.getXVelocity());
			addScore(Player.One);				// Increase Player Ones score
			resetBall();
		}
		
		if(ball.getYPosition() <= 0 || ball.getYPosition() >= getHeight() - ball.getHeight()) {		// Hit top or bottom of screen
			ball.setYVelocity(-ball.getYVelocity());
		}
	}
	
	public void resetBall() {
		ball.resetToInitialPosition();
	}
	
	private void checkPaddleBounce() {
		if(ball.getXVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setXVelocity(BALL_MOVEMENT_SPEED);
		}
		if(ball.getXVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
			ball.setXVelocity(-BALL_MOVEMENT_SPEED);
		}
	}
	
	public void addScore(Player player) {
		if(player == Player.One) {
			player1Score++;
		} else if(player == Player.Two) {
			player2Score++;
		}
	}
	
	public void checkWin() {
		if(player1Score == POINTS_TO_WIN) {
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		}
		if(player2Score == POINTS_TO_WIN) {
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}
	}
	
	private void paintScores(Graphics g) {
		Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		String leftScore = Integer.toString(player1Score);		// Convert Integer scores to string
		String rightScore = Integer.toString(player2Score);	
		g.setFont(scoreFont);
		g.drawString(leftScore, SCORE_TEXT_X, SCORE_TEXT_Y);	// Draw Player ones score
		g.drawString(rightScore, getWidth()-SCORE_TEXT_X, SCORE_TEXT_Y);	// Draw Player twos score
	}
	
	public void paintWinner(Graphics g) {
		Font winfont = new Font(WIN_FONT_FAMILY, Font.BOLD, WIN_FONT_SIZE);
		g.setFont(winfont);
		if(gameWinner == Player.One) {
			g.drawString(WINNER_TEXT, WIN_TEXT_X, WIN_TEXT_Y);
		} else if(gameWinner == Player.Two) {
			g.drawString(WINNER_TEXT, getWidth()-WIN_TEXT_X, WIN_TEXT_Y);
		}
		
		/***
		 * if(gameWinner != null){
		 * Font winnerFont = new Font(WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
		 * g.setFont(winnerFont);
		 * int xPosition = getWidth() / 2;
		 * if(gameWinner == Player.One) {
		 * xPosition -= WINNER_TEXT_X;
		 * } else if(gameWinner == Player.Two){
		 * xPosition += WINNER_TEXT_X;
		 * }
		 * g.drawString(WINNER_TEXT, xPosition, WINNER_TEXT_Y);
		 * }
		 * }
		 */
		
	}
}
