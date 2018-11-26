package FX_��ͼ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jfxtras.styles.jmetro8.JMetro;

/**
* @author HLX
* @version ����ʱ�䣺2018��10��5�� ����10:24:48
* @description
*/
public class MyRandomColorPicker extends Application {

	TilePane tile = new TilePane();
	FlowPane flowPane = new FlowPane();
	ScrollPane scrollPane = new ScrollPane(tile);
	TabPane tabPane = new TabPane();
	BorderPane root = new BorderPane();
	Stage stage;
	Tooltip tooltip = new Tooltip("�Ҽ������ղ���ɫӴ~");
	FXMLLoader loader = new FXMLLoader(getClass().getResource("myFavorite.fxml"));

	Random random = new Random();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		flowPane.setAlignment(Pos.CENTER);
		flowPane.setPadding(new Insets(5));
		layoutTilePane(20);
		layoutRootTop();
		layoutRootRight(20);
		root.setPadding(new Insets(0, 0, 20, 0));
		tile.setPadding(new Insets(10));

		layoutTabPane();
		root.setCenter(tabPane);

		primaryStage.setScene(scene);
		primaryStage.setTitle("�����ɫѡ����");
		primaryStage.show();
		primaryStage.getIcons().add(new Image("colorIcon2.png"));

		/**JMetro����*/
//		new JMetro(JMetro.Style.LIGHT).applyTheme(root);

