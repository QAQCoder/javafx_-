package SVGPath;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
* @author HLX
* @version 创建时间：2018年10月11日 上午11:06:06
* @description
*/
public class Demo1 extends Application {

	SVGPath path = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		path = 画直线();
		Group group = new Group();
		group.getChildren().addAll(加入贝之曲线());
		Scene scene = new Scene(group);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private SVGPath 画直线() {
		SVGPath path = new SVGPath();
		path.setFill(Color.BLUE);
		path.setStroke(Color.YELLOW);
		path.setStrokeWidth(5.0);

		path.setContent("M150 50 L60 220 L240 220 Z");

		return path;
	}

	private SVGPath[] 加入贝之曲线() {
		SVGPath[] ps = new SVGPath[3];

		SVGPath path = new SVGPath();
		path.setFill(Color.BLUE);
		path.setStroke(Color.YELLOW);
		path.setStrokeWidth(5.0);
		path.setContent("M 50 350 l 150 -300");

		SVGPath path2 = new SVGPath();
		path2.setFill(Color.RED);
		path2.setStroke(Color.BLACK);
		path2.setStrokeWidth(5.0);
		path2.setContent("M 200 50 l 150 300");

		SVGPath path3 = new SVGPath();
		path3.setFill(Color.WHITE);
		path3.setStroke(Color.BLUE);
		path3.setStrokeWidth(5.0);
		path3.setContent("M 50 350 q 150 300 300 0");

		ps[0] = path; ps[1] = path2;  ps[2] = path3;
		return ps;
	}
}
