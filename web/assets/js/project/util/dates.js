function generatePDF(div, nameFile, horizontal){
 
    kendo.drawing
    .drawDOM(div, 
    { 
        landscape:horizontal,
        forcePageBreak: ".page-break" ,
    paperSize: "A4",
       margin: { 
            top: "1cm"},
//            bottom: "0,5mm" },
        scale: 0.9,
        height: 500,
        with:400
//         keepTogether: ".prevent-split"
    })
        .then(function(group){
        kendo.drawing.pdf.saveAs(group, nameFile)
    });
  
}

function messageOk(message){
     Swal.fire({
            icon: 'success',
            title: message,
            showConfirmButton: true
        })
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

function showAnimation() {

    let animacion = `<div class="col-sm-12 d-flex justify-content-center">
                        <div class="loader" id="loader">
                        <img src="assets/img/cat3.gif" class="img-fluid" alt="Responsive image"> 
                       </div>
                    </div>`
    let select = document.getElementById('resultado');
    select.innerHTML = animacion
}

function typesActivitys() {

    let filtros2 = document.getElementById('TypeActivity')

    let str = ``

    $.ajax({
        type: "GET",
        url: './getTypesActivitys',
        datatype: 'json'
    }).done(function (data) {


        for (var item of data) {
            str += `<option value="${item.tipo_actividad}">${item.tipo_actividad}</option>`
        }

        filtros2.innerHTML += str;
        
    })

}

function menu(id){
    let menuItem = document.getElementById(id)
    menuItem.classList.add('active')
}

function convertToCSV(objArray) {
 const array = typeof objArray != "object" ? JSON.parse(objArray) : objArray;
 let str = "";

for (let i = 0; i < array.length; i++) {
  let line = "";
  for (let index in array[i]) {
   if (line != "") line += ";";

line += array[i][index];
  }

str += line + "\r\n";
 }

return str;
}

function exportCSVFile(headers, items, fileName) {
    
 if (headers) {
  items.unshift(headers);
 }

const jsonObject = JSON.stringify(items);

const csv = convertToCSV(jsonObject);

const exportName = fileName + ".csv" || "export.csv";



const blob = new Blob([new Uint8Array([0xEF, 0xBB, 0xBF]), // UTF-8 BOM
                    "Text",csv], { type: "text/plain;charset=utf-8" });
 if (navigator.msSaveBlob) {
  navigator.msSaveBlob(blob, exportName);
 } else {
  const link = document.createElement("a");
  if (link.download !== undefined) {
   const url = URL.createObjectURL(blob);
   link.setAttribute("href", url);
   link.setAttribute("download", exportName);
   link.style.visibility = "hidden";
   document.body.appendChild(link);
   link.click();
   document.body.removeChild(link);
  }
 }
}