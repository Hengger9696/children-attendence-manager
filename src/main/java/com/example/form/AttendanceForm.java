//メイン画面の出欠・体調の押印の入力内容をタイムリーフに送るためのDTOクラス

package com.example.form;

import java.util.List;

import com.example.entity.Child;


public class AttendanceForm {
	//リストから送信されるフィールド
	private List<Child> childrenList;
	//リストのアクセサメソッド
	public List<Child> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<Child> childrenList) {
		this.childrenList = childrenList;
	} 
}