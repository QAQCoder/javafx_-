package FX_绘图Demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
* @author HLX
* @version 创建时间：2018年10月9日 下午2:26:44
* @description
*/
public class SecondDemo extends Application implements Initializable {

	public static void main(String[] args) {
		launch(args);
	}

	private Stage stage;
	private String path = "SecondFXML.fxml";

	@FXML private Tab tab_1;
	@FXML private Tab tab_2;
	@FXML private AnchorPane tab1_anchorPane;
	@FXML private AnchorPane tab2_anchorPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFXML.fxml"));
		BorderPane root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Canvas-绘制直线");
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize.............");
		initTab1();
		initTab2();
	}

	private void initTab1() {
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource(path));
		BorderPane bp;
		try {
			bp = (BorderPane)loader2.load();
			tab1_anchorPane.getChildren().add(bp);
			bp.prefWidthProperty().bind(tab1_anchorPane.widthProperty());
			bp.prefHeightProperty().bind(tab1_anchorPane.heightProperty());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initTab2() {
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("OvalFXML.fxml"));
		BorderPane bp;
		try {
			bp = (BorderPane)loader2.load();
			tab2_anchorPane.getChildren().add(bp);
			bp.prefWidthProperty().bind(tab2_anchorPane.widthProperty());
			bp.prefHeightProperty().bind(tab2_anchorPane.heightProperty());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
