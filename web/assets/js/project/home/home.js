
$(document).ready(function () {

    menu('menuHome')

    setTimeout(() => {
        query()
    }, 3000)

})


function query() {
    $.ajax({
        type: "GET",
        url: './SelectgetProgramsWithOutCoor',
        datatype: 'json'
    }).done(function (data) {

        if (data.length > 0) {
            showAlert(data.length)
            generateTable(data)
        } else {
            generateMessage()
            showOne()
        }

    })
}

function update(data) {

    $('#modalEdit').modal('show')

    $('#programa').val(data)


    let coor = document.getElementById('coor')

    let cordinacion = `<option value="No">No</option>`

    $.ajax({
        type: "GET",
        url: './getCoor',
        datatype: 'json'
    }).done(function (data) {


        for (var item of data) {
            if (item.coordinacion != 'n/a') {
                cordinacion += `<option value="${item.coordinacion}">${item.coordinacion}</option>`
            }
        }


        coor.innerHTML = cordinacion;
    })


}

function generateMessage() {

    let str = ` `
    str = `<div class="alert alert-info alert-dismissible fade show" role="alert">
  <strong>Ya no hay resultados!</strong>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>`
    let select = document.getElementById('examples');
    select.innerHTML = ''
    select.innerHTML = str

}

function generateTable(data) {

    let select = document.getElementById('tabla');
    let str = ` `

    for (var item of data) {

        str += `<tr id="row" class="chiquito">
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.nivelFormacion}</td>
                                                    <td>${item.participaciones}</td>
                                                 <td>         
                                                      <button class="btn btn-warning btn-sm" role="button" onclick = "update('${item.nombrePrograma}')" >
                                                            <i class="fas fa-edit"></i>
                                                        </button>           
                                                </td>
                                                </tr> `
    }

    select.innerHTML = str;
}

function hola() {
    $('#modalPrograms').modal('show')
    query()
}

function showAlert(tama) {
    let menu = document.getElementById('alerta')
    let dialog = `<div class="alert alert-danger alert-dismissible fade show" role="alert">
  <strong>Hola tienes ${tama} programas!</strong> sin actualizar su coordinación.
    <button id = 'seePrograms' type="button" class="btn btn-primary hvr-icon-pop" onclick="hola()"><i class="fas fa-edit hvr-icon"></i>Actualizar</button>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>`
    menu.innerHTML = dialog;

}

function showOne() {
    let menu = document.getElementById('alerta')
    let dialog = `<div class="alert alert-secondary alert-dismissible fade show" role="alert">
  <strong>Todo en orden!</strong>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>`
    menu.innerHTML = dialog;

}

$('#botonUpdate').click(function () {

    let select = document.getElementById('coor').value
    let prog = document.getElementById('programa').value


    if (select == 'No') {
        messageInfo('seleccione cordinación')
    } else {



        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            }
        })

        swalWithBootstrapButtons.fire({
            title: 'esta seguro?',
            text: prog + " en " + select,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Si, !',
            cancelButtonText: 'No, !',
            reverseButtons: true,
            showLoaderOnConfirm: true
        }).then((result) => {
            if (result.value) {

                let data = {
                    coor: select,
                    programa: prog
                }

                updateProgram(data)

            } else if (
                    /* Read more about handling dismissals below */
                    result.dismiss === Swal.DismissReason.cancel
                    ) {
                swalWithBootstrapButtons.fire(
                        'Cancelado',
                        'Ups :)',
                        ':D'
                        )
            }
        })




    }

})

function updateProgram(data) {

    $.ajax({
        type: "POST",
        url: './UpdateProgram',
        datatype: 'json',
        data: data
    }).done(function (data) {

        if (data) {
            messageOk('actualizado :D')

        } else {
            messageError('ups :(')
        }

        $('#modalEdit').modal('hide')
        query()


    })


}