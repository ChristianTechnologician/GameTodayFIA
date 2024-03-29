<%@ page import="java.util.List" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="Model.Recensione.Recensione" %>
<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Catalogo Recensioni"/>
        <jsp:param name="style" value="bootstrap"/>
        <jsp:param name="script" value=""/>
    </jsp:include>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="background-color: #141414; color: white; font-family: AlumniSans-Italic">
<%
    List<Recensione> recensione = (List<Recensione>) request.getAttribute("recensione");
%>

<header>
    <%@include file="../partials/headerCustomer.jsp"%>
</header>

<div class="album py-5" style="background-color: #141414">
    <div class="container" style="background-color: #141414">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" style="background-color: #141414">
            <% for(Recensione generale: recensione){
                if (generale != null && generale.getData() != null){
                    if (generale.getTitolo().contains(":")) {
                        String[] parts = generale.getTitolo().split(Pattern.quote(":"));
            %>
            <form action="${pageContext.request.contextPath}/RecensioneServlet" method="get" style="background-color: #141414">
                <button style="padding: 0px">
            <div class="col">
                <div class="card shadow-sm">
                    <img src="./img/<%=parts[0]+parts[1]%>/<%=parts[0]+parts[1]%>-1.jpg" class="bd-placeholder-img card-img-top" width="100%" height="225"  role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    </img>
                    <div class="card-body" style="background-color: turquoise">
                        <p class="card-text" style="color: black"><%=generale.getTitolo()%></p>
                        <div class="d-flex justify-content-between align-items-center">
                        </div>
                    </div>
                </div>
            </div>
                    <input type="hidden" id="dettaglioRecensione" name="dettaglioRecensione" value="<%=generale.getTitolo()%>">
                </button>
            </form>
            <%   }else{
            %>
            <form action="${pageContext.request.contextPath}/RecensioneServlet" method="get" style="background-color: #141414">
                <button style="padding: 0px">
                    <div class="col">
                        <div class="card shadow-sm">
                            <%if(generale.getTitolo().contains("videogioco")){%>
                            <img src="./img/genericaFIA/not.png" class="bd-placeholder-img card-img-top" width="100%" height="225"  role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false" alt="immagineDivertente">
                            <%}else{%>
                            <img src="./img/<%=generale.getTitolo()%>/<%=generale.getTitolo()%>-1.jpg" class="bd-placeholder-img card-img-top" width="100%" height="225"  role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <%}%>
                            <rect width="100%" height="100%" fill="#55595c"></rect>
                            </img>
                            <div class="card-body" style="background-color: turquoise">
                                <p class="card-text" style="color: black"><%=generale.getTitolo()%></p>
                                <div class="d-flex justify-content-between align-items-center">
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="dettaglioRecensione1" name="dettaglioRecensione" value="<%=generale.getTitolo()%>">
                </button>
            </form>
            <%}%>
            <%}%>
            <%}%>
        </div>
    </div>
</div>
<footer>
    <%@include file="../partials/footerCustomer.jsp"%>
</footer>
</body>
</html>