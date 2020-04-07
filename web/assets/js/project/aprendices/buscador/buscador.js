

function searchAprendizWord(data) {
    
    $('#coor').val(1)

    $.ajax({
        type: "GET",
        url: './getSearchAprendizWord',
        datatype: 'json',
        data: data
    }).done(function (data) {

        if (data <= 0) {
            validationSizeSelect(returnDataAprendiz().palabra)
            return false
        }

        validationResult(data.length)

        generateTableaprendices(data);
        
    })

}



function generateTableaprendices(data) {

    let sum = 0

    let select = document.getElementById('tabla');
    let str = `<table id="examples" class="table table-striped table-bordered table-responsive">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Programa</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

    for (var item of data) {
        str += `<tr id="" class="chiquito1 text-justify">
                                                    <td>${item.documento_aprendiz}</td>
                                                    <td>${item.nombre_aprendiz}</td>
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.actividades.cantidad}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-xs" role="button" onclick = "getActivitysByAprendiz('${item.id_aprendiz}')" >
                                                            <i class="fas fa-list-ol"></i> 
                                                        </button>            
                                                </td>
                                               
                                                </tr> `
        sum += parseInt(item.actividades.cantidad);
    }
    console.log(sum)
    str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Programa</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>`


    select.innerHTML = str;
}

function returnDataAprendiz() {
    
    let fechai = document.getElementById('fechaI').value
    let fechaf = document.getElementById('fechaF').value
    let word = document.getElementById('keyWord').value
    let select = document.getElementById('coor').value

    if (select == 'No') {
        select = ''
    }

    let data = {
        palabra: word,
        fechaInicial: fechai,
        fechaFinal: fechaf
    }

    return data

}