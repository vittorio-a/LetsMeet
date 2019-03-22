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

			<title>Insert title here</title>
			
		</head>
		<script type="text/javascript">
	
		
		$.ajax({
			 url: "/LetsMeetWeb/auth/account/profiloControl",
		      type: 'GET',
		      dataType: "json",
		      success:function(response){
		    	  utente = response.data.utente;
		     	  eventi = response.data.eventi;
		    	  console.log(response);
		    	  console.log(eventi);
		    	  $("#nome-text").text(utente.credentials.username);
		    	  $("#email-text").text("Email: "+ utente.email);
		    	  $("#feedback-text").text("FeedbackUtente: " + utente.feedbackUtente);
		    	  
		    	 // var tableBody = $("#table-body");
		    	 var dataSet = [];
		    	  eventi.forEach(function(evento){
		    		var row = [
		    			evento.nome + "|" + evento.idEvento,
		    			evento.nPartecipanti,
		    			evento.tipo.nomeTipo,
		    			evento.feedback,
		    			evento.posizione.formattedAdress,
		    			(new Date(evento.oraInizio.seconds * 1000)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ")
		    		];
		    		dataSet.push(row);
		    		//nome evento con link alla pagina dell'evento
		    	  
		    		
		    		/*var row = document.createElement("tr");
					var td = document.createElement("td");
					var data = document.createElement("a");
					var txt = document.createTextNode(evento.nome);
					data.setAttribute("href", "/LetsMeetWeb/auth/infoEvento.jsp?idEvento=" + evento.idEvento);
	    		  	data.appendChild(txt);
	    		  	td.appendChild(data);
	    		  	row.appendChild(td);
	    		  	
	    		  	//npartecipanti
	    		  	td = document.createElement("td");
					data = document.createTextNode(evento.nPartecipanti);
					td.appendChild(data);
					row.appendChild(td);
					
					//tipo
					td = document.createElement("td");
					data = document.createTextNode(evento.tipo.nomeTipo);
					
					
					
					tableBody.appendChild(row);
					*/
		    	  });
		    	  
		    	  $('#event-table').DataTable( {
	       			    data: dataSet,
				        columns: [
				            { title: "Nome",
			   		           	"render": function(data, type, row, meta){
			   		           		var elems = data.split("|");
					    	         if(type === 'display'){
				 		                 data = '<a href="/LetsMeetWeb/auth/infoEvento.jsp?idEvento=' + elems[1] + '">' + elems[0] + '</a>';
				        	          }
			                	    return data;
			                  	}
				            },
				            { title: "Partecipanti" },
				            { title: "Tipo" },
				            { title: "Feedback" },
				            { title: "Luogo" },
				            { title: "Data" }
				        ],
				        "scrollY":        "50vh",
				        "scrollCollapse": true,
				        "paging":         false,
				        
				        responsive: true
				        
				    } );
		  	},
			error:function(){
				alert("Nu funzion NIENT");
			}
		});
		
		
		
		</script>
			<body>
			
				<div id="container">
			
						<div id="header">
					
							<h1><a href="../homePage.jsp">LetsMeet</a></h1> 
							
								<h2 id = "nome-text">Nome Utente</h2>
								
								<ul id = "list-credentials">
									<li><p id = "feedback-text">Feedback</p></li>
											
									<li><p id="email-text">Email</p></li>
												
								</ul>
							
							<a href="profilo.jsp"><img id="profilo" alt="profilo" src="../images/profilo.png"></a> 
							
							<a href="impostazioni.html"><img id="settings" alt="settings" src="../images/two-cogwheels-configuration-interface-symbol.png"></a>
							
				
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