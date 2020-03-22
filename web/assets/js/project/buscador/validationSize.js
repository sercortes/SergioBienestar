function validationSize(input) {

    console.log(input)
    let mensage = 'sin resultados para tu búsqueda:"' + input.palabra + '"'
    messageInfo(mensage)

    let select = document.getElementById('tabla');
    select.innerHTML = `
<div class="alert alert-info alert-dismissible fade show" role="alert">
  Sin resultados! : <strong>${input.palabra}</strong>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>`

}



function validationSizeSelect(input) {

    console.log(input)
    let mensage = 'sin resultados para tu búsqueda:"' + input + '"'
    messageInfo(mensage)

    let select = document.getElementById('tabla');
    select.innerHTML = `
<div class="alert alert-info alert-dismissible fade show" role="alert">
  Sin resultados! : <strong>${input}</strong>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>`


}



