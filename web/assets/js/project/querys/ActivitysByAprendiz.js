var dataP
function getActivitysByAprendiz(aprendiz) {


    let fechai = document.getElementById('fechaI').value
    let fechaf = document.getElementById('fechaF').value

    let data = {
        objeto: aprendiz,
        fechaInicial: fechai,
        fechaFinal: fechaf
    };
    listActivitysByAprendiz(data)

}

function listActivitysByAprendiz(data) {

    $('#modalTwo').modal('show')

    $.ajax({
        type: "GET",
        url: './ListActivitysByAprendiz',
        datatype: 'json',
        data: {
            documento: data.objeto,
            fechaInicial: data.fechaInicial,
            fechaFinal: data.fechaFinal
        }
    }).done(function (data) {



        generateInformationAprendiz(data)

        generateTableByaprendiz(data)


    })
    dataP = data
}

$('#modalTwo').on('shown.bs.modal', function () {
    getByTypes(dataP)
})

$('#pdf').click(function () {

    generatePDF('#contenidoModal', 'informeAprendiz', true)

})

function generateTableByaprendiz(data) {

    let select = document.getElementById('tabla2');
    let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                       <th>Actividad</th>
                                        <th>Tipo</th>
                                        <th>Fecha Inicio</th>
                                        <th>Fecha Fin</th>
                                        <th>Responsable</th>
                                        <th>Participaciones</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

    for (var item of data) {

        str += `<tr id="row" class="chiquito">
                                                    <td>${item.actividades.nombre_actividad}</td>
                                                    <td>${item.actividades.tipo_actividad}</td>
                                                    <td>${item.actividades.fecha_inicio}</td>
                                                    <td>${item.actividades.fecha_fin}</td>
                                                    <td>${item.actividades.responsable}</td>
                                                    <td>${item.actividades.cantidad}</td>
                                                
                                                </tr> `
    }


    str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                        <th>Actividad</th>
                                        <th>Tipo</th>
                                        <th>Fecha Inicio</th>
                                        <th>Fecha Fin</th>
                                        <th>Responsable</th>
                                        <th>Participaciones</th>
                                    </tr>
                                </tfoot>
                            </table>`

    select.innerHTML = str;
}

function generateInformationAprendiz(data) {

    $('#modalActividadesAprendiz').text(data[0].nombre_aprendiz)

    $('#pro').text('PROGRAMA : ' + data[0].nivelFormacion + ' EN ' + data[0].nombrePrograma)
    $('#doc').text('DOCUMENTO : ' + data[0].tipo_documento + ' ' + data[0].documento_aprendiz)
    $('#ema').text('EMAIL : ' + data[0].email_aprendiz)
    $('#mun').text('MUNICIPIO : ' + data[0].municipio)
    $('#participaciones').text('# PARTICIPACIONES : ' + data.length)
    $('#fec').text('EDAD : ' + data[0].y)
    $('#fech').text('FECHA NACIMIENTO : ' + data[0].fecha_nacimiento)
    $('#tipo').text('TIPO DE POBLACIÓN : ' + data[0].tipo_poblacion)
    $('#estrato').text('ESTRATO : ' + data[0].estrato)
    $('#fich').text('FICHA : ' + data[0].ficha)

}