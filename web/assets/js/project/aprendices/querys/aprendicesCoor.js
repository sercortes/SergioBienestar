var fichas
var fichasTwo
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

    setTimeout(() => {
        graphicByProgramD(data)
    }, 1300)


    $.ajax({
        type: "GET",
        url: './ListAprendicesByCoor',
        datatype: 'json',
        data: data
    }).done(function (data) {

        fichasTwo = data

        fichas = data

        drawSelectFichas(data)

        drawTable(data)

        generateArrayForProgram(data, total)

    })


}

function selectFicha() {

    let ficha = document.getElementById('fichas').value

    if (ficha == 'No') {
        drawTable(fichasTwo)

    } else {

        let fichasBusqueda = []

        for (var item of fichasTwo) {
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
    
    total = num


    select.innerHTML = str;
    organizateArray()

}

function organizateArray() {
    let dataE = []
    for (var item of fichas) {
        var ob = {
            label: 'FICHA ' + item.ficha,
            y: ((item.participaciones * 100) / total).toFixed(2)
        }
        dataE.push(ob)
    }
    let cabecera = {
        columna1: 'ficha',
        columna2: '%'
    }

    for (var item of dataE) {
        item.y = Math.round(parseInt(item.y))
    }
    dataE.unshift(cabecera)
    fichas = dataE
}

$('#generateInforme').click(function () {
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

function generateArrayForProgram(data, total) {
    let arreglo = []
    for (var item of data) {
        var ob = {
            label: item.ficha,
            y: ((item.participaciones * 100) / total).toFixed(2)
        }
        arreglo.push(ob)
    }

    setTimeout(() => {
        graphicByProgram(arreglo)
    }, 1800)

}


$(document).on('click', '#generateXlsPrograma', function () {
    let titulo = document.getElementById('tittleAprendicesXPrograma').textContent


    exportCSVFile('cabecera', fichas, titulo + '-PARTICIPACIONES-FICHAS')

}) 