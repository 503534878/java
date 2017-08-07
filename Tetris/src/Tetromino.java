import java.awt.Color;
import java.util.Random;

public class Tetromino {
	public Color c;
	public Lattice[] lattices = new Lattice[4];
	//随机生成四格拼版
	public static Tetromino randomType(){
		Random r = new Random();
		switch(r.nextInt(7)) {
		case 0:return new Swagerly();
		case 1:return new Reverse_Swagerly();
		case 2:return new L_Block();
		case 3:return new Reverse_L_Block();
		case 4:return new T_Block();
		case 5:return new Square();
		case 6:return new Line_Piece();
		}
		return null;
	}
	//遍历方格逐个下移
	public void moveDown() {
		for (int i = 0; i < lattices.length; i++) {
			lattices[i].moveDown();
		}
	}
	//遍历方格逐个右移
	public void moveRight() {
		for (int i = 0; i < lattices.length; i++) {
			lattices[i].moveRight();
		}
	}
	//遍历方格逐个左移
	public void moveLeft() {
		for (int i = 0; i < lattices.length; i++) {
			lattices[i].moveLeft();
		}
	}
	//获取拼版第二个方格作为对称中心	顺时针旋转90°
	public Lattice[] spin() {
		if(this.getClass().equals(new Square().getClass()))return null;
		Lattice[] l = new Lattice[4];
		int centerRow = this.lattices[1].getRow();
		int centerCol = this.lattices[1].getCol();
		for (int i = 0; i < lattices.length; i++) {
			int row = lattices[i].getRow();
			int col = lattices[i].getCol();
			l[i] = new Lattice(col-centerCol+centerRow,centerRow-row+centerCol,lattices[i].getColor());
		}
		return l;
	}
}
//建立四格拼版子类  用于实例化7种拼版
class Swagerly extends Tetromino {

	public Swagerly() {
		super();
		c = Color.CYAN;
		lattices[0] = new Lattice(0,4,Color.CYAN);
		lattices[1] = new Lattice(0,5,Color.CYAN);
		lattices[2] = new Lattice(1,5,Color.CYAN);
		lattices[3] = new Lattice(1,6,Color.CYAN);
	}
	
}
class Reverse_Swagerly extends Tetromino {

	public Reverse_Swagerly() {
		super();
		c = Color.red;
		lattices[0] = new Lattice(0,6,Color.red);
		lattices[1] = new Lattice(0,5,Color.red);
		lattices[2] = new Lattice(1,5,Color.red);
		lattices[3] = new Lattice(1,4,Color.red);
	}
	
}
class L_Block extends Tetromino {

	public L_Block() {
		super();
		c = Color.blue;
		lattices[0] = new Lattice(0,4,Color.blue);
		lattices[1] = new Lattice(1,4,Color.blue);
		lattices[2] = new Lattice(2,4,Color.blue);
		lattices[3] = new Lattice(2,5,Color.blue);
	}
	
}
class Reverse_L_Block extends Tetromino {

	public Reverse_L_Block() {
		super();
		c = Color.green;
		lattices[0] = new Lattice(0,5,Color.green);
		lattices[1] = new Lattice(1,5,Color.green);
		lattices[2] = new Lattice(2,5,Color.green);
		lattices[3] = new Lattice(2,4,Color.green);
	}
	
}
class T_Block extends Tetromino {

	public T_Block() {
		super();
		c = Color.ORANGE;
		lattices[0] = new Lattice(0,5,Color.ORANGE);
		lattices[1] = new Lattice(1,5,Color.ORANGE);
		lattices[2] = new Lattice(1,4,Color.ORANGE);
		lattices[3] = new Lattice(1,6,Color.ORANGE);
	}
	
}
class Square extends Tetromino {

	public Square() {
		super();
		c = Color.YELLOW;
		lattices[0] = new Lattice(0,4,Color.YELLOW);
		lattices[1] = new Lattice(0,5,Color.YELLOW);
		lattices[2] = new Lattice(1,4,Color.YELLOW);
		lattices[3] = new Lattice(1,5,Color.YELLOW);
	}
	
}
class Line_Piece extends Tetromino {

	public Line_Piece() {
		super();
		c = Color.MAGENTA;
		lattices[0] = new Lattice(0,4,Color.MAGENTA);
		lattices[1] = new Lattice(0,5,Color.MAGENTA);
		lattices[2] = new Lattice(0,6,Color.MAGENTA);
		lattices[3] = new Lattice(0,7,Color.MAGENTA);
	}
	
}