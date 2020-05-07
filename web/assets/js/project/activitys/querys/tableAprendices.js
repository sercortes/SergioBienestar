
function generateTableAprendicess(data, activ) {

    $('#titulos').text('NOMBRE ACTIVIDAD : ' + activ.nombre)
    $('#dimension').text('DIMENSIÓN : ' + activ.tipo.toString().toUpperCase())
    $('#fechaI').text('FECHA INICIO : ' + activ.fechaI)
    $('#fechaF').text('FECHA FIN : ' + activ.fechaF)
    $('#res').text('RESPONSABLE : ' + activ.res)

    $('#cantidadAprendices').text("#Aprendices " + data.length)

    var num = 0
    let select = document.getElementById('tablaAprendices');
    let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Programa</th>
                                        <th>Coordinación</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

    for (var item of data) {

        str += `<tr id="row" class="chiquito">
                                                    <td>${item.documento_aprendiz}</td>
                                                    <td>${item.nombre_aprendiz}</td>
                                                    <td>${item.ficha}</td>
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.coordinacion}</td>
                                                 <td class="text-center">         
                                                      <button class="btn btn-info btn-sm noexportar" role="button" onclick = "getActivitysByAprendiz('${item.id_aprendiz}')" >
                                                            <i class="fas fa-address-card"></i>
                                                        </button>           
                                                </td>
                                                </tr> `
        num++
    }
    console.log(num)

    str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Programa</th>
                                        <th>Coordinación</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>`

    select.innerHTML = str;
    description()
}
