$(document).ready(
    function () {
        $("#AllBookToProcessing").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "AllBookToProcessing",
                success: function () {
                    showSuccessMessage();
                },
                dataType: 'json'
            });
        }
    });

function showSuccessMessage() {
    $("#DeleteFromBasketSuccess").fadeOut();
    $("#DeleteFromBasketSuccess").fadeIn(1000);
    setTimeout(function () {
        $("#DeleteFromBasketSuccess").fadeOut(1000);
    }, 3000);
}