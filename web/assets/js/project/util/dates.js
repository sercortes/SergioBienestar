function generatePDF(div, nameFile){
  
    kendo.drawing
    .drawDOM(div, 
    { 
        paperSize: "A4",
       margin: { 
            top: "1cm", 
            bottom: "1cm" },
        scale: 0.8,
        height: 500
    })
        .then(function(group){
        kendo.drawing.pdf.saveAs(group, nameFile)
    });
  
}
function messageInfo(message){
     Swal.fire({
            icon: 'info',
            title: message,
            showConfirmButton: true
        })
}
function messageError(message){
     Swal.fire({
            icon: 'error',
            title: message,
            showConfirmButton: true
        })
}
  
function setDefaultDate(){
    document.getElementById('fechaI').value = new Date().getStartYear();
    document.getElementById('fechaF').value = new Date().toDateInputValue();
}

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

Date.prototype.getStartYear = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    local.setMonth(this.getMonth()-(this.getMonth()))
    local.setDate(this.getDay()-this.getDay()+1)
    return local.toJSON().slice(0,10);
});

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