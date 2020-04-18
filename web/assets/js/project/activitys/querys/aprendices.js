var dataGr
var activ

$(document).on('click', '.seeAprendices', function(){
    
    let element = $(this)[0].parentElement.parentElement
    let id = $(element).attr('idActividad')
    var eleme = $(this).closest('tr').find('.elements')
    activ = {
        id:id,
        nombre: eleme[0].outerText,
        tipo:eleme[1].outerText,
        fechaI:eleme[2].outerText,
        fechaF:eleme[3].outerText,
        res:eleme[4].outerText
    }
    
    aprendices()
    
})

function aprendices(){
    
    $('#modalThree').modal('show')
   
     $.ajax({
        type: "GET",
        url: './ListAprendices',
        datatype: 'json',
        data:{
            id:activ.id
        }
    }).done(function (data) {
      
        generateTableAprendicess(data)
        resultGraphicOne(activ.id)
       
    })
    

}

function resultGraphicOne(id){
    
    let dataP
    
    $.ajax({
        type:'GET',
        url: 'SelectStatiticsByActivity',
        data:{
            id:id
        },
        dataType: 'JSON'
    }).done(function(data){
        
        
        let dataG = []
        var total = 0
        
        for(var item of data){
            total+=parseInt(item.participaciones)
            var ob = {
                label:item.nombrePrograma,
                y:item.participaciones
            }
            dataG.push(ob)
        }
      
        dataP = []
        
        for(var item of dataG){
            var ob = {
                label:item.label,
                y:((item.y * 100) / total).toFixed(2)
            }
            dataP.push(ob)
        }
        
        dataGr = dataP
      
    })
    
     
}

$('#modalThree').on('shown.bs.modal', function generargrafica(){
      generateGrap(dataGr)
});


function generateGrap(data){
    
        let chart = new CanvasJS.Chart("graphicOne", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "Participación de programas"
            },subtitles:[
                {
                    text:activ.tipo
                }
            ],
            data: [{
                    type: "pie",
                    toolTipContent: "<b>{label}</b>: {y}%",
                    showInLegend: "true",
                    legendText: "{label}",
                    indexLabelFontSize: 16,
                    indexLabel: "{label} - {y}%",
                    dataPoints: 
			data
                    
                }]
        });
        chart.render();
}

$('#generateXls').click(function(){
    
    let cabecera ={
        hola:'Programa',
        dos:'Porcentaje'
    }
    for(let item of dataGr){
        item.y=Math.round(parseInt(item.y))
    }
    
    exportCSVFile(cabecera,dataGr, activ.nombre.toString().substring(0, 60))
    
})


function generateTableAprendicess(data){
      
       $('#titulos').text('NOMBRE ACTIVIDAD : '+activ.nombre)
       $('#dimension').text('DIMENSIÓN : '+activ.tipo.toString().toUpperCase())
       $('#fechaI').text('FECHA INICIO : '+activ.fechaI)
       $('#fechaF').text('FECHA FIN : '+activ.fechaF)
       $('#res').text('RESPONSABLE : '+activ.res)
       
       $('#cantidadAprendices').text("#Aprendices "+data.length)

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
                                                 <td>         
                                                      <button class="btn btn-info btn-sm" role="button" onclick = "getActivitysByAprendiz('${item.id_aprendiz}')" >
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

$('#prints').click(function(){
    generatePDF('#contenido', activ.nombre.toString().substring(0,55), true)
})


function description(){
    $('#titulosD').text('NOMBRE ACTIVIDAD : '+activ.nombre)
}