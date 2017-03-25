var table;
$('body').ready(function () {
    var ln = x = window.navigator.language || navigator.browserLanguage;
    jQuery.i18n.properties({
        name: 'Messages',
        path: 'resources/message/',
        mode: 'both',
        language: ln.split("-")[0] !== 'es' ? ln.split("-")[0] : '', //se define el lenguaje por medio del navegador
        checkAvailableLanguages: true,
        async: true,
        callback: function () {
            $('[entity]').each(function () {
                //$(this).attr('entity'); se obtiene el valor de el atributo
                //window['acceder']; se obtine la variable global de il8n
                if ($(this).is('input') && $(this).attr('placeholder') == undefined) {
                    $(this).val(window[$(this).attr('entity')]);
                } else if ($(this).is('input') && $(this).attr('placeholder') != undefined) {
                    $(this).attr('placeholder', window[$(this).attr('entity')]);
                } else {
                    $(this).html(window[$(this).attr('entity')]);
                }
            });
            $('#loader-wrapper').css('display','none');
            try {
                table = $('#table').DataTable({
                    "info": false,
                    "pagingType": "full_numbers",
                    "columnDefs": [{
                            "targets": 'no-sort',
                            "orderable": false}],
                    "order": [[1, "desc"]]
                });
            } catch (err) {
                console.log("No existe el campo tabla:" + err);
            }
        }
    });
});
function createCall(idForm, callback) {
    var param = {}, header;
    var objetos = $('#' + idForm).find('input[type=text],input[type=checkbox],input[type=password],input[type=hidden]');
    objetos.map(function (obj, input) {
        if ($(input).attr('subobject') != undefined) {
            if (param [$(input)[0].attributes.subobject.value] == undefined) {//precaucion
                param [$(input)[0].attributes.subobject.value] = {};
            }
            if ($(input).attr('type') == 'checkbox') {
                param [$(input)[0].attributes.subobject.value][$(input).attr('name')] = $(input)[0].checked;
            } else {
                param [$(input)[0].attributes.subobject.value][$(input).attr('name')] = $(input).val();
            }
        } else {
            if ($(input).attr('type') == 'checkbox') {
                param [$(input).attr('name')] = $(input)[0].checked;
                console.log($(input)[0].checked);
            } else if ($(input).attr('type') == 'hidden') {
                header = $(input).val();
                header = $("meta[name='_csrf']").attr("content");
                console.log($(input).val());
            } else {
                param [$(input).attr('name')] = $(input).val();
                console.log($(input).val());
            }
        }
    });
    callWebservices($('#' + idForm).attr('method'), $('#' + idForm).attr('action'), header, param, callback);
}
function callWebservices(method, url, header, param, callback) {
    $.ajax({
        type: method, //POST,GET,PUT,ETC
        url: url,
        dataType: "json",
        context: this,
        headers: {
            'X-CSRF-TOKEN': header,
            'Content-Type': "application/json;charset=UTF-8"
        },
        data: param != {} ? JSON.stringify(param) : '',
        success: function (data) {
            callback(data);
        }
    });
}
function crear() {
    var list = $('#form-create').find("input");
    list.each(function () {
        var input = $(this).attr('type');
        if (input === 'text') {
            $(this).val('');
        } else if (input === 'password') {
            $(this).val('');
        } else if (input === 'hidden') {
            $(this).val('');
        }
    });
    $.fancybox.open({
        src: '#form-create',
        type: 'inline',
        opts: {
            onComplete: function () {
                console.info('done!');
            }
        }
    });
}