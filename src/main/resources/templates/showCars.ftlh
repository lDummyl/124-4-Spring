<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cars</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css"/>
    <script type="text/javascript" src="//code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="//cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
</head>
<body>

<script type="application/javascript">
    $(document).ready(function () {
        $('#cars').DataTable();
    });
</script>

<div><a href="/swagger-ui/index.html?url=/v3/api-docs">Swagger</a></div>
<div><a href="/cars">Show cars</a></div>
<div><a href="/drivers">Show drivers</a></div>
<div><a href="/fillTestData">Fill test data</a></div>

<table id="cars" class="display" style="width:100%">
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Model</th>
        </tr>
    </thead>
    <tbody>
    <#list cars as car>
        <tr>
            <td>${car.id}</td>
            <td>${car.carName}</td>
            <td>${car.modelName}</td>
        </tr>
    </#list>
    </tbody>
</table>

</body>
</html>