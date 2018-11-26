package FX_绘图;
/**
* @author HLX
* @version 创建时间：2018年10月5日 下午9:08:43
* @description
*/

import java.io.Serializable;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.shape.Rectangle;

public class FavoriteColor implements Serializable {
	private static final long serialVersionUID = -6059604960383302870L;

	//	private SimpleIntegerProperty id;
	private SimpleStringProperty date;
	private SimpleObjectProperty<Rectangle> node;
	private SimpleStringProperty colorCode;
	private SimpleStringProperty colorTag;

	public FavoriteColor() {
		this(null, null, null, null);
	}
	public FavoriteColor(String date, Rectangle node, String code, String tag) {
		this.date = new SimpleStringProperty(date);
		this.node = new SimpleObjectProperty<Rectangle>(node);
		this.colorCode = new SimpleStringProperty(code);
		this.colorTag = new SimpleStringProperty(tag);
	}

//	public int getId() {
//		return id.get();
//	}
//	public void setId(int id) {
//		this.id.set(id);
//	}
//	public SimpleIntegerProperty idProperty() {
//		return id == null ? id = new SimpleIntegerProperty(0) : id;
//	}
	//////////////////
	public String getDate() {
		return date.get();
	}
	public void setDate(String date) {
		this.date.set(date);;
	}
	public SimpleStringProperty dateProperty() {
		return date == null ? date = new SimpleStringProperty("未知时间") : date;
	}
	////////////////
	public Rectangle getNode() {
		return node.get();
	}
	public void setNode(Rectangle node) {
		this.node.set(node);
	}
	public SimpleObjectProperty<Rectangle> nodeProperty() {
		return node;
	}
	/////////////////
	public String getColorCode() {
		return colorCode.get();
	}
	public void setColorCode(String colorCode) {
		this.colorCode.set(colorCode);;
	}
	public SimpleStringProperty colorCodeProperty() {
		return colorCode;
	}
	////////////////
	public String getColorTag() {
		return colorTag.get();
	}
	public void setColorTag(String colorTag) {
		this.colorTag.set(colorTag);;
	}
	public SimpleStringProperty colorTagProperty() {
		return colorTag;
	}
}
