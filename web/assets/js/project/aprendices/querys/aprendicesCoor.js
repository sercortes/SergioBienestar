var fichas
var total
function aprendicesByCoor(id) {

    let programa = document.getElementById('tittleAprendicesXPrograma')
    let fechai = document.getElementById('fechaI').value
    let fechaf = document.getElementById('fechaF').value

    $('#modalPrograma').modal('show')
    programa.innerHTML = " " + id

    let data = {
        programa: id,
        fechaInicial: fechai,
        fechaFinal: fechaf
    }
 
    $.ajax({
        type: "GET",
        url: './ListAprendicesByCoor',
        datatype: 'json',
        data: data
    }).done(function (data) {

        fichas = data

        drawSelectFichas(data)

        drawTable(data)
        
        generateArrayForProgram(data, total)

    })


}

function selectFicha() {
    
    let ficha = document.getElementById('fichas').value
    
    if (ficha == 'No') {
        drawTable(fichas)

    } else {

        let fichasBusqueda = []
        
        for (var item of fichas) {
            if (item.ficha == ficha) {
                fichasBusqueda.push(item)
            }
        }
        drawTable(fichasBusqueda)

    }
}

function drawTable(data) {

    let num = 0

    let select = document.getElementById('tablaPrograma');
    let str = ` `

    for (var item of data) {

        str += `<tr id="row" class="chiquito">
                                                    <td>${item.ficha}</td>
                                                    <td>${item.coordinacion}</td>
                                                    <td>${item.participaciones}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-sm" role="button" onclick = "fichasTotal(${item.ficha})" >
                                                            <i class="fas fa-users"></i>
                                                        </button>           
                                                </td>
                                                </tr> `
        num += parseInt(item.participaciones)


    }
    console.log(num)
    total = num
    
    
    select.innerHTML = str;
}

$('#generateInforme').click(function(){
    let titulo = document.getElementById('tittleAprendicesXPrograma').textContent
    generatePDF('#informePrograma', titulo, true)
})

function drawSelectFichas(data) {
    
    let fichaSelect = document.getElementById('fichas')
    let option = '<option value="No">No</option>'
    for (var item of data) {
        option += `<option value="${item.ficha}">${item.ficha}</option>`
    }
    fichaSelect.innerHTML = option;
    
}

function generateArrayForProgram(data, total){
     let arreglo = []
    for(var item of data){
        var ob = {
            label:item.ficha,
            y:((item.participaciones * 100) / total).toFixed(2)
        }
        arreglo.push(ob)
    }
    
   setTimeout(() => {
       graphicByProgram(arreglo)
   }, 1800) 
   
}


