package FX_��ͼ;
/**
* @author HLX
* @version ����ʱ�䣺2018��10��5�� ����10:25:10
* @description
*/

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class ColorTilePane extends TilePane {

	private VBox vBox;
	private HBox hBox;
	private TextField color_tf;

	public ColorTilePane() {
		color_tf = new TextField();
		hBox = new HBox();
		vBox = new VBox(hBox);
	}

	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		Button copy_btn = new Button("����");

		hBox.getChildren().addAll(color_tf, copy_btn);

//		layoutInArea(vBox, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, HPos.CENTER, VPos.CENTER);
//
		color_tf.setOnMouseClicked( me -> {
			color_tf.selectAll();
		});
		copy_btn.setOnMouseClicked( me -> {
			color_tf.selectAll();
			final Clipboard clipboard = Clipboard.getSystemClipboard();
			final ClipboardContent content = new ClipboardContent();
			content.putString(color_tf.getText() == null ? "null" : color_tf.getText());
			clipboard.setContent(content);
		});
	}
	public void setColorCode(String colorCode) {  //��color_tf������ɫ����
		if(color_tf == null)
			color_tf = new TextField(colorCode);
		color_tf.setText(colorCode);
	}
}
