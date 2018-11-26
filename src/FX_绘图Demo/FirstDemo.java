package FX_绘图Demo;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
* @author HLX
* @version 创建时间：2018年10月5日 上午9:05:03
* @description FX中文版-P506
*/
public class FirstDemo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = new Canvas();
		paintCanvas(canvas);
		BorderPane root = new BorderPane(canvas);

		//鼠标点击画布，重绘
		canvas.setOnMouseClicked( (MouseEvent me) -> {
			GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
			graphicsContext2D.setFill(Color.WHITE);
			graphicsContext2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			paintCanvas(canvas);
		});

		Scene scene = new Scene(root, Color.PINK);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Canvas练习");
		primaryStage.show();
	}

	/**
	 * 根据Canvas绘制
	 * @param canvas
	 */
	private void paintCanvas(Canvas canvas) {
		canvas.setWidth(800); canvas.setHeight(500);  //设置画布长和宽
		GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();  //由canvas取得graphicContext

		Random random = new Random();
		for(int i = 1; i <= 20; i++) {
			int index = random.nextInt(3);

			double centerX = Math.random() * 300;  //设置弧形中心的横坐标
			double centerY = Math.random() * 300;  //设置弧形中心的纵坐标
			double radiusX = Math.random() * 200;  //弧形的水平半径
			double radiusY = Math.random() * 200;  //弧形的垂直半径

			double leftX = Math.random() * 300;   //弧形左上角水平坐标
			double leftY = Math.random() * 300;  //弧形右上角垂直坐标

			double width = Math.random() * 300; //弧形的宽度
			double height = Math.random() * 300; //高度

			double startAngle = Math.random() * 300; //弧形开始角度
			double length = Math.random() * 300; //弧形底线的长度
			double arcExtent = Math.random() * 200; //弧形的弧角范围

			int red = random.nextInt(255);  //设置填充的颜色
			int green = random.nextInt(255);
			int blue = random.nextInt(255);

			switch (index) {
			case 0:
				//绘制弧形
				graphicsContext2D.arc(centerX, centerY, radiusX, radiusY, startAngle, length);
				break;
			case 1:
				graphicsContext2D.setFill(Color.rgb(red, green, blue)); //设置填充颜色
				//绘制填满弧形
				graphicsContext2D.fillArc(leftX, leftY, width, length, startAngle, arcExtent, ArcType.CHORD);
				break;
			case 2:
				graphicsContext2D.setFill(Color.rgb(red, green, blue)); //设置填充颜色
				graphicsContext2D.fillArc(leftX, leftY, width, length, startAngle, arcExtent, ArcType.ROUND);
				break;
			case 3:
				graphicsContext2D.setFill(Color.rgb(red, green, blue)); //设置填充颜色
				graphicsContext2D.fillArc(leftX, leftY, width, length, startAngle, arcExtent, ArcType.OPEN);
				break;
			default:
				break;
			}
		}
	}
}
