$(document).ready(function () {
    getAllAlumnos();
});
function crearAlumno() {
    $('input[type="text"]').value='';
    $('input[type=checkbox]').prop('checked', false);
    /*$('.id-usuarioc').val('');
    $('.usuarioc').val('');
    $('.correoc').val('');
    $('.nombrec').val('');
    $('.apepc').val('');
    $('.apemc').val('');
    $('.activoc').prop('checked', false);
    $('.contrasenac').val('');*/
    $.fancybox([
        {href: '#Form-create-usuario',
            helpers: {
                overlay: {closeClick: false

                }
                //Disable click outside event
            }
        }
    ]);
}
function modifAlumno(){
    
}
function getAllAlumnos() {
    $.ajax({
        type: "GET",
        url: '../alumnos/findAll.do',
        dataType: "json",
        context: this,
        //data: param,
        success: function (data) {
            var model = createStruct(data.response);
            $('#body-alumnos').html(model);
            table = $('#table-alumnos').DataTable({
                "info": false,
                "pagingType": "full_numbers",
                "columnDefs": [{
                        "targets": 'no-sort',
                        "orderable": false}],
                "order": [[1, "desc"]]
            });
        }
    });
}
createStruct = function (data) {
    var model = '';
    data.Alumnos.forEach(function (inf) {
        model += '<tr id="user_' + inf.idAlumno + '">';
        model += '<td valign="top"><input name="usuario" type="checkbox" style="margin-left:30%" onchange="editarOcultar()" value="' + inf.idAlumno + '"></td>';
        model += '<td valign="top">' + inf.nombre + '</td>';
        model += '<td valign="top">' + inf.apellidoP + '</td>';
        model += '<td valign="top">' + inf.apellidoM + '</td>';
        var estatus = inf.activo ? 'Activo' : 'Inactivo';
        model += '<td valign="top">' + estatus + '</td>';
        model += '<td valign="top">' + new Date(inf.altaSistema).yyyymmdd() + '</td>';
        model += '<td valign="top">' + inf.idTutor.datosGenerales.nombre + ' ' + inf.idTutor.datosGenerales.apellidoP + ' ' + inf.idTutor.datosGenerales.apellidoM + '</td>';
        model += '<td valign="top"></td>';
        model += '</tr>';
    });
    return model;
};
function alerta(data) {
    alert(JSON.stringify(data));
}