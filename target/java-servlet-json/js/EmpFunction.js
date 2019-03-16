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

    // Variable declaration
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
                        .append($("<td/>").html("<button class='btn btn-primary' type='button' data-target='#editModal' onclick='getEditPettyCash(this.value)' value = '" + pettycash.code.toString() + "''><span class='glyphicon glyphicon-cog' aria-hidden='true'></span>&nbsp;&nbsp;Edit</button>"))
                        .append($("<td/>").html("<button class='btn btn-warning' type='button' onclick='cancelPettyCash(this.value)' value = '" + pettycash.code.toString() + "''><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>&nbsp;&nbsp;Cancel</button>"))



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
 Function     : addPettyCash
 Parameters   : none
 Descrition : addPettyCash is a function to request server side to add new Petty Cash
 <------------------------------------------------------------------------------>*/
function addPettyCash() {

    // Variable declaration
    var pettycash = new Object();
    pettycash.emp_id = $('#emp_id').val();
    pettycash.description = $('#description').val();
    pettycash.amount = $('#amount').val();
    pettycash.action = "requestPettyCash";

    //Call requestPettyCash
    $.ajax({
        url: "jsonservlet",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(pettycash),
        contentType: 'application/json',
        mimeType: 'application/json',
        async: false,
        success: function (data) {
            // Clear input and text area
            $('#emp_id').val("");
            $('#description').val("");
            $('#amount').val("");
            $('#addModal').modal('toggle');
            alert("Success");

            readData();
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}

/*<------------------------------------------------------------------------------>
 Function     : editPettyCash
 Parameters   : code
 Descrition : editPettyCash is a function to request server side to edit specific Petty Cash
 <------------------------------------------------------------------------------>*/
function editPettyCash(code) {

    // Variable declaration
    var pettycash = new Object();
    pettycash.code = code;
    pettycash.emp_id = $('#editEmp_id').val();
    pettycash.description = $('#editDescription').val();
    pettycash.amount = $('#editAmount').val();
    pettycash.action = "editPettyCash";

    //Call editPettyCash
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
            $('#editModal').modal('toggle');
            readData();
            alert("Success");
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}

/*<------------------------------------------------------------------------------>
 Function     : getEditPettyCash
 Parameters   : code
 Descrition : getEditPettyCash is a function to request specific petty cash from server side
 and provide infomation in edit modal
 <------------------------------------------------------------------------------>*/
function getEditPettyCash(code) {

    // Variable declaration
    var pettycash = new Object();
    pettycash.code = code;
    pettycash.action = "getEditPettyCash";

    // Clear input and text area
    $('#editEmp_id').val("");
    $('#editDescription').val("");
    $('#editAmount').val("");

    //Call getEditPettyCash
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

            $.each(data, function (index, pettycash) {


                if (pettycash.message === "Uneditable") {
                    alert("Uneditable");
                } else {
                    $('#editModal').modal('toggle');

                    $('#headerCode').text(pettycash.code);

                    $('#editEmp_id').val(pettycash.emp_id);

                    $('#editDescription').val(pettycash.description);

                    $('#editAmount').val(pettycash.amount);

                    //$('#confirmEdit').html("<button class='btn btn-primary' type='button' onclick='editPettyCash(this.value)' value = '" + pettycash.code.toString() + "''>Confirm</button>");
                    $('#confirmEdit').val(pettycash.code.toString());
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
 Function     : cancelPettyCash
 Parameters   : code
 Descrition : cancelPettyCash is a function to request server side to change 
 Status of petty cash to be "Cancel" 
 <------------------------------------------------------------------------------>*/
function cancelPettyCash(code) {

    // Variable declaration
    var pettycash = new Object();
    pettycash.code = code;
    pettycash.action = "cancelPettyCash";

    // Call cancelPettyCash
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
                //Validate message from server side
                if (pettycash.message === "Uncancelable") {
                    alert("Uncancelable");
                } else {
                    alert("Success");
                    readData();
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
 Function     : DeletePettyCash
 Parameters   : code
 Descrition   : DeletePettyCash is a function to request server side to delete 
 specific Petty Cash
 Status       : Not in use
 <------------------------------------------------------------------------------>*/
function DeletePettyCash(code) {

    // Variable declaration
    var pettycash = new Object();
    pettycash.code = code;
    pettycash.action = "deletePettyCash";

    // Call deletePettyCash
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

                //Validate message from server side
                if (pettycash.message === "Undeletable") {
                    alert("Undeletable");
                } else {
                    alert("Delete Success");
                }
            });

            readData();
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}