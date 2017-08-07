import java.awt.Color;

public class Lattice {
	private int row;
	private int col;
	private Color color;

	
	public Lattice(int row, int col,Color color) {
		super();
		this.row = row;
		this.col = col;
		this.color = color;
	}
	public void moveDown() {
		setRow(this.getRow()+1);

	}
	public void moveRight() {
		setCol(this.getCol()+1);

	}
	public void moveLeft() {
		setCol(this.getCol()-1);

	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
