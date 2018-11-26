package FX_��ͼDemo;

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
* @version ����ʱ�䣺2018��10��5�� ����9:05:03
* @description FX���İ�-P506
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

		//������������ػ�
		canvas.setOnMouseClicked( (MouseEvent me) -> {
			GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
			graphicsContext2D.setFill(Color.WHITE);
			graphicsContext2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			paintCanvas(canvas);
		});

		Scene scene = new Scene(root, Color.PINK);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Canvas��ϰ");
		primaryStage.show();
	}

	/**
	 * ����Canvas����
	 * @param canvas
	 */
	private void paintCanvas(Canvas canvas) {
		canvas.setWidth(800); canvas.setHeight(500);  //���û������Ϳ�
		GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();  //��canvasȡ��graphicContext

		Random random = new Random();
		for(int i = 1; i <= 20; i++) {
			int index = random.nextInt(3);

			double centerX = Math.random() * 300;  //���û������ĵĺ�����
			double centerY = Math.random() * 300;  //���û������ĵ�������
			double radiusX = Math.random() * 200;  //���ε�ˮƽ�뾶
			double radiusY = Math.random() * 200;  //���εĴ�ֱ�뾶

			double leftX = Math.random() * 300;   //�������Ͻ�ˮƽ����
			double leftY = Math.random() * 300;  //�������ϽǴ�ֱ����

			double width = Math.random() * 300; //���εĿ��
			double height = Math.random() * 300; //�߶�

			double startAngle = Math.random() * 300; //���ο�ʼ�Ƕ�
			double length = Math.random() * 300; //���ε��ߵĳ���
			double arcExtent = Math.random() * 200; //���εĻ��Ƿ�Χ

			int red = random.nextInt(255);  //����������ɫ
			int green = random.nextInt(255);
			int blue = random.nextInt(255);

			switch (index) {
			case 0:
				//���ƻ���
				graphicsContext2D.arc(centerX, centerY, radiusX, radiusY, startAngle, length);
				break;
			case 1:
				graphicsContext2D.setFill(Color.rgb(red, green, blue)); //���������ɫ
				//������������
				graphicsContext2D.fillArc(leftX, leftY, width, length, startAngle, arcExtent, ArcType.CHORD);
				break;
			case 2:
				graphicsContext2D.setFill(Color.rgb(red, green, blue)); //���������ɫ
				graphicsContext2D.fillArc(leftX, leftY, width, length, startAngle, arcExtent, ArcType.ROUND);
				break;
			case 3:
				graphicsContext2D.setFill(Color.rgb(red, green, blue)); //���������ɫ
				graphicsContext2D.fillArc(leftX, leftY, width, length, startAngle, arcExtent, ArcType.OPEN);
				break;
			default:
				break;
			}
		}
	}
}
