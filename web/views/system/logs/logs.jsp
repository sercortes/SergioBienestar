
<%@include file="/views/template/head.jspf"%>


<%@include file="/views/template/menu.jspf"%>


<c:if test="${USER.perfil != 5}">
    <c:redirect url="/Home"/>
</c:if>
<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">




    <div class="row">
        <div class="col-lg-12">
            <h5 class="titulos hvr-icon-pop"><i class="fas fa-server hvr-icon"></i> Eventos sistema</h5>
        </div>
    </div>
    <hr>

</div>

<div class="container-fluid">
    <div class="row">

      

        <div class="col-sm-12 d-flex justify-content-center">
           
            <table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                       <th>Usuario</th>
                                        <th># Registros Agregados</th>
                                        <th>Fecha</th>
                                    </tr>
                                </thead>
                                <tbody id="tabla">
                                    
                               </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                        <th>Usuario</th>
                                        <th># Registros Agregados</th>
                                        <th>Fecha</th>
                                    </tr>
                                </tfoot>
                            </table>                 
                            
            
        </div>

    </div>    

</div>


<%@include file="/views/template/footer.jspf"%>

<script src="assets/js/project/util/dates.js" charset="utf-8"></script>

<script src="assets/js/project/log/home.js" charset="utf-8"></script>
<script src="assets/js/project/log/crud.js" charset="utf-8"></script>