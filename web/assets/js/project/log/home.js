$(function () {

    menu('menuLogs')

    Logs()

})

function Logs() {


    $.ajax({
        type: "POST",
        url: './getEventsLogs',
        datatype: 'json'
    }).done(function (data) {

        drawTable(data)

    })

}