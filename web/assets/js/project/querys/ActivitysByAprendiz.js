
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

        let select = document.getElementById('tabla2');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="bg-primary">
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
                                    <tr class="bg-primary">
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
    })

    getByTypes(data)
    
}

$('#pdf').click(function(){

    generatePDF('#contenidoModal', 'informeAprendiz')
  
  
})

function generatePDF(div, nameFile){
    kendo.drawing
    .drawDOM(div, 
    { 
        paperSize: "A4",
//       margin: { 
//            top: "1cm", 
//            bottom: "1cm" },
        scale: 0.8,
        height: 500
    })
        .then(function(group){
        kendo.drawing.pdf.saveAs(group, nameFile)
    });
  
}

function generateInformationAprendiz(data){
    
        $('#modalActividadesAprendiz').text(data[0].nombre_aprendiz)
    
        $('#pro').text('PROGRAMA : '+data[0].nivelFormacion+' EN '+data[0].nombrePrograma)
        $('#doc').text('DOCUMENTO : '+data[0].tipo_documento+' '+data[0].documento_aprendiz)
        $('#ema').text('EMAIL : '+data[0].email_aprendiz) 
        $('#mun').text('MUNICIPIO : '+data[0].municipio) 
        $('#participaciones').text('# PARTICIPACIONES : '+data.length)
        $('#fec').text('EDAD : '+data[0].y) 
        $('#fech').text('FECHA NACIMIENTO : '+data[0].fecha_nacimiento) 
        $('#tipo').text('TIPO DE POBLACIÓN : '+data[0].tipo_poblacion)
        $('#estrato').text('ESTRATO : '+data[0].estrato)
    
}