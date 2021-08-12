var main = {
    init : function () {
        var _this = this;
        $('#btn-gamer-find').on('click', function () {
            _this.find();
        });
    },
    find : function () {
        var keyword = $('#keyword').val();
        $.ajax({
            type: 'GET',
            url: '/api/v1/riot/'+keyword,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function(res) {
            window.location.href = '/summoner/'+keyword;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
main.init();

