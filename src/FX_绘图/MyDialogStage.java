package FX_绘图;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
* @author HLX
* @version 创建时间：2018年10月29日 下午10:44:51
* @description
*/
public class MyDialogStage extends Application implements Initializable {

	private String title;
	private String ccontent;
	private boolean isModalStage;
	private Stage stage;
	@FXML ImageView imageView;
	@FXML Label lbl_Time;

//	public static void main(String[] args) {
//		launch(args);
//	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("myDialogFXML.fxml"));
		BorderPane root = loader.load();
		Scene scene = new Scene(root);
		this.stage = primaryStage;
		stage.setScene(scene);
		stage.setTitle(title);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.resizableProperty().set(false);
		stage.show();
	}

	public MyDialogStage() {
		this(null, null);
	}
	public MyDialogStage(String title, String content) {
		this.title = title;
		this.ccontent = content;
	}
	public void setStageModal(boolean bool) {
		if(bool == true)
			this.stage.initModality(Modality.WINDOW_MODAL);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imageView.setImage(new Image("smile.gif"));
		showTime();
	}

	private void showTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater( () -> lbl_Time.setText(sdf.format(new Date())) );
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 1000, 1000);
	}
//	@FXML private void ok_Btn_Event() {
//		double w = imageView.fitWidthProperty().get();
//		double h = imageView.fitHeightProperty().get();
//		System.out.println(w + "--" + h);
//	}
//	@FXML private void cancel_Btn_Event() {
//		Image image = new Image("smile.gif");
//		System.out.println(image.widthProperty().get() + "---" + image.getHeight());
//		stage.hide();
//	}
}
