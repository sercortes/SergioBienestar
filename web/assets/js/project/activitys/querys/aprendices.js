var dataGr
var dataGrTwo
var dataGrOne
var activ
var tota

$(document).on('click', '.seeAprendices', function () {

    let element = $(this)[0].parentElement.parentElement
    let id = $(element).attr('idActividad')
    var eleme = $(this).closest('tr').find('.elements')
    activ = {
        id: id,
        nombre: eleme[0].outerText,
        tipo: eleme[1].outerText,
        fechaI: eleme[2].outerText,
        fechaF: eleme[3].outerText,
        res: eleme[4].outerText
    }

    aprendices()

})

function aprendices() {

    $('#modalThree').modal('show')

    $.ajax({
        type: "GET",
        url: './ListAprendices',
        datatype: 'json',
        data: {
            id: activ.id
        }
    }).done(function (data) {

        generateTableAprendicess(data, activ)

    })


}

$('#modalThree').on('shown.bs.modal', function generargrafica() {

    $.ajax({
        type: 'GET',
        url: 'SelectStatiticsByActivity',
        data: {
            id: activ.id
        },
        dataType: 'JSON'
    }).done(function (data) {


        let dataG = []
        var total = 0

        for (var item of data) {
            total += parseInt(item.participaciones)
            var ob = {
                label: item.nombrePrograma,
                y: item.participaciones
            }
            dataG.push(ob)
        }

        generateGrap(dataG, total, activ.tipo)

        GrapColumn(dataG, activ.tipo)

        tota = total
        dataGr = dataG
        dataGrOne = dataG
        
        generabeHeader()
        generabeHeaderTwo()

    })
});


$('#generateXls').click(function () {

    exportCSVFile('cabecera', dataGr, activ.nombre.toString().substring(0, 60))

})

$('#generateXlsTwo').click(function () {

    exportCSVFile('cabecera', dataGrTwo, activ.nombre.toString().substring(0, 60))

})

function generabeHeader() {
    let cabecera = {
        hola: 'Programa',
        dos: 'Porcentaje'
    }

    let dataP = []

    for (var item of dataGr) {
        var ob = {
            label: item.label,
            y: ((item.y * 100) / tota).toFixed(2)
        }
        dataP.push(ob)
    }

    for (let item of dataP) {
        item.y = Math.round(parseInt(item.y))
    }
    dataP.unshift(cabecera)
    dataGr = dataP
}

function generabeHeaderTwo() {
    let cabecera = {
        hola: 'Programa',
        dos: 'Cantidad'
    }

    let dataP = []

    for (var item of dataGrOne) {
        var ob = {
            label: item.label,
            y: parseInt(item.y)
        }
        dataP.push(ob)
    }

    dataP.unshift(cabecera)
    dataGrTwo = dataP
}


$('#prints').click(function () {
 
    generatePDF('#contenido', activ.nombre.toString().substring(0, 55), true)

})


function description() {
    $('#titulosD').text('NOMBRE ACTIVIDAD : ' + activ.nombre)
}