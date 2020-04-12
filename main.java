package com.example.mypackage;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

class Amazon {
    public void getDetailsAmazon(String main) throws IOException {
        Document doc = Jsoup.connect(main).get();
        String title = doc.title();
        System.out.println("title is: " + title);
        Elements links = doc.getElementsByClass("a-link-normal a-text-normal");

        for (Element link : links) {
            Elements element = link.getElementsByTag("a");
            for (Element elmain : element){
                System.out.println("\nlink : " + elmain.attr("href"));
                System.out.println("https://www.amazon.in" + elmain.attr("href"));
                System.out.println("Text : " + elmain.text());

            }
        }
    }
}

class Flipkart {
    public void getDetailsFlipkart(String main) throws IOException {
        Document doc = Jsoup.connect(main).get();
        String title = doc.title();
        System.out.println("title is: " + title);
        Elements links = doc.getElementsByClass("bhgxx2 col-12-12");
        for (Element link : links) {
            Elements element = link.getElementsByTag("a");
            Elements price = link.getElementsByClass("_1uv9Cb");
            System.out.println("Price of the item: " + price.text());
            for (Element elmain : element) {
                for (int n = 0; n < 5; n++){
                    System.out.println("\nlink : " + elmain.attr("href"));
                    System.out.println("https://www.flipkart.com" + elmain.attr("href"));
                    System.out.println("Text : " + elmain.text());
                }
            }
        }

    }
}


class Snapdeal {
    public void getDetailsSnapdeal(String main) throws IOException {
        Document doc = Jsoup.connect(main).get();
        String title = doc.title();
        System.out.println("title is: " + title);
        //col-xs-6  favDp product-tuple-listing js-tuple
        Elements links = doc.getElementsByClass("col-xs-6  favDp product-tuple-listing js-tuple ");

        for (Element link : links) {
            Elements element = link.getElementsByTag("a");
            for (Element elmain : element){
                System.out.println("\nlink : " + elmain.attr("href"));
                System.out.println("Text : " + elmain.text());

            }
        }
    }
}


public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("What are you searching for: ");
        Scanner sc = new Scanner(System.in);
        String search = sc.nextLine();

        //Different String for search
        String flip = SearchFlipkart(search);
        String amaz = SearchAmazon(search);
        String snap = SearchSnapdeal(search);


        //Flipkart class call
        Flipkart searchflipkart = new Flipkart();
        searchflipkart.getDetailsFlipkart(flip);

        System.out.println("=====================================================================================================================================");

        //Amazon class call
        Amazon searchamazon = new Amazon();
        searchamazon.getDetailsAmazon(amaz);

        System.out.println("==============================================================================================================================================");

        //SnapDeal class call
        Snapdeal searchsnapdeal = new Snapdeal();
        searchsnapdeal.getDetailsSnapdeal(snap);

    }


    public static String SearchAmazon(String search) {
        return ("https://www.amazon.in/s?k=" + search + "&ref=nb_sb_noss_2");
    }

    public static String SearchFlipkart(String search) {
        return ("https://www.flipkart.com/search?q=" + search + "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off");
    }

    //
//    public static String SearchEbay(String search){
//
//    }
//
    public static String SearchSnapdeal(String search) {
        return ("https://www.snapdeal.com/search?keyword=" + search + "&santizedKeyword=&catId=&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&changeBackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy");
    }
}



