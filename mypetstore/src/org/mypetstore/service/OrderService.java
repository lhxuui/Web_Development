package org.mypetstore.service;

import org.mypetstore.domain.Item;
import org.mypetstore.domain.LineItem;
import org.mypetstore.domain.Order;
import org.mypetstore.domain.Sequence;
import org.mypetstore.persistence.ItemDAO;
import org.mypetstore.persistence.LineItemDAO;
import org.mypetstore.persistence.OrderDAO;
import org.mypetstore.persistence.SequenceDAO;
import org.mypetstore.persistence.impl.ItemImpl;
import org.mypetstore.persistence.impl.LineItemImpl;
import org.mypetstore.persistence.impl.OrderImpl;
import org.mypetstore.persistence.impl.SequenceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private SequenceDAO sequenceDAO;
    private LineItemDAO lineItemDAO;

    public OrderService(){
        itemDAO = new ItemImpl();
        orderDAO = new OrderImpl();
        sequenceDAO = new SequenceImpl();
        lineItemDAO = new LineItemImpl();
    }

    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            Map<String, Object> param = new HashMap<String, Object>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            itemDAO.updateInventoryQuantity(param);
        }

        orderDAO.insertOrder(order);
        orderDAO.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemDAO.insertLineItem(lineItem);
        }
    }

    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrder(orderId);
        order.setLineItems(lineItemDAO.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemDAO.getItem(lineItem.getItemId());
            ((Item) item).setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDAO.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = sequenceDAO.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceDAO.updateSequence(parameterObject);
        return sequence.getNextId();
    }
}
