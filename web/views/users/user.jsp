
<%@include file="/views/template/head.jspf"%>


<%@include file="/views/template/menu.jspf"%>


<c:if test="${USER.perfil != 5}">
    <c:redirect url="/Home"/>
</c:if>
<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">




    <div class="row">
        <div class="col-lg-12">
            <h5 class="titulos hvr-icon-pop"><i class="fas fa-users-cog hvr-icon"></i> Usuarios</h5>
            <button type="button" id="botonAdd" class="btn btn-primary float-right"><i class="fas fa-plus-square"></i> Agregar</button>
        </div>
    </div>
    <hr>

</div>

<div class="container-fluid">
    <div class="row">

      

        <div class="col-sm-12 d-flex justify-content-center">
           
            <table id="examples" class="table table-striped table-bordered table-responsive-sm">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                       <th>Nombre</th>
                                        <th>Apellido</th>
                                        <th>Email</th>
                                        <th>Estado</th>
                                        <th>Perfil</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody id="tabla">
                                    
                               </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                       <th>Nombre</th>
                                        <th>Apellido</th>
                                        <th>Email</th>
                                        <th>Estado</th>
                                        <th>Perfil</th>
                                        <th>Opciones</th>
                                    </tr>
                                </tfoot>
                            </table>                 
                            
            
        </div>

    </div>    

</div>

<%@include file="/views/users/modalEdit.jspf"%>
<%@include file="/views/users/modalUpdate.jspf"%>
<%@include file="/views/users/modalUpdatePassword.jspf"%>



<%@include file="/views/template/footer.jspf"%>

<script src="assets/js/project/util/dates.js" charset="utf-8"></script>
<script src="assets/js/project/util/bootstrapVali.js" charset="utf-8"></script>

<script src="assets/js/project/user/home.js" charset="utf-8"></script>
<script src="assets/js/project/user/pass.js" charset="utf-8"></script>
<script src="assets/js/project/user/crud.js" charset="utf-8"></script>