<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand" href="<c:url value="/productList"/>">
                    Phonify
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-phone-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M3 2a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V2zm6 11a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                    </svg>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="<c:url value="/productList"/>">Catalog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/cart"/>">Cart</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/order"/> ">Order</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/admin/orders"/> ">Admin</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/quickOrderEntity"/> ">Quick Order</a>
                        </li>
                    </ul>
                </div>
                <div class="d-flex justify-content-between align-items-end">
                    <div class="d-flex flex-column align-items-end">
                        <c:if test="${not empty pageContext.request.remoteUser}">
                            <b>${pageContext.request.remoteUser}</b>

                           <form class="form-inline" action="<c:url value="/appLogout"/>" method="post">
                                <input type="submit" value="Logout" />
                            </form>
                        </c:if>


                    </div>
                    <div class="d-flex flex-column align-items-end">
                        <a href="<c:url value="/login"/>" class="mb-3">Login</a>
                        <a href="<c:url value="/cart"/>" class="btn btn-outline-primary">
                            My cart:
                            <span id="cart-items-quantity"></span>
                            <span id="total-price"></span>
                        </a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>