package FX_��ͼ;
/**
* @author HLX
* @version ����ʱ�䣺2018��10��5�� ����9:55:39
* @description
*/

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommonData {

	private static ObservableList<FavoriteColor> data;

	public static ObservableList<FavoriteColor> getData() {
		return data == null ? data = FXCollections.observableArrayList() : data;
	}

	public static ArrayList<FavoriteColor> getSerialzableList() {
		ArrayList<FavoriteColor> list = new ArrayList<FavoriteColor>();
		if(data != null) {
			for(FavoriteColor fc : data)
				list.add(fc);
		}
		return list.size() > 0 ? list : null;
	}
}
