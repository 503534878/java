import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;


public class Launcher extends Frame{
	private static int ROWS = 21;
	private static int COLS = 10;
	private Lattice[][] wall = new Lattice[21][10];
	private Color temp;
	private Tetromino tetromino = Tetromino.randomType();
	private Tetromino nextTetromino = Tetromino.randomType();
//	private boolean pause = false;
	private Image image = new ImageIcon("backgound.png").getImage();
	private int score = 0;
	//����Frame����
	public Launcher(String s) throws HeadlessException {
		super(s);
		setBounds(300, 300, 400, 400);
		setVisible(true);
		//���window������   ���ڹرմ���
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMoniter());	
	}
	//���������������ڲ��� ���ò�������
	class KeyMoniter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(!isGameOver()){
				super.keyPressed(e);
				int key = e.getKeyCode();
				switch(key){
				case KeyEvent.VK_UP:spinAction();break;
				case KeyEvent.VK_DOWN:moveDownAction();break;
				case KeyEvent.VK_LEFT:moveLeftAction();break;
				case KeyEvent.VK_RIGHT:moveRightAction();break;
				case KeyEvent.VK_SPACE:fastLanding();break;
//				case KeyEvent.VK_R:restart();break;
				}
				repaint();
			}
		}
	}
	//˫�����ͼ
	private Image iBuffer;  
    private Graphics gBuffer;  
    public void update(Graphics scr)  
    {  
        if(iBuffer==null)  
        {  
           iBuffer=createImage(this.getSize().width,this.getSize().height);  
           gBuffer=iBuffer.getGraphics();  
        }  
        // gBuffer.setColor(getBackground());  
        // gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);  
           paint(gBuffer);  
           scr.drawImage(iBuffer,0,0,this);  
    }  
    //״̬�жϷ���
    //ȷ���Ƿ��ȶ�(�Ӵ����ϰ�  ��������)
	public boolean isStable() {
		if(tetromino==null)return false;
		Lattice[] lattices = tetromino.lattices;
		for (int i = 0; i < lattices.length; i++) {
			int row = lattices[i].getRow();
			int col = lattices[i].getCol();
			if((row+1)==this.ROWS||wall[row+1][col]!=null){
				return true;
			}
		}
		return false;

	}
	//ʹƴ���ȶ� ��tetromino�д���wall���� ���»�ȡ��һ��tetromino
	public void stable() {
		for (int j = 0; j < tetromino.lattices.length; j++) {
			Lattice l = tetromino.lattices[j];
			wall[l.getRow()][l.getCol()] = l;
		}
		removeLine();
		tetromino = nextTetromino;
		nextTetromino = Tetromino.randomType();

	}
	//�ж��Ƿ���Ϸ����
	public boolean isGameOver() {
		for (int col = 4; col <= 8; col++) {
			if(wall[0][col] != null)
				return true;
		}
		return false;
	}
	//�ж��ܷ������ƶ�
	public boolean canMoveLeft() {
		if(tetromino==null)return false;
		Lattice[] lattices = tetromino.lattices;
		for (int i = 0; i < lattices.length; i++) {
			int row = lattices[i].getRow();
			int col = lattices[i].getCol();
			if((col-1)==-1||wall[row][col-1]!=null)return false;
		}
		return true;
	}
	//�ж��ܷ������ƶ�
	public boolean canMoveRight() {
		if(tetromino==null)return false;
		Lattice[] lattices = tetromino.lattices;
		for (int i = 0; i < lattices.length; i++) {
			int row = lattices[i].getRow();
			int col = lattices[i].getCol();
			if((col+1)==this.COLS||wall[row][col+1]!=null)return false;
		}
		return true;
	}
	//�жϲ������� �Ʒ�
	public void removeLine() {
		int count = 0;
		boolean remove = true;
		int removeRow = 21;
		for (int row = 0; row < this.ROWS; row++) {
			for (int col = 0; col < this.COLS; col++) {
				if(wall[row][col]==null){
					remove = false;
					break;
				}
			}
			if(remove){
				for(int col = 0;col<this.COLS; col++){
					wall[row][col] = null;
				}
				count++;
				removeRow = row;
				for (int iRow = removeRow; iRow > 0; iRow--) {
					for (int iCol = 0; iCol < this.COLS; iCol++) {
						if(wall[iRow-1][iCol]!=null){
							wall[iRow-1][iCol].setRow(iRow);
						}
					wall[iRow][iCol] = wall[iRow-1][iCol];
					}
				}
			}
			else{
				remove = true;
			}
		}
		switch(count){
		case 1:score += 10;break;
		case 2:score += 30;break;
		case 3:score += 60;break;
		case 4:score += 100;break;
		}
		count = 0;
	}
	//paint��ͼ����
	//��дpaint����
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(image, 0, 0, null);
		g.drawRect(45, 45, 150, 315);
		paintTetromino(g,tetromino,false);
		paintTable(g);
		paintWall(g);
		paintGameOver(g);
	}
	//�����Ҳ�Ʒְ�
	public void paintTable(Graphics g) {
		Font temp = g.getFont();
		paintNext(g);
		g.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,30));
		g.drawString("Score:", 220, 165);
		g.drawString(String.valueOf(score), 315, 165);
		g.setFont(temp);
	}
	//�����ĸ�ƴ��
	public void paintTetromino(Graphics g, Tetromino t,boolean next) {
		temp = g.getColor();
		g.setColor(t.c);
		for (int i = 0; i < t.lattices.length; i++) {
			if(!next)
			g.fill3DRect(t.lattices[i].getCol()*15+45,
					t.lattices[i].getRow()*15+45, 15, 15,true);
			else
			g.fill3DRect((t.lattices[i].getCol()+12)*15+45,
					(t.lattices[i].getRow()+1)*15+45, 15, 15,true);
		}
		g.setColor(temp);
	}
	//������һ���ĸ�ƴ��
	public void paintNext(Graphics g) {
		if(nextTetromino==null)return;
			paintTetromino(g,nextTetromino,true);
		
	}
	//�����Ѿ��ȶ����ϰ���
	public void paintWall(Graphics g) {
		temp = g.getColor();
		for (int i = 0; i < wall.length; i++) {
			for (int j = 0; j < wall[i].length; j++) {
				if(wall[i][j]!=null){
					g.setColor(wall[i][j].getColor());
					g.fill3DRect(wall[i][j].getCol()*15+45, wall[i][j].getRow()*15+45, 15, 15,true);
				}
			}
		}
		g.setColor(temp);
	}
	//����GameOver
	public void paintGameOver(Graphics g) {
		Font temp = g.getFont();
		Color tempColor = g.getColor();
		if(isGameOver()){
		tetromino = null;
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font(Font.SERIF,Font.BOLD,50));
		g.drawString("GAMEOVER", 50, 210);
		g.setFont(temp);
		g.setColor(tempColor);
		}
	}
	//ƴ���ƶ���ת����
	//�����ƶ�
	public void moveDownAction() {
		if(!this.isStable())
			tetromino.moveDown();
		else
			this.stable();
	}
	//���ٽ���
	public void fastLanding() {
		while(!this.isStable()){
		tetromino.moveDown();
		}
		this.stable();
	}
	//�����ƶ�
	public void moveLeftAction() {
		if(this.canMoveLeft())
		tetromino.moveLeft();
	}
	//�����ƶ�
	public void moveRightAction() {
		if(this.canMoveRight())
		tetromino.moveRight();
	}
	//��ת����
	public void spinAction() {
		Lattice[] l = tetromino.spin();
		if(l==null)return;
		for (int i = 0; i < l.length; i++) {
			int row = l[i].getRow();
			int col = l[i].getCol();
			if(row<0||row>=this.ROWS||col<0||col>=this.COLS||wall[row][col]!=null)return;
		}
		tetromino.lattices = l;
	}
	//��ʼ������	������ʱ��		��ʼ����Ϸ
	public void restart() {
//		pause = false;
		int s = 0;
		wall = new Lattice[this.ROWS][this.COLS];
		tetromino = Tetromino.randomType();
		nextTetromino = Tetromino.randomType();
		Timer t = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				if(!isGameOver()){
					moveDownAction();
					repaint();
				}
			}
		};
		t.schedule(task, 500, 500);
	}
	//ʵ����Launcher ������Ϸ
	public static void main(String[] args) {
		Launcher l = new Launcher("Tetris");
		l.restart();
	}
}
