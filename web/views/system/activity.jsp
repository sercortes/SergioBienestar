
<%@include file="/views/template/head.jspf"%>
<%@include file="/views/template/menu.jspf"%>


<%@include file="/views/system/modalActivitys/buscador.jspf"%>


<%@include file="/views/system/modalActivitys/aprendices.jspf"%>
<%@include file="/views/system/modals/actividadesAprendiz.jspf"%>



</div>

<div id="pager" class="col-sm-12">
    <nav aria-label="Page navigation example">
    <ul id="pagination" class="pagination"></ul>
    </nav>

    </div>


<%@include file="/views/template/footer.jspf"%>

<script src="assets/js/pagination/pager.js" charset="utf-8"></script>


<script src="assets/js/project/activitys/start.js" charset="utf-8"></script>
<script src="assets/js/project/activitys/buscador/buscador.js" charset="utf-8"></script>
<script src="assets/js/project/activitys/canva/canvaActivity.js" charset="utf-8"></script>


<script src="assets/js/project/util/dates.js" charset="utf-8"></script>

<script src="assets/js/project/activitys/querys/aprendices.js" charset="utf-8"></script>
<script src="assets/js/project/activitys/querys/tableAprendices.js" charset="utf-8"></script>

<!-- consulta que comparten las dos vistas-->
<script src="assets/js/project/querys/ActivitysByAprendiz.js" charset="utf-8"></script>
<script src="assets/js/project/canva/GraphicByAprendiz.js" charset="utf-8"></script>

 <script src="assets/js/pdf/jszip.min.js"></script>
<script src="assets/js/pdf/kendo.all.min.js"></script>
<script src="assets/js/canvas/canvasjs.min.js" charset="utf-8"></script>
