
<%@include file="/views/template/head.jspf"%>


<%@include file="/views/template/menu.jspf"%>



<!-- Topbar -->

<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">




    <div class="row">
        <div class="col-lg-12">
            <h1 class="titulos text-center"> <i class="far fa-smile-beam"></i> Bienvenido ${USER.nombre}</h1>
            <h5 class="titulos hvr-icon-pop"><i class="fas fa-laptop-code hvr-icon"></i> Sistema de control y seguimiento de actividades de Bienestar al aprendiz</h5>
        </div>
        <c:if test="${USER.perfil == 5}">
            <div class="col-lg-12" id="alerta">

            </div>
        </c:if>
    </div>
    <hr>

</div>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-6 justify-content-center">

            <a href="./Activitys" class="menu hvr-forward w-100"> 
                <div class="card text-white bg-info mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-calendar-check"></i> Ver actividades</h5>
                        <p class="card-text">Observe y dellate todas la actividades realizadas por bienestar.</p>
                    </div>
                </div>
            </a>

            <a href="./Statistics" class="menu hvr-forward w-100"> 
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-chart-area"></i> Ver estadísticas</h5>
                        <p class="card-text">Visualice las gráficas sobre las dimensiones de bienestar con el fin de tomar las mejores decisiones.</p>
                    </div>
                </div>
            </a>
            <c:if test="${USER.perfil == 5 || USER.perfil == 2}">
                <a href="./Upload" class="menu hvr-forward w-100"> 
                    <div class="card text-white bg-warning mb-3">
                        <div class="card-body">
                            <h5 class="card-title"><i class="fas fa-database"></i> Importe la Base de Datos</h5>
                            <p class="card-text">Restaure la BD para que la información quede actualizada.</p>
                        </div>
                    </div>
                </a>
                 </c:if>

        </div>

        <div class="col-sm-6">

            <a href="./Aprendices" class="menu hvr-forward w-100"> 
                <div class="card text-white bg-secondary mb-3 ">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-users"></i> Ver aprendices</h5>
                        <p class="card-text">Control y seguimiento de los programas de formación.</p>
                    </div>
                </div>
            </a>



            <c:if test="${USER.perfil == 5 || USER.perfil == 2}">
                <a href="./Responsables" class="menu hvr-forward w-100"> 
                    <div class="card text-white morado mb-3">
                        <div class="card-body">
                            <h5 class="card-title"><i class="fas fa-laptop-code"></i> Gestión de códigos</h5>
                            <p class="card-text">Recuerde actualizar los códigos de los responsables con el fin de realizar una migración éxitosa.</p>
                        </div>
                    </div>
                </a>
                </c:if>
                <c:if test="${USER.perfil == 5 }">
                <a href="./Users" class="menu hvr-forward w-100"> 
                    <div class="card text-white usuarios mb-3">
                        <div class="card-body">
                            <h5 class="card-title"><i class="fas fa-users-cog"></i> Gestión de usuarios</h5>
                            <p class="card-text">Administre todos los usuarios del sistema.</p>
                        </div>
                    </div>
                </a>
            </c:if>

            </div>

         
           
    </div>    

</div>


<%@include file="/views/home/modalPrograms.jspf"%>
<%@include file="/views/home/modalEdit.jspf"%>

<%@include file="/views/template/footer.jspf"%>

    <script src="assets/js/project/home/menu.js" charset="utf-8"></script>
<c:if test="${USER.perfil == 5}">
    <script src="assets/js/project/home/home.js" charset="utf-8"></script>
</c:if>

<script src="assets/js/project/util/dates.js" charset="utf-8"></script>