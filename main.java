package com.example.mypackage;

import java.io.IOException;
import java.util.ArrayList;
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
            //     Elements price = link.getElementsByClass("_1uv9Cb");
            Elements price = link.getElementsByClass("_1vC4OE _2rQ-NK");
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
            Elements elLink = link.getElementsByTag("a");

            Elements eltitle = link.getElementsByClass("product-title "); //for product title

            Elements elpricebefore = link.getElementsByClass("lfloat product-desc-price strike ");

            Elements elpriceafter = link.getElementsByClass("lfloat product-price");

            Elements discount = link.getElementsByClass("product-discount");



            //product title loop
            for(Element titleOfProduct : eltitle){
                if(titleOfProduct == null){
                    System.out.println("No title");
                }
                else {
                    System.out.println("\nName of the product: " + titleOfProduct.text());
                }
            }

            //product original price loop
            for(Element priceOfProductBefore : elpricebefore){
                System.out.println("Original price : "+ priceOfProductBefore.text());
            }

            //product discounted price loop
            for(Element priceOfProductAfter : elpriceafter){
                System.out.println("Discounted price : " + priceOfProductAfter.text());
            }

            //discount in number loop
            for(Element productdiscount : discount){
                System.out.println("Discount on product : " + productdiscount.text());
            }

            ArrayList<String> linkArray = new ArrayList<String>();
            for (Element elementLink : elLink){
                String MainLink = elementLink.attr("href");
                linkArray.add(MainLink);
            }
            for (int i = 0; i<1; i++){
                System.out.println("Link : " + linkArray.get(i));
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


//            //Flipkart class call
//            Flipkart searchflipkart = new Flipkart();
//            searchflipkart.getDetailsFlipkart(flip);
//
//        System.out.println("=====================================================================================================================================");
//
//        //Amazon class call
//        Amazon searchamazon = new Amazon();
//        searchamazon.getDetailsAmazon(amaz);
//
//        System.out.println("==============================================================================================================================================");
//
        //SnapDeal class call
        com.example.mypackage.Snapdeal searchsnapdeal = new com.example.mypackage.Snapdeal();
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