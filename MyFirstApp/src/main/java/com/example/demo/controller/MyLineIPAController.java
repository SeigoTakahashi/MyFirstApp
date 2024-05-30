package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Event;
import com.example.demo.bean.Grades;
import com.example.demo.bean.LineData;
import com.example.demo.service.UserStateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class MyLineIPAController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private UserStateService userStateService;

	String disMondai;
	String disList;
	String disA;
	String disI;
	String disU;
	String disE;
	String disAnswer;
	String disUrl;
	int count;
	boolean Qflg = false;

	String url = "";
	String temp = "";
	List<String> YEAR;
	//ここにチャンネルアクセストークンを貼る！
	String channelAccessToken = "NTp/N5n9ds34z6yxIMljUPa3VERI/ynyqFUNJZR8B9N2PHYnLmn0n+r79bTNzX4f27Nato5paS8xaDwfmNpKX9qs/CbJV1H3ZT/UWLtn1yZROteQzDnmAe+mhYnvr3YoeBIq9BsMSU+i8xENOIEdYAdB04t89/1O/w1cDnyilFU=";

	@PostMapping("/lineIPA")
	@CrossOrigin(origins = "*")
	public void postApidata(@RequestBody LineData webhookData) {
		for (Event event : webhookData.getEvents()) {
			String replyToken = event.getReplyToken();
			String eventType = event.getType();
			String userId = event.getSource().getUserId();
			if(userId == null) {
				userStateService.setUserState(userId, "FE");
			}
			
			String userState = userStateService.getUserState(userId);
			if ("FE".equals(userState)) {
				url = "https://www.fe-siken.com/fekakomon.php";
				temp = "https://www.fe-siken.com/kakomon/";
				YEAR = new ArrayList<String>(Arrays.asList(
						"03_menjo/",
						"02_menjo/",
						"01_aki/",
						"31_haru/",
						"30_aki/",
						"30_haru/",
						"29_aki/",
						"29_haru/",
						"28_aki/",
						"28_haru/",
						"27_aki/",
						"27_haru/",
						"26_aki/",
						"26_haru/",
						"25_aki/",
						"25_haru/",
						"24_aki/",
						"24_haru/",
						"23_aki/",
						"23_toku/",
						"22_aki/",
						"22_haru/",
						"21_aki/",
						"21_haru/",
						"20_aki/",
						"20_haru/",
						"19_aki/",
						"19_haru/",
						"18_aki/",
						"18_haru/",
						"17_aki/",
						"17_haru/",
						"16_aki/",
						"16_haru/",
						"15_aki/",
						"15_haru/",
						"14_aki/",
						"14_haru/",
						"13_aki/",
						"13_haru/"));
			} else if ("AP".equals(userState)) {
				url = "https://www.ap-siken.com/apkakomon.php";
				temp = "https://www.ap-siken.com/kakomon/";
				YEAR = new ArrayList<String>(Arrays.asList(
						"05_haru/",
						"04_aki/",
						"04_haru/",
						"03_aki/",
						"03_haru/",
						"02_aki/",
						"01_aki/",
						"31_haru/",
						"30_aki/",
						"30_haru/",
						"29_aki/",
						"29_haru/",
						"28_aki/",
						"28_haru/",
						"27_aki/",
						"27_haru/",
						"26_aki/",
						"26_haru/",
						"25_aki/",
						"25_haru/",
						"24_aki/",
						"24_haru/",
						"23_aki/",
						"23_toku/",
						"22_aki/",
						"22_haru/",
						"21_aki/",
						"21_haru/"));
			}
			String replyText = "";
			if ("postback".equals(eventType)) {
				String datetime = event.getPostback().getParams().getTime();
				jdbcTemplate.update("UPDATE ipa SET notification_time = ? WHERE userId = ?;", datetime,
						userId);
				pushMessage(userId, "通知設定を行いました");

			} else {
				replyText = event.getMessage().getText();
			}

			int userCount = jdbcTemplate.queryForObject(
					"SELECT COUNT(*) FROM ipa WHERE userId = ?", Integer.class, userId);

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 12);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			// Calendarからjava.sql.Dateに変換
			Date date = new Date(calendar.getTimeInMillis());
			if (userCount == 0) {
				jdbcTemplate.update("INSERT INTO ipa VALUES (?, ?, ?, ?, ?)", userId, 0, 0, date, "FE");
				jdbcTemplate.update("INSERT INTO ipa VALUES (?, ?, ?, ?, ?)", userId, 0, 0, date, "AP");
			}

			String[] messages = { "素晴らしい！", "知識が豊富！", "Amazing！", "Excellent！", "Professional！", "Intelligent！",
					"才能がある！", "天才！？", "神！？" };
			String send = "";
			if (Qflg) {
				if (replyText.equals(disAnswer)) {
					count++;
					if (count == 1) {
						send = "正解です。正解は" + disAnswer + "です。\r\n【解答詳細】\r\n" + disUrl;
					} else {
						if (count % 5 == 0) {
							Random rand = new Random();
							int i = rand.nextInt(messages.length);
							String message = messages[i];
							send = "正解です。正解は" + disAnswer + "です。\r\n" + message + "\r\n" + count
									+ "問連続正解！\r\n【解答詳細】\r\n"
									+ disUrl;
						} else {
							send = "正解です。正解は" + disAnswer + "です。\r\n" + count + "問連続正解！\r\n【解答詳細】\r\n" + disUrl;
						}

					}

					replyMessage(replyToken, send);
					Qflg = false;
					jdbcTemplate.update("UPDATE ipa SET correct = correct + 1 WHERE userId = ? AND category = ?",
							userId, userState);
				} else {
					count = 0;
					send = "不正解です。正解は" + disAnswer + "です\r\n【解答詳細】\r\n" + disUrl;
					replyMessage(replyToken, send);
					Qflg = false;
				}
			} else {

				if ("通知設定を行う".equals(replyText)) {
					sendDatetimePickerAction(replyToken);
				} else if ("過去問道場を開く".equals(replyText)) {
					replyMessage(replyToken, url);
				} else if ("過去問を解く".equals(replyText)) {
					scrapeWebPage();
					if (disList == null) {
						send = "【問題】" + disMondai + "\r\n(ア) " + disA + "\r\n(イ) " + disI + "\r\n(ウ) " + disU
								+ "\r\n(エ) "
								+ disE;
					} else {
						send = "【問題】" + disMondai + "\r\n" + disList;
						disList = null;
					}
					jdbcTemplate.update("UPDATE ipa SET sum = sum + 1 WHERE userId = ? AND category = ?", userId,
							userState);
					replyMessage(replyToken, send);

				} else if ("成績照会".equals(replyText)) {
					Grades grades = new Grades();
					List<Map<String, Object>> resultList = jdbcTemplate
							.queryForList("SELECT * FROM ipa WHERE userId = ? AND category = ?", userId, userState);
					for (Map<String, Object> user : resultList) {
						send += "モード：" + user.get("category").toString() + "\r\n";
						send += "総学習問題数：" + user.get("sum").toString() + "\r\n";
						send += "正解数：" + user.get("correct").toString() + "\r\n";
						if ((int) user.get("sum") == 0) {
							send += "正解率：0%\r\n";
						} else {
							double correct = (int) user.get("correct");
							double sum = (int) user.get("sum");
							double rate = correct / sum * 100;
							send += "正解率：" + String.format("%.1f%%", rate) + "\r\n";
							grades.setRate(String.format("%.1f%%", rate));

						}
						String originStr = user.get("notification_time").toString();
						String newStr = "";
						int count = 0;
						for (int i = 0; i < originStr.length(); i++) {
							if (originStr.charAt(i) == ':') {
								count++;
							}
							if (count == 2) {
								break;
							}
							newStr += originStr.charAt(i);
						}
						send += "通知時間：" + newStr;
						//replyMessage(replyToken, send);

						String mode = userState;
						String sum = user.get("sum").toString();
						String correct = user.get("correct").toString();
						String notification_time = newStr;
						grades.setMode(mode);
						grades.setSum(sum);
						grades.setCorrect(correct);
						grades.setNotification_time(notification_time);
						grades.setUserId(userId);
						flexMessage(grades);

					}

				} else if ("通知設定を行いました".equals(replyText)) {

				} else if ("基本情報".equals(replyText)) {
					userStateService.setUserState(userId, "FE");
					replyButtonsTemplate(replyToken, "FE");
				} else if ("応用情報".equals(replyText)) {
					userStateService.setUserState(userId, "AP");
					replyButtonsTemplate(replyToken, "AP");
				} else {
					userState = userStateService.getUserState(userId);
					replyButtonsTemplate(replyToken, userState);

				}
			}

		}

	}

	@Scheduled(cron = "0 * * * * ?")
	public void sendGoodMorningMessage() {

		// メッセージを送るユーザのLINEユーザIDを指定。
		// 多分DBから取って来る感じになるかな？今回は固定値をぶちこみ。
		// 現在時刻を取得
		LocalTime now = LocalTime.now();

		// DateTimeFormatterを使用して"00:00"の形式にフォーマット
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String time = now.format(formatter);

		List<Map<String, Object>> userList = jdbcTemplate
				.queryForList("SELECT DISTINCT userId FROM ipa WHERE notification_time = ?", time);

		for (Map<String, Object> user : userList) {
			String send = "";
			scrapeWebPage();
			if (disList == null) {
				send = "【問題】" + disMondai + "\r\n(ア) " + disA + "\r\n(イ) " + disI + "\r\n(ウ) " + disU
						+ "\r\n(エ) "
						+ disE;
			} else {
				send = "【問題】" + disMondai + "\r\n" + disList;
				disList = null;
			}
			String userId = (String) user.get("userId");
			jdbcTemplate.update("UPDATE ipa SET sum = sum + 1");
			// メッセージを送信
			pushMessage(userId, send);

		}

	}

	

	// メッセージ送信メソッド
	private void pushMessage(String to, String pushText) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/push";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", pushText);

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("to", to);
		body.put("messages", Collections.singletonList(message));

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	private void flexMessage(Grades grades) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/push";
		//String jsonUrl = "https://7329-116-82-246-181.ngrok-free.app/json/reference.json";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);
		Path path1 = Paths.get("src/main/resources/static/json/reference.json");
        
        ObjectMapper om = new ObjectMapper();
        JsonNode json = null;
		try {
			json = om.readTree(path1.toFile());
			((ObjectNode) json.get("body").get("contents").get(3).get("contents").get(0).get("contents").get(1)).put("text", grades.getMode());
			((ObjectNode) json.get("body").get("contents").get(3).get("contents").get(2).get("contents").get(1)).put("text", grades.getSum());
			((ObjectNode) json.get("body").get("contents").get(3).get("contents").get(3).get("contents").get(1)).put("text", grades.getCorrect());
			((ObjectNode) json.get("body").get("contents").get(3).get("contents").get(4).get("contents").get(1)).put("text", grades.getRate());
			((ObjectNode) json.get("body").get("contents").get(3).get("contents").get(5).get("contents").get(1)).put("text", grades.getNotification_time());
			((ObjectNode) json.get("body").get("contents").get(5).get("contents").get(1)).put("text", grades.getUserId());
			

			
		} catch (IOException e) {
			e.printStackTrace();
		}


		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "flex");
		message.put("altText", "FlexMessage");
		message.put("contents",  json);
		

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("to", grades.getUserId());
		body.put("messages", Collections.singletonList(message));

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	private void sendDatetimePickerAction(String replyToken) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";
		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);
		// 時刻選択アクションの設定
		String label = "通知する時刻を選択してください";
		String data = "action=set_notification";
		String initial = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		String max = "23:59";
		String min = "00:00";
		Map<String, Object> datetimePicker = new HashMap<>();
		datetimePicker.put("type", "datetimepicker");
		datetimePicker.put("label", label);
		datetimePicker.put("data", data);
		datetimePicker.put("mode", "time");
		datetimePicker.put("max", max);
		datetimePicker.put("min", min);
		datetimePicker.put("initial", initial);
		// テンプレートメッセージの設定
		Map<String, Object> template = new HashMap<>();
		template.put("type", "buttons");
		template.put("text", "通知設定");
		template.put("actions", Collections.singletonList(datetimePicker));

		// メッセージの設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "template");
		message.put("altText", "通知設定");
		message.put("template", template);
		// リクエストボディの設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));
		// HTTPリクエストの送信
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, new HttpEntity<>(body, headers),
				String.class);

	}

	private void scrapeWebPage() {

		try {
			// 目標とするURLを指定してJsoupでHTMLを取得する
			//変数「doc」に取得したHTMLがまるっと入る。
			String FE = temp;
			Random rand = new Random();

			int num = rand.nextInt(YEAR.size());
			String PERIOD = YEAR.get(num);
			String URL = FE + PERIOD;

			num = rand.nextInt(80) + 1;
			String NUM = "q" + num + ".html";

			String FULLURL = URL + NUM;

			Document doc = Jsoup.connect(FULLURL).get();
			Element mondai = doc.getElementById("mondai");
			Element answer = doc.getElementById("answerChar");
			Element select_a = doc.getElementById("select_a");
			Element select_i = doc.getElementById("select_i");
			Element select_u = doc.getElementById("select_u");
			Element select_e = doc.getElementById("select_e");
			Element mondaiImg = mondai.selectFirst("img");
			System.out.println(mondai.text());
			disMondai = mondai.text();
			if (mondaiImg != null) {
				String src = mondaiImg.attr("src");
				System.out.println(URL + src);
				disMondai += URL + src;
			}

			Element aImg = null;
			Element iImg = null;
			Element uImg = null;
			Element eImg = null;
			if (select_a == null || select_i == null || select_u == null || select_e == null) {
				Elements selectList = doc.getElementsByClass("selectList");
				for (Element v : selectList) {
					Element img = v.selectFirst("img");
					String src = img.attr("src");
					System.out.println(URL + src);
					disList = URL + src;
					System.out.println("ア イ ウ エ");

				}
			} else {
				aImg = select_a.selectFirst("img");
				iImg = select_i.selectFirst("img");
				uImg = select_u.selectFirst("img");
				eImg = select_e.selectFirst("img");

				if (aImg != null) {
					System.out.println("ア " + select_a.text());
					disA = select_a.text();
					String src = aImg.attr("src");
					System.out.println(URL + src);
					disA += URL + src;
				} else {
					System.out.println("ア " + select_a.text());
					disA = select_a.text();
				}
				if (iImg != null) {
					System.out.println("イ " + select_i.text());
					disI = select_i.text();
					String src = aImg.attr("src");
					System.out.println(URL + src);
					disI += URL + src;
				} else {
					System.out.println("イ " + select_i.text());
					disI = select_i.text();
				}
				if (uImg != null) {
					System.out.println("ウ " + select_u.text());
					disU = select_u.text();
					String src = aImg.attr("src");
					System.out.println(URL + src);
					disU += URL + src;
				} else {
					System.out.println("ウ " + select_u.text());
					disU = select_u.text();
				}
				if (eImg != null) {
					System.out.println("エ " + select_e.text());
					disE = select_e.text();
					String src = aImg.attr("src");
					System.out.println(URL + src);
					disE += URL + src;
				} else {
					System.out.println("エ " + select_e.text());
					disE = select_e.text();
				}
			}

			System.out.println(answer.text());
			disAnswer = answer.text();
			disUrl = FULLURL;
			Qflg = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*******************************************************************:
	 * ここから↓は今は気にしないでOK!
	 *******************************************************************/
	
	//文字を送りたい場合はこのメソッドを呼び出す。
	//呼び出す際、第二引数に送りたい文字列を渡す。
	private void replyMessage(String replyToken, String replyText) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";
		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);
		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", replyText);
		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));
		//System.out.println("test");
		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	private void replyButtonsTemplate(String replyToken, String userState) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";
		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);
		// 1つ目のボタン
		Map<String, Object> button1 = new HashMap<>();
		button1.put("type", "message");
		button1.put("label", "通知設定を行う");
		button1.put("text", "通知設定を行う");
		// 2つ目のボタン
		Map<String, Object> button2 = new HashMap<>();
		button2.put("type", "message");
		button2.put("label", "過去問道場を開く");
		button2.put("text", "過去問道場を開く");
		// 3つ目のボタン
		Map<String, Object> button3 = new HashMap<>();
		button3.put("type", "message");
		button3.put("label", "過去問を解く");
		button3.put("text", "過去問を解く");
		// 4つ目のボタン
		Map<String, Object> button4 = new HashMap<>();
		button4.put("type", "message");
		button4.put("label", "成績照会");
		button4.put("text", "成績照会");

		//画面に出したいボタンをリストに詰める。
		List<Map<String, Object>> buttons = Arrays.asList(button1, button2, button3, button4);
		// テンプレートにボタンを追加
		Map<String, Object> template = new HashMap<>();
		template.put("type", "buttons");
		template.put("text", "要件：" + userState);
		template.put("actions", buttons);
		// メッセージにテンプレートを追加
		Map<String, Object> message = new HashMap<>();
		message.put("type", "template");
		message.put("altText", "ボタンテンプレート");
		message.put("template", template);
		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));
		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}
}