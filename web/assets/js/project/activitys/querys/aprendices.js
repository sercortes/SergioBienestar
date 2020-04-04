
function aprendices(id){
    
    let idActividad = id
    
    $('#modalThree').modal('show')
    

   
     $.ajax({
        type: "GET",
        url: './ListAprendices',
        datatype: 'json',
        data:{
            id:id
        }
    }).done(function (data) {
      
      let acti = data[0].actividades.nombre_actividad
      
       $('#titulos').text(acti)
       
      let cantidad = document.getElementById('cantidadAprendices')
      cantidad.innerHTML = "#Aprendices "+data.length

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

        resultGraphicOne(idActividad)
       
    })
    

}

function resultGraphicOne(id){
    
    
    $.ajax({
        type:'GET',
        url: 'SelectStatiticsByActivity',
        data:{
            id:id
        },
        dataType: 'JSON',
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
      
        let dataP = []
        
        for(var item of dataG){
            var ob = {
                label:item.label,
                y:((item.y * 100) / total).toFixed(2)
            }
            dataP.push(ob)
        }
        
        setTimeout(generateGrap(dataP), 1000)
        
        
    })
    
}


$('#modalThree').on("scroll", function (){
    var position = $('#modalThree').scrollTop();
  var bottom = $(document).height() - $(window).height();
  
  console.log(position)
  console.log(bottom)
    
    
      if (position == bottom ) {
    console.log(consulta)    
    }
    
}) ;




function generateGrap(data){
    
    
            var chart = new CanvasJS.Chart("graphicOne", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            title: {
                text: "Participación de programas"
            },
            data: [{
                    type: "pie",
                    startAngle: 235,
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

