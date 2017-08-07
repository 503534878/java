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
	//创建Frame窗口
	public Launcher(String s) throws HeadlessException {
		super(s);
		setBounds(300, 300, 400, 400);
		setVisible(true);
		//添加window监听器   用于关闭窗口
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMoniter());	
	}
	//声明按键监听器内部类 设置操作方法
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
	//双缓冲绘图
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
    //状态判断方法
    //确认是否稳定(接触到障碍  不能下落)
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
	//使拼版稳定 从tetromino中存入wall数组 从新获取下一个tetromino
	public void stable() {
		for (int j = 0; j < tetromino.lattices.length; j++) {
			Lattice l = tetromino.lattices[j];
			wall[l.getRow()][l.getCol()] = l;
		}
		removeLine();
		tetromino = nextTetromino;
		nextTetromino = Tetromino.randomType();

	}
	//判断是否游戏结束
	public boolean isGameOver() {
		for (int col = 4; col <= 8; col++) {
			if(wall[0][col] != null)
				return true;
		}
		return false;
	}
	//判断能否向左移动
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
	//判断能否向右移动
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
	//判断并消除行 计分
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
	//paint绘图方法
	//重写paint方法
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
	//绘制右侧计分板
	public void paintTable(Graphics g) {
		Font temp = g.getFont();
		paintNext(g);
		g.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,30));
		g.drawString("Score:", 220, 165);
		g.drawString(String.valueOf(score), 315, 165);
		g.setFont(temp);
	}
	//绘制四格拼版
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
	//绘制下一个四格拼版
	public void paintNext(Graphics g) {
		if(nextTetromino==null)return;
			paintTetromino(g,nextTetromino,true);
		
	}
	//绘制已经稳定的障碍物
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
	//绘制GameOver
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
	//拼版移动旋转方法
	//向下移动
	public void moveDownAction() {
		if(!this.isStable())
			tetromino.moveDown();
		else
			this.stable();
	}
	//快速降落
	public void fastLanding() {
		while(!this.isStable()){
		tetromino.moveDown();
		}
		this.stable();
	}
	//向左移动
	public void moveLeftAction() {
		if(this.canMoveLeft())
		tetromino.moveLeft();
	}
	//向右移动
	public void moveRightAction() {
		if(this.canMoveRight())
		tetromino.moveRight();
	}
	//旋转动作
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
	//初始化参数	启动计时器		开始新游戏
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
	//实例化Launcher 启动游戏
	public static void main(String[] args) {
		Launcher l = new Launcher("Tetris");
		l.restart();
	}
}
