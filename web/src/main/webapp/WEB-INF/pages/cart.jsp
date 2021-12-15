<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:page pageTitle="Cart">
    <div class="container">

        <table class="table-striped table-bordered" style="width: 1000px">
            <tr>
                <th>Brand</th>
                <th>Model</th>
                <th>Color</th>
                <th>Display size</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            <form:form method="put" modelAttribute="cartItemListDto" id="update-form">
                <c:forEach var="item" items="${cartItems}" varStatus="status">
                    <c:set var="phone" value="${item.value.phone}"/>
                    <tr id="${phone.id}">
                        <td>${phone.brand}</td>
                        <td><a href="<c:url value="/productDetails/${phone.id}"/>">${phone.model}</a></td>
                        <td>
                            <c:forEach var="color" items="${phone.colors}" varStatus="index">
                                <c:out value="${color.code}"/>
                                <c:if test="${not index.last}">, </c:if>
                            </c:forEach>
                        </td>
                        <td>${phone.displaySizeInches}"</td>
                        <td>$${phone.price}</td>

                        <td>
                            <c:set var="cartItems" value="${cartItemListDto.cartItems}"/>
                            <c:set var="i" value="${status.index}"/>

                            <form:hidden path="cartItems[${i}].id"/>
                            <form:input path="cartItems[${i}].quantity" value="${cartItems[i].quantity}" id="input-${phone.id}"
                                        cssClass="text-right"/>
                            <form:errors path="cartItems[${i}].quantity"
                                        cssClass="error"/>
                        </td>
                        <td><button class="btn btn-outline-primary" id="button-${phone.id}">Delete</button></td>
                    </tr>
                </c:forEach>
            </form:form>
        </table>
        <button form="update-form" id="update" class="btn btn-outline-primary">Update</button>
    </div>
</tags:page>