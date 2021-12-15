<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<tags:page pageTitle="Order">
    <div class="container">
        <div class="row">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Color</th>
                    <th>Display size</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderitem" items="${order.orderItems}" varStatus="varStatus">
                    <c:set var="phone" value="${orderitem.phone}"/>
                    <tr>
                        <td>${phone.brand}</td>
                        <td>${phone.model}</td>
                        <td>
                            <c:forEach var="color" items="${phone.colors}" varStatus="status">
                                <c:choose>
                                    <c:when test="${empty phone.colors}">
                                        <c:out value="-"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${color.code}"/>
                                        <c:out value="${status.last ? '' : ', '}"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </td>
                        <td>${phone.displaySizeInches}"</td>
                        <td>
                                ${orderitem.quantity} <br/>
                            <form:errors path="order.orderItems[${varStatus.index}].quantity"/>
                        </td>

                        <td>${phone.price}$</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4"></td>
                    <td>Subtotal</td>
                    <td>${order.subtotal}$</td>
                </tr>
                <tr>
                    <td colspan="4"></td>
                    <td>Delivery</td>
                    <td>${order.deliveryPrice}$</td>
                </tr>
                <tr>
                    <td colspan="4"></td>
                    <td>TOTAL</td>
                    <td>${order.totalPrice}$</td>
                </tr>
                </tbody>
            </table>
        </div>
        <hr>
        <br>
        <div class="row">
            <div class="col-4">
                <form:form method="post" action="/phoneshop-web/order" modelAttribute="order">
                    <table>
                        <tr>
                            <td>First name:</td>
                            <td>
                                <form:input path="firstName" class="form-control" placeholder="Enter your name"/>
                                <form:errors path="firstName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Last name:</td>
                            <td>
                                <form:input path="lastName" class="form-control" placeholder="Enter your last name"/>
                                <form:errors path="lastName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Address:</td>
                            <td>
                                <form:input path="deliveryAddress" class="form-control" placeholder="Enter your address"/>
                                <form:errors path="deliveryAddress"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Phone:</td>
                            <td>
                                <form:input path="contactPhoneNo" class="form-control" placeholder="+375(29)6923456"/>
                                <form:errors path="contactPhoneNo"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <textarea placeholder="Additional information"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button class="btn btn-success">Order</button>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>

    </div>

</tags:page>