package FX_绘图;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

/**
* @author HLX
* @version 创建时间：2018年10月5日 下午9:07:27
* @description
*/
public class MyFavoriteController implements Initializable {

	@FXML private BorderPane rootPane;
	@FXML private TableView<FavoriteColor> tab2_TableView;
	@FXML private TableColumn<FavoriteColor, String> column_id;
	@FXML private TableColumn<FavoriteColor, String> column_date;
	@FXML private TableColumn<FavoriteColor, Rectangle> column_show;
	@FXML private TableColumn<FavoriteColor, String> column_code;
	@FXML private TableColumn<FavoriteColor, String> column_tag;
	@FXML private TableColumn<FavoriteColor, String> column_delete;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("MyFavoriteController--------initialize");
		initTableView();
	}

	private void initTableView() {
		tab2_TableView.setItems(CommonData.getData());
		column_id.setCellFactory(new Callback<TableColumn<FavoriteColor,String>,TableCell<FavoriteColor,String>>(){
			@Override
			public TableCell<FavoriteColor, String> call(TableColumn<FavoriteColor, String> param) {
				return new TableCell<FavoriteColor, String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if(getIndex()+1 < CommonData.getData().size())
							setText(String.valueOf(getIndex() + 1));
					}
				};
			}
		});////////////////////
		column_date.setCellValueFactory(new PropertyValueFactory<FavoriteColor, String>("date"));
		column_show.setCellValueFactory(new PropertyValueFactory<FavoriteColor, Rectangle>("node"));
		column_code.setCellValueFactory(new PropertyValueFactory<FavoriteColor, String>("colorCode"));
		column_tag.setCellValueFactory(new PropertyValueFactory<FavoriteColor, String>("colorTag"));
	}

	private void initColumn() {

	}

	private class SetColumnAlignmentCell extends TableCell<FavoriteColor, String> {
		int type = 0;
		public SetColumnAlignmentCell(int type) {
			this.type = type;
			setAlignment(Pos.CENTER);
		}
	}//private class SetColumnAlignmentCell
}
