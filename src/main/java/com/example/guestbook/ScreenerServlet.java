package com.example.guestbook;


import com.google.appengine.api.datastore.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class ScreenerServlet extends HttpServlet {






	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String site = req.getParameter("site");
        Scrapper scrap = new Scrapper();

        Document dom = scrap.getDom(site);

        resp.setCharacterEncoding("UTF-16");
        PrintWriter writer = resp.getWriter();

        compareDomAsText(dom, dom, writer);
	}

    /**
     * Сравнивает DOM сайта со старой версией DOM
     **/
    private void compareDOMs(Document expected, Document actual) {
        List<Node> nodes = actual.childNodes();
        for(Node node : nodes) {

        }
    }
    /**
     * Сравнивает DOM как текст
     **/
    private void compareDomAsText(Document expected, Document actual, PrintWriter writer) {
//        writer.println(expected.toString());
        int compareResult = expected.toString().compareTo(actual.toString());
        if(compareResult == 0) {
            writer.println("There is no difference");
        } else {
            writer.println("There is difference");
        }
    }

    /**
     * Сохраняет DOM сайта в хранилище
     * */
    private void saveDoc() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//        Entity employee = new Entity("Darth");
//
//        employee.setProperty("firstName", "Darth");
//        employee.setProperty("lastName", "Veider");
//        employee.setProperty("hireDate", new Date());
//        employee.setProperty("attendedHrTraining", true);
//
//        Key key = datastore.put(employee);
//
//        PrintWriter writer = resp.getWriter();
//        writer.write("Entity is inserted " + key.getId());
//
//        if(req.getParameter("key") != null) {
//            Query.Filter propertyFilter =
//                    new Query.FilterPredicate("lastName", Query.FilterOperator.EQUAL, "Veider");
//            Query q = new Query("Person").setFilter(propertyFilter);
//        }
    }

}