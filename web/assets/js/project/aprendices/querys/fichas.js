
function fichasTotal(id){
      
    $('#exampleModal').modal('show')
  
        let fechai = document.getElementById('fechaI').value
        let fechaf = document.getElementById('fechaF').value
        
        let data = {
            id: id,
            fechaInicial : fechai,
            fechaFinal : fechaf
        };
       
      setTimeout(()=> {
            graphicByFicha(data)
      }, 1800)
  
   
     $.ajax({
        type: "GET",
        url: './ListAprendicesByFicha',
        datatype: 'json',
        data:{
            id:id,
            fechaInicial : data.fechaInicial,
            fechaFinal : data.fechaFinal
        }
    }).done(function (data) {
        
      let acti = data[0].ficha
      let num = 0
      
       $('#tittleListAprendices').text('FICHA : '+acti)
       
      let cantidad = document.getElementById('cantidad')
      cantidad.innerHTML = "# Aprendices : "+data.length
      
      let programa = document.getElementById('programaFicha')
       programa.innerHTML = "Programa Formación : "+data[0].nombrePrograma

     let select = document.getElementById('tabla1');
        let str = `<table id="examples" class="table table-striped table-bordered table-responsive-sm">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Coordinación</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {
            str += `<tr id="row" class="chiquito">
                                                    <td>${item.documento_aprendiz}</td>
                                                    <td>${item.nombre_aprendiz}</td>
                                                    <td>${item.coordinacion}</td>
                                                    <td>${item.participaciones}</td>
                                                 <td class="noexportar">         
                                                      <button class="btn btn-info btn-sm" role="button" onclick = "getActivitysByAprendiz(${item.id_aprendiz})" >
                                                            <i class="fas fa-address-card"></i>
                                                        </button>           
                                                </td>
                                                </tr> `
        num +=parseInt(item.participaciones)
        }
        console.log(num)

        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                         <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Coordinación</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>`

        select.innerHTML = str;

       
       
    })

    
}

$('#generateInformeFicha').click(function(){
    let titulo = document.getElementById('tittleListAprendices').textContent
    generatePDF('#informeFichas', titulo, true)
})