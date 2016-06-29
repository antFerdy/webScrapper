package com.example.guestbook;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.net.*;
import java.io.*;



public final class Scrapper {
    static WebClient client;


    public Document getDom(String site) {
        if(site == null || site.equalsIgnoreCase("")) {
            site = "http://www.akorda.kz/";
        }

        String html = openBrowser(site);
        Document doc = Jsoup.parse(html);
        return doc;
    }

    public static String openBrowser(String url) {
        client = new WebClient(BrowserVersion.FIREFOX_45);
        client.getOptions().setThrowExceptionOnScriptError(false);
        String resp = "";
        try {
            Page page = client.getPage(url);
            resp = page.getWebResponse().getContentAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    public static String initializeURL(String url) {
        String output = "";
        try {
            URL urlObj = new URL(url);
            URLConnection conn = urlObj.openConnection();

            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while((line = reader.readLine()) != null) {
                output += line;
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }

        return output;
    }


}
