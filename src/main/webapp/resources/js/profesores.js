$(document).ready(function (){
    //getAllProfesores();
    table = $('#table-users').DataTable({
                "info": false,
                "pagingType": "full_numbers",
                "columnDefs": [{
                        "targets": 'no-sort',
                        "orderable": false}],
                "order": [[1, "desc"]]
            });
});
function crearUsuario() {
    $('.id-usuarioc').val('');
    $('.usuarioc').val('');
    $('.correoc').val('');
    $('.nombrec').val('');
    $('.apepc').val('');
    $('.apemc').val('');
    $('.activoc').prop('checked', false);
    $('.contrasenac').val('');
    $.fancybox([
        {href: '#Form-create-usuario',

            helpers: {
                overlay: {closeClick: false

                }
                //Disable click outside event
            },
        }



    ]);
}
function getAllProfesores() {
    $.ajax({
        type: "GET",
        url: '../profesores/getAll.do',
        dataType: "json",
        context: this,
        //data: param,
        success: function (data) {
            var model = createStruct(data.response);
            $('#body-users').html(model);
            table = $('#table-users').DataTable({
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
    data.Profesores.forEach(function (inf) {
        var fecha = new Date(inf.fechaAlta);
        model += '<tr id="user_' + inf.idUsuario + '">';
        model += '<td valign="top"><input name="usuario" type="checkbox" style="margin-left:30%" onchange="editarOcultar()" value="' + inf.idUsuario + '"></td>';
        model += '<td valign="top">' + inf.idUsuario + '</td>';
        model += '<td valign="top">' + inf.usuario + '</td>';
        model += '<td valign="top">' + inf.datosGenerales.email + '</td>';
        model += '<td valign="top">' + inf.datosGenerales.nombre + '</td>';
        model += '<td valign="top">' + inf.datosGenerales.apellidoP + '</td>';
        model += '<td valign="top">' + inf.datosGenerales.apellidoM + '</td>';
        var estatus = inf.activo ? 'Activo' : 'Inactivo';
        model += '<td valign="top">' + estatus + '</td>';
        model += '<td valign="top">' + new Date(inf.altaSistema).yyyymmdd() + '</td>';
        model += '</tr>';
    });
    return model;
};

function alerta(obj){
    alert(obj);
}