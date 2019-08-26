$(function() {

    //Expand fill input groups
    $('.orderfill').hide();

    $('.adjust').hide();

    $('.append').click(function() {
        $('.orderfill').slideToggle('500ms');
    });
});

function expand_button(id) {
    //Show change button

    $('#quantity_adjust_btn' + id).fadeIn('slow');

}

function hide_button(id) {
    //Show change button
    $('#quantity_adjust_btn' + id).fadeOut('slow');
}

const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
        confirmButton: 'btn btn-light',
        cancelButton: 'btn btn-light'
    },
    buttonsStyling: false,
});

//Confirm box for product in cart
function Remove_product(Productname, pid){
    swalWithBootstrapButtons.fire({
        title: '<strong style="font-family: Calibri, sans-serif; color: #f27474;">Are you sure?</strong>',
        text: 'Once removed, ' + Productname + ' will no longer be in your Cart!',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, remove it!',
        cancelButtonText: 'No, cancel it!',
    }).then((result) => {
        if (result.value) {
            swalWithBootstrapButtons.fire(
                'Deleted!',
                Productname + ' has been removed from your Cart.',
                'success'
            ).then(function () {
                document.querySelector('#Removed-no' + pid).submit();
            });
        }
        else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire(
                'Cancelled!',
                Productname +' will be in your Cart.',
                'error'
            );
        }
    });
}

//Confirm box for order
function CF_Order(){
    swalWithBootstrapButtons.fire({
        title: '<strong style="font-family: Calibri, sans-serif; color: #f27474;">Are you sure?</strong>',
        text: 'Once done, This order will be added to your Orders list and you will get your merchandise(s) on the ship date!',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, do it!',
        cancelButtonText: 'Not yet!',
    }).then((result) => {
        if (result.value) {
            document.querySelector('#Final').submit();
        }
        else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire(
                'Cancelled!',
                'Want more huh?',
                'error'
            );
        }
    });
}

//Remove all products from orders
function Abort_Order(UserID){
    swalWithBootstrapButtons.fire({
        title: '<strong style="font-family: Calibri, sans-serif; color: #f27474;">Are you sure?</strong>',
        text: 'Cautious, all products will no longer be in your Cart!',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, remove all!',
        cancelButtonText: 'No, cancel it!',
    }).then((result) => {
        if (result.value) {
            swalWithBootstrapButtons.fire(
                'Deleted!',
                'All products has been removed from your Cart.',
                'success'
            ).then(function () {
                $.ajax({
                    url: 'abort-order',
                    method: 'post',
                    data_type: 'json',
                    data: {UserID : JSON.stringify(UserID)},
                    success: function (response) {
                        alert(response);
                    }
                }).then(function () {
                    location.reload();
                });
            });
        }
        else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire(
                'Cancelled!',
                'All products which have been added will be in your Cart.',
                'error'
            );
        }
    });
}

//Quantity change notification
function Quantity_Change(ProductID, Productname) {
    swalWithBootstrapButtons.fire({
        title: '<strong style="font-family: Calibri, sans-serif; color: #f27474;">Are you sure?</strong>',
        text: 'You will change the quantity of ' + Productname + ' in your Cart.',
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, do it!',
        cancelButtonText: 'No, cancel it! ',
    }).then((result) => {
        if (result.value) {
            swalWithBootstrapButtons.fire(
                'Done!',
                'The quantity of your ' + Productname + ' has been changed.',
                'success'
            ).then(function () {
                document.querySelector('#change_number' + ProductID).submit();
            });
        }
        else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire(
                'Cancelled!',
                'The quantity of your ' + Productname + ' will not be changed.',
                'error'
            )
        }
    });
}