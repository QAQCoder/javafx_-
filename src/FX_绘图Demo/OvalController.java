package FX_绘图Demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
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
* @version 创建时间：2018年10月11日 上午10:06:23
* @description
*/
public class OvalController implements Initializable {

	@FXML private Button btn_clear;
	@FXML private ColorPicker colorPicker_sel;
	@FXML private Slider slider_lineWidth;
	@FXML private Label lbl_showWidth;
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
		drawCanvas();
		clearDraw();
		colorSelect();
		strokeWidth();
	}

	private void initCanvas() {
		canvas.widthProperty().bind(vBox.widthProperty());
		canvas.heightProperty().bind(vBox.heightProperty());
	}

	private void drawCanvas() {

		canvas.setOnMousePressed( (MouseEvent me) -> {
			if(first) {
				firstX = me.getX();  firstY = me.getY();
				first = false;
			}
		});

		canvas.setOnMouseReleased( me -> {
			if(first == false) {
				secondX = me.getX(); secondY = me.getY();
				double width = secondX - firstX;
				double height = secondY - firstY;
				first = true;
				graphicsContext.strokeOval(firstX, firstY, width, height);
			}
		});
	}//

	private void clearDraw() {
		btn_clear.setOnMouseClicked( me -> {
			graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		});
	}//

	private void colorSelect() {
		colorPicker_sel.setOnMouseClicked( me -> {
			graphicsContext.setStroke(colorPicker_sel.getValue());
		});
	}

	private void strokeWidth() {
		slider_lineWidth.setMin(1.0);
		slider_lineWidth.setMax(20.0);
		slider_lineWidth.valueProperty().addListener( new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				graphicsContext.setLineWidth(slider_lineWidth.getValue());
				lbl_showWidth.setText(String.format("%.1f", slider_lineWidth.getValue()));
			}
		});
	}
}
