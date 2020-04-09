var input = ''
var $pagination = $('#pagination'),
        totalRecords = 0,
        records = [],
        displayRecords = [],
        recPerPage = 10,
        page = 1,
        totalPages = 0,
        initiateStartPageClick = true

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
    $pagination.twbsPagination('destroy');
    
    let select = document.getElementById('coor').value
    if (select == '' || select == 'No') {

        buscarAprendiz()
    } else {
        selectCoordinacion()
    }

}

function buscarAprendiz() {

$pagination.twbsPagination('destroy');
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

        records = data
        totalRecords = data.length
        totalPages = Math.ceil(totalRecords / recPerPage)

        apply_pagination()

    })


}


function apply_pagination() {

    $pagination.twbsPagination({
        totalPages: totalPages,
        visiblePages: 4,
        onPageClick: function (event, page) {
            displayRecordsIndex = Math.max(page - 1, 0) * recPerPage;
            endRec = (displayRecordsIndex) + recPerPage;

            displayRecords = records.slice(displayRecordsIndex, endRec);
            generateTableaprendices(displayRecords)
        }
    });
}