let getUserID = document.getElementById('Userid').value;

$(function () {

    $(document).ajaxStart(function () {
        $('.loader').addClass('show');
    });

    $(document).ajaxStop(function () {
        console.log('Done!');
        $('.loader').removeClass('show');
    });

    document.getElementById('All').checked = true;

    TypeValue();

    $('#Sort_option').on('change', function () {
        TypeValue();
    });

    $('.search-input').on('keyup', function () {
        if (document.getElementById('All').checked) {
            let search_result = document.getElementById('search-result').textContent;
            search(search_result, 0);
        }
        else if (document.getElementById('Low-Budget').checked) {
            let search_result = document.getElementById('search-result').textContent;
            search(search_result, 1);
        }
        else if (document.getElementById('Mid-Range').checked) {
            let search_result = document.getElementById('search-result').textContent;
            search(search_result, 2);
        }
        else if (document.getElementById('High-Range').checked) {
            let search_result = document.getElementById('search-result').textContent;
            search(search_result, 3);
        }
    });
});

var option = function() {
    var x = document.getElementById("Sort_option").selectedIndex;
    var y = document.getElementById("Sort_option").options;
    return y[x].text;
};

function TypeValue() {
    if (document.getElementById('All').checked) {
        console.log('Get all! | Sort by ' + option());
        getList('All', option());
    }
    else if (document.getElementById('Low-Budget').checked) {
        console.log('Get Low-Budget list! | Sort by ' + option());
        getList('Low-Budget', option());
    }
    else if (document.getElementById('Mid-Range').checked) {
        console.log('Get Mid-Range list! | Sort by ' + option());
        getList('Mid-Range', option());
    }
    else if (document.getElementById('High-Range').checked) {
        console.log('Get High-Range list! | Sort by ' + option());
        getList('High-Range', option());
    }

}

var searching = angular.module('Search', []);
searching.controller('product_search_controller', function ($scope) {
    console.log($scope.product_name);
});

function search(search_result, id) {
    console.log(search_result + ' ' + id);
    $.ajax({
        url: 'get_product_search',
        type: 'get',
        dataType: 'json',
        data: { Search_result : search_result , Typeid : id},
        cache: false,
        success: function (responsedata) {
            //console.log(responsedata);
            $('#List').empty();
            responsedata.forEach(function(list, i, product) {

                let productID = product[i].ProductID;
                let productName = product[i].Productname;
                let price = product[i].Price;
                let ImgSrc = product[i].Imagesource;

                /*console.log('Product name is ' + productName);*/
                let eachProduct =
                    '<li>' +
                    '<form id="access' + productID + '" method="get" action="Product">' +
                    '<input type="hidden" name="ID" value="' + productID + '">' +
                    '<div class="boundary_box">' +
                    '<a href="javascript:access(' + productID + ');" style="border: none; background: none; width: 100%;">' +
                    '<div>' +
                    '<span>' +
                    '<img style="height: 150px; width: auto;" src="'+ ImgSrc +'">' +
                    '</span>' +
                    '<span>' +
                    '<h6 style="text-align: center;"><strong>' + productName + '</strong></h6>' +
                    '</span>' +
                    '<div class="Price">' +
                    '<h5 style="text-align: center;"><strong>' + Price_Format(price) + '₫</strong></h5>' +
                    '</div>' +
                    '</div>' +
                    '<div class="buttons">' +
                    '<a href="javascript:Add_To_Cart(' + productID + ');" id="' + productID + ')" class="Buy">Add To Cart</a>' +
                    '<a href="#" class="Rating">Rating</a>' +
                    '</div>' +
                    '</a>' +
                    '</div>' +
                    '</form>' +
                    '</li>';
                $('#List').append(eachProduct);
            });
        }
    });
}

function getList(type, sort_option) {
    $.ajax({
        url: 'get_product',
        type: 'get',
        dataType: 'json',
        data: { Type : type , Sort_option : sort_option },
        cache: false,
        success: function (responsedata) {
            //console.log(responsedata);
            $('#List').empty();
            responsedata.forEach(function(list, i, product) {

                let productID = product[i].ProductID;
                let productName = product[i].Productname;
                let price = product[i].Price;
                let ImgSrc = product[i].Imagesource;

                /*console.log('Product name is ' + productName);*/
                let eachProduct =
                '<li>' +
                    '<form id="access' + productID + '" method="get" action="main-page/product">' +
                        '<input type="hidden" name="ID" value="' + productID + '">' +
                        '<div class="boundary_box">' +
                            '<a href="javascript:access(' + productID + ');" style="border: none; background: none; width: 100%;">' +
                                '<div>' +
                                    '<span>' +
                                        '<img style="height: 150px; width: auto;" src="'+ ImgSrc +'">' +
                                    '</span>' +
                                    '<span>' +
                                        '<h6 style="text-align: center;"><strong>' + productName + '</strong></h6>' +
                                    '</span>' +
                                    '<div class="Price">' +
                                        '<h5 style="text-align: center;"><strong>' + Price_Format(price) + '₫</strong></h5>' +
                                    '</div>' +
                                '</div>' +
                                '<div class="buttons">' +
                                    '<a href="javascript:Add_To_Cart(' + productID + ');" id="' + productID + ')" class="Buy">Add To Cart</a>' +
                                    '<a href="#" class="Rating">Rating</a>' +
                                '</div>' +
                            '</a>' +
                        '</div>' +
                    '</form>' +
                '</li>';
                $('#List').append(eachProduct);
            });
        }
    });
}

function Price_Format(price) {
    price = price.toLocaleString();
    return price;
}
function No_Account_Notify() {
    Swal.fire({
        type: 'error',
        title: 'Oops...',
        text: 'No Account is logged in, you need to log in or sign up in order to access your Cart!',
        button: 'OK!'
    }).then(function () {
        location.replace('login');
    });
}

//Access product form submit function
function access(id) {
    document.querySelector('#access' + id).submit();
}

function Add_To_Cart(ProductID) {
    console.log('Get ' + getUserID);
    if (getUserID != -1) {
        $.ajax({
            url: 'Cart',
            type: 'post',
            dataType: 'json',
            data: { UserID : getUserID,
                ProductID : JSON.stringify(ProductID) },
            cache: false,
            success: function (response) {
                console.log('Status: ' + response);
                if(response === "Succeed") {
                    Swal.fire({
                        type: 'success',
                        title: 'Good!',
                        text: response + ' to this add product in your Cart.',
                        button: 'OK!'
                    });
                }
                else {
                    Swal.fire({
                        type: 'error',
                        title: 'Oops!',
                        text: response + ' to this add product in to your Cart due to out of order and also you can only add 10 products maximum in each only.',
                        button: 'OK!'
                    });
                }
            },
        });
    }
    else {
        Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'No Account is logged in, you need to log in or sign up in order to access your Cart!',
            button: 'OK!'
        }).then(function () {
            location.replace('login');
        });
    }
}