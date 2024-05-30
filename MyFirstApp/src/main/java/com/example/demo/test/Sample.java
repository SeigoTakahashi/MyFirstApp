package com.example.demo.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Sample {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		scrapeWebPage();

	}

	private static void scrapeWebPage() {
		List<String> YEAR = new ArrayList<String>(Arrays.asList(
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
		try {

			// 目標とするURLを指定してJsoupでHTMLを取得する
			//変数「doc」に取得したHTMLがまるっと入る。
			String FE = "https://www.fe-siken.com/kakomon/";
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
			
			if (mondaiImg != null) {
				String src = mondaiImg.attr("src");
				System.out.println(URL + src);
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
					System.out.println("ア イ ウ エ");

				}
			} else {
				aImg = select_a.selectFirst("img");
				iImg = select_i.selectFirst("img");
				uImg = select_u.selectFirst("img");
				eImg = select_e.selectFirst("img");

				if (aImg != null) {
					System.out.println("ア " + select_a.text());
					String src = aImg.attr("src");
					System.out.println(URL + src);
				} else {
					System.out.println("ア " + select_a.text());
				}
				if (iImg != null) {
					System.out.println("イ " + select_i.text());
					String src = aImg.attr("src");
					System.out.println(URL + src);
				} else {
					System.out.println("イ " + select_i.text());
				}
				if (uImg != null) {
					System.out.println("ウ " + select_u.text());
					String src = aImg.attr("src");
					System.out.println(URL + src);
				} else {
					System.out.println("ウ " + select_u.text());
				}
				if (eImg != null) {
					System.out.println("エ " + select_e.text());
					String src = aImg.attr("src");
					System.out.println(URL + src);
				} else {
					System.out.println("エ " + select_e.text());
				}
			}

			System.out.println(answer.text());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
