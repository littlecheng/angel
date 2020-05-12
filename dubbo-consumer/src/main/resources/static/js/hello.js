$(document).ready(function() {
 /*   $.ajax({
        url: "http://localhost:8086/greeting"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });*/

     $.ajax({
            type: "GET",
            url: "http://localhost:8086/jquery/greeting",
            dataType: "JSON",
            success: function(data) {
                    console.info(data);

                   $('.greeting-id').append(data.id);
                   $('.greeting-content').append(data.content);
            }
          });

});