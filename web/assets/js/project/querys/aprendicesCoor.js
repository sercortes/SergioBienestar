var fichas

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

    graphicByProgram(data)


    $.ajax({
        type: "GET",
        url: './ListAprendicesByCoor',
        datatype: 'json',
        data: data
    }).done(function (data) {

        fichas = data

        drawSelectFichas(data)

        drawTable(data)

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
    let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Ficha</th>
                                        <th>Coordinación</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

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

    str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Programa</th>
                                    </tr>
                                </tfoot>
                            </table>`

    select.innerHTML = str;
}

function drawSelectFichas(data) {
    
    let fichaSelect = document.getElementById('fichas')
    let option = '<option value="No">No</option>'
    for (var item of data) {
        option += `<option value="${item.ficha}">${item.ficha}</option>`
    }
    fichaSelect.innerHTML = option;
    
}