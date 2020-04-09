
<%@include file="/views/template/head.jspf"%>
<%@include file="/views/template/menu.jspf"%>


<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">
    <h3 class="text-center titulos"><i class="fas fa-users"></i> APRENDICES</h3>

   <%@include file="/views/system/modalAprendiz/buscador.jspf"%>
    
<%@include file="/views/system/modalAprendiz/aprendicesXprograma.jspf"%>

<%@include file="/views/system/modalAprendiz/aprendicesXficha.jspf"%>

<%@include file="/views/system/modals/actividadesAprendiz.jspf"%>
 

    <div id="pager" class="col-sm-12">
    <nav aria-label="Page navigation example">
    <ul id="pagination" class="pagination"></ul>
    </nav>

    </div>

<%@include file="/views/template/footer.jspf"%>


<script src="assets/js/pagination/pager.js" charset="utf-8"></script>
<script src="assets/js/project/util/dates.js" charset="utf-8"></script>
<script src="assets/js/project/aprendices/start.js" charset="utf-8"></script>

<script src="assets/js/project/aprendices/buscador/buscador.js" charset="utf-8"></script>
<script src="assets/js/project/aprendices/querys/coordinacion.js" charset="utf-8"></script>
<script src="assets/js/project/aprendices/querys/fichas.js" charset="utf-8"></script>
<script src="assets/js/project/aprendices/querys/aprendicesCoor.js" charset="utf-8"></script>

<script src="assets/js/project/aprendices/canva/GraphicByFicha.js" charset="utf-8"></script>
<script src="assets/js/project/aprendices/canva/GraphicByProgram.js" charset="utf-8"></script>
<script src="assets/js/project/aprendices/canva/GraphicByCoordinacion.js" charset="utf-8"></script>
<script src="assets/js/project/aprendices/canva/GraphicByYearCoor.js" charset="utf-8"></script>

<!-- consulta que comparten las dos vistas-->
<script src="assets/js/project/querys/ActivitysByAprendiz.js" charset="utf-8"></script>
<script src="assets/js/project/canva/GraphicByAprendiz.js" charset="utf-8"></script>

 <script src="assets/js/pdf/jszip.min.js"></script>
<script src="assets/js/pdf/kendo.all.min.js"></script>
<script src="assets/js/canvas/canvasjs.min.js" charset="utf-8"></script>
