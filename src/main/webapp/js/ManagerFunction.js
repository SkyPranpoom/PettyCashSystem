
$(document).ready(function () {
    readData();

});

/*<------------------------------------------------------------------------------>
 Function     : readData
 Parameters   : none
 Descrition : readData is a function to request all petty cash from server side
 and put all petty cash's data in to table form
 <------------------------------------------------------------------------------>*/
function readData() {
    var pettycash = new Object();
    pettycash.action = "readPettyCash";

    $.ajax({
        url: "jsonservlet",
        //action: 'requestPettyCash',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(pettycash),
        contentType: 'application/json',
        mimeType: 'application/json',
        async: false,
        success: function (data) {
            $("tr:has(td)").remove();
            $.each(data, function (index, pettycash) {

                $("#added-pettycashes").append($('<tr/>')
                        .append($('<td/>').html("<p>" + pettycash.code + "</p>"))
                        .append($("<td/>").html("<p>" + pettycash.emp_id + "</p>"))
                        .append($("<td/>").html("<p>" + pettycash.description + "</p>"))
                        .append($("<td/>").html("<p>" + pettycash.amount + "</p>"))
                        .append($("<td/>").html("<p>" + pettycash.status + "</p>"))
                        .append($("<td/>").html("<p>" + pettycash.create_datetime + "</p>"))
                        .append($("<td/>").html("<p>" + pettycash.update_datetime + "</p>"))
                        .append($("<td/>").html("<button class='btn btn-primary' type='button' data-target='#approveModal' onclick='getPettyCash(this.value)' value = '" + pettycash.code.toString() + "''><span class='glyphicon glyphicon-list-alt' aria-hidden='true'></span>&nbsp;&nbsp;Detail</button>"))

                        //Delete Function
                        //.append($("<td/>").html("<button class='btn btn-danger' type='button' onclick='DeletePettyCash(this.value)' value = '" + pettycash.code.toString() + "''>Delete</button>"))
                        );
            });
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}


/*<------------------------------------------------------------------------------>
 Function     : getPettyCash
 Parameters   : code
 Descrition : getPettyCash is a function to request specific petty cash from server side
 and provide infomation in modal
 <------------------------------------------------------------------------------>*/
function getPettyCash(code) {

    // get inputs
    var pettycash = new Object();
    pettycash.code = code;
    pettycash.action = "getPettyCashDetail";

    $.ajax({
        url: "jsonservlet",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(pettycash),
        contentType: 'application/json',
        mimeType: 'application/json',
        async: false,
        success: function (data) {



            $.each(data, function (index, pettycash) {

                if (pettycash.message === "Uneditable") {
                    alert("Uneditable");
                } else {
                    $('#approveModal').modal('toggle');

                    $('#headerCode').text(pettycash.code);
                    $('#Emp_id').text(pettycash.emp_id);
                    $('#Description').text(pettycash.description);
                    $('#Amount').text(pettycash.amount);
                    $('#Status').text(pettycash.status);
                    $('#Create_datetime').text(pettycash.create_datetime);
                    $('#Update_datetime').text(pettycash.update_datetime);
                    $('#notApprovePettyCash').val(pettycash.code.toString());
                    $('#approvePettyCash').val(pettycash.code.toString());
                }
            });

            readData();
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}

/*<------------------------------------------------------------------------------>
 Function     : approvePettyCash
 Parameters   : code
 Descrition : approvePettyCash is a function to request server side to change 
 Status of petty cash to be "Approve" 
 <------------------------------------------------------------------------------>*/
function approvePettyCash(code) {

    // get inputs
    var pettycash = new Object();
    pettycash.code = code;
    pettycash.action = "approvePettyCash";

    $.ajax({
        url: "jsonservlet",
        //action: 'requestPettyCash',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(pettycash),
        contentType: 'application/json',
        mimeType: 'application/json',
        async: false,
        success: function (data) {
            $('#approveModal').modal('toggle');
            readData();
            alert("Success");
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}

/*<------------------------------------------------------------------------------>
 Function     : notApprovePettyCash
 Parameters   : code
 Descrition : notApprovePettyCash is a function to request server side to change 
 Status of petty cash to be "Not Approve" 
 <------------------------------------------------------------------------------>*/
function notApprovePettyCash(code) {

    // get inputs
    var pettycash = new Object();
    pettycash.code = code;
    pettycash.action = "notApprovePettyCash";

    $.ajax({
        url: "jsonservlet",
        //action: 'requestPettyCash',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(pettycash),
        contentType: 'application/json',
        mimeType: 'application/json',
        async: false,
        success: function (data) {
            $('#approveModal').modal('toggle');
            readData();
            alert("Success");
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}
