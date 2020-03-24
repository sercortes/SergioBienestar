


function validationSizeSelect(input) {

    let select = document.getElementById('resultado')
    select.innerHTML = `
<div class="alert alert-danger alert-dismissible fade show" role="alert">
  Sin resultados! : <strong>${input}</strong>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>`
    
    let tabla = document.getElementById('tabla')
    tabla.innerHTML = ''

}

function validationResult(size) {


    let select = document.getElementById('resultado');
    select.innerHTML = `
<div class="alert alert-info alert-dismissible fade show" role="alert">
  <strong>${size}</strong> Resultados
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>`


}



