package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//P15を参考にclass Esptranに対して
//アノテーションを2つつけよう。
@Entity
@Table(name = "esptran")
public class Esptran {

	/***************************
	 * P14,P15を参考に変数を定義し、アノテーションを付与しよう。
	 * 尚、主キーは「jpn」とする。
	 * 変数名はDBのEsptranテーブルを確認しないと分からないので、
	 * workbenchで確認する事。
	 ***************************/

	//ここに変数定義とアノテーション

	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。
	@Column(name = "jpn")
	private String jpn;

	@Column(name = "esp")
	private String esp;
	///////

	/***************************
	 * 2つの変数に対してgetter,setterを作ろう。
	 * Eclipse上部のソース→getter及びsetterの生成で作れるよ。
	 ***************************/

	//ここにgetter,setter
	public String getJpn() {
		return jpn;
	}

	public void setJpn(String jpn) {
		this.jpn = jpn;
	}

	public String getEsp() {
		return esp;
	}

	public void setEsp(String esp) {
		this.esp = esp;
	}

	/////////
}