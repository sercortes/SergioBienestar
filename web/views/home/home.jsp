
<%@include file="/views/template/head.jspf"%>


<%@include file="/views/template/menu.jspf"%>



<!-- Topbar -->

<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">




    <div class="row">
        <div class="col-lg-12">
            <h1 class="titulos text-center"> <i class="far fa-smile-beam"></i> BIENVENIDO</h1>
            <h5 class="titulos hvr-icon-pop"><i class="fas fa-laptop-code hvr-icon"></i> Sistema de control y seguimiento de actividades de Bienestar al aprendiz</h5>
        </div>
        <div class="col-lg-12" id="alerta">
            
        </div>
    </div>
    <hr>

</div>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-6 justify-content-center">

            <a href="./Activitys" class="menu hvr-grow"> 
                <div class="card text-white bg-info mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-calendar-check"></i> Ver actividades</h5>
                        <p class="card-text">Observe y dellate todas la actividades realizadas por bienestar.</p>
                    </div>
                </div>
            </a>

            <a href="./Statistics" class="menu hvr-grow"> 
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-chart-area"></i> Ver estadísticas</h5>
                        <p class="card-text">Visualice las gráficas que permitan tomar las mejores decisiones.</p>
                    </div>
                </div>
            </a>


        </div>

        <div class="col-sm-6">

            <a href="./Upload" class="menu hvr-grow"> 
                <div class="card text-white bg-warning mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-database"></i> Importe la Base de Datos</h5>
                        <p class="card-text">Restaure la BD para que la información quede actualizada.</p>
                    </div>
                </div>
            </a>
            <a href="./Aprendices" class="menu hvr-grow"> 
                <div class="card text-white bg-secondary mb-3 ">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-users"></i> Ver aprendices</h5>
                        <p class="card-text">Busque por medio de ficha o programa de formación.</p>
                    </div>
                </div>
            </a>     

        </div>

        <div class="col-sm-12 d-flex justify-content-center">
           
          
            
        </div>

    </div>    

</div>

<%@include file="/views/home/modalPrograms.jspf"%>
<%@include file="/views/home/modalEdit.jspf"%>



<%@include file="/views/template/footer.jspf"%>

<script src="assets/js/project/home/home.js" charset="utf-8"></script>
<script src="assets/js/project/util/dates.js" charset="utf-8"></script>