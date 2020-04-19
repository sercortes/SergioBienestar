var email
$(function () {

    Profile()

})

function Profile() {


    $.ajax({
        type: "GET",
        url: './getUser',
        datatype: 'json'
    }).done(function (data) {

        setEmail(data)

    })

}

function setEmail(data){
    $('#emailShow').val(data)
    email = data
}

$(document).on('click','#botonEditPass', function(){
    
      updatePassword(email)
    
})