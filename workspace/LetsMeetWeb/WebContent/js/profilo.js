
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
		