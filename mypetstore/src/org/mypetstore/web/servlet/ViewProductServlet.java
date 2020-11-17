package org.mypetstore.web.servlet;

import org.mypetstore.domain.Item;
import org.mypetstore.domain.Product;
import org.mypetstore.persistence.CategoryDAO;
import org.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewProductServlet extends HttpServlet {
    private static final String VIEW_PRODUCT="WEB-INF/jsp/catalog/Product.jsp";
    private String productId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productId = req.getParameter("productId");
        CatalogService service = new CatalogService();
        Product product =service.getProduct(productId);
        List<Item> itemList = service.getItemListByProduct(productId);

        HttpSession session = req.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemList);

        req.getRequestDispatcher(VIEW_PRODUCT).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
