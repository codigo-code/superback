
function gapiLoad() {
	gapi.client.setApiKey('1062778371602-vbfcphkh9s6v6qds3akvb80n06rjid4f.apps.googleusercontent.com');	// app api-wide client api key
	getGoogleContactEmails(function(result){
		console.log(result);
	});
}

function getGoogleContactEmails(callback) {
  var oauth_clientKey = '1062778371602-vbfcphkh9s6v6qds3akvb80n06rjid4f.apps.googleusercontent.com'; // replace with your oauth client api key
	var firstTry = true;
	function connect(immediate, callback){
	    var config = {
	        'client_id': oauth_clientKey,
	        'scope': 'https://www.google.com/m8/feeds',
	        'immediate': immediate,
	    };

	    gapi.auth.authorize(config, function () {
			var authParams = gapi.auth.getToken();
	        $.ajax({
	            url: 'https://www.google.com/m8/feeds/contacts/default/full?max-results=10000',
	            dataType: 'jsonp',
	            type: "GET",
	            data: authParams,
	            success: function (data) {
	                var parser = new DOMParser();
	 				xmlDoc = parser.parseFromString(data,"text/xml");
	 				var entries = xmlDoc.getElementsByTagName('feed')[0].getElementsByTagName('entry');
	 				var contacts = [];
	 				for (var i = 0; i < entries.length; i++){
	 					var name = entries[i].getElementsByTagName('title')[0].innerHTML;
	 					var emails = entries[i].getElementsByTagName('email');
	 					for (var j = 0; j < emails.length; j++){
	 					  var email = emails[j].attributes.getNamedItem('address').value;
	 					  contacts.push({name: name, email: email});
	 					}
	 				}

					 
	            },
	            error: function (data) {
	            	if (firstTry)
						connect(false, callback);
					firstTry = false;
	            }
	        })
	    });
	}
	console.log(callback);
	document.write(callback);
	connect(true, callback);
}

