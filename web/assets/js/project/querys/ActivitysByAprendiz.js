
function getActivitysByAprendiz(aprendiz) {


    let fechai = document.getElementById('fechaI').value
    let fechaf = document.getElementById('fechaF').value

    let data = {
        objeto: aprendiz,
        fechaInicial: fechai,
        fechaFinal: fechaf
    };
    if (!validationDate(data)) {
        return false
    }

    listActivitysByAprendiz(data)

}



function listActivitysByAprendiz(data) {

    $('#modalTwo').modal('show')

    getByTypes(data)

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

    
        $('#modalActividadesAprendiz').text(data[0].nombre_aprendiz)
    
        $('#pro').text(data[0].nombrePrograma)

        let select = document.getElementById('tabla2');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Programa</th>
                                        <th>Coordinación</th>
                                        <th>Detalles</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {

            str += `<tr id="row${item.nombre_actividad}" class="chiquito">
                                                    <td>${item.actividades.nombre_actividad}</td>
                                                    <td>${item.actividades.tipo_actividad}</td>
                                                    <td>${item.actividades.fecha_inicio}</td>
                                                    <td>${item.actividades.fecha_fin}</td>
                                                    <td>${item.actividades.responsable}</td>
                                                    <td>${item.actividades.cantidad}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-sm" role="button" onclick = "detailsAprendiz('${item.documento_aprendiz}')" >
                                                            <i class="fas fa-address-card"></i>
                                                        </button>           
                                                </td>
                                                </tr> `
        }


        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Programa</th>
                                        <th>Coordinación</th>
                                        <th>Detalles</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>`

        select.innerHTML = str;
    })






}


