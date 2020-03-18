
<%@include file="/views/template/head.jspf"%>
<%@include file="/views/template/menu.jspf"%>
        <!-- Topbar -->

        <!-- Container Fluid-->
     <div class="container-fluid" id="container-wrapper">
    <h3 class="text-center"><i class="fab fa-angellist"></i> Actividades</h3>
    
   <div class="row">
       <div class="col-sm-12">
                   <button id="filtroPrograma" type="button" class="btn btn-success float-right mb-2 ml-2"><i class="fab fa-searchengin"></i> Buscar</button>
    <button id="borrarBusqueda" type="button" class="btn btn-danger float-right mb-2"><i class="fas fa-window-close"></i> Limpiar</button>
     
        
           </div>
       </div>


     <div class="row">
        <div class="col-sm-4">


              <div id="filtros" class="float-left mb-2">
                        
                    </div>
    
      </div>
         <div class="col-sm-4">


              <div id="filtros1">
                        
                    </div>
    
      </div>
         <div class="col-sm-4" id="filtros2">

         
    
      </div>
    
    </div>
    

    <div class="row">
        <div class="col-sm-12">
            
              
              
              
                 <div id="tabla" class="table-responsive">
                        
                    </div>
            
            
        </div>
    </div>

<!-- Modal -->
<div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Lista de aprendices</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <h6 class="chiquito">Nombre Actividad:</h6>
          <p id="titulo" class="chiquito"></p>
          <h6 id="cantidad" class="chiquito">#Aprendices: </h6>
          <div class="row">
        <div class="col-sm-12">

                 <div id="tabla1" class="table-responsive-xl">
                        
                    </div>
            
            
        </div>
    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fas fa-window-close"></i> Cerrar</button>
        <button type="button" class="btn btn-primary"><i class="fas fa-print"></i> Imprimir</button>
      </div>
    </div>
  </div>
</div>
        

<!-- ModalTwo -->
<div class="modal fade" id="modalTwo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalTwo">Listado de actividades</h5>
         <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <h6 class="chiquito">Nombre aprendiz:</h6>
          <p id="name" class="chiquito"></p>
          <h6 id="program" class="chiquito">Programa: </h6>
          <p id="pro" class="chiquito"></p>
          <div class="row">
        <div class="col-sm-12">

                 <div id="tabla2" class="table-responsive-xl">
                        
                    </div>
            
            
        </div>
    </div>
      </div>
      <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fas fa-window-close"></i> Cerrar</button>
        <button type="button" class="btn btn-primary"><i class="fas fa-print"></i> Imprimir</button>
      </div>
    </div>
  </div>
</div>
 







</div>

      

        

  
        
        <%@include file="/views/template/footer.jspf"%>



  <script src="assets/js/project/select/validationSelect.js" charset="utf-8"></script>
  <script src="assets/js/project/select/botonsFiltro.js" charset="utf-8"></script>
  <script src="assets/js/project/select/listarPrograma.js" charset="utf-8"></script>
  
  <script src="assets/js/project/querys/activitys.js" charset="utf-8"></script>
  <script src="assets/js/project/querys/aprendices.js" charset="utf-8"></script>
  
  <script src="assets/js/project/querys/coordinacion.js" charset="utf-8"></script>
  <script src="assets/js/project/querys/TypesActivitys.js" charset="utf-8"></script>
  <script src="assets/js/project/querys/fichas.js" charset="utf-8"></script>
  <script src="assets/js/project/querys/aprendicesCoor.js" charset="utf-8"></script>
  
  <script src="assets/js/project/querys/ActivitysByAprendiz.js" charset="utf-8"></script>
  