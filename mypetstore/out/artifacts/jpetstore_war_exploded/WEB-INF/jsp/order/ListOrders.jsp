<%@ include file="../common/IncludeTop.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h2>My Orders</h2>

<table>
	<tr>
		<th>Order ID</th>
		<th>Date</th>
		<th>Total Price</th>
	</tr>

	<c:forEach var="order" items="${sessionScope.orderList}">
		<tr>
			<td>
				<a href="viewOrder?orderId=${order.orderId}">${order.orderId}</a>
			</td>
			<td>
				<fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
			</td>
			<td>
				<fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
			</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="../common/IncludeBottom.jsp"%>


