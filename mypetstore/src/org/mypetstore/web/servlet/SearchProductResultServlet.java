package org.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

@WebServlet("/SearchProductResultServlet")
public class SearchProductResultServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String keyword = req.getParameter("term");
        System.out.println(keyword);
        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        JSONArray jsonArray = new JSONArray();
        //返回结果
        jsonArray.addAll(productList);

        out.write(jsonArray.toString());
        out.flush();
        out.close();
    }
}
