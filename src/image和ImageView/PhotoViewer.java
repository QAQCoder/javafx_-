package image��ImageView;

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
* @version ����ʱ�䣺2018��9��23�� ����8:29:52
* @description  P129
*/
public class PhotoViewer extends Application {

	//url�ַ����б�
	private final List<String> imageFiles = new ArrayList<>();
	// The current index into the imageFile
	private int currentIndex = -1;
	//ö����һ������ǰ�İ�ťָʾ
	public enum ButtonMove {NEXT, PREV};
	// Current image view display
	private ImageView currentImageView;
	//���ؽ���ָʾ��
	private ProgressIndicator progressIndicator;
	//��֪����ʲô
	private AtomicBoolean loading = new AtomicBoolean();

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Chapter 5 Photo Viewer");
		Group root = new Group();
		Scene scene = new Scene(root, 500, 250, Color.DARKGRAY);

		scene.getStylesheets().add(this.getClass().getResource("photo-viewer.css").toExternalForm());
		primaryStage.setScene(scene);

		//����currentImageView������
		currentImageView = createImageView(scene.widthProperty());

		//// set up drag & drop file abilities--֧�ֿ���ק
		setupDragAndDrop(scene);

		primaryStage.show();
	}

	private ImageView createImageView(ReadOnlyDoubleProperty doubleProperty) {
		ImageView imageView = new ImageView();
		//�����ݺ��--set aspect ratio
		imageView.setPreserveRatio(true);
		//���ݳ���������С--resize based on the scene
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
		// Dragging over surface�Ϲ�����
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
	 * ���URL���ļ���չ��ƥ��JPG��JPEG��PNG��GIF���򷵻�true��
	 * @ PARAM URL��׼ӳ���ļ�URL·����
	 * ���URL����չ��JPG��JPEG��PNG��GIFƥ�䣬�򷵻ز�������true��
	 * @return
	 */
	private boolean isValidImageFile(String url) {
		List<String> imgTypes = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp");
		if(imgTypes.contains(url))
			return imgTypes.stream().anyMatch(predicate -> url.endsWith(predicate));  //�Ƿ�������ĸ�ʽ��β
		return false;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
