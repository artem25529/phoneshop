<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<tags:page pageTitle="Admin page">
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Order number</th>
                <th>Customer</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Date</th>
                <th>Total price</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>
                        <a href="<c:url value="/orderOverview/${order.secureId}"/>">
                                ${order.id}
                        </a>
                    </td>
                    <td>${order.firstName} ${order.lastName}</td>
                    <td>${order.contactPhoneNo}</td>
                    <td>${order.deliveryAddress}</td>
                    <td>${order.date}</td>
                    <td>${order.totalPrice}</td>
                    <td>
                        <c:set var="status" value="${order.status}"/>
                        <select id="select-status-${order.id}">
                            <option value="NEW">New</option>
                            <option value="DELIVERED">Delivered</option>
                            <option value="REJECTED">Rejected</option>
                        </select>
                        <script>
                            let status = '${status}';
                            console.log(status);
                            $('option[value=' + status + '').attr('selected', 'true');
                        </script>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="<c:url value="/resources/js/changeStatus.js"/>">
    </script>
</tags:page>