package com.example.demo.bean;

public class Gacha {
	String name;
	String rarerity;
	String imgUrl;

	public Gacha() {
	}

	public Gacha(String name, String rarerity, String imgUrl) {
		this.name = name;
		this.rarerity = rarerity;
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRarerity() {
		return rarerity;
	}

	public void setRarerity(String rarerity) {
		this.rarerity = rarerity;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}