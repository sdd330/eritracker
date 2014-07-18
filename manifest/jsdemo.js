var SM_KEY_WORD = "jsdemo";
var REFRESH_TIME = 30000; // Refresh time set to 30000 ms
var TIMEOUT_HANDLE = null;

var LocalStorageManager = {

    setStoredString: function() {

        // get string in text input field
        var str = $("#inputText")[0].value;

        // store string into Storage Manager with keyword
        SM.set(SM_KEY_WORD, str);
    },

    updateStoredString: function() {

        // get string stored in Storage Manager with keyword
        var storedString = SM.get(SM_KEY_WORD);
        if (storedString == null) {
            storedString = "";
        }

        // replace text with stored string
        $("li.text")[1].innerText = storedString;
    },

    resetSubmitLinkFunction: function() {
        var submitButton = $("li.link")[0];
        if (submitButton != undefined || submitButton != null) {
            submitButton.setAttribute("onclick", "refreshStringLabel()");
            this.updateStoredString();
        }
    }
};

function reloadPage() {
    PM.load(PM.visiblePanel.attr("id"), null, {
        geocoder: true
    });
}

function refreshStringLabel() {
    LocalStorageManager.setStoredString();
    LocalStorageManager.updateStoredString();
}

$(document).ajaxComplete(onComplete);

function createTDOnly10(inHtml) {
    return "<td colspan='1' rowspan='1' class=' only10 ' style='width:33.33333333333333%;' align='left'>" + inHtml +
            "</td>";
}

function takeEffect(element) {
    var effectTimer = setInterval(function() {
        $(element).fadeOut(300).fadeIn(300);
    }, 1000);

    setTimeout(function() {
        clearInterval(effectTimer);
    }, 5000);
}

function onComplete(event, xhr, settings) {
    //settings.url.toString(); parameter = "sid"  => screen_id
    var url = settings.url;

    if (url.indexOf("screenID=tracker") > -1) {
        var containers = $(".contentContainer");
        var container3 = containers[2];
        var bigtable = $(container3).find(".only10");
        var smalltable = $(container3).find(".only7");

        var image1Html = "<img id='jsImg1' src='http://eripark.duapp.com/resources/parking.jpg' alt=' ' class=' ' style='max-width:100%'>";
        var image2Html = "<img id='jsImg2' src='http://eripark.duapp.com/resources/charging.jpg' alt=' ' class=' ' style='max-width:100%'>";
        var image3Html = "<img id='jsImg3' src='http://eripark.duapp.com/resources/food.jpg' alt=' ' class=' ' style='max-width:100%'>";

        var imageRow = "<tr style='line-height:100%;'>" + createTDOnly10(image1Html) + createTDOnly10(image2Html) +
                createTDOnly10(image3Html) + "</tr>";

        var number1Html = "<div id='jsTxt1' class='text limited ' style=''>还剩10个车位</div>";
        var number2Html = "<div id='jsTxt2' class='text limited ' style=''>6</div>";
        var number3Html = "<div id='jsTxt3' class='text limited ' style=''>4</div>";

        var numberRow = "<tr style=''>" + createTDOnly10(number1Html) + createTDOnly10(number2Html) +
                createTDOnly10(number3Html) + "</tr>";
        var fullHtml = imageRow + numberRow;

        $(bigtable).html(fullHtml);
        //$(smalltable).html(fullHtml.replaceAll(".only10", ".only7"));

        setTimeout(function() {
            var jsImg1 = $(bigtable).find("#jsImg1");
            $(jsImg1).attr("src", "http://eripark.duapp.com/resources/parkingwarning.png");
            var jsTxt1 = $(bigtable).find("#jsTxt1");
            $(jsTxt1).html("还剩9个车位");
            takeEffect(jsImg1);
        }, 2000);
    }

}
