package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "drinks")
public class Drinks {

	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。
	@Column(name = "drink_id")
	private int drink_id;

	@Column(name = "drink_name")
	private String drink_name;
	
	@Column(name = "drink_price")
	private int drink_price;

	public int getDrink_id() {
		return drink_id;
	}

	public void setDrink_id(int drink_id) {
		this.drink_id = drink_id;
	}

	public String getDrink_name() {
		return drink_name;
	}

	public void setDrink_name(String drink_name) {
		this.drink_name = drink_name;
	}

	public int getDrink_price() {
		return drink_price;
	}

	public void setDrink_price(int drink_price) {
		this.drink_price = drink_price;
	}

	
	
	
}