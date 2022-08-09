<%@ page import="Model.Videogioco.Videogioco" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.regex.Pattern" %>
<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Pagina Consigliati"/>
        <jsp:param name="style" value=""/>
        <jsp:param name="script" value="login"/>
    </jsp:include>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        .bottone {
            width: 100%;
            height: 500px;
            background-color: #FF0000;
            background-repeat: no-repeat;
            background-position: center center;
        }
    </style>
</head>
<body id="main" style="background-color: #141414; color: white; font-family: AlumniSans-Italic">

<%
    ArrayList<Videogioco> listaRisultati = (ArrayList<Videogioco>) request.getAttribute("listaRisultati");
%>

<header>
    <%@include file="../partials/headerCustomer.jsp"%>
</header>

<div class="album py-5" style="background-color: #141414">
    <div class="container" style="background-color: #141414">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" style="background-color: #141414">
            <% for(Videogioco generale: listaRisultati){
                if (generale != null ){
                    if (generale.getTitolo().contains(":")) {
                        String[] parts = generale.getTitolo().split(Pattern.quote(":"));
            %>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="${pageContext.request.contextPath}/img/<%=parts[0]+parts[1]%>/<%=parts[0]+parts[1]%>-1.jpg" class="bd-placeholder-img card-img-top" width="100%" height="225"  role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    </img>
                    <div class="card-body" style="background-color: turquoise">
                        <p class="card-text" style="color: black"><%=generale.getTitolo()%></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <form action="${pageContext.request.contextPath}/VideogiocoServlet" method="post">
                                    <button type="submit" class="btn btn-sm btn-outline-dark" style="border-width: medium" id="dettaglioVideogioco" name="dettaglioVideogioco" value="<%=generale.getTitolo()%>">Info</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/RecensioneServlet" method="get">
                                    <button type="submit" class="btn btn-sm btn-outline-dark" style="border-width: medium" id="dettaglioRecensione" name="dettaglioRecensione" value="<%=generale.getTitolo()%>">Recensione</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%   }else{
            %>
            <div class="col" style="background-color: #141414">
                <div class="card shadow-sm">
                    <%if(generale.getTitolo().contains("videogioco")){%>
                    <img src="${pageContext.request.contextPath}/img/genericaFIA/not.png" class="bd-placeholder-img card-img-top" width="100%" height="225"  role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false" alt="immagineDivertente">
                    <%}else{%>
                    <img src="${pageContext.request.contextPath}/img/<%=generale.getTitolo()%>/<%=generale.getTitolo()%>-1.jpg" class="bd-placeholder-img card-img-top" width="100%" height="225"  role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                    <%}%>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    </img>
                    <div class="card-body" style="background-color: turquoise">
                        <p class="card-text" style="color: black"><%=generale.getTitolo()%></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <form action="${pageContext.request.contextPath}/VideogiocoServlet" method="post">
                                    <button type="submit" class="btn btn-sm btn-outline-dark" style="border-width: medium">Info
                                        <input type="hidden" id="dettaglioVideogioco1" name="dettaglioVideogioco" value="<%=generale.getTitolo()%>">
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/RecensioneServlet" method="get">
                                    <button type="submit" class="btn btn-sm btn-outline-dark" style="border-width: medium">Recensione
                                        <input type="hidden" id="dettaglioRecensione1" name="dettaglioRecensione" value="<%=generale.getTitolo()%>">
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
            <%}%>
            <%}%>
        </div>
    </div>
</div>


<nav class="pagination-wrapper" aria-label="navigazione pagina">
    <ul class="pagination justify-content-center">
        <form action="${pageContext.request.contextPath}/utenteProfile" method="get">
        <li class="page-item d-none d-sm-flex">
            <button class="nav-link" style="background-color: transparent;border: 0px" id="1" name="gestioneUtente" value="1">1</button>
        </li>
        </form>
        <form action="${pageContext.request.contextPath}/utenteProfile" method="get">
        <li class="page-item d-none d-sm-flex">
            <button class="nav-link" style="background-color: transparent;border: 0px" id="2" name="gestioneUtente" value="2">2</button>
        </li>
        </form>
        <form action="${pageContext.request.contextPath}/utenteProfile" method="get">
        <li class="page-item d-none d-sm-flex">
            <button class="nav-link" style="background-color: transparent;border: 0px" id="3" name="gestioneUtente" value="3">3</button>
        </li>
        </form>
    </ul>
</nav>




















<!--
<div id="demo" class="carousel slide" data-bs-ride="carousel">

    <div class="carousel-indicators">
        <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
        <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
        <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
    </div>


    <div class="carousel-inner">
            <%int i = 0;
        for (Videogioco videogioco : listaRisultati ) {
            if (videogioco.getTitolo().contains(":")) {
                String[] parts = videogioco.getTitolo().split(Pattern.quote(":"));
                    if(i==0){%>
        <div class="carousel-item active">
            <%}else{%>
            <div class="carousel-item">
                <%}%>
                <form action="${pageContext.request.contextPath}/VideogiocoServlet" method="post">
                    <button class="bottone" style="background-image: url('./img/<%=parts[0]+parts[1]%>/<%=parts[0]+parts[1]%>-1.jpg');"><input type="hidden" id="dettaglioVideogioco1" name="dettaglioVideogioco" value="<%=videogioco.getTitolo()%>"></button>
                </form>
                <div class="carousel-caption">
                    <h3><%=videogioco.getTitolo()%></h3>
                    <p>Casa produttrice : <%=videogioco.getCasaProduttrice()%></p>
                </div>
            </div>
            <%}else{
                if(i==0){%>
            <div class="carousel-item active">
                <%}else{%>
                <div class="carousel-item">
                    <%}%>
                    <form action="${pageContext.request.contextPath}/VideogiocoServlet" method="post">
                        <%String id = "dettaglioVideogioco2";
                            if(videogioco.getTitolo().contains("videogioco")){
                                String game = "not";
                        %>
                        <button class="bottone" style="background-image: url('./img/genericaFIA/<%=game%>.png');"><input type="hidden" id="<%=id%>" name="dettaglioVideogioco" value="<%=videogioco.getTitolo()%>"></button>
                        <%}else{%>
                        <button class="bottone" style="background-image: url('./img/<%=videogioco.getTitolo()%>/<%=videogioco.getTitolo()%>-1.jpg');"><input type="hidden" id="<%=id%>" name="dettaglioVideogioco" value="<%=videogioco.getTitolo()%>"></button>
                        <%}%>
                    </form>
                    <div class="carousel-caption">
                        <h3><%=videogioco.getTitolo()%></h3>
                        <p>Casa produttrice : <%=videogioco.getCasaProduttrice()%></p>
                    </div>
                </div>
                <%}%>
                <%i++;}%>
                <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </button>
            </div>
        </div>
        <br>
        <br>
-->

<footer>
    <%@include file="../partials/footerCustomer.jsp"%>
</footer>

</body>
</html>
