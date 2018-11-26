package FX_��ͼDemo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
* @author HLX
* @version ����ʱ�䣺2018��10��9�� ����2:31:01
* @description
*/
public class SecondFxmlController implements Initializable {

	@FXML private Button clear_Btn;
	@FXML private ColorPicker color_Picker;
	@FXML private ColorPicker bg_colorPicker;
	@FXML private Slider lineWidth_Slider;
	@FXML private Label currentWidth_Lbl;
	@FXML private VBox vBox;
	@FXML private Canvas canvas;
	private GraphicsContext graphicsContext;

	double firstX = 0, firstY = 0;
	double secondX = 0, secondY = 0;
	boolean first = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initCanvas();
		if(graphicsContext == null)
			graphicsContext = canvas.getGraphicsContext2D();
		penColor();
		drawCanvas();
		clearCanvas();
		bgColor();
		currentWidth_Lbl.setText(String.valueOf(lineWidth_Slider.getValue()));
		setLineWidth();
	}

	private void initCanvas() {
		canvas.widthProperty().bind(vBox.widthProperty());
		canvas.heightProperty().bind(vBox.heightProperty());
	}

	private void drawCanvas() {

		canvas.setOnMousePressed( (MouseEvent me) -> { //��갴�µ�ʱ���¼����
			if(first) {
				firstX = me.getX();  firstY = me.getY();
				graphicsContext.strokeLine(firstX, firstY, firstX, firstY);
				first = false;
			}
		});
		canvas.setOnMouseReleased( me -> {  //����ɿ�����¼��������㣬����ȷ��һ��ֱ�ߣ�so��QAQ
			if(first == false) {
				secondX = me.getX();  secondY = me.getY();
				graphicsContext.strokeLine(firstX, firstY, secondX, secondY);
				//�޸ı�־
				first = true;
			}
		});
	}//

	private void penColor() {  //��colorѡ�������û��ʵ���ɫ
		color_Picker.setOnAction( ae -> {
			System.out.println("color_Picker");
//			if(graphicsContext == null)
//				graphicsContext = canvas.getGraphicsContext2D();
			graphicsContext.setStroke(color_Picker.getValue());
		});
	}

	private void bgColor() {
		bg_colorPicker.setOnAction( ae -> {
			System.out.println("bg_colorPicker");
			graphicsContext.setFill(bg_colorPicker.getValue());
		});
	}

	private void clearCanvas() {
		clear_Btn.setOnAction( ae -> {
			System.out.println("clear ");
			graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		});
	}

	private void setLineWidth() {
		lineWidth_Slider.setMin(1.0);
		lineWidth_Slider.setMax(20.0);
		lineWidth_Slider.setValue(graphicsContext.getLineWidth()); //��ȡ��ǰ�����ıʻ����

		//һ������
//		lineWidth_Slider.setShowTickLabels(true);
//		lineWidth_Slider.setShowTickMarks(true); //��ʾһͷһβ������

		lineWidth_Slider.setMajorTickUnit(2.0); //��ʾ�̶ȣ����ü��Ϊ2.0
		lineWidth_Slider.setMinorTickCount(1); //���������Ч��������

		lineWidth_Slider.setBlockIncrement(2.0);

		lineWidth_Slider.setSnapToTicks(true);

		lineWidth_Slider.valueProperty().addListener(
			(ObservableValue<? extends Number> ov, Number oldV, Number newV) -> {
				graphicsContext.setLineWidth(lineWidth_Slider.getValue());
				currentWidth_Lbl.setText(String.format("%.1f", lineWidth_Slider.getValue()));
		});
	}
}
