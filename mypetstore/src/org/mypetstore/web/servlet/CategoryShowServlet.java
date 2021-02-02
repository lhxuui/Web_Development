package org.mypetstore.web.servlet;

import org.mypetstore.domain.Product;
import org.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/CategoryShowServlet")
public class CategoryShowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        System.out.println(categoryId);
        CatalogService catalogService = new CatalogService();
        List<Product> productList = catalogService.getProductListByCategory(categoryId);

        String respXML = "Product ID      Name\n";
        int i = 0;
        while(i < productList.size()){
            Product product = productList.get(i);
            respXML += product.getProductId() + "      " + product.getName() + "\n";
            i++;
        }

        resp.setContentType("text/xml");
        PrintWriter out = resp.getWriter();
        out.write(respXML);
        out.flush();
        out.close();
    }


}
