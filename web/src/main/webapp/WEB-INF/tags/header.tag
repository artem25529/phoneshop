<header>
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/productList">
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
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/productList">Catalog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">SomePage</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Somepage</a>
                        </li>
                    </ul>
                </div>
                <div class="d-flex justify-content-between align-items-end">
                    <div class="d-flex flex-column align-items-end">
                        <a href="#" class="mb-3">Login</a>
                        <a href="#" class="btn btn-outline-primary">
                            My cart:
                            <span id="cart-items-quantity"></span>
                            <span id="total-price"></span>
                        </a>
                    </div>
                </div>
            </div>
        </nav>
        <hr>
    </div>
</header>