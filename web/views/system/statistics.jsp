
<%@include file="/views/template/head.jspf"%>


<%@include file="/views/template/menu.jspf"%>
<!-- Topbar -->

<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">




    <div class="row">
        <div class="col-lg-12">
            <h1 class="titulos text-center"><i class="fas fa-chart-pie"></i> ESTADÍSTICAS</h1>
        </div>
    </div>
    <hr>

</div>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-3">
            
             <label for="validationTooltip03">Año Inicial</label>
            <select class="form-control" id="yearStart" tabindex="4" onchange="" required>
  
          </select>
             
        </div>
        
          <div class="col-sm-3">
              
             <label for="validationTooltip03">Año Final</label>
            <select class="form-control" id="yearFinish" tabindex="4" onchange="" required>
          
          </select>
             
        </div>
        
        <div class="col-sm-3" id="">
             <label for="validationTooltip03">Tipo Actividad</label>
            <select name="actividadTipos" class="form-control" id="TypeActivity" tabindex="4" required>
          <option value="No">No</option>
          </select>
        </div>

        <div class="col-sm-3">
      
         <button class="btn btn-primary btn-lg hvr-pulse-grow mt-4" type="button" id="buttonGenerateG"><i class="fas fa-file-invoice"></i> Generar</button>

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

<script src="assets/js/project/statistics/start.js" charset="utf-8"></script>
<script src="assets/js/project/statistics/typesEveryYear.js" charset="utf-8"></script>

<script src="assets/js/project/util/dates.js" charset="utf-8"></script>

 <script src="assets/js/pdf/jszip.min.js"></script>
<script src="assets/js/pdf/kendo.all.min.js"></script>
<script src="assets/js/canvas/canvasjs.min.js" charset="utf-8"></script>