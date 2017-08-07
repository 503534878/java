//import java.awt.Frame;
//import java.awt.Graphics;
//import java.awt.Image;
//
//public class Battle extends Frame {
////	int m[][] = new int[21][10];
//	
//	public Battle() {
//		setBounds(300, 300, 400, 400);
//		setVisible(true);
//	}
//	@Override
//	public void paint(Graphics g) {
//		
////		super.paint(g);
////		for (int i = 0; i < 21; i++) { 
////			for (int j = 0; j < 10; j++) {
////				g.drawRect(j*15+20, i*15+50, 15, 15);
////			}
////		}
//	}
//}
////
////public void paint(Graphics g) {  
////    Graphics tg = this.getBufferStrategy().getDrawGraphics();/*获取后台缓冲画布*/  
////    tg.fillRect(5, 30, 150, 340);/*涂黑背景*/  
////    for (int i = 0; i < 21; i++)  
////        for (int j = 0; j < 10; j++) {  
////            if (matrix[i][j] != 0)  
////                tg.drawImage(images[matrix[i][j]], j * 15 + 5, i * 15 + 15,null);/*根据后台背景的矩阵绘制已经固定的各方块*/  