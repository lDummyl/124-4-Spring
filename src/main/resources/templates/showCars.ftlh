<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cars</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>List of cars</h2>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Model</th>
    </tr>

    <#list cars as car>
        <tr>
            <td>${car.id}</td>
            <td>${car.carName}</td>
            <td>${car.modelName}</td>
        </tr>
    </#list>
</table>

</body>
</html>