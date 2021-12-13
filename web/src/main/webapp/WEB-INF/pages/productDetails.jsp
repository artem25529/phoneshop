
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="phone" scope="request" type="com.es.core.model.phone.Phone"/>

<tags:page pageTitle="Product details">
    <br/>
<div class="container">
    <div class="row">
        <div class="col-4" style="text-align: center">
            <button class="btn btn-outline-primary" onclick="window.location.href = '<c:url value="/productList"/>'">
            Back to product list
        </button>
            <br/><br/>
            <h2>${phone.model}</h2>
            <img class="img" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
            <p>${phone.description}</p>
            <br/>
            <h3>Price: $${phone.price}</h3>
            <input type="text" id="item-quantity-${phone.id}" value="1" width="100px"/>
            <div id="item-quantity-error-${phone.id}"></div>
            <button type="button" id="add-to-cart-${phone.id}" class="btn btn-outline-primary">
                Add to Cart
            </button>
        </div>
        <div class="col-2"></div>
        <div class="col-4">
            <h3>Display</h3>
            <table class="table-striped table-bordered">
                <tr>
                    <td>Size</td>
                    <td>${phone.displaySizeInches}"</td>
                </tr>
                <tr>
                    <td>Resolution</td>
                    <td>${phone.displayResolution}</td>
                </tr>
                <tr>
                    <td>Technology</td>
                    <td>${phone.displayTechnology}</td>
                </tr>
                <tr>
                    <td>Pixel density</td>
                    <td>${phone.pixelDensity}</td>
                </tr>
            </table><br>

            <h3>Dimensions&weight</h3>
            <table class="table-striped table-bordered">
                <tr>
                    <td>Length</td>
                    <td>${phone.lengthMm}mm</td>
                </tr>
                <tr>
                    <td>Width</td>
                    <td>${phone.widthMm}mm</td>
                </tr>
                <tr>
                    <td>Color</td>
                    <td>
                        <c:forEach var="color" items="${phone.colors}" varStatus="index">
                            <c:out value="${color.code}"/>
                            <c:if test="${not index.last}">, </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>Weight</td>
                    <td>${phone.weightGr}gr</td>
                </tr>
            </table><br>

            <h3>Camera</h3>
            <table class="table-striped table-bordered">
                <tr>
                    <td>Front</td>
                    <td>${phone.frontCameraMegapixels}megapixels</td>
                </tr>
                <tr>
                    <td>Back</td>
                    <td>${phone.backCameraMegapixels}megapixels</td>
                </tr>
            </table><br>

            <h3>Battery</h3>
            <table class="table-striped table-bordered">
                <tr>
                    <td>Talk time</td>
                    <td>${phone.talkTimeHours} hours</td>
                </tr>
                <tr>
                    <td>Stand by time</td>
                    <td>${phone.standByTimeHours} hours</td>
                </tr>
                <tr>
                    <td>Battery capacity</td>
                    <td>${phone.batteryCapacityMah}mAh</td>
                </tr>
            </table><br>

            <h3>Other</h3>
            <table class="table-striped table-bordered">
                <tr>
                    <td>Color</td>
                    <td>
                        <c:forEach var="color" items="${phone.colors}" varStatus="index">
                            <c:out value="${color.code}"/>
                            <c:if test="${not index.last}">, </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>Device type</td>
                    <td>${phone.deviceType}</td>
                </tr>
                <tr>
                    <td>Bluetooth</td>
                    <td>${phone.bluetooth}</td>
                </tr>
            </table><br>
        </div>

    </div>

</div>

</tags:page>