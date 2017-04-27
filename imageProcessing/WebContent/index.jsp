<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
#upload-button{
border: 1px solid #DDD;
width: 100px;
height:80px;
margin: 20px auto;
background: url('image/upload.png');
background-repeat:no-repeat;
background-size: 100% 100%;
}
#upload-button input[type='file']{
width: 100%;
height: 100%;
opacity: 0;
}



body {
    margin: 0;
    background-color: lightgreen;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 25%;
    background-color: #f1f1f1;
    position: fixed;
    height: 100%;
    overflow: auto;
}

li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}

</style>

</head>
<body>

<ul>
  <li><a class="active" href="index.jsp">Inicio</a></li>
  <li><a href="#page1">Equalizar histograma</a></li>
  <li><a href="#page2">kittler</a></li>
  <li><a href="#page3">Manipular imagen</a></li>
  <li><a href="#page4">Etiquetar imagen</a></li>
  
</ul>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
<br>
<br>
<br>
<form name ="imagenForm" method="post" action="imageServlet">
Ruta: <input type= "text" name="ruta" /> 
<br/>
<br/>
<br/>
<input type="submit" value="Ejecutar" />
</form>
</div>
  


</body>
</html>