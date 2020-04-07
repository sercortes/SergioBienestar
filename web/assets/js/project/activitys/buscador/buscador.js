var input = ''
var $pagination = $('#pagination'),
        totalRecords = 0,
        records = [],
        displayRecords = [],
        recPerPage = 10,
        page = 1,
        totalPages = 0,
        initiateStartPageClick = true


function listarActivitysSearch(data) {


    $.ajax({
        type: "GET",
        url: './ListActivitysSearch',
        data: data,
        async: true,
        datatype: 'json'
    }).done(function (data) {

        if (data <= 0) {
            validationSizeSelect(returtDataActivitys().palabra)
            return false;
        }


        validationResult(data.length)

        records = data
        totalRecords = data.length
        totalPages = Math.ceil(totalRecords / recPerPage)

        apply_pagination()
    })


}



function apply_pagination() {

    $pagination.twbsPagination({
        totalPages: totalPages,
        visiblePages: 4,
        onPageClick: function (event, page) {
            displayRecordsIndex = Math.max(page - 1, 0) * recPerPage;
            endRec = (displayRecordsIndex) + recPerPage;

            displayRecords = records.slice(displayRecordsIndex, endRec);
            generateTableBuscador()
        }
    });
}

function generateTableBuscador() {

    let num = 0

    let select = document.getElementById('tabla');
    let str = `<table id="examples" class="table table-striped table-bordered table-responsive">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                        <th>Actividad</th>
                                        <th>Tipo</th>
                                        <th>Fecha Inicio</th>
                                        <th>Fecha Fin</th>
                                        <th>Responsable</th>
                                        <th>Aprendices</th>
                                        <th>Listado Aprendices</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

    for (var item of displayRecords) {
        str += `<tr id="row${item.nombre_actividad}" class="chiquito1 text-justify">
                                                    <td>${item.nombre_actividad}</td>
                                                    <td>${item.tipo_actividad}</td>
                                                    <td>${item.fecha_inicio}</td>
                                                    <td>${item.fecha_fin}</td>
                                                    <td>${item.responsable}</td>
                                                    <td>${item.cantidad}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-xs" role="button" onclick = "aprendices('${item.idRealActividad}')" >
                                                            <i class="fas fa-list-ol"></i> 
                                                        </button>            
                                                </td>
                                                </tr> `
        num += parseInt(item.cantidad)
    }
    console.log(num)

    str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                        <th>Actividad</th>
                                        <th>Tipo</th>
                                        <th>Fecha Inicio</th>
                                        <th>Fecha Fin</th>
                                        <th>Responsable</th>
                                        <th>Aprendices</th>
                                        <th>Listado Aprendices</th>
                                    </tr>
                                </tfoot>
                            </table><div id="graficos">

            </div>`

    select.innerHTML = str;
}

$('#buttonSearch').click(function () {

    input = document.getElementById('keyWord').value

    if (input != '') {
        if (input.length <= 2) {
            messageInfo('Completa la palabra clave')
            return false
        }

    }
    showAnimation()

    $pagination.twbsPagination('destroy');
    listarActivitysSearch(returtDataActivitys())

});

function cambiarFecha() {
    $pagination.twbsPagination('destroy');
    showAnimation()
    listarActivitysSearch(returtDataActivitys())

}

$(document).ready(function () {

    showAnimation()
    listarActivitysSearch(returtDataActivitys())

});

function returtDataActivitys() {

    let fechai = document.getElementById('fechaI').value
    let fechaf = document.getElementById('fechaF').value
    let word = document.getElementById('keyWord').value
    let select = document.getElementById('TypeActivity').value

    if (select == 'No') {
        select = ''
    }

    let data = {
        palabra: word,
        fechaInicial: fechai,
        fechaFinal: fechaf,
        tipo: select
    }

    return data

}



