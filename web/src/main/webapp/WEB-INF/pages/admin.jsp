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
                        <th>
                            <a href="<c:url value="/orderOverview/${order.secureId}"/>">
                                    ${order.id}
                            </a>
                        </th>
                        <th>${order.firstName} ${order.lastName}</th>
                        <th>${order.contactPhoneNo}</th>
                        <th>${order.deliveryAddress}</th>
                        <th>${order.date}</th>
                        <th>${order.totalPrice}</th>
                        <th>${order.status}</th>
                    </tr>
                </c:forEach>
                </tbody>

        </table>
    </div>

</tags:page>