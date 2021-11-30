<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:page pageTitle="Product List">
  <div class="container mt-5">
    <div class="row">
      <div class="col">
        <h2>Phones</h2>
        <h5><c:out value="Found ${phoneQuantity} phones"/></h5>
      </div>
      <div class="col">
        <form method="get" class="form-inline float-right">
          <input class="form-control mr-2" type="text" name="query" value="${param.query}"/>
          <button class="btn btn-outline-primary">Search</button>
        </form>
      </div>
    </div>
    <c:if test="${not empty phones}">
      <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
          <th id="image-col" scope="col">Image</th>
          <th id="brand-col" scope="col">
            Brand
            <span class="float-right">
              <tags:sortlink sortBy="brand" orderBy="asc"/>
              <tags:sortlink sortBy="brand" orderBy="desc"/>
            </span>
          </th>
          <th id="model-col" scope="col">
            Model
            <span class="float-right">
              <tags:sortlink sortBy="model" orderBy="asc"/>
              <tags:sortlink sortBy="model" orderBy="desc"/>
            </span>
          </th>
          <th id="color-col" scope="col">Color</th>
          <th id="display-size-col" scope="col">
            Display size
            <span class="float-right">
              <tags:sortlink sortBy="displaySizeInches" orderBy="asc"/>
              <tags:sortlink sortBy="displaySizeInches" orderBy="desc"/>
            </span>
          </th>
          <th id="price-col" scope="col">
            Price
            <span class="float-right">
              <tags:sortlink sortBy="price" orderBy="asc"/>
              <tags:sortlink sortBy="price" orderBy="desc"/>
            </span>
          </th>
          <th id="quantity-col" scope="col">Quantity</th>
          <th id="action-col" scope="col">Action</th>
        </tr>
        </thead>
        <c:forEach var="phone" items="${phones}">
          <tr>
            <td>
              <img class="img" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
            </td>
            <td>${phone.brand}</td>
            <td>${phone.model}</td>
            <td>
              <c:forEach var="color" items="${phone.colors}" varStatus="index">
                <c:out value="${color.code}"/>
                <c:if test="${not index.last}">, </c:if>
              </c:forEach>
            </td>
            <td>${phone.displaySizeInches}"</td>
            <td>$ ${phone.price}</td>
            <td>
              <input type="text" id="item-quantity-${phone.id}" value="1"/>
              <div id="item-quantity-error-${phone.id}"></div>
            </td>
            <td>
              <button type="button" id="add-to-cart-${phone.id}" class="btn btn-outline-primary">
                Add to
              </button>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
    <c:if test="${not empty phones}">
      <tags:pagination currentPage="${page}" pagesNumber="${pages}"/>
    </c:if>
  </div>
</tags:page>