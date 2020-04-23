
<%@include file="/views/template/head.jspf"%>


<%@include file="/views/template/menu.jspf"%>



<c:if test="${USER.perfil != 5 && USER.perfil != 2}">
    <c:redirect url="/Home"/>
</c:if>
<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">




    <div class="row">
        <div class="col-lg-12">
            <h5 class="titulos hvr-icon-pop"><i class="fas fa-laptop-code hvr-icon"></i> Gestión de códigos</h5>
            <button type="button" id="botonAdd" class="btn btn-primary float-right"><i class="fas fa-plus-square"></i> Agregar</button>
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
                                       <th>Nombre</th>
                                        <th>Código</th>
                                        <th>Año</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody id="tabla">
                                    
                               </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                        <th>Nombre</th>
                                        <th>Código</th>
                                        <th>Año</th>
                                        <th>Opciones</th>
                                    </tr>
                                </tfoot>
                            </table>                 
                            
            
        </div>

    </div>    

</div>

<%@include file="/views/responsables/modalEdit.jspf"%>
<%@include file="/views/responsables/modalUpdate.jspf"%>



<%@include file="/views/template/footer.jspf"%>

<script src="assets/js/project/util/bootstrapVali.js" charset="utf-8"></script>
<script src="assets/js/project/util/dates.js" charset="utf-8"></script>
<script src="assets/js/project/resp/home.js" charset="utf-8"></script>
<script src="assets/js/project/resp/crud.js" charset="utf-8"></script>