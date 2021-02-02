$(document).ready(function () {
    let tabs = $( "#tabs" ).tabs();

    function addTab() {
        var label = tabTitle.val() || "Tab " + tabCounter,
            id = "tabs-" + tabCounter,
            li = $( tabTemplate.replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) ),
            tabContentHtml = tabContent.val() || "Tab " + tabCounter + " content.";

        tabs.find( ".ui-tabs-nav" ).append( li );
        tabs.append( "<div id='" + id + "'><p>" + tabContentHtml + "</p></div>" );
        tabs.tabs( "refresh" );
        tabCounter++;
    }
});