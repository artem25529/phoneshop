<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<tags:page pageTitle="Quick order">
    <div class="container">
        <div class="row justify-content-center">
            <form:form method="post" action="${pageContext.request.contextPath}/quickOrderEntity"
                       modelAttribute="quickOrderItemDto">
                <table>
                    <thead>
                    <tr>
                        <th>Product model</th>
                        <th>Quantity</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="status" begin="0" end="9">
                        <c:set var="i" value="${status.index}"/>
                        <tr>
                            <td>
                                <form:input path="quickOrderItemList[${i}].model"/>
                                <form:errors path="quickOrderItemList[${i}].model"/>
                            </td>
                            <td>
                                <form:input path="quickOrderItemList[${i}].quantity"/>
                                <form:errors path="quickOrderItemList[${i}].quantity"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button>Add to cart</button>
            </form:form>

        </div>
    </div>

    <script>
        $('input[id$=model]').each(function () {
            let modelId = $(this).attr('id');
            let quantityId = modelId.substring(0, 20).concat('quantity');

            if ($('span[id=' + modelId + '.errors]').length === 0) {
                if ($('span[id=' + quantityId + '.errors]').length === 0) {
                    $(this).val('');
                    $('#' + quantityId).val('');
                }
            }


        })
    </script>

</tags:page>