		stage.setOnCloseRequest( we -> {
//			Alert alert = new Alert(AlertType.WARNING, "�رգ�", ButtonType.OK, ButtonType.OK);
//			if(alert.)
			System.out.println("close app");
			/**
			 * ����ObservableList��SimpleStringProperty��֧�����л��������ղز��ܱ��浽�������ˣ���������
			 */
//			try {
//				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("location.txt")));
//
////				if(CommonData.getData() != null)
//				oos.writeObject(CommonData.getSerialzableList());
//				oos.close();
//			} catch (Exception e) {
//				System.out.println("���л�����");
//				e.printStackTrace();
//			}
		});
	}

	private void layoutTilePane(int nums) {
		tile.getChildren().clear();
		for (int i = 1; i <= nums; i++) {
			HBox hBox = new HBox();
			VBox box = new VBox();
			box.setSpacing(5);
			box.setPadding(new Insets(8));

			int red = random.nextInt(255);  //����������ɫ
			int green = random.nextInt(255);
			int blue = random.nextInt(255);

			Rectangle rectangle = new Rectangle(80, 80, Color.rgb(red, green, blue));

			rectangle.setArcWidth(20); rectangle.setArcHeight(20);
//			rectangle.setStroke(Color.rgb(red%2, green%2, blue%2));
//			rectangle.setStrokeType(StrokeType.OUTSIDE);
//			rectangle.setStrokeWidth(2.0);

			TextField color_tf = new TextField();
			color_tf.setPrefSize(85, 5);
			if((0<=red&&red<=255)&&(0<=green&&green<=255)&&(0<=blue&&blue<=255)) {
				String str1 = new String("#");
				str1 = str1 +Integer.toHexString(red)+Integer.toHexString(green)+Integer.toHexString(blue);
				color_tf.setText(str1.toUpperCase());
			}

			Button copy_btn = new Button("����");
//			copy_btn.setTextFill(Color.rgb(red, green, blue));
			hBox.getChildren().addAll(color_tf, copy_btn);

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
			box.getChildren().addAll(rectangle, hBox);
			tile.getChildren().add(box);

			rectangleMouseEvent(rectangle, red, green, blue, color_tf.getText());
		}
	}//

	private void layoutRootTop() {

		VBox vBox = new VBox();
		MenuBar menuBar = new MenuBar();
		MenuItem item1 = new MenuItem("��ɫϵ");
		item1.setOnAction( ae -> setUserAgentStylesheet(STYLESHEET_CASPIAN));
		SeparatorMenuItem sItem = new SeparatorMenuItem();
		MenuItem item2 = new MenuItem("��ɫϵ");
		item2.setOnAction( ae -> setUserAgentStylesheet(STYLESHEET_MODENA));
		SeparatorMenuItem sItem2 = new SeparatorMenuItem();
		MenuItem item3 = new MenuItem("ʹ��JMetro����Ƥ��(���ȶ�)");
//		item3.setOnAction( me -> new JMetro(JMetro.Style.LIGHT).applyTheme(root) );
		item3.setOnAction( me -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("JMetro���ⲻ�ȶ�");
			alert.setContentText("��ֹͣʹ��");
			alert.showAndWait();
		});
		Menu menu = new Menu("�޸�����", null, item1, sItem, item2, sItem2, item3);

		Menu menuAbout = new Menu(" ���� ", null);
		MenuItem itemA_1 = new MenuItem(" ���� ");
		itemA_1.setOnAction( me -> {
			MyDialogStage dialogStage = new MyDialogStage();
			try {
				dialogStage.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		menuAbout.getItems().add(itemA_1);

		menuBar.getMenus().addAll(menu, menuAbout);
		vBox.getChildren().addAll(menuBar);
		root.setTop(vBox);
	}//

	private void layoutRootRight(int nums) {
		VBox rootRightVbox = new VBox();
		ObservableList<Node> list = rootRightVbox.getChildren();

		Button freshColor_Btn = new Button("ˢ��");
		Button myFavorite = new Button("�ҵ��ղ�");
		ToolBar toolBar = new ToolBar(freshColor_Btn, myFavorite);
		freshColor_Btn.setOnAction( ae -> {
			layoutTilePane(nums);
		});
		list.add(toolBar);

		TextArea textArea = new TextArea("�ղ���");
		textArea.setPrefWidth(50);
		list.add(textArea);

		root.setRight(rootRightVbox);
	}

	/**
	 * ���ο��¼�ע��
	 * @param rectangle
	 */
	private void rectangleMouseEvent(Rectangle rectangle, int r, int g, int b, String colorCode) {
		MenuItem item = new MenuItem("�ղ���ɫ");
		ContextMenu menu = new ContextMenu(item);
		rectangle.setOnMouseClicked( (MouseEvent me) -> {
			if(me.getButton() == MouseButton.SECONDARY) {
				menu.setX(stage.getX()+me.getSceneX()+10);  menu.setY(stage.getY()+me.getSceneY()+10);
				menu.show(stage);
			}
		});
		rectangle.setOnMouseEntered( me -> {
			tooltip.setX(stage.getX()+me.getSceneX()+10);  tooltip.setY(stage.getY()+me.getSceneY()+10);
			tooltip.show(stage);
		});
		rectangle.setOnMouseExited( me -> {
			tooltip.hide();
		});
		item.setOnAction( me -> {
			Rectangle recTemp = new Rectangle(50, 50, Color.rgb(r, g, b));
			recTemp.setArcWidth(30); recTemp.setArcHeight(30);
			recTemp.setStroke(Color.rgb(r%2, g%2, b%2));
			recTemp.setStrokeType(StrokeType.OUTSIDE);
			recTemp.setStrokeWidth(2.0);

			FavoriteColor favoriteColor = new FavoriteColor(new Date().toLocaleString(), recTemp, colorCode, "��");
			CommonData.getData().add(favoriteColor);
			System.out.println("��ǰ�ղأ�" + CommonData.getData().size());
		});
	}//

	private void layoutTabPane() {
		Tab tab1 = new Tab("��ɫѡ��");
		tab1.setContent(scrollPane);
		tab1.setClosable(false);
		Tab tab2 = new Tab("�ҵ��ղ�");
		tab2.setClosable(false);
		AnchorPane anchorPane = new AnchorPane();
		tab2.setContent(anchorPane);
		try {
			BorderPane rootPane = loader.load();
			anchorPane.getChildren().add(rootPane);
			rootPane.prefWidthProperty().bind(anchorPane.widthProperty());
			rootPane.prefHeightProperty().bind(anchorPane.heightProperty());
		} catch (IOException e) {
			e.printStackTrace();
		}

		tabPane.getTabs().addAll(tab1, tab2);
	}
};
