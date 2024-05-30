package com.example.demo.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Grades {
	private String mode;
	private String sum;
	private String correct;
	private String rate;
	private String notification_time;
	private String userId;

	// デフォルトコンストラクタ
	public Grades() {
	}

	// getters and setters
}