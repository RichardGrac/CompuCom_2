function changeStatus(type, id_status, iter) {
    var status = document.getElementById(type + iter).value;
    var url = '/shippings/updatestatus?id_ss=' + id_status + '&status=' + status;
    $.get(url, function () {
        window.location.href = "/shippings/pendings";
    });
}
