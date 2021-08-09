<!DOCTYPE html>
<html lang="en">
<head>
    <title>Drivers</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>List of drivers</h2>

<table>
    <tr>
        <th>Id</th>
        <th>FirstName</th>
        <th>LastName</th>
    </tr>

    <#list drivers as driver>
        <tr>
            <td>${driver.id}</td>
            <td>${driver.firstName}</td>
            <td>${driver.lastName}</td>
        </tr>
    </#list>
</table>

</body>
</html>