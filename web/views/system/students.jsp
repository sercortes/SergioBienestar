
<%@include file="/views/template/head.jspf"%>
<%@include file="/views/template/menu.jspf"%>


<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">
    <h3 class="text-center titulos"><i class="fas fa-users"></i> APRENDICES</h3>

    <div class="row">
            <div class="col-sm-3">

         <label for="validationCustom01">Fecha inicial:</label>
            <div class="input-group">
                <input type="date" class="form-control" id="fechaI" placeholder="" onkeydown="return false" onchange="cambiarFecha()" required>
            </div>
           </div>
        
        <div class="col-sm-3">


            <label for="validationTooltip03">Fecha final:</label>
            <input type="date" class="form-control" id="fechaF" onkeydown="return false" onchange="cambiarFecha()" required>


        </div>
  
            <div class="col-sm-2">
                
                <label for="validationTooltip03">Coordinación</label>
                <select name="coor" class="form-control" id="coor" tabindex="4" onchange="selectCoordinacion()" required>
                <option value="No">No</option>
                
          </select>
    
            
        </div>
        
        <div class="col-sm-4">
            <label for="validationCustom01">Palabra clave:</label>
            <div class="input-group">
                <input name="nombre" type="text" class="form-control" id="keyWord" minlength="2" maxlength="20" placeholder="" required>

                <div class="input-group-append">
                    <button class="btn btn-primary" type="button" id="buttonSearch"><i class="fas fa-search"></i> Buscar</button>
                </div>
            </div>
            
        </div>
        
 

    </div>
    <div class="row pb-3 pt-2">

        
       
        
        <div id="filtros" class="col-sm-5">
            
                    
            
        </div>
         <div id="" class="col-sm-3">

        </div>

       

    </div>




    <div class="row">
        <div class="col-sm-12">


            <div id="resultado">
                
            </div>

            <div id="tabla">
            
            
           
               
                                
            </div>
             


        </div>
        
    </div>
    
<%@include file="/views/system/modalAprendiz/aprendicesXprograma.jspf"%>

<%@include file="/views/system/modalAprendiz/aprendicesXficha.jspf"%>

<%@include file="/views/system/modals/actividadesAprendiz.jspf"%>
    
<%@include file="/views/template/footer.jspf"%>


<script src="assets/js/project/util/dates.js" charset="utf-8"></script>
<script src="assets/js/project/aprendices/start.js" charset="utf-8"></script>

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
