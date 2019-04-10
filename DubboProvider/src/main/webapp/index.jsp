<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
</body>
<script>
    (function ($) {
        var data=
            {"typename":"ktw:village","version":"2.0.0","service":"WFS",
                "request":"GetFeature","outputFormat":"application/json","startIndex":0,"count":5,"srsName":"EPSG:4326","filterName":"the_geom","cql_filter":"DWITHIN(the_geom,POINT(30.48180976467465 114.4424970190005),0,meters)"};
        $.ajax({
            url: "http://192.168.1.93:8080/hgis/ows",
            dataType: 'json',
            type: 'POST',
            data:data,
            cache: false,
            timeout: 2000,
            async: false,//同步
            ContentType:'application/x-www-form-urlencoded',
            error: function () {
            },
            success: function (data) {
                console.log(data);
            }
        });
    })(jQuery);
</script>
</html>
