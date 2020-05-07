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
    let str = `<table id="examples" class="table table-striped table-bordered table-responsive-sm">
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
        str += `<tr idActividad="${item.idRealActividad}" class="chiquito1 text-justify">
                                                    <td class="elements">${item.nombre_actividad}</td>
                                                    <td class="elements">${item.tipo_actividad}</td>
                                                    <td class="elements">${item.fecha_inicio}</td>
                                                    <td class="elements">${item.fecha_fin}</td>
                                                    <td class="elements">${item.responsable}</td>
                                                    <td>${item.cantidad}</td>
                                                 <td class="text-center">         
                                                      <button class="seeAprendices btn btn-info btn-xs" >
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




