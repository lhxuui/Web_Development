package org.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mypetstore.domain.Cart;
import org.mypetstore.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;


@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
    private  Cart cart;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        cart = (Cart)session.getAttribute("cart");

        Iterator<CartItem> cartItemIterator = cart.getAllCartItems();

        while (cartItemIterator.hasNext()) {
            //产品数量增加
            CartItem cartItem = (CartItem) cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();

            try {
                int quantity = Integer.parseInt((String) req.getParameter("quantity"));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItemIterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("cart", cart);


        Cart cart2 = (Cart)session.getAttribute("cart");
        Iterator<CartItem> cartItemIterator2 = cart2.getAllCartItems();
        String quantityAll = "";
        while (cartItemIterator2.hasNext()) {
            //产品数量增加
            CartItem cartItem2 = cartItemIterator2.next();
            int quantity2 = cartItem2.getQuantity();
            quantityAll += quantity2 + "," + cartItem2.getTotal() + "," + cart2.getSubTotal();
        }

        resp.setContentType("text/xml");
        PrintWriter out = resp.getWriter();
        out.write(quantityAll);
        out.flush();
        out.close();
    }
}
