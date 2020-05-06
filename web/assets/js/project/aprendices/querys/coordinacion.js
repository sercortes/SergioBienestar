var input
var total
function selectCoordinacion() {

    showAnimation()
    $pagination.twbsPagination('destroy');
    let select = document.getElementById('coor').value

    if (select == 'No') {

    } else {
        $('#keyWord').val('')

        let fechai = document.getElementById('fechaI').value
        let fechaf = document.getElementById('fechaF').value

        input = {
            id: select,
            fechaInicial: fechai,
            fechaFinal: fechaf
        };

        listarCoor(input)

    }
}



function listarCoor(data) {
    $.ajax({
        type: "GET",
        url: './getByCoor',
        datatype: 'json',
        data: {
            id: data.id,
            fechaInicial: data.fechaInicial,
            fechaFinal: data.fechaFinal
        }
    }).done(function (data) {

        if (data <= 0) {
            validationSizeSelect(input.id)
            return false
        }

        validationResult(data.length)


        let sum = 0
        let coor = document.getElementById('coor').value
        let select = document.getElementById('tabla');
        let str = ` <h4 class="titulos hvr-icon-pop mt-4" id="tituloInforme">${coor}</h4>
    <table id="examples" class="table-responsive-sm table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                        <th>Nombre programas</th>
                                        <th>Coordinación</th>
                                        <th># Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {
            str += `<tr id="" class="chiquito1 text-justify">
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.coordinacion}</td>
                                                    <td>${item.participaciones}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-xs" role="button" onclick = "aprendicesByCoor('${item.nombrePrograma}')" >
                                                            <i class="fas fa-list-ol"></i> 
                                                        </button>            
                                                </td>
                                               
                                                </tr> `
            sum += parseInt(item.participaciones);
        }
        console.log(sum)
        total = sum
        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                         <th>Nombre programas</th>
                                        <th>Coordinación</th>
                                        <th># Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>
    
        
    
    <div id="graficaBarra" class="page-break">
    
            </div>
                <hr>
            <div class="row">
                <div class="col-md-10">
                    
                </div>
                <div class="col-md-2">
                    <button id="generateXlsBarra" type="button" class="btn btn-success float-right"><i class="far fa-file-excel"></i> Generar</button>
                </div>
            </div>
                <hr>
    
    
    
    
            <div id="graficos" class="page-break">
    
            </div>
                <hr>
            <div class="row">
                <div class="col-md-10">
                    
                </div>
                <div class="col-md-2">
                    <button id="generateXlsCoorPrograma" type="button" class="btn btn-success float-right"><i class="far fa-file-excel"></i> Generar</button>
                </div>
            </div>
                
                <hr>
            <div id="yearRange" class="page-break">

            </div>
                <hr>
            <div class="row">
                <div class="col-md-10">
                    
                </div>
                <div class="col-md-2">
                    <button id="generateXlsCoorYearRanges" type="button" class="btn btn-success float-right"><i class="far fa-file-excel"></i> Generar</button>
                </div>
            </div>
                <hr>
         <div id="buttons" class="m-3 float-right">
        <button id="generatePdf" type="button" class="btn btn-primary hvr-pulse-grow" onclick="printCoor()"><i class="fas fa-print"></i> Imprimir</button>`


        select.innerHTML = str;

        generateArrayStatics(data, sum)

        let datos = generateQueryYearCoor(input);
        generateArrayStaticsYearCoor(datos, total)

    })


}

function printCoor() {
    let select = document.getElementById('coor').value
    generatePDF('#informe', select, true)
}

function generateArrayStatics(data, total) {
    generateGraphicByCoor(data, total)
    generateGraphicByCoorBarra(data)
    setArray(data, total)
}

