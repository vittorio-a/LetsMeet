<%@page import="it.unisa.studenti.letsmeet.control.gestione_account.LoginControl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

		<head>
			<meta name="viewport" content="width=device-width,initial-scale=1.0">
			<meta charset="UTF-8">
			<link href="../css/profilo.css" rel="stylesheet" type="text/css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
			<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
			<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
			<script type="text/javascript" src="../js/profilo.js"></script>

			<title>Insert title here</title>
			
		</head>
		<script type="text/javascript">
		
		</script>
			<body>
			
				<div id="container">
			
						<div id="header">
					
							<h1><a href="../auth/homePage.jsp">LetsMeet</a></h1> 
							
								<h2 id = "nome-text">Nome Utente</h2>
								
								<ul id = "list-credentials">
									<li><p id = "feedback-text">Feedback</p></li>
											
									<li><p id="email-text">Email</p></li>
												
								</ul>
							
							<a href="profilo.jsp"><img id="profilo" alt="profilo" src="../images/profilo.png"></a> 
							
							<a href="#"><img id="settings" alt="settings" src="../images/two-cogwheels-configuration-interface-symbol.png"></a>
							
				
						</div>
						
						
						
						<div id="logo_container">
					
							<img id="logo" alt="logo" src="../images/logo-LetsMeet.png">
			
			
						</div>
						
						
						<div id="container_info">
							<table id="event-table" class="display">
								<thead>
								<tr>
									<th>
										Evento
									</th>
									<th>
										Partecipanti
									</th>
									<th>
										Tipo
									</th>
									<th>
										Feedback
									</th>
									<th>
										Luogo
									</th>
									<th>
										Data
									</th>
								</tr>
								</thead>
								<tbody id="table-body">
								</tbody>
							</table>
						
						</div>
						
						<footer>
				
							<h3>LetsMeet2019</h3>
				
						</footer>
						
				</div>

			</body>
</html>