
<%@include file="/views/template/head.jspf"%>


<%@include file="/views/template/menu.jspf"%>

<!-- Container Fluid-->
<div class="container-fluid" id="container-wrapper">

    <div class="row">
        <div class="col-lg-12">
            <h5 class="titulos hvr-icon-pop"><i class="fas fa-users-cog hvr-icon"></i> Perfil</h5>
        </div>
    </div>
    <hr>

</div>

<div class="container-fluid">
    <div class="row">

      

        <div class="col-sm-12 d-flex justify-content-center">
           
            
            <form id="" autocomplete="off">
                                <div class="form-group">
                                    <label  class="col-form-label float-left">Email:</label>
                                    <input type="email" class="form-control" id="emailShow" autocomplete="none" disabled>
                                </div>
                    <button type="button" id="botonEditPass" class="btn btn-warning float-right"><i class="fas fa-key"></i> Cambiar contraseña</button>
            </form>
                    
            
        </div>
        
  
    </div>    

</div>


<%@include file="/views/system/profile/modalUpdatePassword.jspf"%>



<%@include file="/views/template/footer.jspf"%>

<script src="assets/js/project/util/dates.js" charset="utf-8"></script>

<script src="assets/js/project/profile/home.js" charset="utf-8"></script>
<script src="assets/js/project/profile/pass.js" charset="utf-8"></script>