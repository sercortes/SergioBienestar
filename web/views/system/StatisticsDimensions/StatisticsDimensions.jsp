
<%@include file="/views/template/head.jspf"%>


<%@include file="/views/template/menu.jspf"%>
<!-- Topbar -->

<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">




    <div class="row">
        <div class="col-lg-12">
            <h1 class="titulos text-center"><i class="fas fa-chart-area"></i> INFORME DIMENSIONES</h1>
        </div>
    </div>
    <hr>

</div>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-2">
            
             <label for="validationTooltip03">Fecha Inicial</label>
              <div class="input-group">
                <input type="date" class="form-control" id="fechaI" placeholder="" onkeydown="return false" required>
            </div>
  
        </div>
        
          <div class="col-sm-2">
              
             <label for="validationTooltip03">Fecha Final</label>
             <div class="input-group">
                <input type="date" class="form-control" id="fechaF" placeholder="" onkeydown="return false" required>
            </div>
             
        </div>
        
        <div class="col-sm-2" id="">
             <label for="validationTooltip03">Dimensión</label>
            <select name="actividadTipos" class="form-control" id="TypeActivity" tabindex="4" required>
          <option value="No">No</option>
          </select>
        </div>
        
        <div class="col-sm-2" id="">
                
            <button class="btn btn-primary btn-lg hvr-pulse-grow mt-4" type="button" id="buttonGenerateG"><i class="fas fa-file-invoice"></i> Generar</button>
             
        </div>

        <div class="col-sm-2">
      

    </div>    

   


</div>
    
    <div id="informe">
        

    
    <h4 class="titulos hvr-icon-pop mt-4" id="tituloInforme"></h4>
    <hr>
     <div class="row">
        
         <div class="col-sm-12" id="outputOne">
            
             
            
        </div>
        
    </div>
  
    <div class="row page-break">
        
         <div class="col-sm-12" id="outPutTwo">
            
            
        </div>
        
    </div>
    
        </div>
    
    <div id="buttons" class="m-3 float-right">
        
    </div>

</div>


<%@include file="/views/template/footer.jspf"%>

<script src="assets/js/project/dimensions/start.js" charset="utf-8"></script>
<script src="assets/js/project/dimensions/typesEveryYearProgram.js" charset="utf-8"></script>

<script src="assets/js/project/util/dates.js" charset="utf-8"></script>

 <script src="assets/js/pdf/jszip.min.js"></script>
<script src="assets/js/pdf/kendo.all.min.js"></script>
<script src="assets/js/canvas/canvasjs.min.js" charset="utf-8"></script>