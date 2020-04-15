
<%@include file="/views/template/head.jspf"%>
<%@include file="/views/template/menu.jspf"%>
<!-- Topbar -->

<c:if test="${USER.perfil != 5}">
    <c:redirect url="/Home"/>
</c:if>

<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">
    <h3 class="text-center titulos"><i class="fas fa-database"></i> IMPORTAR BASE DE DATOS</h3>


    <div class="row">
        <div class="col-lg-12">
            <p>Seleccione el archivo sobre de las actividades realizadas por bienestar de sofia plus</p>
            <ul>
                <li>
                    No funciona con archivos modificados
                </li>
                <li>
                    Debe subir el archivo que contenga todas las categorias
                </li>
                <li>
                    Debe subir el archivo tal cual se descargo de sofia plus
                </li>
            </ul>
            
        </div>
        
    </div>

    <div class="row" action>
        <form  action="Uploading" method="POST" enctype="multipart/form-data" id="formulario" name="formulario">
        <div class="col-sm-12 text-center">
            <div class="input-group">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="fileToUpload" name="fileToUpload" aria-describedby="inputGroupFileAddon04" >
                    <label class="custom-file-label" for="inputGroupFile04" data-browse="Elegir">Seleccione el archivo</label>
                </div>
            </div>
        </div>
    </div>
    <hr class="sidebar-divider">
    <button type="submit" id="send" class="btn btn-primary btn-lg hvr-push"><i class="far fa-file-excel fa-1x"></i> Cargar archivo</button>
</form>
        
</div>
<div class="d-flex justify-content-center">
    <div class="loader" id="loader">
       <img src="assets/img/cat3.gif" class="img-fluid" alt="Responsive image"> 
    </div>
   <div class="loader1" id="loader">
   </div>
</div>


<script src="assets/js/jquery.js"></script>

<%@include file="/views/template/footer.jspf"%>

  <script src="assets/js/project/uploadfile/inputFile.js" charset="utf-8"></script>
  
  <script src="assets/js/project/util/dates.js" charset="utf-8"></script>