$(document).ready(
    function () {
        $("#deleteFromBasket").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            var formData = {
                id: $("#bookIdDelete").val()
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "deleteFromBasket",
                data: JSON.stringify(formData),
                dataType: 'json'
            });
        }
    });
