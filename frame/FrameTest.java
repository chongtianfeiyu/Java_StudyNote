package frame;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameTest {

	public static void main(String[] args) {
		 EventQueue.invokeLater( new Runnable() {
		 	public void run() {
		 		SimpleFrame frame = new SimpleFrame();
		 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 		frame.setVisible(true);
		 	}
		 });
		//输出系统拥有的字体
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String fontName : fontNames) 
        	System.out.println(fontName);
	}

}

class SimpleFrame extends JFrame{
	/**
	 * @author zyh
	 */
	private static final long serialVersionUID = 1L;
	public SimpleFrame() {
		setTitle("fuck you");
        Toolkit kit = Toolkit.getDefaultToolkit();
        
        Image img = kit.getImage("/home/zyh/workspace/testJava/src/frame/icon.jpg");
        setIconImage(img);
        /*
         * 得到屏幕的像素大小。双屏的话，长度取两者之和，宽度取最大者
         */
        Dimension screenSize = kit.getScreenSize();        
        setSize(screenSize.width / 4, screenSize.height / 2);
        System.out.println(screenSize.width + "\n" + screenSize.height);
        //让系统决定框架放在位置
        setLocationByPlatform(true);
        //设背景颜色，直接setBackground不行，要先得到需要改颜色的Component，再setBackground
//        getComponent(0).setBackground(Color.YELLOW);
        MyPanel panel = new MyPanel(screenSize.width / 4, screenSize.height / 2);
//        setLayout(null);
//        panel.setBackground(Color.BLUE);
        add(panel);
//        ImagePanel ima = new ImagePanel();
//        add(ima);
	}
}

class MyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MyPanel() { }
	public MyPanel(int width, int height) {
		setBounds(0, 0, width, height);
	}
	
	//下面这个方法在执行两次，不知道为啥，应该是程序自己调用的。
	//更新：每当改变界面大小，则会重新绘制一遍，即下面的方法会重新执行
	//关于背景颜色：若没有下面的方法，则可以通过panel.setBackground
	public void paintComponent(Graphics g) {
		String message = "Hello, World! p g";
		Graphics2D g2 = (Graphics2D) g;
		/*
		 * 设置字体
		 */
        Font f = new Font("SansSerif", Font.BOLD, 20);
        g2.setFont(f);
        FontRenderContext context = g2.getFontRenderContext();
        // bounds得到的是 message 的字体数据如长、宽、上坡度
        Rectangle2D bounds = f.getStringBounds(message, context);
        
        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = (getHeight() - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        System.out.println("上坡度：" + ascent);
        double baseY = y + ascent;
        //字体的定位是用于基线的，也就是说，如果定在（0,0）处，那么"Hello, World! p g"只能看见 “, p g” 下坡度的东西
        g2.drawString(message, (int)x, (int)baseY);
//		g2.drawString(message, 0, 0);
        
        g2.setPaint(Color.LIGHT_GRAY);
        g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));
        g2.draw(new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight()));
		//设置颜色
		g2.setPaint(Color.RED);
		
		/*
		 * 绘制3D的矩形
		 */
		g2.draw3DRect(500, 10, 100, 150, true);
		
		/*
		 * 矩形
		 */
		Rectangle2D rect = new Rectangle2D.Double(110, 100, 200, 150);
		System.out.println(rect.getX() + "\ttime:  " + System.currentTimeMillis());
		g2.draw(rect);
		g2.setPaint(Color.BLUE);
		
		/*
		 * 椭圆
		 */
		Ellipse2D e =new Ellipse2D.Double();
		//根据矩形的边得到椭圆
		e.setFrame(rect);
		g2.draw(e);
		g2.setPaint(Color.LIGHT_GRAY);
		
		/*
		 * 直线
		 */
		g2.draw(new Line2D.Double(100, 100, 200, 150));
		
		/*
		 * 圆形
		 */
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(rect.getCenterX(), rect.getCenterY(), rect.getCenterX()+150, rect.getCenterY()+150);
		g2.draw(circle);
	}
}

class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ImagePanel() {
		try {
			image = ImageIO.read(new File("/home/zyh/workspace/testJava/src/frame/pic.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		if (image == null) return ;
		
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		System.out.println(this.getClass());
		g.drawImage(image, 0, 0, null);
		
		//一列一列铺满图像
		for (int i = 0; i * imageWidth <= getWidth(); ++i) 
			for (int j = 0; j * imageHeight <= getHeight(); ++j) 
				if (i + j > 0)
					g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
	}
	
	private Image image = null;
}