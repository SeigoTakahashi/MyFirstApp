package com.example.demo.bean;

public class ApiData {
	String message;
	int quantity;

	public ApiData() {
	}

	//コンストラクタ。
	//この部分がJsonデータの中身になる。
	//例えば↓の例で言うと、
	//{message:あいうえお}みたいなデータになる。
	public ApiData(String message, int quantity) {
		this.message = message;
		this.quantity = quantity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

