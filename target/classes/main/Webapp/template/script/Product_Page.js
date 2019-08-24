var letterNumber = /^[0-9 a-z A-Z \W]+$/;
let getUserID = document.getElementById('Userid').value;

$(function () {

    console.log(getUserID);

    $('.Product_review_button').hide();

    $('.Reply_Section').hide();

    $('.Post_review').hide();

    $('#Product_opinion').click(function () {
        $('.Product_review_button').fadeIn(100);
        $('#Product_opinion').addClass('expand');
    });

    $('#Product_opinion').on("keyup", function () {
        let word = document.getElementById('Product_opinion').value;
        let count = word.toString().length;
        document.getElementById('caution').innerHTML = '80 words minimum.  ' + '(' + count + ' word(s))';
    });


    $('.Review-input').click(function () {
        $('.Review-input').removeClass('Length_Caution');
    });

    //Check if user has logged in or not
    $('.Product_buy_button_flopped').click( function () {
        Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'No account is logged in, you need to log in or sign up in order to initialize buying any kind of product!',
            button: 'OK!'
        }).then(function () {
            location.replace('Login.jsp');
        });
    })
});

//Send request to PostReview to process review post from user
function post_review() {
    let word = document.getElementById('Product_opinion').value;
    let count = word.toString().length;

    if (count >= 80) {
        if ((word.match(letterNumber))) {
            let json_post_review = $('.Create_Review').serialize();
            console.log(json_post_review);

            $.ajax({
                url: 'post-user-review',
                type: 'post',
                data: json_post_review,
                cache: false,
                success: function () {
                    document.getElementById('caution').innerHTML = "*Your review has been added.";
                    $('.Product_review_button').fadeOut(100);
                },
                error: function () {

                }
            });
        }
        else {
            document.getElementById('caution').innerHTML = "*Alphabets accepted only.";
        }
    }
    else {
        $('.Review-input').addClass('Length_Caution');
    }
}

//Send request to process preorder
function Add_To_Cart(ProductID) {
    $.ajax({
        url: 'add-to-cart',
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
                    text: response + ' to add this product in your Cart.',
                    button: 'OK!'
                });
            }
            else {
                Swal.fire({
                    type: 'error',
                    title: 'Oops!',
                    text: response + ' to add this product in to your Cart.',
                    button: 'OK!'
                });
            }
        },
    });
}

function No_Account_Notify() {
    Swal.fire({
        type: 'error',
        title: 'Oops...',
        text: 'No account is logged in, you need to log in or sign up in order to access your Cart!',
        button: 'OK!'
    }).then(function () {
        location.replace('login');
    });
}

//Expand Replies
function Reply_Button_Toggle(PostID) {
    /*let Reply_unexpanded = "Style/IMG/reply-icon.png";
    let Reply_expanded = "https://image.flaticon.com/icons/png/128/1381/1381635.png";
    document.getElementById('Reply-button');

    document.getElementById('Reply-button').src = (document.getElementById('Reply-button').src === Reply_unexpanded)? Reply_expanded : Reply_unexpanded;
    */
    $('#Reply' + PostID).toggle('500ms');
}

//Expand Review Form
function Expand_Review_Form(Productname) {
    console.log(getUserID);
    if (getUserID != -1) {
        $('.Post_review').toggle('500ms');
        document.getElementById('caution').innerHTML = '';
    }
    else {
        Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'No account is logged in, you need to log in or sign up in order to write your review about ' + Productname + '!',
            button: 'OK!'
        })/*.then(function () {
            location.replace('Login.jsp');
        });*/
    }
}

//Reply, 2 variables to check if user is log in or not
//User has to log in in order to reply
function Create_Reply(PostID) {
    let json_reply = $('.Create_Reply' + PostID).serialize();

    let text_in_rep_from = document.getElementById('Review_Reply' + PostID).value;
    //event.preventDefault();
    console.log('Json value of reply form is: ' + json_reply);
    //var str = text_in_rep_from.val();
    if (getUserID !== -1) {
        if ((text_in_rep_from.match(letterNumber))) {
            $.ajax({
                url: 'ReplyPost',
                type: 'post',
                processData: false,
                data: json_reply,
                success: function () {
                    //creates an array of replies (name and value) by serializing form
                    let form_val = $('.Create_Reply' + PostID).serializeArray();

                    let getvalueform = {};
                    $.each(form_val, function (i, dataobj) {
                        getvalueform[dataobj.name] = dataobj.value;
                    });

                    let Replycmt = getvalueform['ReplyCmt'];
                    let Fullname = getvalueform['Name'];
                    //alert('You reply Post#' + PostID);

                    //Append the reply in html
                    let reply = '<li>' +
                        '<div>' +
                        '<h6><strong>' + Fullname + '</strong></h6>' +
                        '<p class="Reply_Comments" style="margin-left: 20px;" >' + Replycmt + '</p>' +
                        '</div>'+
                        '</li>';

                    $('#Review_Reply' + PostID).val('');
                    $('#User_Reply_List' + PostID).append(reply);
                },
                error: function () {
                    alert('Oops');

                }
            });
        }
        else {
            document.getElementById('Review_Reply' + PostID).value = "*Alphabets accepted only."
        }
    }
    else {
        Swal.fire({
            type: 'error',
            title: 'Oops...',
            text: 'No account is logged in, you need to log in or sign up in order to write your review or reply to other reviews!',
            button: 'OK!'
        });

    }
}

function Reply_Count(PostID) {
    let Replylist_size = $('#User_Reply_List' + PostID).find('li').size();
    console.log('Value is ' +  Replylist_size);
    document.getElementById('CountReply' + PostID).innerHTML = Replylist_size;
}

//click like event for user, user can only hit once.
function Like_ReviewPost(postID) {

    let like_count = Number(document.getElementById('Like_Count' + postID).innerText);
    //Check if the post has been liked by user
    //If it is, Class '.like_disabled' will be added to the like a href link class
    //If it is not, Class '.like_disabled' will not be added to the like a href link class
    //This will create a logic that when an user click 'like' on 1st click, he/she will only click unlike on 2nd click
    if(!($('.like_click' + postID).hasClass('like_disabled'))) {
        let like = {PostID: postID , UserID: getUserID};
        $.ajax({
            url: 'LikeReview',
            type: 'post',
            data: $.param(like),
            processData: false,
            cache: false,
            success: function (response) {
                console.log("Total like: " + response);
                $('.like_click' + postID).addClass('like_disabled');
                document.getElementById('Like_Count' + postID).innerText = response;
                console.log('Successfully send like request JSON UserID: ' + getUserID + ' and PostID: ' + postID + " and Likes are " + like_count);
            },
            error: function () {

                //clickAndDisable();
            }
        });
    }
    else {
        let unlike = {PostID: postID , UserID: getUserID};
        $.ajax({
            url: 'UnlikeReview',
            type: 'post',
            data: $.param(unlike),
            processData: false,
            cache: false,
            success: function (response) {
                console.log("Total like: " + response);
                $('.like_click' + postID).removeClass('like_disabled');
                document.getElementById('Like_Count' + postID).innerText = response;
                console.log('Successfully send unlike request JSON PostID: ' + postID + " and Likes are " + like_count);
            },
            error: function () {

                //clickAndDisable();
            }
        });

    }

}
