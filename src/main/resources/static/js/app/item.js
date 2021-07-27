var main = {
        init : function () {
                var _this = this;

                $("#digital").on('click', function () {
                    var category = $("#digital").val();
                    location.href="/items/"+category;
                });
                $("#appliance").on('click', function () {
                    var category = $("#appliance").val();
                    location.href="/items/"+category;
                });

                $("#clothing").on('click', function () {
                    var category = $("#clothing").val();
                    location.href="/items/"+category;
                });

                $("#book").on('click', function () {
                    var category = $("#book").val();
                    location.href="/items/"+category;
                });

                $("#etc").on('click', function () {
                    var category = $("#etc").val();
                    location.href="/items/"+category;
                });

        }

 };

main.init();