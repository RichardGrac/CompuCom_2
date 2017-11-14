function status(deal_id) {
    var url = '/banner/change-status?id=' + deal_id;
    $.get(url);
}