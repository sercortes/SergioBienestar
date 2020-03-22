
Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

Date.prototype.getStartYear = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    local.setMonth(this.getMonth()-(this.getMonth()))
    local.setDate(this.getDay()-this.getDay()+1)
    return local.toJSON().slice(0,10);
});

$(function(){
    
    let filtros1 = document.getElementById('coor')
    
    
    let cordinacion = ``
    
    
    $.ajax({
            type: "GET",
            url: './getCoor',
            datatype: 'json'
        }).done(function (data) {
                      

            for (var item of data) {
                cordinacion += `<option value="${item.coordinacion}">${item.coordinacion}</option>`
            }
            
            
            filtros1.innerHTML += cordinacion;
               
            
        })
    
    typesActivitys()

    setDefaultDate()
    
})


  
function setDefaultDate(){
    document.getElementById('fechaI').value = new Date().getStartYear();
    document.getElementById('fechaF').value = new Date().toDateInputValue();
}
