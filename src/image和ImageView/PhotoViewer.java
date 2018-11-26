package image和ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
* @author HLX
* @version 创建时间：2018年9月23日 下午8:29:52
* @description  P129
*/
public class PhotoViewer extends Application {

	//url字符串列表
	private final List<String> imageFiles = new ArrayList<>();
	// The current index into the imageFile
	private int currentIndex = -1;
	//枚举下一个和以前的按钮指示
	public enum ButtonMove {NEXT, PREV};
	// Current image view display
	private ImageView currentImageView;
	//加载进度指示器
	private ProgressIndicator progressIndicator;
	//不知道是什么
	private AtomicBoolean loading = new AtomicBoolean();

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Chapter 5 Photo Viewer");
		Group root = new Group();
		Scene scene = new Scene(root, 500, 250, Color.DARKGRAY);

		scene.getStylesheets().add(this.getClass().getResource("photo-viewer.css").toExternalForm());
		primaryStage.setScene(scene);

		//设置currentImageView的区域
		currentImageView = createImageView(scene.widthProperty());

		//// set up drag & drop file abilities--支持可拖拽
		setupDragAndDrop(scene);

		primaryStage.show();
	}

	private ImageView createImageView(ReadOnlyDoubleProperty doubleProperty) {
		ImageView imageView = new ImageView();
		//设置纵横比--set aspect ratio
		imageView.setPreserveRatio(true);
		//根据场景调整大小--resize based on the scene
		imageView.fitWidthProperty().bind(doubleProperty);
		return imageView;
	}

	/*
	* Sets up the drag and drop capability for files and URLs to be
	* dragged and dropped onto the scene. This will load the image into
	* the current image view area.
	* @param scene The primary application scene.
	*/
	private void setupDragAndDrop(Scene scene) {
		// Dragging over surface拖过表面
		scene.setOnDragOver((DragEvent de) -> {
			Dragboard dragboard = de.getDragboard();
			if(dragboard.hasFiles() ||
					(dragboard.hasUrl() && isValidImageFile(dragboard.getUrl()))) {
				de.acceptTransferModes(TransferMode.LINK);
			} else {
				de.consume();
			}
		});

		scene.setOnDragDropped((DragEvent de) -> {
			Dragboard db = de.getDragboard();
			if(db.hasFiles() && !db.hasUrl()) {
				db.getFiles().stream().forEach( action -> {

				});
			}
		});
	}

	/**
	 * 如果URL的文件扩展名匹配JPG、JPEG、PNG和GIF，则返回true。
	 * @ PARAM URL标准映像文件URL路径。
	 * 如果URL的扩展与JPG、JPEG、PNG和GIF匹配，则返回布尔返回true。
	 * @return
	 */
	private boolean isValidImageFile(String url) {
		List<String> imgTypes = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp");
		if(imgTypes.contains(url))
			return imgTypes.stream().anyMatch(predicate -> url.endsWith(predicate));  //是否以上面的格式结尾
		return false;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
