$(function () {

    //When Order Management Page is opened, the order details such as products will be default hide/
    $('.order_details').hide();
});

//When user click on each order, it will expand
function Toggle_expand(ID) {
       $('.list' + ID).fadeToggle('500ms');
}