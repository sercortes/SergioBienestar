

$(function () {

    menu('menuAprendices')

    let coor = document.getElementById('coor')

    let cordinacion = ``

    $.ajax({
        type: "GET",
        url: './getCoor',
        datatype: 'json'
    }).done(function (data) {


        for (var item of data) {
            cordinacion += `<option value="${item.coordinacion}">${item.coordinacion}</option>`
        }


        coor.innerHTML += cordinacion;
    })

    setDefaultDate()

    showAnimation()

    searchAprendiz(returnDataAprendiz())


})

function cambiarFecha() {
    
    let select = document.getElementById('coor').value
    if (select == '' || select == 'No') {

        buscarAprendiz()
    } else {
        selectCoordinacion()
    }

}

function buscarAprendiz() {

showAnimation()
      

    let word = document.getElementById('keyWord').value
    if (word == '') {
        searchAprendiz(returnDataAprendiz())
    } else if (word.length <= 2) {
        messageInfo('Completa el documento o el nombre')
        return false
    } else {
        searchAprendizWord(returnDataAprendiz())
    }

}

$('#buttonSearch').click(function () {

    buscarAprendiz()

})




function searchAprendiz(data) {

    $('#coor').val(1)   

    $.ajax({
        type: "GET",
        url: './getSearchAprendiz',
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


