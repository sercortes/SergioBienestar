


function validationSizeSelect(input) {

    let mensage = 'sin resultados para tu búsqueda:"' + input + '"'

    let select = document.getElementById('tabla');
    select.innerHTML = `
<div class="alert alert-info alert-dismissible fade show" role="alert">
  Sin resultados! : <strong>${input}</strong>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>`


}



