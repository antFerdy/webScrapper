package com.example.guestbook;


import com.google.appengine.api.datastore.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.google.appengine.api.datastore.Text;


public class ScreenerServlet extends HttpServlet {






	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String site = req.getParameter("site");
        if(site == null || site.equalsIgnoreCase("")) {
            site = "http://www.akorda.kz/";
        }

        Scrapper scrap = new Scrapper();
        String domHtml = scrap.getDom(site);

        resp.setCharacterEncoding("UTF-16");
        PrintWriter writer = resp.getWriter();

//        Text oldDom = getOldDom(site);
//        if(oldDom == null) {
//            writer.println("The site is inserted first time");
//            saveDom(domHtml, site);
//        } else {
//            int compareResult = compareDomAsText(domHtml, oldDom.getValue(), writer);
//            if(compareResult == 0) {
//                writer.println("There is no difference " + compareResult);
//            } else {
//                writer.println("There is difference " + compareResult);
//            }
//        }

        String result = scrap.findElemenByXPath(domHtml, "//*[@id=\"e2c7\"]");

        writer.println(result);
	}



    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String elementHtml = req.getParameter("element");
        String path = req.getParameter("path");

        System.err.println(elementHtml);
        System.err.println(path);

        resp.getWriter().println(elementHtml + " " + path);
    }



    /**
     * получаем DOM по Id
     * */
    private Text getOldDom(String site) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key key = KeyFactory.createKey("Dom", site);
        try {
            Entity entity = datastore.get(key);

            Text doc = (Text) entity.getProperty("domData");
            return doc;
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Сохраняет DOM сайта в хранилище
     * */
    private void saveDom(String domToSave, String site) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Entity dom = new Entity("Dom", site);
        Text txt = new Text(domToSave);
        dom.setProperty("domData", txt);

        datastore.put(dom);
    }

    /**
     * Сравнивает DOM как текст
     **/
    private int compareDomAsText(String expected, String actual, PrintWriter writer) {
        int compareResult = expected.compareTo(actual);
        return compareResult;
    }




}