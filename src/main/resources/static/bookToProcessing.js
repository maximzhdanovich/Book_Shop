$(document).ready(
    function () {
        $("#bookToProcessing").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            var formData = {
                id: $("#bookIdFor").val()
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "bookToProcessing",
                success: function () {
                    showSuccessMessage1();
                },
                data: JSON.stringify(formData),
                dataType: 'json'
            });
        }
    });

function showSuccessMessage1() {
    $("#AddOnProcessing").fadeOut();
    $("#AddOnProcessing").fadeIn(1000);
    setTimeout(function () {
        $("#AddOnProcessing").fadeOut(1000);
    }, 3000);
}
