//Rating product for user
$(function () {
    $(".my-rating").starRating({
        starSize: 25,
        initialRating: 0,
        activeColor: '#f76b1c',
        disableAfterRate: false,
        callback: function(currentRating, $el) {
            console.log('Your current rating is ' + currentRating);
            document.getElementById('Product_rating').value = currentRating;
        }
    });

    $(".my-rating-1").starRating({
        readOnly: true,
        strokeColor: '#894A00',
        strokeWidth: 10,
        starSize: 20,
    });
});

